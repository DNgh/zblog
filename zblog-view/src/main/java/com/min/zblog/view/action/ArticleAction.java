package com.min.zblog.view.action;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
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
	
	private List<ArticleInfo> articleList;
	
	private List<CategoryInfo>  categoryInfoList;
	
	private List<ArchiveInfo>  archiveInfoList;
	
	private String categoryName;
	
	private String archiveName;
	
    public String show(){
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
    	//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	this.archiveInfoList = archiveService.fetchArchiveInfo();
    	//标签
    	//阅读排行
    	//文章
    	logger.info(this.categoryName);
    	this.articleList = articleService.listArticleByCategoryName(this.categoryName);
    	
    	return SUCCESS;
    }
    
    public String listArticleByArchive() {
    	//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	this.archiveInfoList = archiveService.fetchArchiveInfo();
    	//标签
    	//阅读排行
    	//文章
    	this.articleList = articleService.listArticleByArchive(this.archiveName);
    	
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

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
    
}
