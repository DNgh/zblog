package com.min.zblog.view.action;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.service.ArticleService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	private TmArticle article;
	
	@Autowired
	private ArticleService articleService;
	
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
    
    public String markdown(){
    	return SUCCESS;
    }
    
    public TmArticle getArticle(){
    	return article;
    }
}
