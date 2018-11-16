package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面归档信息
 * @author zhouzm
 *
 */
public class ArchiveInfo implements Serializable {
	
	private static final long serialVersionUID = 8217913542038217099L;
	
	private Long id;
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
	
	/**
	 * 创建日期
	 */
	private Date createTime;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
