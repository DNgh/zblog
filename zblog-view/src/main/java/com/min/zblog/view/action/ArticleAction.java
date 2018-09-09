package com.min.zblog.view.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private TmArticle article;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ArchiveService  archiveService;
	
	@Autowired
	private TagService tagService;
	
	/**
	 * 右侧显示文章列表
	 */
	private List<ArticleInfo> articleList;
	
	/**
	 * 分类信息
	 */
	private List<CategoryInfo>  categoryInfoList;
	
	/**
	 * 归档信息
	 */
	private List<ArchiveInfo>  archiveInfoList;
	
	/**
	 * 标签信息
	 */
	private List<TagInfo>  tagInfoList;
	
	/**
	 * 文章阅读排行
	 */
	private List<ArticleInfo> articleRankList;
	
	/**
	 * 博客统计信息
	 */
	private BlogInfo blogInfo;
	
	private String categoryName;
	
	private String archiveName;
	
	private String tagName;
	
    public String show(){
    	fetchCommonData();
    	
    	//文章
    	ActionContext context = ActionContext.getContext();
    	Map<String, Object> map = context.getParameters();
    	String[] obj = (String[])map.get("articleKey");
    	logger.info("articleKey:"+obj[0]);
    	
    	//替换回车换行符，否则页面js脚本报语法错。
    	article = articleService.findOne(Long.valueOf(obj[0]));
    	article.setContent(article.getContent().replaceAll("\n", "\\\\n"));
    	article.setContent(article.getContent().replaceAll("\r", ""));
    	return SUCCESS;
    }
    
    public String listArticleByCategory() {
    	fetchCommonData();
    	
    	//文章
    	logger.info(this.categoryName);
    	this.articleList = articleService.listArticleByCategoryName(this.categoryName);
    	
    	return SUCCESS;
    }
    
    public String listArticleByArchive() {
    	fetchCommonData();
    	
    	//文章
    	this.articleList = articleService.listArticleByArchive(this.archiveName);
    	
    	return SUCCESS;
    }
    
    public String listArticleByTag(){
    	fetchCommonData();
    	
    	//文章
    	this.articleList = articleService.listArticleByTag(this.tagName);
    	
    	return SUCCESS;
    }
    
    public TmArticle getArticle(){
    	return article;
    }
    
    public List<ArticleInfo> getArticleList(){
    	return this.articleList;
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<CategoryInfo> getCategoryInfoList() {
		return this.categoryInfoList;
	}
	
	public List<ArchiveInfo> getArchiveInfoList() {
		return this.archiveInfoList;
	}
	
	public List<ArticleInfo> getArticleRankList() {
		return this.articleRankList;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	
	public List<TagInfo> getTagInfoList() {
		return this.tagInfoList;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public BlogInfo getBlogInfo(){
		return this.blogInfo;
	}
	
	public void fetchCommonData(){
		//博客统计信息
		this.blogInfo = articleService.obtainBlogInfo();
		//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	this.archiveInfoList = archiveService.fetchArchiveInfo();
    	//标签
    	this.tagInfoList = tagService.fetchTagInfo();
    	//阅读排行
    	this.articleRankList = articleService.listArticleByReadRank();
	}
	
}
