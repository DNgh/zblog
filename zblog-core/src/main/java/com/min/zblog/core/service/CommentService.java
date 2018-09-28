package com.min.zblog.core.service;

import java.util.List;

import com.min.zblog.data.view.CommentInfo;

public interface CommentService {
	public List<CommentInfo> listCommentByArticleId(Long id);
	
	public List<CommentInfo> listCommentByRId(Long id);
}
