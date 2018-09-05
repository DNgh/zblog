package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.dao.ArticleQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.view.CategoryInfo;

/**
 * <p>Title: CategoryServiceImpl</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月6日
 * @version 1.0
 */
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ArticleQueryDsl articleQueryDsl;
	
	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.CategoryService#fetchCategoryInfo()
	 */
	@Override
	public List<CategoryInfo> fetchCategoryInfo() {
		List<TmCategory> categoryList = categoryDao.findAll();
		if(categoryList == null || categoryList.isEmpty()){
			return null;
		}
		
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		for(TmCategory tmCategory:categoryList){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setIcon(tmCategory.getIcon());
			categoryInfo.setCategoryName(tmCategory.getName());
			categoryInfo.setArticleNum(articleQueryDsl.countArticleByCategoryId(tmCategory.getId()));
			
			categoryInfoList.add(categoryInfo);
		}
		return categoryInfoList;
	}

}
