package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.core.dao.ArticleDao;
import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.core.dao.ArticleTagDao;
import com.min.zblog.core.dao.CategoryDao;
import com.min.zblog.core.dao.TagDao;
import com.min.zblog.core.dao.VisitHstDao;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.enums.VisitType;

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
		blogQueryDsl.deleteVisitHstByArticleId(article.getId());
		//删除标签关联记录
		blogQueryDsl.deleteVisitHstByArticleId(article.getId());
		//删除评论
	}

	@Override
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
	 * @see com.min.zblog.core.service.ArticleService#listArticleByArchive(java.lang.String)
	 */
	@Override
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
	 * @see com.min.zblog.core.service.ArticleService#listArticleByReadRank()
	 */
	@Override
	public List<ArticleInfo> listArticleByReadRank() {
		List<TmArticle> tmArticleList = blogQueryDsl.fetchTopArticleByHst(5, VisitType.READ, ArticleState.PUBLISH);
		List<ArticleInfo> articleInfoList = new ArrayList<ArticleInfo>();
		for(TmArticle article:tmArticleList){
			
			ArticleInfo articleInfo = new ArticleInfo();
//			articleInfo.setCommentNum(blogQueryDsl.countCommentByArticleId(article.getId()));
//			articleInfo.setCreateTime(article.getCreateTime());
//			articleInfo.setDescription(article.getDescription());
			articleInfo.setId(article.getId());
			articleInfo.setReadNum(blogQueryDsl.countVisitHstByArticleId(article.getId(), VisitType.READ));
			articleInfo.setTitle(article.getTitle());
			articleInfoList.add(articleInfo);
		}
		return articleInfoList;
	}

	/* 获取博客统计信息
	 * @see com.min.zblog.core.service.ArticleService#obtainBlogInfo()
	 */
	@Override
	public BlogInfo obtainBlogInfo() {
		BlogInfo blogInfo = new BlogInfo();
		blogInfo.setTotalArticleNum(articleDao.countByState(ArticleState.PUBLISH));
		blogInfo.setTotalReadNum(blogQueryDsl.countVisitHstByType(VisitType.READ, ArticleState.PUBLISH));
		blogInfo.setTotalCommentNum(blogQueryDsl.countCommentByState(ArticleState.PUBLISH));
		
		return blogInfo;
	}

	@Override
	public PageInfo<ArticleInfo> listArticleByPage(long pageSize, long currentPage) {
		List<TmArticle> tmArticleList = blogQueryDsl.fetchArticleByPage(currentPage, pageSize, ArticleState.PUBLISH);
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
		long total = articleDao.countByState(ArticleState.PUBLISH);
		pageInfo.setTotalPages((total%pageSize == 0)?(total/pageSize):((total/pageSize)+1));
		pageInfo.setList(articleInfoList);
		
		return pageInfo;
	}

	@Override
	public ArticleInfo findOneArticle(Long id) {
		ArticleInfo articleInfo = new ArticleInfo();
		
		TmArticle article = articleDao.findOne(id);
		if(article != null){
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setContent(article.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
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
	public ArticleInfo findPreOneArticle(Long id) {
		TmArticle article = blogQueryDsl.fetchFirstArticleByIdNear(id, ArticleState.PUBLISH, Indicator.Y);
		ArticleInfo articleInfo = new ArticleInfo();
		if(article != null){
			articleInfo.setId(article.getId());
			articleInfo.setTitle(article.getTitle());
			articleInfo.setDescription(article.getDescription());
			articleInfo.setContent(article.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
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
			articleInfo.setContent(article.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
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
}
