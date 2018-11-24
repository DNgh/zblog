package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArticleDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.OperateHstDao;
import com.min.zblog.core.dao.UserDao;
import com.min.zblog.core.dao.VisitHstDao;
import com.min.zblog.core.service.OperateHstService;
import com.min.zblog.core.service.VisitHstService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmVisitHst;
import com.min.zblog.data.entity.TsOperateHst;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.OperateHstInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.VisitHstInfo;
import com.min.zblog.facility.enums.VisitType;

/**
 * <p>Title: OperateHstServiceImpl</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年10月13日
 * @version 1.0
 */
@Service
public class OperateHstServiceImpl implements OperateHstService {
	
	@Autowired
	private OperateHstDao operateHstDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	/* 分页查询操作历史
	 * @see com.min.zblog.core.service.OperateHstService#queryOperateHstByPage(long, long, java.util.Map)
	 */
	@Override
	public PageInfo<OperateHstInfo> queryOperateHstByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TsOperateHst> tsOperateHstList = blogQueryDsl.fetchOperateHstConditionByPage(currentPage, pageSize, map);
		List<OperateHstInfo> operateHstInfoList = new ArrayList<OperateHstInfo>();
		for(TsOperateHst operateHst:tsOperateHstList){
			
			OperateHstInfo operateHstInfo = new OperateHstInfo();
			operateHstInfo.setId(operateHst.getId());
			TsUser user = userDao.findOne(operateHst.getUserId());
			if(user != null){
				operateHstInfo.setUsername(user.getUsername());
			}
			operateHstInfo.setIp(operateHst.getIp());
			operateHstInfo.setUrl(operateHst.getUrl());
			operateHstInfo.setName(operateHst.getName());
			operateHstInfo.setDescription(operateHst.getDescription());
			operateHstInfo.setCreateTime(operateHst.getCreateTime());
			
			operateHstInfoList.add(operateHstInfo);
		}
		
		PageInfo<OperateHstInfo> pageInfo = new PageInfo<OperateHstInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countOperateHstByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(operateHstInfoList);
		
		return pageInfo;
	}

}
