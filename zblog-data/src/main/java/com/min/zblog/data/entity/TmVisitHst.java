package com.min.zblog.data.entity;
// Generated 2018-8-19 21:26:30 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.min.zblog.facility.enums.VisitType;

/**
 * TmVisitHst generated by hbm2java
 */
@Entity
@Table(name = "tm_visit_hst", catalog = "zblog")
public class TmVisitHst implements java.io.Serializable {

	private Long id;
	private Long articleId;
	private Long userId;
	private String ip;
	private String browser;
	private VisitType visitType;
	private Date createTime;
	private Integer jpaVersion;

	public TmVisitHst() {
	}

	public TmVisitHst(Long articleId) {
		this.articleId = articleId;
	}

	public TmVisitHst(Long articleId, Long userId, String ip, String browser, VisitType visitType,
			Date createTime, Integer jpaVersion) {
		this.articleId = articleId;
		this.userId = userId;
		this.ip = ip;
		this.browser = browser;
		this.visitType = visitType;
		this.createTime = createTime;
		this.jpaVersion = jpaVersion;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "article_id", nullable = false)
	public Long getArticleId() {
		return this.articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	@Column(name = "user_id")
	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "ip", length = 64)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "browser", length = 128)
	public String getBrowser() {
		return this.browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Column(name = "visit_type", length = 1)
	@Enumerated(EnumType.STRING)
	public VisitType getVisitType() {
		return this.visitType;
	}

	public void setVisitType(VisitType visitType) {
		this.visitType = visitType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "jpa_version", nullable = false)
	public Integer getJpaVersion() {
		return this.jpaVersion;
	}

	public void setJpaVersion(Integer jpaVersion) {
		this.jpaVersion = jpaVersion;
	}

}
