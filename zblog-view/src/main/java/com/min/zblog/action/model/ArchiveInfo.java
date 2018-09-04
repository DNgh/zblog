package com.min.zblog.action.model;

import java.io.Serializable;

/**
 * 页面归档信息
 * @author zhouzm
 *
 */
public class ArchiveInfo implements Serializable {
	
	private static final long serialVersionUID = 8217913542038217099L;
	
	/**
	 * 归档文章日期，取文章创建日期
	 */
	private String createDate;
	
	/**
	 * 归档文章个数
	 */
	private int articleNum;
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(int articleNum) {
		this.articleNum = articleNum;
	}
}
