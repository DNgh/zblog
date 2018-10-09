package com.min.zblog.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.min.zblog.core.dao.CommentDao;
import com.min.zblog.core.service.CommentService;
import com.min.zblog.data.entity.TmComment;
import com.min.zblog.data.view.CommentInfo;

@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations={"classpath:spring-hibernate.xml"}) //加载配置文件
public class TestCommentService {
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentDao commentDao;
	
	
	public void testListComment(){
		List<CommentInfo> list = commentService.listCommentByArticleId(Long.valueOf(10));
		System.out.println("父评论");
		for(CommentInfo commentInfo:list){
			System.out.println("////////////////////////////////////////////////////////");
			System.out.println(commentInfo.getId());
			System.out.println(commentInfo.getRid());
			System.out.println(commentInfo.getPid());
			System.out.println(commentInfo.getContent());
			System.out.println(commentInfo.getCreateTime());
		}
		
		List<CommentInfo> sublist = commentService.listCommentByRId(Long.valueOf(4));
		System.out.println("子评论");
		for(CommentInfo commentInfo:sublist){
			System.out.println("////////////////////////////////////////////////////////");
			System.out.println(commentInfo.getId());
			System.out.println(commentInfo.getRid());
			System.out.println(commentInfo.getPid());
			System.out.println(commentInfo.getContent());
			System.out.println(commentInfo.getCreateTime());
		}
	}
	
	@Test
	public void testSaveComment(){
		TmComment tmComment = new TmComment();
		tmComment.setArticleId(Long.valueOf(10));
		tmComment.setContent("niuXX的");
		tmComment.setCreateTime(new Date());
		tmComment.setFavorNum(0);
		tmComment.setNickname("草帽X");
		tmComment.setPid(Long.valueOf(4));
		tmComment.setRid(Long.valueOf(4));
		tmComment.setPnickname("牛X");
		tmComment.setUpdateTime(new Date());
		tmComment.setJpaVersion(0);
		commentDao.save(tmComment);
	}
}
