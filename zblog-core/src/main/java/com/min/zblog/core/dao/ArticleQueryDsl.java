package com.min.zblog.core.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.QTmArticle;
import com.min.zblog.data.entity.QTmArticleTag;
import com.min.zblog.data.entity.QTmCategory;
import com.min.zblog.data.entity.QTmVisitHst;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ArticleQueryDsl {
	@PersistenceContext
	private EntityManager em;
	
	private JPAQueryFactory queryFactory;
	
	private QTmArticle qTmArticle = QTmArticle.tmArticle;
	
	private QTmVisitHst qTmVisitHst = QTmVisitHst.tmVisitHst;
	
	private QTmArticleTag qTmArticleTag = QTmArticleTag.tmArticleTag;
	
	private QTmCategory qTmCategory = QTmCategory.tmCategory;
	
	@PostConstruct
    public void init() {
       queryFactory = new JPAQueryFactory(em);
    }
	
	public List<TmArticle> findArticleByCategoryId(Long id) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle).where(qTmArticle.categoryId.eq(id))
				.orderBy(qTmArticle.createTime.asc()).fetch();
		return list;
	}
	
	public long deleteVisitHstByArticleId(Long id) {
		return queryFactory.delete(qTmVisitHst).where(qTmVisitHst.articleId.eq(id)).execute();
	}
	
	public long deleteArticleTagByArticleId(Long id) {
		return queryFactory.delete(qTmArticleTag).where(qTmArticleTag.articleId.eq(id)).execute();
	}
	
	public long countArticleByCategoryId(Long id) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		return query.from(qTmArticle).where(qTmArticle.categoryId.eq(id))
				.fetchCount();
	}
}
