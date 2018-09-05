package com.min.zblog.facility.enums;

/**
 * <p>Title: Indicator</p>
 * <p>Description: 是否枚举值</p>
 * @author	zhouzm
 * @date	2018年8月26日
 * @version 1.0.0
 */
@EnumInfo({
	"Y|是",
	"N|否"
})
public enum Indicator {
	Y("是"),
	N("否");
	
	private String desc;
	
	private Indicator(String desc){
		this.desc = desc;
	}
	
	public String getDesc(){
		return this.desc;
	}
}
