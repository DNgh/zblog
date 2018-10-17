package com.min.zblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping("/home")
    public ModelAndView index(){
    	ModelAndView modelAndView =new ModelAndView("home");
//        modelAndView.addObject("hello", "access index success");
        return modelAndView;
    }
}
