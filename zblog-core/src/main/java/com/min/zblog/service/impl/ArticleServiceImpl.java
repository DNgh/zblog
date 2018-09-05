package com.min.zblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.dao.ArticleDao;
import com.min.zblog.dao.ArticleQueryDsl;
import com.min.zblog.dao.ArticleTagDao;
import com.min.zblog.dao.VisitHstDao;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.service.ArticleService;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private VisitHstDao visitHstDao;
	@Autowired
	private ArticleTagDao articleTagDao;
	@Autowired
	private ArticleQueryDsl articleQueryDsl;
	
	public List<TmArticle> listAll() {
		return articleDao.findAll();
	}
	
	public TmArticle findOne(Long id) {
		return articleDao.findOne(id);
	}
	
	@Transactional
	public void addArticle(TmArticle article) {
		articleDao.save(article);
	}
	
	public void deleteArticleById(Long id) {
		TmArticle article = articleDao.findOne(id);
		if(article == null){
			
		}
		
		//删除文章
		articleDao.delete(id);
		//删除访问历史
		articleQueryDsl.deleteVisitHstByArticleId(article.getId());
		//删除标签关联记录
		articleQueryDsl.deleteVisitHstByArticleId(article.getId());
		//删除评论
	}
}
