package com.min.zblog.view.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.google.common.reflect.ClassPath.ResourceInfo;
import com.min.zblog.api.rpc.ArchiveService;
import com.min.zblog.api.rpc.ArticleService;
import com.min.zblog.api.rpc.CategoryService;
import com.min.zblog.api.rpc.CommentService;
import com.min.zblog.api.rpc.OperateHstService;
import com.min.zblog.api.rpc.ResourceService;
import com.min.zblog.api.rpc.RoleService;
import com.min.zblog.api.rpc.TagService;
import com.min.zblog.api.rpc.UserService;
import com.min.zblog.api.rpc.VisitHstService;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.CommentInfo;
import com.min.zblog.data.view.OperateHstInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.data.view.UserInfo;
import com.min.zblog.data.view.VisitHstInfo;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.utils.Constants;

public class TestRPCClient {
	    
    public static void main(String[] args) {
    	testArchiveService();
    	
    	testArticleService();
    	
    	testCategoryService();
    	
    	testCommentService();
    	
//    	testOperateHstService();
    	
    	testTagService();
    	
//        testUserService();
    	
        testVisitHstService();
	}
    
    public static void testUserService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
    	
    	UserService service = context.getBean(UserService.class);
        TsUser tsUser = service.findUserByUsername("minzone");
        System.out.println("获取TsUser，用户名："+tsUser.getUsername());
        
        UserInfo userInfo = service.loadUserByUsername("test");
        System.out.println("获取UserInfo，用户名："+userInfo.getUsername()+",邮箱："+userInfo.getEmail());
        
        context.close();
    }
    
    public static void testCategoryService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
        CategoryService service = context.getBean(CategoryService.class);
        Map<String, Object> map = new HashMap<>();
        map.put(Constants.AVAILABLE, Indicator.Y);
        PageInfo<CategoryInfo> pageInfo = service.queryCategoryByPage(5, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<CategoryInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testArchiveService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	ArchiveService service = context.getBean(ArchiveService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<ArchiveInfo> pageInfo = service.queryArchiveByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<ArchiveInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testArticleService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	ArticleService service = context.getBean(ArticleService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<ArticleInfo> pageInfo = service.queryArticleByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<ArticleInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testCommentService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	CommentService service = context.getBean(CommentService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<CommentInfo> pageInfo = service.queryCommentByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<CommentInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testOperateHstService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	OperateHstService service = context.getBean(OperateHstService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<OperateHstInfo> pageInfo = service.queryOperateHstByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<OperateHstInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testResourceService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	ResourceService service = context.getBean(ResourceService.class);
        LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>> 
        	resultMap = service.buildResourceMap();
        if(resultMap != null) {
        	System.out.println("获取LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>>："+resultMap);
        }
        
        context.close();
    }
    
    public static void testRoleService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	RoleService service = context.getBean(RoleService.class);
        List<GrantedAuthority> list = service.getUserAuthorities(Long.valueOf(1));
        if(list != null) {
        	System.out.println("获取List<GrantedAuthority>，总条数："+list.size());
        }
        
        context.close();
    }
    
    public static void testTagService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	TagService service = context.getBean(TagService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<TagInfo> pageInfo = service.queryTagByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<TagInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
    
    public static void testVisitHstService() {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-context.xml");
        
    	VisitHstService service = context.getBean(VisitHstService.class);
        Map<String, Object> map = new HashMap<>();
        PageInfo<VisitHstInfo> pageInfo = service.queryVisitHstByPage(10, 1, map);
        if(pageInfo != null) {
        	System.out.println("获取PageInfo<VisitHstInfo>，总条数："+pageInfo.getCount());
        }
        
        context.close();
    }
}
