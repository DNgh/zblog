package com.min.zblog.view.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.CategoryInfo;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	private List<TmArticle> articleList;
	
	private List<CategoryInfo>  categoryInfoList;
	
    public String home(){
    	//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	categoryService.
    	//标签
    	//阅读排行
    	//文章
    	this.articleList = articleService.listAll();
    	
    	return SUCCESS;
    }
    
    public String about(){
    	return SUCCESS;
    }
    
    public String contact(){
    	return SUCCESS;
    }

	public List<TmArticle> getArticleList() {
		return articleList;
	}
	
	public void setCategoryInfoList(List<TmArticle> articleList){
		for(TmArticle article:articleList){
			CategoryInfo info = new CategoryInfo();
//			info.setCategoryName(article.getCategoryId());
//			info.setArticleNum(articleNum);
//			categoryInfoList.add(e);
		}
	}
	
	public List<CategoryInfo> getCategoryInfoList() {
		return categoryInfoList;
	}
    
}
