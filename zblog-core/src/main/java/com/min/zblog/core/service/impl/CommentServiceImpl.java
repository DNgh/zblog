package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArticleDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CommentDao;
import com.min.zblog.core.service.CommentService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmComment;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.CommentInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.Indicator;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentDao commentDao;
	
	@Autowired
	private ArticleDao articleDao;
	
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

	/**
	 * 点赞数累计
	 * 找不到评论，返回false；找到，点赞数加1，返回true。
	 */
	@Override
	public boolean addFavorNum(Long commentId) {
		TmComment comment = commentDao.findOne(commentId);
		if(comment == null){
			return false;
		}
		
		comment.setFavorNum(comment.getFavorNum()+1);
		comment.setUpdateTime(new Date());
		commentDao.save(comment);
		
		return true;
	}

	@Override
	public CommentInfo addComment(Long articleId, Long commentRid,
			Long commentPid, String commentContent, String pnickname, String nickname,
			String email, String website, String ip, String browser) {
		TmComment tmComment = new TmComment();
		tmComment.setArticleId(articleId);
		tmComment.setContent(commentContent);
		tmComment.setCreateTime(new Date());
		tmComment.setFavorNum(0);
		tmComment.setNickname(nickname);
		tmComment.setPid(commentPid);
		tmComment.setRid(commentRid);
		tmComment.setPnickname(pnickname);
		tmComment.setUpdateTime(new Date());
		tmComment.setIp(ip);
		tmComment.setBrowser(browser);
		tmComment.setJpaVersion(0);
		commentDao.save(tmComment);
		
		CommentInfo commentInfo = new CommentInfo();
		commentInfo.setContent(tmComment.getContent());
		commentInfo.setCreateTime(tmComment.getCreateTime());
		commentInfo.setFavorNum(tmComment.getFavorNum());
		commentInfo.setId(tmComment.getId());
		commentInfo.setNickname(tmComment.getNickname());
		commentInfo.setPid(tmComment.getPid());
		commentInfo.setPnickname(tmComment.getPnickname());
		commentInfo.setRid(tmComment.getRid());
		
		return commentInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.CommentService#queryCommentByPage(long, long, java.util.Map)
	 */
	@Override
	public PageInfo<CommentInfo> queryCommentByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmComment> tmCommentList = blogQueryDsl.fetchCommentConditionByPage(currentPage, pageSize, map);
		List<CommentInfo> commentInfoList = new ArrayList<CommentInfo>();
		for(TmComment comment:tmCommentList){
			
			CommentInfo commentInfo = new CommentInfo();
			commentInfo.setId(comment.getId());
			TmArticle article = articleDao.findOne(comment.getArticleId());
			if(article != null){
				commentInfo.setArticleTitle(article.getTitle());
			}
			commentInfo.setRid(comment.getRid());
			commentInfo.setPid(comment.getPid());
			commentInfo.setPnickname(comment.getPnickname());
			commentInfo.setNickname(comment.getNickname());
			commentInfo.setContent(comment.getContent());
			commentInfo.setFavorNum(comment.getFavorNum());
			commentInfo.setCreateTime(comment.getCreateTime());
			
			commentInfoList.add(commentInfo);
		}
		
		PageInfo<CommentInfo> pageInfo = new PageInfo<CommentInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countCommentByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(commentInfoList);
		
		return pageInfo;
	}

}
