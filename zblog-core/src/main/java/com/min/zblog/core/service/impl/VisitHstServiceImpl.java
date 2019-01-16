package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArticleDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.VisitHstDao;
import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.api.rpc.ArticleService;
import com.min.zblog.api.rpc.VisitHstService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmVisitHst;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.VisitHstInfo;
import com.min.zblog.facility.enums.VisitType;

/**
 * <p>Title: VisitHstServiceImpl</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年10月13日
 * @version 1.0
 */
@Service
public class VisitHstServiceImpl implements VisitHstService {
	
	@Autowired
	private VisitHstDao visitHstDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Autowired
	private ArticleDao articleDao;
	
	@Autowired
	private ArticleService articleService;
	
	/* 添加浏览历史记录
	 * type: 数据访问类型
	 */
	@Override
	public void addVisitHst(Long id, String ip, String browser, VisitType type) {
		TmVisitHst tmVisitHst = new TmVisitHst();
		tmVisitHst.setArticleId(id);
		tmVisitHst.setIp(ip);
		tmVisitHst.setBrowser(browser);
		tmVisitHst.setVisitType(type);
		tmVisitHst.setCreateTime(new Date());
		tmVisitHst.setJpaVersion(0);
		
		visitHstDao.save(tmVisitHst);
		
		//更新阅读数
		if(VisitType.READ == type) {
			GlobalContextHolder.addOneBlogInfoReadNum();
			//更新排行榜
			articleService.initArticleReadRank();
		}
	}

	@Override
	public PageInfo<VisitHstInfo> queryVisitHstByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmVisitHst> tmVisitHstList = blogQueryDsl.fetchVisitHstConditionByPage(currentPage, pageSize, map);
		List<VisitHstInfo> visitHstInfoList = new ArrayList<VisitHstInfo>();
		for(TmVisitHst visitHst:tmVisitHstList){
			
			VisitHstInfo visitHstInfo = new VisitHstInfo();
			visitHstInfo.setId(visitHst.getId());
			TmArticle article = articleDao.findOne(visitHst.getArticleId());
			if(article != null){
				visitHstInfo.setArticleTitle(article.getTitle());
			}
			visitHstInfo.setIp(visitHst.getIp());
			visitHstInfo.setBrowser(visitHst.getBrowser());
			visitHstInfo.setVisitTypeDesc(visitHst.getVisitType().getDesc());
			visitHstInfo.setCreateTime(visitHst.getCreateTime());
			
			visitHstInfoList.add(visitHstInfo);
		}
		
		PageInfo<VisitHstInfo> pageInfo = new PageInfo<VisitHstInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countVisitHstByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(visitHstInfoList);
		
		return pageInfo;
	}

}
