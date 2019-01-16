package com.min.zblog.admin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.min.zblog.api.rpc.CategoryService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.utils.Constants;

@Controller
@RequestMapping("/category")
public class CategoryController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping("/queryPage")
    public ModelAndView queryPage(){
    	ModelAndView modelAndView =new ModelAndView("queryCategory");
    	
        return modelAndView;
    }
	
	@ResponseBody
	@RequestMapping("/query")
    public Map<String, Object> query(@RequestParam(value="page")Integer page, 
    		@RequestParam(value="limit")Integer limit,
    		@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
    		@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate, 
    		@RequestParam(value="available",required=false)Indicator available){
		Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put(Constants.START_DATE, startDate);
    	reqMap.put(Constants.END_DATE, endDate);
    	reqMap.put(Constants.AVAILABLE, available);
    	
    	PageInfo<CategoryInfo> pageInfo = categoryService.queryCategoryByPage(limit, page, reqMap);
    	logger.debug("currentPage:"+pageInfo.getCurrentPage()+",totalPages:"+pageInfo.getTotalPages());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("count", pageInfo.getCount());
        map.put("data", pageInfo.getList());
        map.put("msg", "加载成功");
        return map;
    }
	
	@RequestMapping("/editorPage")
    public ModelAndView editorPage(@RequestParam(value="categoryId")Long id){
    	ModelAndView modelAndView =new ModelAndView("editCategory");
    	//加载文章
    	CategoryInfo categoryInfo = categoryService.findOneCategory(id);
        modelAndView.addObject("categoryInfo", categoryInfo);
        
        return modelAndView;
    }
	
	@ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> saveCategory(
    		@RequestParam(value="categoryId") Long categoryId,
    		@RequestParam(value="name") String name,
    		@RequestParam(value="description") String description,
    		@RequestParam(value="icon") String icon,
    		@RequestParam(value="available") Indicator available){

    	//保存到数据库
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("categoryId", categoryId);
    	reqMap.put("name", name); 
    	reqMap.put("description", description);
    	reqMap.put("icon", icon); 
    	reqMap.put("available", available);
    	
    	//加载文章
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		categoryService.saveCategory(reqMap);
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
	
	@RequestMapping("/newPage")
    public ModelAndView newPage(){
    	ModelAndView modelAndView =new ModelAndView("newCategory");
        
        return modelAndView;
    }
	
	@ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> addCategory(
    		@RequestParam(value="name") String name,
    		@RequestParam(value="description") String description,
    		@RequestParam(value="icon") String icon,
    		@RequestParam(value="available") Indicator available){

    	//保存到数据库
		Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("name", name); 
    	reqMap.put("description", description);
    	reqMap.put("icon", icon); 
    	reqMap.put("available", available);
    	
    	TmCategory saveCategory = categoryService.addCategory(reqMap);
    	
    	//返回json格式结果
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", true);
    	result.put("message", "");
    	result.put("categoryId", saveCategory.getId());
    	
        return result;
    }
	
	@ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> deleteCategory(
    		@RequestParam(value="categoryId") Long categoryId){
    	Map<String, Object> result = new HashMap<String, Object>();
		try{
			categoryService.deleteCategoryById(categoryId);
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
