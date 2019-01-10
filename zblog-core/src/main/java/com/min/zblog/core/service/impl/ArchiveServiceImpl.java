package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArchiveDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.exception.ProcessException;
import com.min.zblog.facility.utils.Constants;

/**
 * <p>Title: ArchiveServiceImpl</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月7日
 * @version 1.0
 */
@Service
public class ArchiveServiceImpl implements ArchiveService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ArchiveDao archiveDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.ArchiveService#fetchArchiveInfo()
	 */
	@Override
	public List<ArchiveInfo> fetchArchiveInfo() {
		return GlobalContextHolder.getArchiveInfoList();
	}

	@Override
	public PageInfo<ArchiveInfo> queryArchiveByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmArchive> tmArchiveList = blogQueryDsl.fetchArchiveConditionByPage(currentPage, pageSize, map);
		List<ArchiveInfo> archiveInfoList = new ArrayList<ArchiveInfo>();
		for(TmArchive archive:tmArchiveList){
			
			ArchiveInfo archiveInfo = new ArchiveInfo();
			archiveInfo.setId(archive.getId());
			archiveInfo.setArchiveName(archive.getName());
			archiveInfo.setArchiveTitle(archive.getTitle());
			archiveInfo.setArticleNum(archive.getCount());
			archiveInfo.setCreateTime(archive.getCreateTime());
			
			archiveInfoList.add(archiveInfo);
		}
		
		PageInfo<ArchiveInfo> pageInfo = new PageInfo<ArchiveInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArchiveByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(archiveInfoList);
		
		return pageInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.ArchiveService#deleteArchiveById(java.lang.Long)
	 */
	@Override
	public void deleteArchiveById(Long archiveId) throws ProcessException {
		TmArchive archive = archiveDao.findOne(archiveId);
		if(archive == null){
			throw new ProcessException(Constants.ERRV001_CODE, Constants.ERRV001_MSG);
		}
		
		long count = blogQueryDsl.countArticleByArchiveId(archiveId, null);
		if(count > 0){
			throw new ProcessException(Constants.ERRV002_CODE, Constants.ERRV002_MSG);
		}
		
		//更新归档信息
		GlobalContextHolder.deleteArchiveInfo(archive.getId());
		
		//删除归档
		archiveDao.delete(archiveId);
	}

	@Override
	public void initArchiveInfo() {
		List<TmArchive> archiveList = blogQueryDsl.fetchArchives(Indicator.Y);
		List<ArchiveInfo> archiveInfoList = new ArrayList<ArchiveInfo>();
		for(TmArchive tmArchive:archiveList){
			long articleNum = blogQueryDsl.countArticleByArchiveId(tmArchive.getId(), ArticleState.PUBLISH);
			ArchiveInfo archiveInfo = new ArchiveInfo();
			archiveInfo.setArchiveTitle(tmArchive.getTitle());
			archiveInfo.setArchiveName(tmArchive.getName());
			archiveInfo.setArticleNum(articleNum);
			
			archiveInfoList.add(archiveInfo);
		}
		//缓存
		GlobalContextHolder.setArchiveInfoList(archiveInfoList);
	}
}
