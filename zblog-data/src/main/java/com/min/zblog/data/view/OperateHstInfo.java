package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面显示操作历史信息
 * @author zhouzm
 *
 */
public class OperateHstInfo implements Serializable {

	private static final long serialVersionUID = -1085778405285680739L;
	
	/**
	 * 历史id
	 */
	private Long id;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 请求路径
	 */
	private String url;
	
	/**
	 * 操作类型
	 */
	private String name;
	
	/**
	 * 操作详情
	 */
	private String description;
	
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
