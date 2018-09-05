package com.min.zblog.data.view;

import java.io.Serializable;

/**
 * 页面显示分类信息
 * @author zhouzm
 *
 */
public class CategoryInfo implements Serializable {

	private static final long serialVersionUID = -4391562715087734051L;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 所属分类，文章数 
	 */
	private int articleNum;
	
	/**
	 * 分类图标
	 */
	private String icon;
	
	/**
	 * 页面显示分类html
	 */
	private String html;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}
}
