package com.min.zblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.dao.ArticleDao;
import com.min.zblog.entity.TmArticle;
import com.min.zblog.service.ArticleService;

public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	public List<TmArticle> listAll() {
		return articleDao.findAll();
	}
}
