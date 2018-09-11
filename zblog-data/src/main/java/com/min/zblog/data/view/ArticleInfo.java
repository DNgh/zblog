package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 页面显示文章统计信息
 * @author zhouzm
 *
 */
public class ArticleInfo implements Serializable {

	private static final long serialVersionUID = -2090308250539116291L;
	
	/**
	 * 文章ID
	 */
	private Long id;
	
	/**
	 * 文章标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 创建日期
	 */
	private Date createTime;
	
	/**
	 * 阅读数
	 */
	private long readNum;
	
	/**
	 * 评论数
	 */
	private long commentNum;
	
	/**
	 * 点赞数
	 */
	private long favorNum;
	
	/**
	 * 分享数
	 */
	private long shareNum;
	
	/**
	 * 分类名
	 */
	private String categoryName;
	
	/**
	 * 标签名
	 */
	private List<String> tagList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public long getReadNum() {
		return readNum;
	}

	public void setReadNum(long readNum) {
		this.readNum = readNum;
	}

	public long getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(long commentNum) {
		this.commentNum = commentNum;
	}
	
	public long getFavorNum() {
		return favorNum;
	}

	public void setFavorNum(long favorNum) {
		this.favorNum = favorNum;
	}
	
	public long getShareNum() {
		return shareNum;
	}

	public void setShareNum(long shareNum) {
		this.shareNum = shareNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<String> getTagList() {
		return tagList;
	}

	public void setTagList(List<String> tagList) {
		this.tagList = tagList;
	}
	
}
