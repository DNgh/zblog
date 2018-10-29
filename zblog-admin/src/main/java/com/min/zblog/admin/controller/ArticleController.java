package com.min.zblog.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;

//import com.min.service.ArticleService;

@Controller
@RequestMapping("/article")
public class ArticleController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CategoryService categoryservice;
	
	@Autowired
    TagService tagService;
	
	@Autowired
    ArticleService articleService;
	
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
    public Map<String, Object> addArticle(
    		@RequestParam(value="title") String title, @RequestParam(value="description") String description,
    		@RequestParam(value="top") boolean top, @RequestParam(value="recommend") boolean recommend,
    		@RequestParam(value="original") boolean original, @RequestParam(value="comment") boolean comment,
    		@RequestParam(value="categoryId") Long categoryId, @RequestParam(value="tagIdList") List<Long> tagIdList,
    		@RequestParam(value="markdown") String markdown, @RequestParam(value="state") String state){

    	//保存到数据库
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("title", title); 
    	reqMap.put("description", description);
    	reqMap.put("top", top?Indicator.Y:Indicator.N); 
    	reqMap.put("recommend", recommend?Indicator.Y:Indicator.N);
    	reqMap.put("original", original?Indicator.Y:Indicator.N); 
    	reqMap.put("comment", comment?Indicator.Y:Indicator.N);
    	reqMap.put("categoryId", categoryId); 
    	reqMap.put("tagIdList", tagIdList);
    	reqMap.put("markdown", markdown); 
    	if(StringUtils.isNotBlank(state)){
    		reqMap.put("state", ArticleState.valueOf(state));
    	}else{
    		reqMap.put("state", ArticleState.CREATE);
    	}
    	
    	TmArticle saveArticle = articleService.addArticle(reqMap);
    	
    	//返回json格式结果
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", true);
    	result.put("message", "");
    	result.put("articleId", saveArticle.getId());
    	
        return result;
    }
    
    @RequestMapping("/editorPage")
    public ModelAndView editorPage(@RequestParam(value="articleId")Long id){
    	ModelAndView modelAndView =new ModelAndView("editArticle");
    	//加载文章
    	ArticleInfo articleInfo = articleService.findOneArticle(id);
    	categoryInfoList = categoryservice.fetchCategoryInfo();
    	tagInfoList = tagService.fetchTagInfo();
    	
        modelAndView.addObject("categoryInfoList", categoryInfoList);
        modelAndView.addObject("tagInfoList", tagInfoList);
        modelAndView.addObject("articleInfo", articleInfo);
        
        return modelAndView;
    }
    
    @ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> saveArticle(
    		@RequestParam(value="articleId") Long articleId,
    		@RequestParam(value="title") String title, @RequestParam(value="description") String description,
    		@RequestParam(value="top") boolean top, @RequestParam(value="recommend") boolean recommend,
    		@RequestParam(value="original") boolean original, @RequestParam(value="comment") boolean comment,
    		@RequestParam(value="categoryId") Long categoryId, @RequestParam(value="tagIdList") List<Long> tagIdList,
    		@RequestParam(value="markdown") String markdown, @RequestParam(value="state") String state){

    	//保存到数据库
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("articleId", articleId);
    	reqMap.put("title", title); 
    	reqMap.put("description", description);
    	reqMap.put("top", top?Indicator.Y:Indicator.N); 
    	reqMap.put("recommend", recommend?Indicator.Y:Indicator.N);
    	reqMap.put("original", original?Indicator.Y:Indicator.N); 
    	reqMap.put("comment", comment?Indicator.Y:Indicator.N);
    	reqMap.put("categoryId", categoryId); 
    	reqMap.put("tagIdList", tagIdList);
    	reqMap.put("markdown", markdown); 
    	if(StringUtils.isNotBlank(state)){
    		reqMap.put("state", ArticleState.valueOf(state));
    	}else{
    		reqMap.put("state", ArticleState.CREATE);
    	}
    	
    	//加载文章
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		articleService.saveArticle(reqMap);
    		//返回json格式结果
        	result.put("success", true);
        	result.put("message", "");
    	}catch(Exception e){
    		//返回json格式结果
        	result.put("success", false);
        	result.put("message", e.getMessage());
    	}
    	
        return result;
    }
}