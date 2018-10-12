package com.min.zblog.core.service;

import java.util.List;

import com.min.zblog.data.view.CommentInfo;

public interface CommentService {
	public List<CommentInfo> listCommentByArticleId(Long id);
	public List<CommentInfo> listCommentByRId(Long id);
	public boolean addFavorNum(Long commentId);
	public CommentInfo addComment(Long articleId, Long commentRid,
			Long commentPid, String commentContent, String pnickname, String nickname,
			String email, String website, String ip, String browser);
}
