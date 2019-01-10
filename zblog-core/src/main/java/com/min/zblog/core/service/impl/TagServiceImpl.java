package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArticleTagDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.TagDao;
import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.exception.ProcessException;
import com.min.zblog.facility.utils.Constants;
import com.min.zblog.facility.utils.PageUtil;

@Service
public class TagServiceImpl implements TagService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TagDao tagDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Autowired
	private ArticleTagDao articleTagDao;

	@Override
	public List<TagInfo> fetchTagInfo() {
		return GlobalContextHolder.getTagInfoList();
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.TagService#queryTagByPage(long, long, java.util.Map)
	 */
	@Override
	public PageInfo<TagInfo> queryTagByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmTag> tmTagList = blogQueryDsl.fetchTagConditionByPage(currentPage, pageSize, map);
		List<TagInfo> tagInfoList = new ArrayList<TagInfo>();
		for(TmTag tag:tmTagList){
			
			TagInfo tagInfo = new TagInfo();
			tagInfo.setId(tag.getId());
			tagInfo.setTagName(tag.getName());
			tagInfo.setDescription(tag.getDescription());
			tagInfo.setCreateTime(tag.getCreateTime());
			
			tagInfoList.add(tagInfo);
		}
		
		PageInfo<TagInfo> pageInfo = new PageInfo<TagInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countTagByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(tagInfoList);
		
		return pageInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.TagService#findOneTag(java.lang.Long)
	 */
	@Override
	public TagInfo findOneTag(Long id) {
		TagInfo tagInfo = new TagInfo();
		
		TmTag tag = tagDao.findOne(id);
		if(tag != null){
			tagInfo.setId(id);
			tagInfo.setTagName(tag.getName());
			tagInfo.setDescription(tag.getDescription());
		}
		
		return tagInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.TagService#saveTag(java.util.Map)
	 */
	@Override
	public TmTag saveTag(Map<String, Object> map) {
		TmTag tag = tagDao.findOne((Long)map.get("tagId"));
		if(tag == null){
			throw new ProcessException(Constants.ERRT001_CODE, Constants.ERRT001_MSG);
		}
		
		Date time = new Date();
		tag.setName((String)map.get("name"));
		tag.setDescription((String)map.get("description"));
		tag.setUpdateTime(time);
    	
		tagDao.save(tag);
		
		//更新标签
		TagInfo tagInfo = new TagInfo();
		tagInfo.setId(tag.getId());
		tagInfo.setTagName(tag.getName());
		GlobalContextHolder.updateTagInfo(tagInfo);
		
		return tag;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.TagService#deleteTagById(java.lang.Long)
	 */
	@Override
	public void deleteTagById(Long id) throws ProcessException {
		TmTag tag = tagDao.findOne(id);
		if(tag == null){
			throw new ProcessException(Constants.ERRT001_CODE, Constants.ERRT001_MSG);
		}
		
		long count = blogQueryDsl.countArticleByTagId(id, null);
		if(count > 0){
			throw new ProcessException(Constants.ERRT002_CODE, Constants.ERRT002_MSG);
		}
		
		//更新标签
		GlobalContextHolder.deleteTagInfo(tag.getId());
		
		//删除分类
		tagDao.delete(id);
	}

	@Override
	public TmTag addTag(Map<String, Object> map) {
		Date time = new Date();

		TmTag tag = new TmTag();
		tag.setUserId(Long.valueOf(0));
		tag.setName((String)map.get("name"));
		tag.setDescription((String)map.get("description"));
		tag.setCreateTime(time);
		tag.setUpdateTime(time);
		tag.setJpaVersion(0);
    	
		tagDao.save(tag);
		
		//更新标签
		int index = GlobalContextHolder.getTagInfoList().size()%PageUtil.LABEL_STYTLE.length;
		TagInfo tagInfo = new TagInfo();
		tagInfo.setId(tag.getId());
		tagInfo.setTagName(tag.getName());
		tagInfo.setStyle(PageUtil.LABEL_STYTLE[index]);
		
		GlobalContextHolder.addTagInfo(tagInfo);
		
    	return tag;
	}

	@Override
	public void initTagInfo() {
		List<TmTag> tagList = tagDao.findAllByOrderByCreateTimeAsc();
		List<TagInfo> tagInfoList = new ArrayList<TagInfo>();
		int count = 0;
		for(TmTag tmTag:tagList){
			logger.info("count:"+count+"|"+PageUtil.LABEL_STYTLE[count]);
			
			TagInfo tagInfo = new TagInfo();
			tagInfo.setId(tmTag.getId());
			tagInfo.setTagName(tmTag.getName());
			tagInfo.setStyle(PageUtil.LABEL_STYTLE[count]);
			tagInfoList.add(tagInfo);
			
			count = ((++count)%PageUtil.LABEL_STYTLE.length);
		}
		//缓存
		GlobalContextHolder.setTagInfoList(tagInfoList);
	}

}
