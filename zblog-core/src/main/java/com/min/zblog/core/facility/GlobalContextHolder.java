package com.min.zblog.core.facility;

import java.util.List;

import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.ArticleInfo;
import com.min.zblog.data.view.BlogInfo;
import com.min.zblog.data.view.CategoryInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.data.view.UserInfo;

/**
 * 前端展示数据缓存，spring启动后初始化
 * @author zhouzm
 *
 */
public class GlobalContextHolder
{
	private static volatile BlogInfo blogInfo = null;
	
	private static volatile List<CategoryInfo> categoryInfoList = null;
	
	private static volatile List<ArchiveInfo> archiveInfoList = null;
	
	private static volatile List<TagInfo> tagInfoList = null;
	
	private static volatile List<ArticleInfo> articleReadRankList = null;
	
	private static volatile UserInfo userInfo = null;
	
	private static final String BLOGINFO_ARTICLENUM_LOCK = new String("BLOGINFO_ARTICLENUM_LOCK");
	
	private static final String BLOGINFO_READNUM_LOCK = new String("BLOGINFO_READNUM_LOCK");
	
	private static final String BLOGINFO_COMMENTNUM_LOCK = new String("BLOGINFO_COMMENTNUM_LOCK");
	
	private static final String CATEGORYINFO_ARTICLENUM_LOCK = new String("CATEGORYINFO_ARTICLENUM_LOCK");
	
	private static final String CATEGORYINFO_LOCK = new String("CATEGORYINFO_LOCK");
	
	private static final String ARCHIVEINFO_ARTICLENUM_LOCK = new String("ARCHIVEINFO_ARTICLENUM_LOCK");
	
	private static final String ARCHIVEINFO_LOCK = new String("ARCHIVEINFO_LOCK");
	
	private static final String TAGINFO_LOCK = new String("TAGINFO_LOCK");

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
	
	/**
	 * 文章数增一
	 */
	public static void addOneBlogInfoArticleNum() {
		synchronized (BLOGINFO_ARTICLENUM_LOCK) {
			blogInfo.setTotalArticleNum(
					blogInfo.getTotalArticleNum()+1);
		}
	}
	
	/**
	 * 文章数减一
	 */
	public static void substractOneBlogInfoArticleNum() {
		synchronized (BLOGINFO_ARTICLENUM_LOCK) {
			blogInfo.setTotalArticleNum(
					blogInfo.getTotalArticleNum()-1);
		}
	}
	
	public static void addOneBlogInfoReadNum() {
		synchronized (BLOGINFO_READNUM_LOCK) {
			blogInfo.setTotalReadNum(
					blogInfo.getTotalReadNum()+1);
		}
	}
	
	public static void substractOneBlogInfoReadNum() {
		synchronized (BLOGINFO_READNUM_LOCK) {
			blogInfo.setTotalReadNum(
					blogInfo.getTotalReadNum()-1);
		}
	}
	
	/**
	 * 重新发布回收箱文章，加上阅读数
	 * @param num
	 */
	public static void addBlogInfoReadNum(long num) {
		synchronized (BLOGINFO_READNUM_LOCK) {
			blogInfo.setTotalReadNum(
					blogInfo.getTotalReadNum()+num);
		}
	}
	
	/**
	 * 将文章放入回收箱，减掉阅读数
	 * @param num
	 */
	public static void substractBlogInfoReadNum(long num) {
		synchronized (BLOGINFO_READNUM_LOCK) {
			blogInfo.setTotalReadNum(
					blogInfo.getTotalReadNum()-num);
		}
	}
	
	public static void addOneBlogInfoCommentNum() {
		synchronized (BLOGINFO_COMMENTNUM_LOCK) {
			blogInfo.setTotalCommentNum(
					blogInfo.getTotalCommentNum()+1);
		}
	}
	
	public static void substractOneBlogInfoCommentNum() {
		synchronized (BLOGINFO_COMMENTNUM_LOCK) {
			blogInfo.setTotalCommentNum(
					blogInfo.getTotalCommentNum()-1);
		}
	}
	
	/**
	 * 重新发布回收箱文章，加上阅读数
	 * @param num
	 */
	public static void addBlogInfoCommentNum(long num) {
		synchronized (BLOGINFO_COMMENTNUM_LOCK) {
			blogInfo.setTotalCommentNum(
					blogInfo.getTotalCommentNum()+num);
		}
	}
	
