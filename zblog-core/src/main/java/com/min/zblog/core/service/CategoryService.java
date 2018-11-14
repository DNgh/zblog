package com.min.zblog.core.service;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;

/**
 * <p>Title: CategoryService</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月5日
 * @version 1.0
 */
public interface CategoryService {
	public List<CategoryInfo> fetchCategoryInfo();
	public PageInfo<CategoryInfo> queryCategoryByPage(long pageSize, long currentPage, Map<String, Object> map);
	public CategoryInfo findOneCategory(Long id);
}
