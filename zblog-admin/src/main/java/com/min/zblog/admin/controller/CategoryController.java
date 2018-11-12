package com.min.zblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@RequestMapping("/queryPage")
    public ModelAndView queryPage(){
    	ModelAndView modelAndView =new ModelAndView("queryCategory");
    	
        return modelAndView;
    }
}
