package com.min.zblog.service;

import java.util.List;

import com.min.zblog.entity.TmArticle;

public interface ArticleService {
	public List<TmArticle> listAll();
	public void addArticle(TmArticle article);
	public TmArticle findOne(Long id);
	public void deleteArticleById(Long id);
}
