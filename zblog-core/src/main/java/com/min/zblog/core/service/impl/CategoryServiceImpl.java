package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.facility.enums.Indicator;

/**
 * <p>Title: CategoryServiceImpl</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月6日
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.CategoryService#fetchCategoryInfo()
	 */
	@Override
	public List<CategoryInfo> fetchCategoryInfo() {
		List<TmCategory> categoryList = blogQueryDsl.fetchCategoryOrderBySort(Indicator.Y, Indicator.Y);
		if(categoryList == null || categoryList.isEmpty()){
			return null;
		}
		
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		for(TmCategory tmCategory:categoryList){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setIcon(tmCategory.getIcon());
			categoryInfo.setCategoryName(tmCategory.getName());
			categoryInfo.setArticleNum(tmCategory.getCount());
			
			categoryInfoList.add(categoryInfo);
		}
		return categoryInfoList;
	}

}
