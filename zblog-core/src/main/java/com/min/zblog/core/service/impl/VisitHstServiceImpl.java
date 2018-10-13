package com.min.zblog.core.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.VisitHstDao;
import com.min.zblog.core.service.VisitHstService;
import com.min.zblog.data.entity.TmVisitHst;
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
	}

}
