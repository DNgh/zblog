package com.min.zblog.def;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Title: EnumInfo</p>
 * <p>Description: 枚举注解</p>
 * @author	zhouzm
 * @date	2018年8月26日
 * @version 1.0.0
 */
@Documented
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumInfo {
	public String[] value() default "";
}
