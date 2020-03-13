package com.min.zblog.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;

public class TestGlobalContextHolder {
	public static void main(String[] args) {
		initArchiveInfo();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", ArticleState.PUBLISH);
		addArticle(map);
		
		//打印全局数据
		printGlobalContextHolder();
	}
	
	public static void initArchiveInfo() {
		List<ArchiveInfo> archiveInfoList = new ArrayList<ArchiveInfo>();
		//缓存
		GlobalContextHolder.setArchiveInfoList(archiveInfoList);
	}

	public static TmArticle addArticle(Map<String, Object> map) {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String archiveTitle = sdf.format(time);
		String archiveName = archiveTitle.replace("-", "");
		
		//归档
		TmArchive archive = null;
		Long archiveId;
		if(archive == null){
			int articleCount = 0;
			if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
				articleCount = 1;
			}
			TmArchive newArchive = new TmArchive(archiveName, archiveTitle, articleCount, time, time, 0);
//			archiveDao.save(newArchive);
			newArchive.setId(Long.valueOf(1));
			//更新全局变量
			ArchiveInfo archiveInfo = new ArchiveInfo();
			archiveInfo.setId(newArchive.getId());
			archiveInfo.setArchiveTitle(newArchive.getTitle());
			archiveInfo.setArchiveName(newArchive.getName());
			archiveInfo.setArticleNum(newArchive.getCount());
			archiveInfo.setCreateTime(newArchive.getCreateTime());
			GlobalContextHolder.addArchiveInfo(archiveInfo);
		}else{
			archiveId = archive.getId();
			if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
				archive.setCount(archive.getCount()+1);
				archive.setUpdateTime(time);
//				archiveDao.save(archive);
				//更新全局变量
				GlobalContextHolder.addOneArchiveInfoArticleNum(archiveId);
			}
		}
		return null;
	}
	
	public static void printGlobalContextHolder() {
		List<ArchiveInfo> list = GlobalContextHolder.getArchiveInfoList();
		for(ArchiveInfo info:list) {
			System.out.println("id:"+info.getId()+",archiveTitle:"+info.getArchiveTitle()+",createTime:"+info.getCreateTime());
		}
	}

}
