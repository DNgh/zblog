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
	private int totalPages;
	
	/**
	 * 当前页数
	 */
	private int currentPage;
	
	/**
	 * 分页数据列表
	 */
	private List<T> list;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
	
}
