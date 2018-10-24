package com.min.zblog.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;

//import com.min.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
    TagService tagService;
	
	/**
	 * 分类信息
	 */
	private List<CategoryInfo>  categoryInfoList;
	
	/**
	 * 标签信息
	 */
	private List<TagInfo>  tagInfoList;
    
    @RequestMapping("/index")
    public ModelAndView index(){
    	ModelAndView modelAndView =new ModelAndView("index");
        modelAndView.addObject("hello", "access index success");
        return modelAndView;
    }
    
    @RequestMapping("/newPage")
    public ModelAndView newPage(){
    	ModelAndView modelAndView =new ModelAndView("newArticle");
    	//加载分类信息和标签信息
    	categoryInfoList = categoryservice.fetchCategoryInfo();
    	tagInfoList = tagService.fetchTagInfo();
    	
        modelAndView.addObject("categoryInfoList", categoryInfoList);
        modelAndView.addObject("tagInfoList", tagInfoList);
        
        return modelAndView;
    }
    
    @RequestMapping("/add")
    public ModelAndView addArticle(){
    	ModelAndView modelAndView =new ModelAndView("newArticle");
    	//加载分类信息和标签信息
    	categoryInfoList = categoryservice.fetchCategoryInfo();
    	tagInfoList = tagService.fetchTagInfo();
    	
        modelAndView.addObject("categoryInfoList", categoryInfoList);
        modelAndView.addObject("tagInfoList", tagInfoList);
        
        return modelAndView;
    }
    
    @RequestMapping("/editorPage")
    public ModelAndView editorPage(){
    	ModelAndView modelAndView =new ModelAndView("editArticle");
    	//加载分类信息和标签信息
    	categoryInfoList = categoryservice.fetchCategoryInfo();
    	tagInfoList = tagService.fetchTagInfo();
    	
        modelAndView.addObject("categoryInfoList", categoryInfoList);
        modelAndView.addObject("tagInfoList", tagInfoList);
        
        return modelAndView;
    }
}