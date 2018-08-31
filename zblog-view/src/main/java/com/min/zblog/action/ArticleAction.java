package com.min.zblog.action;

import com.min.zblog.entity.TmArticle;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
	private TmArticle article;
	
    public String execute() {
        return SUCCESS;
    }
    
    public String show(){
    	setArticle();
    	return SUCCESS;
    }
    
    public String markdown(){
    	return SUCCESS;
    }
    
    public void setArticle(){
    	article = new TmArticle();
    	article.setContent("- 加粗    `Ctrl + B` ");
    }
    
    public TmArticle getArticle(){
    	return article;
    }
}
