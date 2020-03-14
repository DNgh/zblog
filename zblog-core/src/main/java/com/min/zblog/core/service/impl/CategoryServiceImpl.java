package com.min.zblog.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.api.rpc.CategoryService;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmArticleTagKey;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;
import com.min.zblog.facility.exception.ProcessException;
import com.min.zblog.facility.utils.Constants;

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
	 * @see com.min.zblog.api.rpc.CategoryService#fetchCategoryInfo()
	 */
	@Override
	public List<CategoryInfo> fetchCategoryInfo() {
		return GlobalContextHolder.getCategoryInfoList();
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
	 * @see com.min.zblog.api.rpc.CategoryService#findOneCategory(java.lang.Long)
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

	@Override
	public TmCategory saveCategory(Map<String, Object> map) {
		TmCategory category = categoryDao.findOne((Long)map.get("categoryId"));
		if(category == null){
			throw new ProcessException(Constants.ERRC001_CODE, Constants.ERRC001_MSG);
		}
		
		
		long articleNum = blogQueryDsl.countArticleByCategoryId(category.getId(), ArticleState.PUBLISH);
		if(articleNum > 0 && Indicator.N == (Indicator)map.get("available")) {
			//正使用，无法停用
			throw new ProcessException(Constants.ERRC003_CODE, Constants.ERRC003_MSG);
		}

		Date time = new Date();
		category.setName((String)map.get("name"));
		category.setDescription((String)map.get("description"));
    	category.setIcon((String)map.get("icon"));
    	category.setAvailable((Indicator)map.get("available"));
    	category.setUpdateTime(time);
    	
		categoryDao.save(category);
		
		//不可用转可用、可用转不可用，更新分类列表
		if(Indicator.N == category.getAvailable() && Indicator.Y == (Indicator)map.get("available")) {
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(category.getId());
			categoryInfo.setIcon(category.getIcon());
			categoryInfo.setCategoryName(category.getName());
			categoryInfo.setArticleNum(articleNum);
			
			GlobalContextHolder.addCategoryInfo(categoryInfo);
		}else if(Indicator.Y == category.getAvailable() && Indicator.N == (Indicator)map.get("available")) {
			GlobalContextHolder.deleteCategoryInfo(category.getId());
		}else if(Indicator.Y == category.getAvailable() && Indicator.Y == (Indicator)map.get("available")) {
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(category.getId());
			categoryInfo.setIcon(category.getIcon());
			categoryInfo.setCategoryName(category.getName());
			categoryInfo.setArticleNum(articleNum);
			
			GlobalContextHolder.updateCategoryInfo(categoryInfo);
		}
		
		return category;
	}
	
	@Override
	public TmCategory addCategory(Map<String, Object> map) {
		Date time = new Date();

		TmCategory category = new TmCategory();
		category.setUserId(Long.valueOf(0));
		category.setName((String)map.get("name"));
		category.setDescription((String)map.get("description"));
		category.setSort(blogQueryDsl.fetchCategoryMaxSort()+1);
    	category.setIcon((String)map.get("icon"));
    	category.setAvailable((Indicator)map.get("available"));
    	category.setCount(0);
    	category.setCreateTime(time);
    	category.setUpdateTime(time);
    	category.setJpaVersion(0);
    	
    	categoryDao.save(category);
    	
    	//更新分类信息，新增分类
    	if(Indicator.Y == category.getAvailable()) {
    		CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(category.getId());
			categoryInfo.setIcon(category.getIcon());
			categoryInfo.setCategoryName(category.getName());
			categoryInfo.setArticleNum(category.getCount());
			
			GlobalContextHolder.addCategoryInfo(categoryInfo);
    	}
    	
    	return category;
	}

	@Override
	public void deleteCategoryById(Long categoryId) throws ProcessException {
		TmCategory category = categoryDao.findOne(categoryId);
		if(category == null){
			throw new ProcessException(Constants.ERRC001_CODE, Constants.ERRC001_MSG);
		}
		
		long count = blogQueryDsl.countArticleByCategoryId(categoryId, null);
		if(count > 0){
			throw new ProcessException(Constants.ERRC002_CODE, Constants.ERRC002_MSG);
		}
		
		//无关联，才删除，删除缓存分类
		GlobalContextHolder.deleteCategoryInfo(category.getId());
		
		//删除分类
		categoryDao.delete(categoryId);
	}

	@Override
	public void initCategoryInfo() {
		List<TmCategory> categoryList = blogQueryDsl.fetchCategoryOrderBySort(Indicator.Y, Indicator.Y);
		List<CategoryInfo> categoryInfoList = new ArrayList<CategoryInfo>();
		for(TmCategory tmCategory:categoryList){
			long articleNum = blogQueryDsl.countArticleByCategoryId(tmCategory.getId(), ArticleState.PUBLISH);
			CategoryInfo categoryInfo = new CategoryInfo();
			categoryInfo.setId(tmCategory.getId());
			categoryInfo.setCategoryName(tmCategory.getName());
			categoryInfo.setDescription(tmCategory.getDescription());
			categoryInfo.setArticleNum(articleNum);
			categoryInfo.setIcon(tmCategory.getIcon());
			categoryInfo.setAvailable(tmCategory.getAvailable());
			categoryInfo.setCreateTime(tmCategory.getCreateTime());
			
			categoryInfoList.add(categoryInfo);
		}
		//缓存
		GlobalContextHolder.setCategoryInfoList(categoryInfoList);
	}

}
