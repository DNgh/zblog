package com.min.zblog.admin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.min.zblog.api.rpc.CategoryService;
import com.min.zblog.api.rpc.UserService;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.UserInfo;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.utils.Constants;

public class TestRPCClient {
	    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
        /*UserService service = context.getBean(UserService.class);
        TsUser tsUser = service.findUserByUsername("minzone");
        System.out.println("获取TsUser，用户名："+tsUser.getUsername());
        
        UserInfo userInfo = service.loadUserByUsername("test");
        System.out.println("获取UserInfo，用户名："+userInfo.getUsername()+",邮箱："+userInfo.getEmail());*/
        
        CategoryService service = context.getBean(CategoryService.class);
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.AVAILABLE, Indicator.Y);
        PageInfo<CategoryInfo> pageInfo = service.queryCategoryByPage(5, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo，总条数："+pageInfo.getCount());
        }
        
        context.close();
	}
}
