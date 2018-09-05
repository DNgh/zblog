package com.min.zblog.facility.enums;

/**
 * <p>Title: UserState</p>
 * <p>Description: 用户状态枚举值</p>
 * @author	zhouzm
 * @date	2018年8月26日
 * @version 1.0.0
 */
@EnumInfo({
	"UNACTIVATED|激活",
	"NORMAL|正常",
	"CLOSED|关闭"
})
public enum UserState {
	UNACTIVATED("激活"),
	NORMAL("正常"),
	CLOSED("关闭");
	
	private String desc;
	
	private UserState(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
