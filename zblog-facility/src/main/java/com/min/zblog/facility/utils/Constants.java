package com.min.zblog.facility.utils;

public class Constants {
	public static final String COOKIE_IP = "COOKIE_IP";
	
	public static final String COOKIE_READ = "COOKIE_READ";
	
	//分页信息
	public static final String LIMIT = "limit";//每页条数
	
	public static final String PAGE = "page";//当前页
	
	public static final String CREATE_TIME = "createTime";
	
	public static final String YEAR = "year";
	
	public static final String MONTH = "month";
	
	public static final String CATEGORY_ID = "categoryId";//分类id
	
	public static final String STATE = "state";
	
	public static final int ARCHIVENAME_LENGTH = 6;
	
	public static final String START_DATE = "startDate";
	
	public static final String END_DATE = "endDate";
	
	public static final String AVAILABLE = "available";
	
	//错误码
	public static final String ERRS001_CODE = "S001";
	public static final String ERRS001_MSG = "系统错误";
	
	public static final String ERRA001_CODE = "A001";
	public static final String ERRA001_MSG = "不存在该文章";
	
	public static final String ERRA002_CODE = "A002";
	public static final String ERRA002_MSG = "文章已被标记为回收,不能重复回收";
	
	public static final String ERRA003_CODE = "A003";
	public static final String ERRA003_MSG = "文章没有被标记为回收,不能直接删除";
	
	public static final String ERRA004_CODE = "A004";
	public static final String ERRA004_MSG = "文章没有被标记为回收,不能重新发布";
	
	public static final String ERRC001_CODE = "C001";
	public static final String ERRC001_MSG = "不存在该分类";

	public static final String ERRC002_CODE = "C002";
	public static final String ERRC002_MSG = "正使用，无法删除";
	
	public static final String ERRC003_CODE = "C003";
	public static final String ERRC003_MSG = "正使用，不允许停用分类";
	
	public static final String ERRV001_CODE = "V001";
	public static final String ERRV001_MSG = "不存在该归档";
	
	public static final String ERRV002_CODE = "V002";
	public static final String ERRV002_MSG = "正使用，无法删除";
	
	public static final String ERRT001_CODE = "T001";
	public static final String ERRT001_MSG = "不存在该标签";
	
	public static final String ERRT002_CODE = "T002";
	public static final String ERRT002_MSG = "正使用，无法删除";
	
	public static final String ERRM001_CODE = "M001";
	public static final String ERRM001_MSG = "不存在该评论";
	
	public static final String ERRU001_CODE = "U001";
	public static final String ERRU001_MSG = "不存在该用户";
}
