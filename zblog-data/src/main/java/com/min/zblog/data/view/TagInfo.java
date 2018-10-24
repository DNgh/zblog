package com.min.zblog.data.view;

import java.io.Serializable;

/**
 * 页面显示标签信息
 * @author zhouzm
 *
 */
public class TagInfo implements Serializable {

	private static final long serialVersionUID = -1085778405285680739L;
	
	/**
	 * 标签id
	 */
	private Long id;
	
	/**
	 * 标签名称
	 */
	private String tagName;
	
	/**
	 * 标签样式
	 */
	private String style;
	
	/**
	 * 页面显示标签html
	 */
	private String html;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
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
}
