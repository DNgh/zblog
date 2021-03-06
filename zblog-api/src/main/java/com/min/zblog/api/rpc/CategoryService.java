package com.min.zblog.api.rpc;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.exception.ProcessException;

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
	public TmCategory saveCategory(Map<String, Object> map);
	public TmCategory addCategory(Map<String, Object> map);
	public void deleteCategoryById(Long categoryId) throws ProcessException;
	public void initCategoryInfo();
}
