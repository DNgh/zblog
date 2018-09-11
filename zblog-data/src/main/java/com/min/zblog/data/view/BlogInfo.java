package com.min.zblog.data.view;

import java.io.Serializable;

/**
 * <p>Title: BlogInfo</p>
 * <p>Description: 博客统计信息</p>
 * @author	zhouzm
 * @date	2018年9月9日
 * @version 1.0
 */
public class BlogInfo implements Serializable {

	private static final long serialVersionUID = 7502863706639982169L;

	private long totalArticleNum;
	
	private long totalReadNum;
	
	private long totalCommentNum;

	public long getTotalArticleNum() {
		return totalArticleNum;
	}

	public void setTotalArticleNum(long totalArticleNum) {
		this.totalArticleNum = totalArticleNum;
	}

	public long getTotalReadNum() {
		return totalReadNum;
	}

	public void setTotalReadNum(long totalReadNum) {
		this.totalReadNum = totalReadNum;
	}

	public long getTotalCommentNum() {
		return totalCommentNum;
	}

	public void setTotalCommentNum(long totalCommentNum) {
		this.totalCommentNum = totalCommentNum;
	}
}
