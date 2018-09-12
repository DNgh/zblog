package com.min.zblog.core.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.QTmTag;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.QTmArchive;
import com.min.zblog.data.entity.QTmArticle;
import com.min.zblog.data.entity.QTmArticleTag;
import com.min.zblog.data.entity.QTmCategory;
import com.min.zblog.data.entity.QTmVisitHst;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.entity.TmVisitHst;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
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
	
	private QTmTag qTmTag = QTmTag.tmTag;
	
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
	
	public List<TmArticle> fetchArticleByPageCategoryName(long pageSize, long currentPage, String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmCategory)
				.where(qTmArticle.categoryId.eq(qTmCategory.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmCategory.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize)
				.fetch();
		return list;
	}
	
	public long countArticleByCategoryName(String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		return query.from(qTmArticle, qTmCategory)
				.where(qTmArticle.categoryId.eq(qTmCategory.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmCategory.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc())
				.fetchCount();
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
	
	public List<TmArticle> fetchArticleByPageArchive(long pageSize, long currentPage, String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmArchive)
				.where(qTmArticle.archiveId.eq(qTmArchive.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmArchive.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	public long countArticleByArchive(String name, ArticleState state){
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		return query.from(qTmArticle, qTmArchive)
				.where(qTmArticle.archiveId.eq(qTmArchive.id)
						.and(qTmArticle.state.eq(state))
						.and(qTmArchive.name.eq(name)))
				.orderBy(qTmArticle.createTime.desc())
				.fetchCount();
	}
	
	public List<TmArticle> fetchArticleByPageTag(long pageSize, long currentPage, String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmTag, qTmArticleTag)
				.where(qTmArticle.id.eq(qTmArticleTag.articleId)
						.and(qTmArticleTag.tagId.eq(qTmTag.id))
						.and(qTmTag.name.eq(name))
						.and(qTmArticle.state.eq(state)))
				.orderBy(qTmArticle.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	public long countArticleByTag(String name, ArticleState state) {
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		return query.from(qTmArticle, qTmTag, qTmArticleTag)
				.where(qTmArticle.id.eq(qTmArticleTag.articleId)
						.and(qTmArticleTag.tagId.eq(qTmTag.id))
						.and(qTmTag.name.eq(name))
						.and(qTmArticle.state.eq(state)))
				.orderBy(qTmArticle.createTime.desc())
				.fetchCount();
	}
	
	/**
	 * 找出前n条数据
	 * @param top
	 * @param type
	 * @param state
	 * @return
	 */
	public List<TmArticle> fetchTopArticleByHst(int top, VisitType type, ArticleState state){
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle, qTmVisitHst)
				.where(qTmArticle.id.eq(qTmVisitHst.articleId)
						.and(qTmVisitHst.visitType.eq(type))
						.and(qTmArticle.state.eq(state)))
				.groupBy(qTmVisitHst.articleId)
				.orderBy(qTmVisitHst.id.count().desc())
				.limit(top)
				.fetch();
		return list;
	}
	
	public long countVisitHstByType(VisitType type, ArticleState state){
		JPAQuery<TmVisitHst> query = new JPAQuery<TmVisitHst>(em);
		long count = query.from(qTmArticle, qTmVisitHst)
				.where(qTmArticle.id.eq(qTmVisitHst.articleId)
						.and(qTmVisitHst.visitType.eq(type))
						.and(qTmArticle.state.eq(state)))
				.fetchCount();
		return count;
	}
	
	public long countArticleByComment(int top, VisitType type, ArticleState state){
		return 0;
	}
	
	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
	 * @param state
	 * @return
	 */
	public List<TmArticle> fetchArticleByPage(long currentPage, long pageSize, ArticleState state){
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle)
				.where(qTmArticle.state.eq(state))
				.orderBy(qTmArticle.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	public List<TmTag> fetchTagByArticleId(Long id){
		JPAQuery<TmTag> query = new JPAQuery<TmTag>(em);
		return query.from(qTmTag, qTmArticleTag)
				.where(qTmArticleTag.tagId.eq(qTmTag.id)
						.and(qTmArticleTag.articleId.eq(id)))
				.orderBy(qTmTag.createTime.desc())
				.fetch();
	}
	
	/**
	 * 查找ID临近的文章
	 * @param id
	 * @param state
	 * @param less
	 * @return
	 */
	public TmArticle fetchFirstArticleByIdNear(Long id, ArticleState state, Indicator less){
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		BooleanExpression exp = qTmArticle.state.eq(state);
		OrderSpecifier<Long> order;
		if(less == Indicator.Y){
			exp = exp.and(qTmArticle.id.lt(id));
			order = qTmArticle.id.desc();
		}else{
			exp = exp.and(qTmArticle.id.gt(id));
			order = qTmArticle.id.asc();
		}
		
		return query.from(qTmArticle).where(exp).orderBy(order).fetchFirst();
	}
}
