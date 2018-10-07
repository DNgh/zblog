package com.min.zblog.view.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.CommentService;
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
	
	@Autowired
	private CommentService commentService;
	
	private Long commentId;
	
	private Map<String, Object> respMap;
	
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
	
}
