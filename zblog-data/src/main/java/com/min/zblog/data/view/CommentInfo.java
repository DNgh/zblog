package com.min.zblog.data.view;

import java.io.Serializable;
import java.util.Date;

/**
 * 页面评论信息
 * @author zhouzm
 *
 */
public class CommentInfo implements Serializable {

	private static final long serialVersionUID = 5998221272634685482L;
	
	/**
	 * 文章标题
	 */
	private String articleTitle;
	
	/**
	 * 根评论id
	 */
	private Long rid;
	
	/**
	 * 父评论id
	 */
	private Long pid;
	
	/**
	 * 评论id
	 */
	private Long id;
	
	/**
	 * 评论内容
	 */
	private String content;
	
	/**
	 * 评论时间
	 */
	private Date createTime;
	
	/**
	 * 评论-用户昵称
	 */
	private String nickname;
	
	/**
	 * 父评论-用户昵称
	 */
	private String pnickname;
	
	/**
	 * 点赞数
	 */
	private long favorNum;
	
	/**
	 * ip地址
	 */
	private String ip;
	
	/**
	 * 浏览器类型
	 */
	private String browser;

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPnickname() {
		return pnickname;
	}

	public void setPnickname(String pnickname) {
		this.pnickname = pnickname;
	}

	public long getFavorNum() {
		return favorNum;
	}

	public void setFavorNum(long favorNum) {
		this.favorNum = favorNum;
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
	
}
