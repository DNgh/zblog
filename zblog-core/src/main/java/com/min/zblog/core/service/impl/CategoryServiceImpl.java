package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;

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
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		for(TmCategory tmCategory:categoryList){
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(tmCategory.getId());
			categoryInfo.setIcon(tmCategory.getIcon());
			categoryInfo.setCategoryName(tmCategory.getName());
			categoryInfo.setArticleNum(tmCategory.getCount());
			
			categoryInfoList.add(categoryInfo);
		}
		return categoryInfoList;
	}

	@Override
	public PageInfo<CategoryInfo> queryCategoryByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmCategory> tmCategoryList = blogQueryDsl.fetchCategoryConditionByPage(currentPage, pageSize, map);
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		for(TmCategory category:tmCategoryList){
			
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(category.getId());
			categoryInfo.setCategoryName(category.getName());
			categoryInfo.setArticleNum(category.getCount());
			categoryInfo.setIcon(category.getIcon());
			categoryInfo.setAvailable(category.getAvailable());
			categoryInfo.setCreateTime(category.getCreateTime());
			
			categoryInfoList.add(categoryInfo);
		}
		
		PageInfo<CategoryInfo> pageInfo = new PageInfo<CategoryInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countCategoryByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(categoryInfoList);
		
		return pageInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.CategoryService#findOneCategory(java.lang.Long)
	 */
	@Override
	public CategoryInfo findOneCategory(Long id) {
		CategoryInfo categoryInfo = new CategoryInfo();
		
		TmCategory category = categoryDao.findOne(id);
		if(category != null){
			categoryInfo.setId(id);
			categoryInfo.setCategoryName(category.getName());
			categoryInfo.setDescription(category.getDescription());
			categoryInfo.setArticleNum(category.getCount());
			categoryInfo.setIcon(category.getIcon());
			categoryInfo.setAvailable(category.getAvailable());
			categoryInfo.setCreateTime(category.getCreateTime());
		}
		
		return categoryInfo;
	}

}
