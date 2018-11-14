package com.min.zblog.admin.controller;

import java.util.Date;
import java.util.HashMap;
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
	CategoryService categoryservice;
	
	@RequestMapping("/queryPage")
    public ModelAndView queryPage(){
    	ModelAndView modelAndView =new ModelAndView("queryCategory");
    	
        return modelAndView;
    }
	
	@ResponseBody
	@RequestMapping("/query")
    public Map<String, Object> query(@RequestParam(value="page")Integer page, 
    		@RequestParam(value="limit")Integer limit,
    		@RequestParam(value="startDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd 00:00:00") Date startDate, 
    		@RequestParam(value="endDate",required=false) @DateTimeFormat(pattern = "yyyy-MM-dd 00:00:00")Date endDate, 
    		@RequestParam(value="available",required=false)Indicator available){
		Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put(Constants.START_DATE, startDate);
    	reqMap.put(Constants.END_DATE, endDate);
    	reqMap.put(Constants.AVAILABLE, available);
    	
    	PageInfo<CategoryInfo> pageInfo = categoryservice.queryCategoryByPage(limit, page, reqMap);
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
    	CategoryInfo categoryInfo = categoryservice.findOneCategory(id);
        modelAndView.addObject("categoryInfo", categoryInfo);
        
        return modelAndView;
    }
}
