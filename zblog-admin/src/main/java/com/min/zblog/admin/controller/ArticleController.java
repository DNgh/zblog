package com.min.zblog.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	Logger logger = LoggerFactory.getLogger(getClass());
	
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
    
    @ResponseBody
    @RequestMapping("/add")
    public Map addArticle(
    		@RequestParam(value="title") String title, @RequestParam(value="description") String description,
    		@RequestParam(value="top") boolean top, @RequestParam(value="recommend") boolean recommend,
    		@RequestParam(value="original") boolean original, @RequestParam(value="comment") boolean comment,
    		@RequestParam(value="categoryId") Long categoryId, @RequestParam(value="tagIdList") List<Long> tagIdList,
    		@RequestParam(value="markdown") String markdown, @RequestParam(value="state") String state){
    	logger.debug("title:"+title+",top:"+top+",tagIdList:"+tagIdList);
    	//保存到数据库
    	//返回json格式结果
    	
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", true);
    	result.put("message", "");
    	
        return result;
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