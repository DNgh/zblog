package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.TagDao;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.utils.PageUtil;

@Service
public class TagServiceImpl implements TagService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TagDao tagDao;

	@Override
	public List<TagInfo> fetchTagInfo() {
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
		return tagInfoList;
	}

}