	/**
	 * 将文章放入回收箱，减掉阅读数
	 * @param num
	 */
	public static void substractBlogInfoCommentNum(long num) {
		synchronized (BLOGINFO_COMMENTNUM_LOCK) {
			blogInfo.setTotalCommentNum(
					blogInfo.getTotalCommentNum()-num);
		}
	}
	
	/**
	 * 分类：文章数增一
	 */
	public static void addOneCategoryInfoArticleNum(Long categoryId) {
		synchronized (CATEGORYINFO_ARTICLENUM_LOCK) {
			for(CategoryInfo info:categoryInfoList) {
				if(info.getId().equals(categoryId)) {
					info.setArticleNum(info.getArticleNum()+1);
					return;
				}
			}
		}
	}
	
	/**
	 * 分类：文章数减一
	 */
	public static void substractOneCategoryInfoArticleNum(Long categoryId) {
		synchronized (CATEGORYINFO_ARTICLENUM_LOCK) {
			for(CategoryInfo info:categoryInfoList) {
				if(info.getId().equals(categoryId)) {
					info.setArticleNum(info.getArticleNum()-1);
					return;
				}
			}
		}
	}
	
	/**
	 * 分类：新增
	 */
	public static void addCategoryInfo(CategoryInfo categoryInfo) {
		synchronized (CATEGORYINFO_LOCK) {
			categoryInfoList.add(categoryInfo);
		}
	}
	
	/**
	 * 分类：删除
	 */
	public static void deleteCategoryInfo(Long categoryId) {
		synchronized (CATEGORYINFO_LOCK) {
			for(CategoryInfo info:categoryInfoList) {
				if(info.getId().equals(categoryId)) {
					categoryInfoList.remove(info);
					return;
				}
			}
		}
	}
	
	/**
	 * 归档：文章数增一
	 */
	public static void addOneArchiveInfoArticleNum(Long archiveId) {
		synchronized (ARCHIVEINFO_ARTICLENUM_LOCK) {
			for(ArchiveInfo info:archiveInfoList) {
				if(info.getId().equals(archiveId)) {
					info.setArticleNum(info.getArticleNum()+1);
					return;
				}
			}
		}
	}
	
	/**
	 * 归档：文章数减一
	 */
	public static void substractOneArchiveInfoArticleNum(Long archiveId) {
		synchronized (ARCHIVEINFO_ARTICLENUM_LOCK) {
			for(ArchiveInfo info:archiveInfoList) {
				if(info.getId().equals(archiveId)) {
					info.setArticleNum(info.getArticleNum()-1);
					return;
				}
			}
		}
	}
	
	/**
	 * 归档：新增
	 */
	public static void addArchiveInfo(ArchiveInfo archiveInfo) {
		synchronized (ARCHIVEINFO_LOCK) {
			archiveInfoList.add(archiveInfo);
		}
	}
	
	/**
	 * 归档：删除
	 */
	public static void deleteArchiveInfo(Long archiveId) {
		synchronized (ARCHIVEINFO_LOCK) {
			for(ArchiveInfo info:archiveInfoList) {
				if(info.getId().equals(archiveId)) {
					archiveInfoList.remove(info);
					return;
				}
			}
		}
	}
	
	/**
	 * 标签：新增
	 */
	public static void addTagInfo(TagInfo tagInfo) {
		synchronized (TAGINFO_LOCK) {
			tagInfoList.add(tagInfo);
		}
	}
	
	/**
	 * 标签：删除
	 */
	public static void deleteTagInfo(Long tagId) {
		synchronized (TAGINFO_LOCK) {
			for(TagInfo info:tagInfoList) {
				if(info.getId().equals(tagId)) {
					tagInfoList.remove(info);
					return;
				}
			}
		}
	}

	/**
	 * 更新标签
	 * @param tag
	 */
	public static void updateTagInfo(TagInfo tag) {
		synchronized (TAGINFO_LOCK) {
			for(TagInfo info:tagInfoList) {
				if(info.getId().equals(tag.getId())) {
					info.setTagName(tag.getTagName());
					return;
				}
			}
		}
	}
	
	/**
	 * 更新分类
	 * @param tag
	 */
	public static void updateCategoryInfo(CategoryInfo categoryInfo) {
		synchronized (CATEGORYINFO_LOCK) {
			for(CategoryInfo info:categoryInfoList) {
				if(info.getId().equals(categoryInfo.getId())) {
					info.setIcon(categoryInfo.getIcon());
					info.setCategoryName(categoryInfo.getCategoryName());
					info.setArticleNum(categoryInfo.getArticleNum());
					return;
				}
			}
		}
	}
}
