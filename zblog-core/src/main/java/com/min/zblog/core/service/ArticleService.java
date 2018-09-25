package com.min.zblog.core.service;

import java.util.List;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.PageInfo;

public interface ArticleService {
	public List<TmArticle> listAll();
	public void addArticle(TmArticle article);
	public TmArticle findOne(Long id);
	public void deleteArticleById(Long id);
	public PageInfo<ArticleInfo> listArticleByPageCategoryName(long pageSize, long currentPage, String name);
	public List<ArticleInfo> listAllArticles();
	public PageInfo<ArticleInfo> listArticleByPageArchive(long pageSize, long currentPage, String name);
	public PageInfo<ArticleInfo> listArticleByPageTag(long pageSize, long currentPage, String name);
	public List<ArticleInfo> listArticleByReadRank();
	public BlogInfo obtainBlogInfo();
	public PageInfo<ArticleInfo> listArticleByPage(long pageSize, long currentPage);
	public ArticleInfo findOneArticle(Long id);
	public ArticleInfo findPreOneArticle(Long id);
	public ArticleInfo findNextOneArticle(Long id);
}