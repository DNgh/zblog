package com.min.zblog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping({"/","/home"})
    public ModelAndView home(){
    	ModelAndView modelAndView =new ModelAndView("home");
//        modelAndView.addObject("hello", "access index success");
        return modelAndView;
    }
	
	@RequestMapping("/loginPage")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}
	
	@RequestMapping("/admin")
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView("adminPage");
		return modelAndView;
	}
	
	@RequestMapping("/accessDenied")
	public ModelAndView accessDenied() {
		ModelAndView modelAndView = new ModelAndView("403");
		return modelAndView;
	}
	
	@RequestMapping("/test")
    public ModelAndView test(){
    	ModelAndView modelAndView =new ModelAndView("test");
//        modelAndView.addObject("hello", "access index success");
        return modelAndView;
    }
}
