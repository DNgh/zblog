package com.min.zblog.view.action;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.CommentService;
import com.min.zblog.data.view.CommentInfo;
import com.min.zblog.facility.utils.CommonUtils;
import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>Title: CommentAction</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年10月7日
 * @version 1.0
 */
public class CommentAction extends ActionSupport {
	
	private static final long serialVersionUID = 8961338680043516073L;
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CommentService commentService;
	
	private Long commentId;
	
	private Map<String, Object> respMap;
	
	private Long articleId;
	
	private Long commentRid;
	
	private Long commentPid;
	
	private String commentContent;
	
	private String pnickname;
	
	private String nickname;
	
	private String email;
	
	private String website;
	
	public String favor(){
		respMap = new HashMap<String, Object>();
		if(commentService.addFavorNum(commentId)){
			respMap.put("success", true);
		}else{
			respMap.put("success", false);
			respMap.put("message", "评论id错误");
		}
		
		return SUCCESS;
	}
	
	public String add(){
		System.out.println("进入add");
		CommentInfo commentInfo = commentService.addComment(articleId, commentRid, commentPid, commentContent, pnickname, nickname, email, website);
		logger.info("CommentInfo:"+commentInfo.getId()+"|"+commentInfo.getContent());
		
		respMap = CommonUtils.convertToMap(commentInfo);
		logger.info("Map:"+respMap.toString());
		System.out.println("退出add");
		return SUCCESS;
	}
	
	public void setCommentId(Long id){
		this.commentId = id;
	}

	public Long getCommentId() {
		return commentId;
	}

	public Map<String, Object> getRespMap() {
		return respMap;
	}

	public void setRespMap(Map<String, Object> respMap) {
		this.respMap = respMap;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getCommentRid() {
		return commentRid;
	}

	public void setCommentRid(Long commentRid) {
		this.commentRid = commentRid;
	}

	public Long getCommentPid() {
		return commentPid;
	}

	public void setCommentPid(Long commentPid) {
		this.commentPid = commentPid;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPnickname() {
		return pnickname;
	}

	public void setPnickname(String pnickname) {
		this.pnickname = pnickname;
	}
	
}
