package com.min.zblog.facility.enums;

/**
 * <p>Title: ArticleState</p>
 * <p>Description: 文章状态枚举值</p>
 * @author	zhouzm
 * @date	2018年8月26日
 * @version 1.0.0
 */
@EnumInfo({
	"CREATE|创建",
	"PUBLISH|发布",
	"DELETE|删除"
})
public enum ArticleState {
	/**
	 * 创建状态（草稿），前端页面不显示，可以发布
	 */
	CREATE("创建"),
	/**
	 * 发布状态，前端页面可以浏览
	 */
	PUBLISH("发布"),
	/**
	 * 进入回收箱的博客，标记为删除状态，前端页面不显示
	 */
	DELETE("删除");
	
	private String desc;
	
	private ArticleState(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
