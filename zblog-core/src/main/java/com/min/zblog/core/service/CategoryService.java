package com.min.zblog.core.service;

import java.util.List;

import com.min.zblog.data.view.CategoryInfo;

/**
 * <p>Title: CategoryService</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月5日
 * @version 1.0
 */
public interface CategoryService {
	public List<CategoryInfo> fetchCategoryInfo();
}
