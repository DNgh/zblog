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

import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.utils.Constants;

@Controller
@RequestMapping("/tag")
public class TagController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	TagService tagService;
	
	@RequestMapping("/queryPage")
    public ModelAndView queryPage(){
    	ModelAndView modelAndView =new ModelAndView("queryTag");
    	
        return modelAndView;
    }
	
	@ResponseBody
	@RequestMapping("/query")
    public Map<String, Object> query(@RequestParam(value="page")Integer page, 
    		@RequestParam(value="limit")Integer limit,
    		@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, 
    		@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
		Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put(Constants.START_DATE, startDate);
    	reqMap.put(Constants.END_DATE, endDate);
    	
    	PageInfo<TagInfo> pageInfo = tagService.queryTagByPage(limit, page, reqMap);
    	logger.debug("currentPage:"+pageInfo.getCurrentPage()+",totalPages:"+pageInfo.getTotalPages());
    	
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("count", pageInfo.getCount());
        map.put("data", pageInfo.getList());
        map.put("msg", "加载成功");
        return map;
    }
	
	@RequestMapping("/editorPage")
    public ModelAndView editorPage(@RequestParam(value="tagId")Long id){
    	ModelAndView modelAndView =new ModelAndView("editTag");
    	//加载标签
    	TagInfo tagInfo = tagService.findOneTag(id);
        modelAndView.addObject("tagInfo", tagInfo);
        
        return modelAndView;
    }
	
	@ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> saveCategory(
    		@RequestParam(value="tagId") Long tagId,
    		@RequestParam(value="name") String name,
    		@RequestParam(value="description") String description){

    	//保存到数据库
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("tagId", tagId);
    	reqMap.put("name", name); 
    	reqMap.put("description", description);
    	
    	//加载文章
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		tagService.saveTag(reqMap);
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
    	ModelAndView modelAndView =new ModelAndView("newTag");
        
        return modelAndView;
    }
	
	@ResponseBody
    @RequestMapping("/add")
    public Map<String, Object> addCategory(
    		@RequestParam(value="name") String name,
    		@RequestParam(value="description") String description){

    	//保存到数据库
		Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("name", name); 
    	reqMap.put("description", description);
    	
    	TmTag saveTag = tagService.addTag(reqMap);
    	
    	//返回json格式结果
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("success", true);
    	result.put("message", "");
    	result.put("tagId", saveTag.getId());
    	
        return result;
    }
	
	@ResponseBody
    @RequestMapping("/delete")
    public Map<String, Object> deleteTag(
    		@RequestParam(value="tagId") Long tagId){
    	Map<String, Object> result = new HashMap<String, Object>();
		try{
			tagService.deleteTagById(tagId);
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
