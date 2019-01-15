package com.min.zblog.admin.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.min.zblog.api.UserService;
import com.min.zblog.data.entity.TsUser;

public class TestRPCClient {
	    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        UserService service=(UserService) context.getBean("client");
        TsUser tsUser = service.findUserByUsername("minzone");
        System.out.println(tsUser.getUsername());
        
        context.close();
	}
}
