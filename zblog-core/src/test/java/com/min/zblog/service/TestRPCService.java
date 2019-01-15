package com.min.zblog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
//@ContextConfiguration(locations={"classpath:spring-context.xml"}) //加载配置文件
public class TestRPCService {
	
//	@Test
//	public void testUser(){
//		System.out.println("user rpc服务");
//	}
	
	public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        context.close();
	}
}
