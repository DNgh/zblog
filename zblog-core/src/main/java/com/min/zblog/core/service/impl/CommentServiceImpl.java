package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CommentDao;
import com.min.zblog.core.service.CommentService;
import com.min.zblog.data.entity.TmComment;
import com.min.zblog.data.view.CommentInfo;
import com.min.zblog.facility.enums.Indicator;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Override
	public List<CommentInfo> listCommentByArticleId(Long id) {
		List<TmComment> commentList = blogQueryDsl.fetchCommentByArticleIdByOrder(id, Indicator.N, Indicator.Y);
		
		List<CommentInfo> commentInfoList = new ArrayList<CommentInfo>();
		for(TmComment tmComment:commentList){
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setContent(tmComment.getContent());
			commentInfo.setCreateTime(tmComment.getCreateTime());
			commentInfo.setFavorNum(tmComment.getFavorNum());
			commentInfo.setId(tmComment.getId());
			commentInfo.setNickname(tmComment.getNickname());
			commentInfo.setPid(tmComment.getPid());
			commentInfo.setPnickname(tmComment.getPnickname());
			commentInfo.setRid(tmComment.getRid());
			
			commentInfoList.add(commentInfo);
		}
		return commentInfoList;
	}
	
	@Override
	public List<CommentInfo> listCommentByRId(Long id) {
		List<TmComment> commentList = commentDao.findByRidOrderByCreateTimeDesc(id);
		
		List<CommentInfo> commentInfoList = new ArrayList<CommentInfo>();
		for(TmComment tmComment:commentList){
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setContent(tmComment.getContent());
			commentInfo.setCreateTime(tmComment.getCreateTime());
			commentInfo.setFavorNum(tmComment.getFavorNum());
			commentInfo.setId(tmComment.getId());
			commentInfo.setNickname(tmComment.getNickname());
			commentInfo.setPid(tmComment.getPid());
			commentInfo.setPnickname(tmComment.getPnickname());
			commentInfo.setRid(tmComment.getRid());
			
			commentInfoList.add(commentInfo);
		}
		return commentInfoList;
	}

}
