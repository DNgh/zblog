package com.min.zblog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring-hibernate.xml"}) //加载配置文件
public class TestArticleService {
	@Autowired
	private ArticleService articleService;
	
	@Test
	public void testArticle(){
		System.out.println(articleService.listAll());
	}
}
