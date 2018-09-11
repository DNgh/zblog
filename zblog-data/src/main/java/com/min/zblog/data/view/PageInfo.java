package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: PageInfo</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月10日
 * @version 1.0
 */
public class PageInfo<T> implements Serializable {

	private static final long serialVersionUID = 1655694949353977273L;

	/**
	 * 总页数
	 */
	private long totalPages;
	
	/**
	 * 当前页数
	 */
	private long currentPage;
	
	/**
	 * 分页数据列表
	 */
	private List<T> list;

	public long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(long totalPages) {
		this.totalPages = totalPages;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(long currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
