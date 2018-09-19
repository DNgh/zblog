package com.min.zblog.view.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
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
	
    public String home(){
    	fetchCommonData();
    	
    	return SUCCESS;
    }
    
    public String about(){
    	fetchCommonData();
    	
    	return SUCCESS;
    }
    
    public String contact(){
    	fetchCommonData();
    	
    	return SUCCESS;
    }
    
    public String test(){
    	
    	return SUCCESS;
    }

	public List<ArticleInfo> getArticleList() {
		return articleList;
	}
	
	public List<CategoryInfo> getCategoryInfoList() {
		return categoryInfoList;
	}
    
	public List<ArchiveInfo> getArchiveInfoList() {
		return this.archiveInfoList;
	}
	
	public List<TagInfo> getTagInfoList() {
		return this.tagInfoList;
	}
	
	public List<ArticleInfo> getArticleRankList() {
		return this.articleRankList;
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
    	//文章
    	this.articleList = articleService.listAllArticles();
	}
}
