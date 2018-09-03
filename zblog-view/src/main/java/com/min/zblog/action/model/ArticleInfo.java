package com.min.zblog.action.model;

import java.io.Serializable;

public class ArticleInfo implements Serializable {
	private static final long serialVersionUID = -2090308250539116291L;

	private int readNum;
	
	private int commentNum;

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
}
