package com.min.zblog.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.min.zblog.api.UserService;
import com.min.zblog.data.view.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/profile")
    public ModelAndView profile(){
    	ModelAndView modelAndView =new ModelAndView("profile");
    	User user = (User)SecurityContextHolder.getContext()
    			.getAuthentication().getPrincipal();
    	logger.debug(user.getUsername());
    	//加载用户详情
    	UserInfo userInfo = userService.loadUserByUsername(user.getUsername());
    	modelAndView.addObject("userInfo", userInfo);
    	
        return modelAndView;
    }
	
	@ResponseBody
    @RequestMapping("/save")
    public Map<String, Object> saveUser(
    		@RequestParam(value="username") String username,
    		@RequestParam(value="nickname") String nickname,
    		@RequestParam(value="mobile") String mobile,
    		@RequestParam(value="location") String location){
		logger.debug("username:"+username+",nickname:"+nickname+",mobile:"+mobile+",location:"+location);
		
    	//保存到数据库
    	Map<String, Object> reqMap = new HashMap<String, Object>();
    	reqMap.put("username", username);
    	reqMap.put("nickname", nickname); 
    	reqMap.put("mobile", mobile);
    	reqMap.put("location", location); 
    	
    	//加载
    	Map<String, Object> result = new HashMap<String, Object>();
    	try{
    		userService.saveUser(reqMap);
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
