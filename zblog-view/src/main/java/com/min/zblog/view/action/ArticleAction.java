package com.min.zblog.view.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.min.zblog.core.service.ArchiveService;
import com.min.zblog.core.service.ArticleService;
import com.min.zblog.core.service.CategoryService;
import com.min.zblog.core.service.CommentService;
import com.min.zblog.core.service.TagService;
import com.min.zblog.core.service.VisitHstService;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.CommentInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.enums.VisitType;
import com.min.zblog.facility.utils.CommonUtil;
import com.min.zblog.facility.utils.Constants;
import com.min.zblog.view.facility.NetworkUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleAction extends ActionSupport {
	private static final long serialVersionUID = 7939126579870842596L;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ArchiveService  archiveService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private VisitHstService visitHstService;
	
	private ArticleInfo articleInfo;
	
	/**
	 * 右侧显示文章列表
	 */
	private List<ArticleInfo> articleList;
	
	/**
	 * 分类信息
	 */
	private List<CategoryInfo>  categoryInfoList;
	
	/**
	 * 归档信息
	 */
	private List<ArchiveInfo>  archiveInfoList;
	
	/**
	 * 标签信息
	 */
	private List<TagInfo>  tagInfoList;
	
	/**
	 * 文章阅读排行
	 */
	private List<ArticleInfo> articleRankList;
	
	/**
	 * 博客统计信息
	 */
	private BlogInfo blogInfo;
	
	/**
	 * 主评论列表
	 */
	private List<CommentInfo> commentInfoList;
	
	/**
	 * 子评论列表
	 */
	private Map<Long, List<CommentInfo>> subCommentMap;
	
	private String categoryName;
	
	private String archiveName;
	
	private String tagName;
	
	private String pageInfoJStr;
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	/**
	 * 当前页数
	 */
	private long page;
	
	/**
	 * 每页数据条数
	 */
	private long pageSize;
	
	private PageInfo<ArticleInfo> pageInfo;
	
	/**
	 * 异步请求结果，返回map，struts2转json
	 */
	private Map<String, Object> respMap;
	
    public String show(){
    	fetchCommonData();

    	//文章
    	ActionContext context = ActionContext.getContext();
    	Map<String, Object> map = context.getParameters();
    	String[] obj = (String[])map.get("articleKey");
    	logger.info("articleKey:"+obj[0]);
    	
    	//替换回车换行符，否则页面js脚本报语法错。
    	articleInfo = articleService.findOneArticle(Long.valueOf(obj[0]));
    	//转换换行符
    	articleInfo.setContent(articleInfo.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
    	//加载评论
    	//根评论
    	commentInfoList = commentService.listCommentByArticleId(articleInfo.getId());
    	//子评论
    	subCommentMap = new HashMap<Long, List<CommentInfo>>();
    	for(CommentInfo commentInfo:commentInfoList){
    		List<CommentInfo> infoList = commentService.listCommentByRId(commentInfo.getId());
    		if(infoList != null && infoList.size()>0){
    			subCommentMap.put(commentInfo.getId(), infoList);
    		}
    	}
    	
    	//统计阅读次数
    	calReadNum(ServletActionContext.getRequest(), ServletActionContext.getResponse(), articleInfo.getId());
    	
    	return SUCCESS;
    }
    
    public String showPre(){
    	fetchCommonData();
    	
    	//文章
    	ActionContext context = ActionContext.getContext();
    	Map<String, Object> map = context.getParameters();
    	String[] obj = (String[])map.get("articleKey");
    	logger.info("articleKey:"+obj[0]);
    	
    	//替换回车换行符，否则页面js脚本报语法错。
    	articleInfo = articleService.findPreOneArticle(Long.valueOf(obj[0]));
    	//转换换行符
    	articleInfo.setContent(articleInfo.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
    	//加载评论
    	//根评论
    	commentInfoList = commentService.listCommentByArticleId(articleInfo.getId());
    	//子评论
    	subCommentMap = new HashMap<Long, List<CommentInfo>>();
    	for(CommentInfo commentInfo:commentInfoList){
    		List<CommentInfo> infoList = commentService.listCommentByRId(commentInfo.getId());
    		if(infoList != null && infoList.size()>0){
    			subCommentMap.put(commentInfo.getId(), infoList);
    		}
    	}
    	
    	//统计阅读次数
    	calReadNum(ServletActionContext.getRequest(), ServletActionContext.getResponse(), articleInfo.getId());
    	
    	return SUCCESS;
    }
    
    public String showNext(){
    	fetchCommonData();
    	
    	//文章
    	ActionContext context = ActionContext.getContext();
    	Map<String, Object> map = context.getParameters();
    	String[] obj = (String[])map.get("articleKey");
    	logger.info("articleKey:"+obj[0]);
    	
    	//替换回车换行符，否则页面js脚本报语法错。
    	articleInfo = articleService.findNextOneArticle(Long.valueOf(obj[0]));
    	//转换换行符
    	articleInfo.setContent(articleInfo.getContent().replaceAll("\n", "\\\\n").replaceAll("\r", ""));
    	//加载评论
    	//根评论
    	commentInfoList = commentService.listCommentByArticleId(articleInfo.getId());
    	//子评论
    	subCommentMap = new HashMap<Long, List<CommentInfo>>();
    	for(CommentInfo commentInfo:commentInfoList){
    		List<CommentInfo> infoList = commentService.listCommentByRId(commentInfo.getId());
    		if(infoList != null && infoList.size()>0){
    			subCommentMap.put(commentInfo.getId(), infoList);
    		}
    	}
    	
    	//统计阅读次数
    	calReadNum(ServletActionContext.getRequest(), ServletActionContext.getResponse(), articleInfo.getId());
    	
    	return SUCCESS;
    }

    public String listArticleByCategory() {
    	//文章
    	logger.info(this.categoryName);
    	pageInfo = articleService.listArticleByPageCategoryName(pageSize, page, categoryName);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		mapper.setDateFormat(sdf); 
			pageInfoJStr = mapper.writeValueAsString(pageInfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return SUCCESS;
    }
    
    public String listArticleByArchive() {
    	//文章
    	pageInfo = articleService.listArticleByPageArchive(pageSize, page, archiveName);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		mapper.setDateFormat(sdf); 
			pageInfoJStr = mapper.writeValueAsString(pageInfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return SUCCESS;
    }
    
    public String listArticleByTag(){
    	//文章
    	pageInfo = articleService.listArticleByPageTag(pageSize, page, tagName);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		mapper.setDateFormat(sdf); 
			pageInfoJStr = mapper.writeValueAsString(pageInfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	
    	return SUCCESS;
    }
    
    public String listAllArticles(){
    	pageInfo = articleService.listArticleByPage(pageSize, page);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
    		mapper.setDateFormat(sdf); 
			pageInfoJStr = mapper.writeValueAsString(pageInfo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return SUCCESS;
    }
    
    public String favor(){
    	ActionContext context = ActionContext.getContext();
    	Map<String, Object> map = context.getParameters();
    	String[] obj = (String[])map.get("articleKey");
    	logger.info("articleKey:"+obj[0]);
    	
    	respMap = new HashMap<String, Object>();
    	//文章id,ip,浏览器类型，访问类型，时间
		visitHstService.addVisitHst(Long.valueOf(obj[0]), 
				NetworkUtil.getIpAddress(ServletActionContext.getRequest()), 
				NetworkUtil.getBrowserVersion(ServletActionContext.getRequest()),
				VisitType.FAVOR);
		respMap.put("success", true);

		return SUCCESS;
    }
    
    public List<ArticleInfo> getArticleList(){
    	return this.articleList;
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public List<CategoryInfo> getCategoryInfoList() {
		return this.categoryInfoList;
	}
	
	public List<ArchiveInfo> getArchiveInfoList() {
		return this.archiveInfoList;
	}
	
	public List<ArticleInfo> getArticleRankList() {
		return this.articleRankList;
	}

	public String getArchiveName() {
		return archiveName;
	}

	public void setArchiveName(String archiveName) {
		this.archiveName = archiveName;
	}
	
	public List<TagInfo> getTagInfoList() {
		return this.tagInfoList;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public BlogInfo getBlogInfo(){
		return this.blogInfo;
	}
	
	public String getPageInfoJStr() {
		return pageInfoJStr;
	}

	public void setPageInfoJStr(String pageInfoJStr) {
		this.pageInfoJStr = pageInfoJStr;
	}
	
	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}
	
	public ArticleInfo getArticleInfo() {
		return articleInfo;
	}

	public void setArticleInfo(ArticleInfo articleInfo) {
		this.articleInfo = articleInfo;
	}
	
	public List<CommentInfo> getCommentInfoList() {
		return commentInfoList;
	}

	public Map<Long, List<CommentInfo>> getSubCommentMap() {
		return subCommentMap;
	}

	public Map<String, Object> getRespMap() {
		return respMap;
	}

	public void setRespMap(Map<String, Object> respMap) {
		this.respMap = respMap;
	}

	public void fetchCommonData(){
		//博客统计信息
		this.blogInfo = articleService.obtainBlogInfo();
		//分类
    	this.categoryInfoList = categoryService.fetchCategoryInfo();
    	//归档
    	this.archiveInfoList = archiveService.fetchArchiveInfo();
    	//标签
    	this.tagInfoList = tagService.fetchTagInfo();
    	//阅读排行
    	this.articleRankList = articleService.listArticleByReadRank();
	}
	
	public void calReadNum(HttpServletRequest req, HttpServletResponse resp, Long id){
		//存在，则返回
		if(NetworkUtil.existCookie(req.getCookies(), Constants.COOKIE_READ+"_"+id)){
			return;
		}
		
		//不存在，则数据库阅读记录+1
		//文章id,ip,浏览器类型，访问类型，时间
		visitHstService.addVisitHst(id, NetworkUtil.getIpAddress(req), 
				NetworkUtil.getBrowserVersion(req), VisitType.READ);
		
		//保存阅读文章id到cookie
		Cookie cookie = new Cookie(Constants.COOKIE_READ+"_"+id, "true");
		cookie.setMaxAge(30*60);//30分钟
		resp.addCookie(cookie);
	}
	
}
