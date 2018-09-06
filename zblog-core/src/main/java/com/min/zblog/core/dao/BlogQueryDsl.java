package com.min.zblog.core.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.QTmArchive;
import com.min.zblog.data.entity.QTmArticle;
import com.min.zblog.data.entity.QTmArticleTag;
import com.min.zblog.data.entity.QTmCategory;
import com.min.zblog.data.entity.QTmVisitHst;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmVisitHst;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BlogQueryDsl {
	@PersistenceContext
	private EntityManager em;
	
	private JPAQueryFactory queryFactory;
	
	private QTmArticle qTmArticle = QTmArticle.tmArticle;
	
	private QTmVisitHst qTmVisitHst = QTmVisitHst.tmVisitHst;
	
	private QTmArticleTag qTmArticleTag = QTmArticleTag.tmArticleTag;
	
	private QTmCategory qTmCategory = QTmCategory.tmCategory;
	
	private QTmArchive qTmArchive = QTmArchive.tmArchive;
	
	@PostConstruct
    public void init() {
       queryFactory = new JPAQueryFactory(em);
    }
	
	public List<TmArticle> findArticleByCategoryId(Long id) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle).where(qTmArticle.categoryId.eq(id))
				.orderBy(qTmArticle.createTime.desc()).fetch();
		return list;
	}
	
	public long deleteVisitHstByArticleId(Long id) {
		return queryFactory.delete(qTmVisitHst).where(qTmVisitHst.articleId.eq(id)).execute();
	}
	
	public long deleteArticleTagByArticleId(Long id) {
		return queryFactory.delete(qTmArticleTag).where(qTmArticleTag.articleId.eq(id)).execute();
	}
	
	/**
	 * 查询全部分类信息
	 * @param sort 排序 Y:asc, N:desc
	 * @param available 是否启用 Y:启用
	 * @return
	 */
	public List<TmCategory> fetchCategoryOrderBySort(Indicator sort, Indicator available) {
		JPAQuery<TmCategory> query = new JPAQuery<TmCategory>(em);
		List<TmCategory> list;
		OrderSpecifier<Integer> order = qTmCategory.sort.asc();
		if(sort == Indicator.N){
			order = qTmCategory.sort.desc();
		}
		list = query.from(qTmCategory).where(qTmCategory.available.eq(Indicator.Y))
				.orderBy(order).fetch();
		
		return list;
	}
	
	public List<TmArticle> fetchArticleByCategoryName(String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmCategory)
				.where(qTmArticle.categoryId.eq(qTmCategory.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmCategory.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc()).fetch();
		return list;
	}
	
	public long countCommentByArticleId(Long id) {
		return 0;
	}
	
	public long countVisitHstByArticleId(Long id, VisitType type) {
		JPAQuery<TmVisitHst> query = new JPAQuery<TmVisitHst>(em);
		
		return query.from(qTmVisitHst).where(qTmVisitHst.articleId.eq(id)
				.and(qTmVisitHst.visitType.eq(type))).fetchCount();
	}
	
	public List<TmArticle> fetchAllArticles(ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle)
				.where(qTmArticle.state.eq(state))
				.orderBy(qTmArticle.createTime.desc()).fetch();
		return list;
	}
	
	public List<TmArchive> fetchArchives(Indicator sort) {
		JPAQuery<TmArchive> query = new JPAQuery<TmArchive>(em);
		List<TmArchive> list;
		OrderSpecifier<String> order = qTmArchive.title.asc();
		if(sort == Indicator.N){
			order = qTmArchive.title.desc();
		}
		list = query.from(qTmArchive).orderBy(order).fetch();
		
		return list;
	}
	
	public List<TmArticle> fetchArticleByArchive(String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmArchive)
				.where(qTmArticle.archiveId.eq(qTmArchive.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmArchive.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc()).fetch();
		return list;
	}
}
