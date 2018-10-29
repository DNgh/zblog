package com.min.zblog.core.service;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.exception.ProcessException;

public interface ArticleService {
	public List<TmArticle> listAll();
	public TmArticle addArticle(Map<String, Object> map);
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
	public TmArticle saveArticle(Map<String, Object> map) throws ProcessException;
}
