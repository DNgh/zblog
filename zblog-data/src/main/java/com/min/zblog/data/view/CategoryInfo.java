package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;

import com.min.zblog.facility.enums.Indicator;

/**
 * 页面显示分类信息
 * @author zhouzm
 *
 */
public class CategoryInfo implements Serializable {

	private static final long serialVersionUID = -4391562715087734051L;
	
	/**
	 * 分类id
	 */
	private Long id;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 分类描述
	 */
	private String description;
	
	/**
	 * 所属分类，文章数 
	 */
	private long articleNum;
	
	/**
	 * 分类图标
	 */
	private String icon;
	
	/**
	 * 页面显示分类html
	 */
	private String html;
	
	/**
	 * 是否可用
	 */
	private Indicator available;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getArticleNum() {
		return articleNum;
	}

	public void setArticleNum(long articleNum) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Indicator getAvailable() {
		return available;
	}

	public void setAvailable(Indicator available) {
		this.available = available;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
