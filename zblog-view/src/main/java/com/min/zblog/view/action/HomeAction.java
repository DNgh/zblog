package com.min.zblog.view.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
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
	
	private List<ArticleInfo> articleList;
	
	private List<CategoryInfo>  categoryInfoList;
	
	private List<ArchiveInfo>  archiveInfoList;
	
	private List<TagInfo>  tagInfoList;
	
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
	
	public void fetchCommonData(){
		//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	this.archiveInfoList = archiveService.fetchArchiveInfo();
    	//标签
    	this.tagInfoList = tagService.fetchTagInfo();
    	//阅读排行
    	//文章
    	this.articleList = articleService.listAllArticles();
	}
}
