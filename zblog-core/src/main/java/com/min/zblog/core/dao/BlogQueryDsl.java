package com.min.zblog.core.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.QTmArchive;
import com.min.zblog.data.entity.QTmArticle;
import com.min.zblog.data.entity.QTmArticleTag;
import com.min.zblog.data.entity.QTmCategory;
import com.min.zblog.data.entity.QTmComment;
import com.min.zblog.data.entity.QTmTag;
import com.min.zblog.data.entity.QTmVisitHst;
import com.min.zblog.data.entity.QTsOperateHst;
import com.min.zblog.data.entity.QTsResource;
import com.min.zblog.data.entity.QTsRole;
import com.min.zblog.data.entity.QTsRoleResource;
import com.min.zblog.data.entity.QTsUser;
import com.min.zblog.data.entity.QTsUserRole;
import com.min.zblog.data.entity.SecuritySource;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmComment;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.entity.TmVisitHst;
import com.min.zblog.data.entity.TsOperateHst;
import com.min.zblog.data.entity.TsResource;
import com.min.zblog.data.entity.TsRole;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;
import com.min.zblog.facility.utils.Constants;
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
	
	private QTmComment qTmComment = QTmComment.tmComment;
	
	private QTsOperateHst qTsOperateHst = QTsOperateHst.tsOperateHst;
	
	private QTsRole qTsRole = QTsRole.tsRole;
	
	private QTsUser qTsUser = QTsUser.tsUser;
	
	private QTsUserRole qTsUserRole = QTsUserRole.tsUserRole;
	
	private QTsResource qTsResource = QTsResource.tsResource;
	
	private QTsRoleResource qTsRoleResource = QTsRoleResource.tsRoleResource;
	
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
	
	public List<TmArticle> fetchArticleByPageCategoryId(long pageSize, long currentPage, Long id, ArticleState state) {
		BooleanExpression exp = qTmArticle.categoryId.eq(id);
		if(state != null){
			exp.and(qTmArticle.state.eq(state));
		}
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle)
				.where(exp)
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
		JPAQuery<TmComment> query = new JPAQuery<TmComment>(em);
		
		return query.from(qTmComment).where(qTmComment.articleId.eq(id)).fetchCount();
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
	
	//统计总历史记录数
	public long countVisitHstByType(VisitType type, ArticleState state){
		JPAQuery<TmVisitHst> query = new JPAQuery<TmVisitHst>(em);
		long count = query.from(qTmArticle, qTmVisitHst)
				.where(qTmArticle.id.eq(qTmVisitHst.articleId)
						.and(qTmVisitHst.visitType.eq(type))
						.and(qTmArticle.state.eq(state)))
				.fetchCount();
		return count;
	}
	
	//统计总评论数
	public long countCommentByState(ArticleState state){
		JPAQuery<TmComment> query = new JPAQuery<TmComment>(em);
		long count = query.from(qTmArticle, qTmComment)
				.where(qTmArticle.id.eq(qTmComment.articleId)
						.and(qTmArticle.state.eq(state)))
				.fetchCount();
		return count;
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
	
	/**
	 * 分页查询,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:state createTime categoryId
	 * @return
	 */
	public List<TmArticle> fetchArticleConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.STATE) != null){
				exp = qTmArticle.state.eq((ArticleState)map.get(Constants.STATE));
			}
			
			//年不为空，则作为查询条件；月不为空，则作为查询条件
			if(StringUtils.isNotBlank((String)map.get(Constants.YEAR))){
				BooleanExpression archiveExp = null;
				if(StringUtils.isNotBlank((String)map.get(Constants.MONTH)) ){
					String name = (String)map.get(Constants.YEAR)
							+(String)map.get(Constants.MONTH);
					archiveExp = qTmArchive.name.eq(name);
				}else{
					archiveExp = qTmArchive.name.goe((String)map.get(Constants.YEAR)+"01")
								.and(qTmArchive.name.loe((String)map.get(Constants.YEAR)+"12"));
				}
				
				JPAQuery<TmArchive> vQuery = new JPAQuery<TmArchive>(em);
				List<TmArchive> tmArchiveList = vQuery.from(qTmArchive)
						.where(archiveExp)
						.fetch();
				if(tmArchiveList != null){
					List<Long> idList = new ArrayList<>();
					for(TmArchive archive:tmArchiveList){
						idList.add(archive.getId());
					}
					if(exp != null){
						exp = exp.and(qTmArticle.archiveId.in(idList));
					}else{
						exp = qTmArticle.archiveId.in(idList);
					}
				}
			}
			
			if(map.get(Constants.CATEGORY_ID) != null){
				if(exp != null){
					exp = exp.and(qTmArticle.categoryId.eq((Long)map.get(Constants.CATEGORY_ID)));
				}else{
					exp = qTmArticle.categoryId.eq((Long)map.get(Constants.CATEGORY_ID));
				}
			}
		}
		
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		List<TmArticle> list = query.from(qTmArticle)
				.where(exp)
				.orderBy(qTmArticle.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:state year month categoryId
	 * @return
	 */
	public Long countArticleByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.STATE) != null){
				exp = qTmArticle.state.eq((ArticleState)map.get(Constants.STATE));
			}
			
			//年不为空，则作为查询条件；月不为空，则作为查询条件
			if(StringUtils.isNotBlank((String)map.get(Constants.YEAR))){
				BooleanExpression archiveExp = null;
				if(StringUtils.isNotBlank((String)map.get(Constants.MONTH)) ){
					String name = (String)map.get(Constants.YEAR)
							+(String)map.get(Constants.MONTH);
					archiveExp = qTmArchive.name.eq(name);
				}else{
					archiveExp = qTmArchive.name.goe((String)map.get(Constants.YEAR)+"01")
								.and(qTmArchive.name.loe((String)map.get(Constants.YEAR)+"12"));
				}
				
				JPAQuery<TmArchive> vQuery = new JPAQuery<TmArchive>(em);
				List<TmArchive> tmArchiveList = vQuery.from(qTmArchive)
						.where(archiveExp)
						.fetch();
				if(tmArchiveList != null){
					List<Long> idList = new ArrayList<>();
					for(TmArchive archive:tmArchiveList){
						idList.add(archive.getId());
					}
					if(exp != null){
						exp = exp.and(qTmArticle.archiveId.in(idList));
					}else{
						exp = qTmArticle.archiveId.in(idList);
					}
				}
			}
			
			if(map.get(Constants.CATEGORY_ID) != null){
				if(exp != null){
					exp = exp.and(qTmArticle.categoryId.eq((Long)map.get(Constants.CATEGORY_ID)));
				}else{
					exp = qTmArticle.categoryId.eq((Long)map.get(Constants.CATEGORY_ID));
				}
			}
		}
		
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		Long total = query.from(qTmArticle)
				.where(exp)
				.fetchCount();
		return total;
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
	
	/**
	 * 查询评论
	 * @param articleId 文章id
	 * @param containSub 是否包含子评论，包含Y
	 * @param less 降序排序Y
	 * @return
	 */
	public List<TmComment> fetchCommentByArticleIdByOrder(Long articleId, Indicator containSub, Indicator less) {
		JPAQuery<TmComment> query = new JPAQuery<TmComment>(em);
		
		BooleanExpression exp = qTmComment.articleId.eq(articleId);
		if(containSub == Indicator.N){
			exp = exp.and(qTmComment.rid.isNull());
		}
		
		OrderSpecifier<Date> order;
		if(less == Indicator.Y){
			order = qTmComment.createTime.desc();
		}else{
			order = qTmComment.createTime.asc();
		}
		
		return query.from(qTmComment).where(exp).orderBy(order).fetch();
	}
	
	/**
	 * 分页查询分类信息,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:available startDate endDate
	 * @return
	 */
	public List<TmCategory> fetchCategoryConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmCategory.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
			
			//是否启用
			if(map.get(Constants.AVAILABLE) != null){
				if(exp != null){
					exp = exp.and(qTmCategory.available.eq((Indicator)map.get(Constants.AVAILABLE)));
				}else{
					exp = qTmCategory.available.eq((Indicator)map.get(Constants.AVAILABLE));
				}
			}
		}
		
		JPAQuery<TmCategory> query = new JPAQuery<TmCategory>(em);
		List<TmCategory> list = query.from(qTmCategory)
				.where(exp)
				.orderBy(qTmCategory.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:available startDate endDate
	 * @return
	 */
	public Long countCategoryByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmCategory.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
			
			//是否启用
			if(map.get(Constants.AVAILABLE) != null){
				if(exp != null){
					exp = exp.and(qTmCategory.available.eq((Indicator)map.get(Constants.AVAILABLE)));
				}else{
					exp = qTmCategory.available.eq((Indicator)map.get(Constants.AVAILABLE));
				}
			}
		}
		
		JPAQuery<TmCategory> query = new JPAQuery<TmCategory>(em);
		Long total = query.from(qTmCategory)
				.where(exp)
				.fetchCount();
		return total;
	}
		
	/**
	 * 读取sort最大值
	 * @param 
	 * @return
	 */
	public Integer fetchCategoryMaxSort(){
		
		JPAQuery<TmCategory> query = new JPAQuery<TmCategory>(em);
		TmCategory category = query.from(qTmCategory)
				.orderBy(qTmCategory.sort.desc())
				.fetchFirst();
		return category.getSort();
	}

	public long countArticleByCategoryId(Long id, ArticleState state) {
		BooleanExpression exp = qTmArticle.categoryId.eq(id);
		if(state != null){
			exp.and(qTmArticle.state.eq(state));
		}
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		long count = query.from(qTmArticle).where(exp).fetchCount();
		
		return count;
	}
	
	/**
	 * 分页查询归档信息,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:startDate endDate
	 * @return
	 */
	public List<TmArchive> fetchArchiveConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmArchive.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmArchive> query = new JPAQuery<TmArchive>(em);
		List<TmArchive> list = query.from(qTmArchive)
				.where(exp)
				.orderBy(qTmArchive.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:startDate endDate
	 * @return
	 */
	public Long countArchiveByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmArchive.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmArchive> query = new JPAQuery<TmArchive>(em);
		Long total = query.from(qTmArchive)
				.where(exp)
				.fetchCount();
		return total;
	}
	
	public long countArticleByArchiveId(Long id, ArticleState state) {
		BooleanExpression exp = qTmArticle.archiveId.eq(id);
		if(state != null){
			exp.and(qTmArticle.state.eq(state));
		}
		JPAQuery<TmArticle> query = new JPAQuery<TmArticle>(em);
		long count = query.from(qTmArticle).where(exp).fetchCount();
		
		return count;
	}
	
	/**
	 * 分页查询标签信息,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:startDate endDate
	 * @return
	 */
	public List<TmTag> fetchTagConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmTag.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmTag> query = new JPAQuery<TmTag>(em);
		List<TmTag> list = query.from(qTmTag)
				.where(exp)
				.orderBy(qTmTag.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:startDate endDate
	 * @return
	 */
	public Long countTagByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmTag.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmTag> query = new JPAQuery<TmTag>(em);
		Long total = query.from(qTmTag)
				.where(exp)
				.fetchCount();
		return total;
	}
	
	public long countArticleByTagId(Long id, ArticleState state) {
		BooleanExpression exp = qTmArticleTag.tagId.eq(id)
				.and(qTmArticleTag.articleId.eq(qTmArticle.id));
		if(state != null){
			exp = exp.and(qTmArticle.state.eq(state));
		}
		
		JPAQuery<TmArticleTag> query = new JPAQuery<TmArticleTag>(em);
		return query.from(qTmArticle, qTmArticleTag)
				.where(exp)
				.fetchCount();
	}
	
	/**
	 * 分页查询评论信息,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:startDate endDate
	 * @return
	 */
	public List<TmComment> fetchCommentConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmComment.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmComment> query = new JPAQuery<TmComment>(em);
		List<TmComment> list = query.from(qTmComment)
				.where(exp)
				.orderBy(qTmComment.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:startDate endDate
	 * @return
	 */
	public Long countCommentByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmComment.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmComment> query = new JPAQuery<TmComment>(em);
		Long total = query.from(qTmComment)
				.where(exp)
				.fetchCount();
		return total;
	}
	
	/**
	 * 分页查询访问历史信息,多个查询条件
	 * @param currentPage
	 * @param pageSize
	 * @param map:startDate endDate
	 * @return
	 */
	public List<TmVisitHst> fetchVisitHstConditionByPage(long currentPage, long pageSize, Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmVisitHst.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmVisitHst> query = new JPAQuery<TmVisitHst>(em);
		List<TmVisitHst> list = query.from(qTmVisitHst)
				.where(exp)
				.orderBy(qTmVisitHst.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:startDate endDate
	 * @return
	 */
	public Long countVisitHstByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTmVisitHst.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TmVisitHst> query = new JPAQuery<TmVisitHst>(em);
		Long total = query.from(qTmVisitHst)
				.where(exp)
				.fetchCount();
		return total;
	}

	/**
	 * @param currentPage
	 * @param pageSize
	 * @param map
	 * @return
	 */
	public List<TsOperateHst> fetchOperateHstConditionByPage(long currentPage, long pageSize, Map<String, Object> map) {
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTsOperateHst.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TsOperateHst> query = new JPAQuery<TsOperateHst>(em);
		List<TsOperateHst> list = query.from(qTsOperateHst)
				.where(exp)
				.orderBy(qTsOperateHst.createTime.desc())
				.offset((currentPage-1)*pageSize)
				.limit(pageSize).fetch();
		return list;
	}
	
	/**
	 * 结合分页查询,统计个数
	 * @param map:startDate endDate
	 * @return
	 */
	public Long countOperateHstByCondition(Map<String, Object> map){
		BooleanExpression exp = null;
		if(map != null){
			if(map.get(Constants.START_DATE) != null && map.get(Constants.END_DATE) != null){
				exp = qTsOperateHst.createTime.between((Date)map.get(Constants.START_DATE), 
						(Date)map.get(Constants.END_DATE));
			}
		}
		
		JPAQuery<TsOperateHst> query = new JPAQuery<TsOperateHst>(em);
		Long total = query.from(qTsOperateHst)
				.where(exp)
				.fetchCount();
		return total;
	}
	
	public List<TsRole> fetchRoleByUserId(Long userId) {
		JPAQuery<TsRole> query = new JPAQuery<TsRole>(em);
		List<TsRole> list = query.from(qTsRole, qTsUser, qTsUserRole)
				.where(qTsRole.id.eq(qTsUserRole.roleId)
						.and(qTsUserRole.userId.eq(qTsUser.id))
						.and(qTsUser.id.eq(userId)))
				.orderBy(qTsRole.createTime.asc())
				.fetch();
		return list;
	}

	public List<SecuritySource> fetchSecuritySource() {
		List<SecuritySource> list = new ArrayList<SecuritySource>();
		//加载角色
		JPAQuery<TsRole> query = new JPAQuery<TsRole>(em);
		List<TsRole> roleList = query.from(qTsRole).fetch();
		
		if(roleList != null) {
			for(TsRole role : roleList) {
				//加载权限
				JPAQuery<TsResource> resQuery = new JPAQuery<TsResource>(em);
				List<TsResource> resList = resQuery.from(qTsResource, qTsRoleResource)
						.where(qTsRoleResource.roleId.eq(role.getId())
								.and(qTsResource.id.eq(qTsRoleResource.resourceId)))
						.fetch();
				if(resList != null) {
					for(TsResource res : resList) {
						SecuritySource source = new SecuritySource(res.getName(), role.getName());
						list.add(source);
					}
				}
			}
			
		}
		
		return list;
	}
}
