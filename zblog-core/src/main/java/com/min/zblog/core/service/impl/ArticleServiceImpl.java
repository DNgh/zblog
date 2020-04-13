package com.min.zblog.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.ArchiveDao;
import com.min.zblog.core.dao.ArticleDao;
import com.min.zblog.core.dao.ArticleTagDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.dao.CommentDao;
import com.min.zblog.core.dao.VisitHstDao;
import com.min.zblog.core.facility.GlobalContextHolder;
import com.min.zblog.api.rpc.ArticleService;
import com.min.zblog.data.entity.TmArchive;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmArticleTagKey;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;
import com.min.zblog.facility.exception.ProcessException;
import com.min.zblog.facility.utils.Constants;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private VisitHstDao visitHstDao;
	@Autowired
	private ArticleTagDao articleTagDao;
	@Autowired
	private CategoryDao categoryDao;
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	@Autowired
	private ArchiveDao archiveDao;
	@Autowired
	private CommentDao commentDao;
	
	public List<TmArticle> listAll() {
		return articleDao.findAll();
	}
	
	public TmArticle findOne(Long id) {
		return articleDao.findOne(id);
	}
	
	public TmArticle addArticle(Map<String, Object> map) {
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String archiveTitle = sdf.format(time);
		String archiveName = archiveTitle.replace("-", "");
		
		//归档
		TmArchive archive = archiveDao.findByName(archiveName);
		Long archiveId;
		if(archive == null){
			int articleCount = 0;
			if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
				articleCount = 1;
			}
			TmArchive newArchive = new TmArchive(archiveName, archiveTitle, articleCount, time, time, 0);
			archiveDao.save(newArchive);
			archiveId = newArchive.getId();
			//更新全局变量
			ArchiveInfo archiveInfo = new ArchiveInfo();
			archiveInfo.setId(newArchive.getId());
			archiveInfo.setArchiveTitle(newArchive.getTitle());
			archiveInfo.setArchiveName(newArchive.getName());
			archiveInfo.setArticleNum(newArchive.getCount());
			archiveInfo.setCreateTime(newArchive.getCreateTime());
			GlobalContextHolder.addArchiveInfo(archiveInfo);
		}else{
			archiveId = archive.getId();
			if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
				archive.setCount(archive.getCount()+1);
				archive.setUpdateTime(time);
				archiveDao.save(archive);
				//更新全局变量
				GlobalContextHolder.addOneArchiveInfoArticleNum(archiveId);
			}
		}
		
		TmArticle article = new TmArticle();
		article.setUserId(Long.valueOf(1));//默认用户1
		article.setTitle((String)map.get("title"));
		article.setDescription((String)map.get("description"));
		article.setTop((Indicator)map.get("top"));
		article.setRecommend((Indicator)map.get("recommend"));
    	article.setOriginal((Indicator)map.get("original"));
    	article.setComment((Indicator)map.get("comment"));
    	article.setCategoryId((Long)map.get("categoryId"));
    	article.setContent((String)map.get("markdown"));
    	article.setState((ArticleState)map.get("state"));
    	article.setArchiveId(archiveId);//归档id
    	article.setCreateTime(time);
    	article.setUpdateTime(time);
    	article.setJpaVersion(0);
    	
		articleDao.save(article);
		
		//更新文章数
		if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
			GlobalContextHolder.addOneBlogInfoArticleNum();
		}
		
		//更新标签
		List<Long> tagIdList = (List<Long>)map.get("tagIdList");
		if(tagIdList != null){
			for(Long tagId:tagIdList){
				//保存关联表tm_article_tag
				TmArticleTag articleTag = new TmArticleTag(tagId, article.getId(), time, time, 0);
				articleTagDao.save(articleTag);
			}
		}
		
		//更新分类
		//getOne委托给JPA实体管理器,返回实体引用，导致LazyInitializationException，建议使用findOne
		if((ArticleState)map.get("state") == ArticleState.PUBLISH) {
			TmCategory category = categoryDao.findOne((Long)map.get("categoryId"));
			if(category != null){
				category.setCount(category.getCount()+1);
				category.setUpdateTime(time);
				categoryDao.save(category);
				//更新全局变量
				GlobalContextHolder.addOneCategoryInfoArticleNum(category.getId());
			}
		}
		
		return article;
	}
	
	/* 
	 * 硬删除，即删除数据库数据。
	 * 如果要硬删除，则当前状态必须是DELETE状态，即已经软删除进入回收箱。否则，拒绝。
	 * 状态转换：CREATE->DELETE->硬删除, PUBLISH->DELETE->硬删除
	 * 如果进入硬删除，则当前状态是DELETE，说明已经软删除及更新内存及数据库统计信息：
	 *    文章数、阅读数、评论数、分类数、归档数、阅读排行文章。
	 * 那么只需要删除数据库数据：访问历史、标签关联数据、评论、文章。
	 */
	public void deleteArticleById(Long id) {
		TmArticle article = articleDao.findOne(id);
		if(article == null){
			throw new ProcessException(Constants.ERRA001_CODE, Constants.ERRA001_MSG);
		}
		if(article.getState() != ArticleState.DELETE){
			throw new ProcessException(Constants.ERRA003_CODE, Constants.ERRA003_MSG);
		}
		
		//删除访问历史
		visitHstDao.deleteTmVisitHstByArticleId(id);
		//删除标签关联记录
		articleTagDao.deleteTmArticleTagByArticleId(id);
		//删除评论
		commentDao.deleteTmCommentByArticleId(id);
		//删除文章
		articleDao.delete(id);
	}
	
	/* 
	 * 软删除
	 * 1、更新已发布文章关联信息
	 * 更新内存数据：文章数、阅读数、评论数、分类数、归档数、阅读排行文章
	 * 更新数据库数据：分类数、归档数、文章状态
	 * 因为软删除，所以不用更新标签、评论、访问历史
	 * 2、更新已创建未发布的文章：不关联其它统计信息，只更新状态
	 * 3、更新已软删除的文章：不关联其它统计信息，只更新状态
	 * @see com.min.zblog.api.rpc.ArticleService#deleteArticleVirtualById(java.lang.Long)
	 */
	@Override
	public void deleteArticleVirtualById(Long articleId) throws ProcessException {
		TmArticle article = articleDao.findOne(articleId);
		if(article == null){
			throw new ProcessException(Constants.ERRA001_CODE, Constants.ERRA001_MSG);
		}
		if(article.getState() == ArticleState.DELETE){
			throw new ProcessException(Constants.ERRA002_CODE, Constants.ERRA002_MSG);
		}
		
		//更新已发布文章关联信息
		if(article.getState() == ArticleState.PUBLISH) {
			//更新文章数
			GlobalContextHolder.substractOneBlogInfoArticleNum();
			//更新阅读数
			long visitNum = blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ);
			GlobalContextHolder.substractBlogInfoReadNum(visitNum);
			//更新评论数
			long commentNum = commentDao.countByArticleId(article.getId());
			GlobalContextHolder.substractBlogInfoCommentNum(commentNum);
			
			//更新分类数
			TmCategory category = categoryDao.findOne(article.getCategoryId());
			if(category != null && category.getCount() != null){
				Integer count = category.getCount()-1;
				category.setCount(count<0?0:count);
				categoryDao.save(category);
				//更新全局变量
				GlobalContextHolder.substractOneCategoryInfoArticleNum(category.getId());
			}
			//更新归档数
			TmArchive archive = archiveDao.findOne(article.getArchiveId());
			if(archive != null && archive.getCount() != null){
				Integer count = archive.getCount()-1;
				archive.setCount(count<0?0:count);
				archiveDao.save(archive);
				//更新全局变量
				GlobalContextHolder.substractOneArchiveInfoArticleNum(archive.getId());
			}
			//更新文章状态
			article.setState(ArticleState.DELETE);
			articleDao.save(article);
			//更新阅读排行文章
			initArticleReadRank();
		}else{
			//更新文章状态
			article.setState(ArticleState.DELETE);
			articleDao.save(article);
		}
	}
	
	/* 
	 * 直接删除，即删除数据库文章及关联数据，不可恢复。
	 * 1、删除已发布文章关联信息
	 * 更新内存数据：文章数、阅读数、评论数、分类数、归档数、阅读排行文章
	 * 更新数据库数据：分类数、归档数
	 * 删除数据库数据：访问历史、标签关联数据、评论、文章。
	 * 2、删除已创建未发布的文章
	 * 不关联其它统计信息，删除数据库数据：访问历史、标签关联数据、评论、文章。
	 * 3、删除已软删除的文章
	 * 不关联其它统计信息，删除数据库数据：访问历史、标签关联数据、评论、文章。
	 * @see com.min.zblog.api.rpc.ArticleService#deleteArticleDirectById(java.lang.Long)
	 */
	@Override
	public void deleteArticleDirectById(Long articleId) throws ProcessException {
		TmArticle article = articleDao.findOne(articleId);
		if(article == null){
			throw new ProcessException(Constants.ERRA001_CODE, Constants.ERRA001_MSG);
		}
		
		//删除已发布文章关联信息
		if(article.getState() == ArticleState.PUBLISH) {
			//更新文章数
			GlobalContextHolder.substractOneBlogInfoArticleNum();
			//更新阅读数
			long visitNum = blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ);
			GlobalContextHolder.substractBlogInfoReadNum(visitNum);
			//更新评论数
			long commentNum = commentDao.countByArticleId(article.getId());
			GlobalContextHolder.substractBlogInfoCommentNum(commentNum);
			
			//更新分类数
			TmCategory category = categoryDao.findOne(article.getCategoryId());
			if(category != null && category.getCount() != null){
				Integer count = category.getCount()-1;
				category.setCount(count<0?0:count);
				categoryDao.save(category);
				//更新全局变量
				GlobalContextHolder.substractOneCategoryInfoArticleNum(category.getId());
			}
			//更新归档数
			TmArchive archive = archiveDao.findOne(article.getArchiveId());
			if(archive != null && archive.getCount() != null){
				Integer count = archive.getCount()-1;
				archive.setCount(count<0?0:count);
				archiveDao.save(archive);
				//更新全局变量
				GlobalContextHolder.substractOneArchiveInfoArticleNum(archive.getId());
			}
			//删除访问历史
			visitHstDao.deleteTmVisitHstByArticleId(articleId);
			//删除标签关联记录
			articleTagDao.deleteTmArticleTagByArticleId(articleId);
			//删除评论
			commentDao.deleteTmCommentByArticleId(articleId);
			//删除文章
			articleDao.delete(articleId);
			//更新阅读排行文章
			initArticleReadRank();
		}else {//删除已创建未发布文章、已软删除文章
			//删除访问历史
			visitHstDao.deleteTmVisitHstByArticleId(articleId);
			//删除标签关联记录
			articleTagDao.deleteTmArticleTagByArticleId(articleId);
			//删除评论
			commentDao.deleteTmCommentByArticleId(articleId);
			//删除文章
			articleDao.delete(articleId);
		}
		
	}
	
	@Override
	//@Cacheable(value="articleByCategoryNameCache", key="T(String).valueOf(#pageSize).concat('-').concat(#currentPage).concat('-').concat(#name)")
	//数据变动，及时刷新缓存，否则数据异常。
	public PageInfo<ArticleInfo> listArticleByPageCategoryName(long pageSize, long currentPage, String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		//根据分类名称，查找已经发布文章
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPageCategoryName(pageSize, currentPage, name, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArticleByCategoryName(name, ArticleState.PUBLISH);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}

	@Override
	public List<ArticleInfo> listAllArticles() {
		//查找已经发布文章
		List<TmArticle> tmArticleList = blogQueryDsl.fetchAllArticles(ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setId(article.getId());
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setTitle(article.getTitle());
			articleInfoList.add(articleInfo);
		}

		return articleInfoList;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.api.rpc.ArticleService#listArticleByArchive(java.lang.String)
	 */
	@Override
	//@Cacheable(value="articleByArchiveCache", key="T(String).valueOf(#pageSize).concat('-').concat(#currentPage).concat('-').concat(#name)")
	//数据变动，及时刷新缓存，否则数据异常。
	public PageInfo<ArticleInfo> listArticleByPageArchive(long pageSize, long currentPage, String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		//根据分类名称，查找已经发布文章
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPageArchive(pageSize, currentPage, name, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArticleByArchive(name, ArticleState.PUBLISH);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}
	//fetchArticleByTag

	@Override
	//@Cacheable(value="articleByTagCache", key="T(String).valueOf(#pageSize).concat('-').concat(#currentPage).concat('-').concat(#name)")
	//数据变动，及时刷新缓存，否则数据异常。
	public PageInfo<ArticleInfo> listArticleByPageTag(long pageSize, long currentPage, String name) {
		if(StringUtils.isBlank(name)) {
			return null;
		}
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPageTag(pageSize, currentPage, name, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArticleByTag(name, ArticleState.PUBLISH);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}

	/* 查询已发布且阅读排行前5的文章
	 * @see com.min.zblog.api.rpc.ArticleService#listArticleByReadRank()
	 */
	@Override
	public List<ArticleInfo> listArticleByReadRank() {
		return GlobalContextHolder.getArticleReadRankList();
	}

	/* 获取博客统计信息
	 * @see com.min.zblog.api.rpc.ArticleService#obtainBlogInfo()
	 */
	@Override
	public BlogInfo obtainBlogInfo() {
		return GlobalContextHolder.getBlogInfo();
	}

	@Override
	//@Cacheable(value="articleByPageCache", key="T(String).valueOf(#pageSize).concat('-').concat(#currentPage).concat('-').concat(#state.name())")
	//数据变动，及时刷新缓存，否则数据异常。
	public PageInfo<ArticleInfo> listArticleByPage(long pageSize, long currentPage, ArticleState state) {
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPage(currentPage, pageSize, state);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = articleDao.countByState(state);
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}
	
	/**
	 * 根据查询条件，统计分页数
	 */
	@Override
	public PageInfo<ArticleInfo> queryArticleByPage(long pageSize, long currentPage, Map<String, Object> map) {
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleConditionByPage(currentPage, pageSize, map);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArticleByCondition(map);
		
		pageInfo.setCount(total);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}

	/**
	 * 查找一篇指定文章
	 */
	@Override
	public ArticleInfo findOneArticle(Long id) {
		ArticleInfo articleInfo = new ArticleInfo();
		
		TmArticle article = articleDao.findOne(id);
		if(article != null){
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setContent(article.getContent());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setFavorNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.FAVOR));
			articleInfo.setShareNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.SHARE));
			articleInfo.setTop(article.getTop() == Indicator.Y?true:false);
			articleInfo.setRecommend(article.getRecommend() == Indicator.Y?true:false);
			articleInfo.setOriginal(article.getOriginal() == Indicator.Y?true:false);
			articleInfo.setComment(article.getComment() == Indicator.Y?true:false);
			TmCategory category = categoryDao.findOne(article.getCategoryId());
			if(category != null){
				articleInfo.setCategoryId(category.getId());
				articleInfo.setCategoryName(category.getName());
			}
			List<TmTag> tmTagList = blogQueryDsl.fetchTagByArticleId(article.getId());
			List<String> list = new ArrayList<String>();
			List<Long> tagIdList = new ArrayList<Long>();
			for(TmTag tag:tmTagList){
				list.add(tag.getName());
				tagIdList.add(tag.getId());
			}
			articleInfo.setTagList(list);
			articleInfo.setTagIdList(tagIdList);
		}
		
		return articleInfo;
	}

	@Override
	public ArticleInfo findPreOneArticle(Long id) {
		TmArticle article = blogQueryDsl.fetchFirstArticleByIdNear(id, ArticleState.PUBLISH, Indicator.Y);
		ArticleInfo articleInfo = new ArticleInfo();
		if(article != null){
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setContent(article.getContent());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setFavorNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.FAVOR));
			articleInfo.setShareNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.SHARE));
			TmCategory category = categoryDao.findOne(article.getCategoryId());
			if(category != null){
				articleInfo.setCategoryName(category.getName());
			}
			List<TmTag> tmTagList = blogQueryDsl.fetchTagByArticleId(article.getId());
			List<String> list = new ArrayList<String>();
			for(TmTag tag:tmTagList){
				list.add(tag.getName());
			}
			articleInfo.setTagList(list);
		}
		return articleInfo;
	}

	@Override
	public ArticleInfo findNextOneArticle(Long id) {
		TmArticle article = blogQueryDsl.fetchFirstArticleByIdNear(id, ArticleState.PUBLISH, Indicator.N);
		ArticleInfo articleInfo = new ArticleInfo();
		if(article != null){
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setContent(article.getContent());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setFavorNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.FAVOR));
			articleInfo.setShareNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.SHARE));
			TmCategory category = categoryDao.findOne(article.getCategoryId());
			if(category != null){
				articleInfo.setCategoryName(category.getName());
			}
			List<TmTag> tmTagList = blogQueryDsl.fetchTagByArticleId(article.getId());
			List<String> list = new ArrayList<String>();
			for(TmTag tag:tmTagList){
				list.add(tag.getName());
			}
			articleInfo.setTagList(list);
		}
		return articleInfo;
	}

	/**
	 * 更新文章(标题、内容、状态。。。)
	 */
	@Override
	public TmArticle saveArticle(Map<String, Object> map) throws ProcessException {
		TmArticle article = articleDao.findOne((Long)map.get("articleId"));
		if(article == null){
			throw new ProcessException(Constants.ERRA001_CODE, Constants.ERRA001_MSG);
		}
		
		Date time = new Date();
		ArticleState originState = article.getState();
		Long originCategoryId = article.getCategoryId();
		article.setTitle((String)map.get("title"));
		article.setDescription((String)map.get("description"));
		article.setTop((Indicator)map.get("top"));
		article.setRecommend((Indicator)map.get("recommend"));
    	article.setOriginal((Indicator)map.get("original"));
    	article.setComment((Indicator)map.get("comment"));
    	article.setCategoryId((Long)map.get("categoryId"));
    	article.setContent((String)map.get("markdown"));
    	if(ArticleState.CREATE == article.getState()){
    		//只有CREATE PUBLISH状态才能编辑
    		//此方法状态转换CREATE->CREATE,PUBLISH->PUBLISH,CREATE->PUBLISH
    		article.setState((ArticleState)map.get("state"));
    	}
    	article.setUpdateTime(time);
//    	article.setJpaVersion(0);
    	
		articleDao.save(article);
		
		//更新标签
		//先删除原先关联标签，然后插入新关联标签
		articleTagDao.deleteTmArticleTagByArticleId(article.getId());
		List<Long> tagIdList = (List<Long>)map.get("tagIdList");
		if(tagIdList != null){
			for(Long tagId:tagIdList){
				//保存关联表tm_article_tag
				TmArticleTag articleTag = new TmArticleTag(tagId, article.getId(), time, time, 0);
				articleTagDao.save(articleTag);
			}
		}
		
		//更新文章数、所属分类文章数、所属归档文章数
		//getOne委托给JPA实体管理器,返回实体引用，导致LazyInitializationException，建议使用findOne
		TmCategory category = categoryDao.findOne((Long)map.get("categoryId"));
		if(originState == ArticleState.CREATE && (ArticleState)map.get("state") == ArticleState.PUBLISH) {
			//文章数
			GlobalContextHolder.addOneBlogInfoArticleNum();
			//所属分类文章数
			if(category != null){
				category.setCount(category.getCount()+1);
				category.setUpdateTime(time);
				categoryDao.save(category);
				//更新全局变量
				GlobalContextHolder.addOneCategoryInfoArticleNum(category.getId());
			}
			//所属归档文章数
			GlobalContextHolder.addOneArchiveInfoArticleNum(article.getArchiveId());
			//阅读排行
			initArticleReadRank();
		} else if(originState == ArticleState.PUBLISH && (ArticleState)map.get("state") == ArticleState.PUBLISH) {
			//先原先关联分类-1，然后新关联分类+1
			//更新全局变量
			if(category != null){
				if(!originCategoryId.equals(category.getId())) {
					TmCategory originCategory = categoryDao.findOne(originCategoryId);
					originCategory.setCount(originCategory.getCount()-1);
					originCategory.setUpdateTime(time);
					categoryDao.save(originCategory);
					
					category.setCount(category.getCount()+1);
					category.setUpdateTime(time);
					categoryDao.save(category);
					
					GlobalContextHolder.substractOneCategoryInfoArticleNum(originCategoryId);
					GlobalContextHolder.addOneCategoryInfoArticleNum(category.getId());
				}
			}
		}
		
		return article;
	}
	
	@Override
	public PageInfo<ArticleInfo> listArticleByPageCategoryId(long pageSize, long currentPage, Long id) {
		if(id == null) {
			return null;
		}
		//根据分类名称，查找已经发布文章
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPageCategoryId(pageSize, currentPage, id, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setCreateTime(article.getCreateTime());
			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			
			articleInfoList.add(articleInfo);
		}
		
		PageInfo<ArticleInfo> pageInfo = new PageInfo<ArticleInfo>();
		pageInfo.setCurrentPage(currentPage);
		long total = blogQueryDsl.countArticleByCategoryId(id, ArticleState.PUBLISH);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}

	@Override
	public void initBlogInfo() {
		BlogInfo blogInfo = new BlogInfo();
		blogInfo.setTotalArticleNum(articleDao.countByState(ArticleState.PUBLISH));
		blogInfo.setTotalReadNum(blogQueryDsl.countVisitHstByType(VisitType.READ, ArticleState.PUBLISH));
		blogInfo.setTotalCommentNum(blogQueryDsl.countCommentByState(ArticleState.PUBLISH));
		//缓存
		GlobalContextHolder.setBlogInfo(blogInfo);
	}

	@Override
	public void initArticleReadRank() {
		List<TmArticle> tmArticleList = blogQueryDsl.fetchTopArticleByHst(5, VisitType.READ, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
			articleInfo.setId(article.getId());
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setTitle(article.getTitle());
			articleInfoList.add(articleInfo);
		}
		//缓存
		GlobalContextHolder.setArticleReadRankList(articleInfoList);
	}

	/* 
	 * 重新发布，已删除文章存在如下情况：
	 * 1、前一状态是已发布文章
	 * 更新内存数据：文章数、阅读数、评论数、分类数、归档数、阅读排行文章
	 * 更新数据库数据：分类数、归档数、文章状态
	 * 因为重新发布已回收文章，所以不用更新标签、评论、访问历史。
	 * 2、前一状态是已创建未发布的文章：不关联其它统计信息，只更新状态。
	 * 
	 * DELETE->PUBLISH，只需更新数据，不用考虑之前状态。
	 * @see com.min.zblog.api.rpc.ArticleService#republishArticleById(java.lang.Long)
	 */
	@Override
	public void republishArticleById(Long articleId) {
		TmArticle article = articleDao.findOne(articleId);
		if(article == null){
			throw new ProcessException(Constants.ERRA001_CODE, Constants.ERRA001_MSG);
		}
		if(article.getState() != ArticleState.DELETE){
			throw new ProcessException(Constants.ERRA004_CODE, Constants.ERRA004_MSG);
		}
		
		//更新文章关联信息
		//更新文章数
		GlobalContextHolder.addOneBlogInfoArticleNum();
		//更新阅读数
		long visitNum = blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ);
		GlobalContextHolder.addBlogInfoReadNum(visitNum);
		//更新评论数
		long commentNum = commentDao.countByArticleId(article.getId());
		GlobalContextHolder.addBlogInfoCommentNum(commentNum);
		
		//更新分类数
		TmCategory category = categoryDao.findOne(article.getCategoryId());
		if(category != null && category.getCount() != null){
			Integer count = category.getCount()+1;
			category.setCount(count<0?0:count);
			categoryDao.save(category);
			//更新全局变量
			GlobalContextHolder.addOneCategoryInfoArticleNum(category.getId());
		}
		//更新归档数
		TmArchive archive = archiveDao.findOne(article.getArchiveId());
		if(archive != null && archive.getCount() != null){
			Integer count = archive.getCount()+1;
			archive.setCount(count<0?0:count);
			archiveDao.save(archive);
			//更新全局变量
			GlobalContextHolder.addOneArchiveInfoArticleNum(archive.getId());
		}
		//更新文章状态
		article.setState(ArticleState.PUBLISH);
		articleDao.save(article);
		//更新阅读排行文章
		initArticleReadRank();
	}
}
