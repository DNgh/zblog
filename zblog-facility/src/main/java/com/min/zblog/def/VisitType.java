package com.min.zblog.def;

/**
 * <p>Title: VisitType</p>
 * <p>Description: 访问类型</p>
 * @author	zhouzm
 * @date	2018年8月26日
 * @version 1.0.0
 */
@EnumInfo({
	"READ|浏览",
	"FAVOR|点赞",
	"SHARE|分享"
})
public enum VisitType {
	READ("浏览"),
	FAVOR("点赞"),
	SHARE("分享");
	
	private String desc;
	
	private VisitType(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
