package com.min.zblog.core.facility;

import java.util.List;

import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.data.view.UserInfo;

public class GlobalContextHolder
{
	private static BlogInfo blogInfo = null;
	
	private static List<CategoryInfo> categoryInfoList = null;
	
	private static List<ArchiveInfo> archiveInfoList = null;
	
	private static List<TagInfo> tagInfoList = null;
	
	private static List<ArticleInfo> articleReadRankList = null;
	
	private static UserInfo userInfo = null;

	public static BlogInfo getBlogInfo() {
		return blogInfo;
	}

	public static void setBlogInfo(BlogInfo blogInfo) {
		GlobalContextHolder.blogInfo = blogInfo;
	}

	public static List<CategoryInfo> getCategoryInfoList() {
		return categoryInfoList;
	}

	public static void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
		GlobalContextHolder.categoryInfoList = categoryInfoList;
	}

	public static List<ArchiveInfo> getArchiveInfoList() {
		return archiveInfoList;
	}

	public static void setArchiveInfoList(List<ArchiveInfo> archiveInfoList) {
		GlobalContextHolder.archiveInfoList = archiveInfoList;
	}

	public static List<TagInfo> getTagInfoList() {
		return tagInfoList;
	}

	public static void setTagInfoList(List<TagInfo> tagInfoList) {
		GlobalContextHolder.tagInfoList = tagInfoList;
	}

	public static List<ArticleInfo> getArticleReadRankList() {
		return articleReadRankList;
	}

	public static void setArticleReadRankList(List<ArticleInfo> articleReadRankList) {
		GlobalContextHolder.articleReadRankList = articleReadRankList;
	}

	public static UserInfo getUserInfo() {
		return userInfo;
	}

	public static void setUserInfo(UserInfo userInfo) {
		GlobalContextHolder.userInfo = userInfo;
	}
	
}
