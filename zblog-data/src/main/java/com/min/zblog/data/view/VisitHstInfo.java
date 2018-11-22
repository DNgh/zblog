package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面显示访问历史信息
 * @author zhouzm
 *
 */
public class VisitHstInfo implements Serializable {

	private static final long serialVersionUID = -1085778405285680739L;
	
	/**
	 * 历史id
	 */
	private Long id;
	
	/**
	 * 文章标题
	 */
	private String articleTitle;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 浏览器类型
	 */
	private String browser;
	
	/**
	 * 访问类型
	 */
	private String visitTypeDesc;
	
	/**
	 * 创建时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getVisitTypeDesc() {
		return visitTypeDesc;
	}

	public void setVisitTypeDesc(String visitTypeDesc) {
		this.visitTypeDesc = visitTypeDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
