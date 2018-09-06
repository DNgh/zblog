package com.min.zblog.data.view;

import java.io.Serializable;

/**
 * 页面归档信息
 * @author zhouzm
 *
 */
public class ArchiveInfo implements Serializable {
	
	private static final long serialVersionUID = 8217913542038217099L;
	
	/**
	 * 归档标题,按日期分类
	 */
	private String archiveTitle;
	
	/**
	 * 归档唯一性标识,按日期分类
	 */
	private String archiveName;
	
	/**
	 * 归档文章个数
	 */
	private long articleNum;

	public String getArchiveTitle() {
		return archiveTitle;
	}

	public void setArchiveTitle(String archiveTitle) {
		this.archiveTitle = archiveTitle;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}

	public long getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(long articleNum) {
		this.articleNum = articleNum;
	}
}
