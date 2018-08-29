package com.min.zblog.action;

import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
 
    public String execute() {
        return SUCCESS;
    }
    
    public String home(){
    	return SUCCESS;
    }
    
    public String about(){
    	return SUCCESS;
    }
    
    public String contact(){
    	return SUCCESS;
    }
}
