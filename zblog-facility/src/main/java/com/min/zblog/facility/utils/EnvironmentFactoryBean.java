package com.min.zblog.facility.utils;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * #自定义生成属性，如果启动命令通过-D指定了env.files参数，则指定目录读取配置文件，整合properties、操作系统环境变量、jvm系统属性参数
 * #否则，使用项目目录下参数。
 * @author zhouzm
 *
 */
public class EnvironmentFactoryBean extends PropertiesLoaderSupport implements FactoryBean<Properties> {
	@Override
	public Properties getObject() throws Exception {

		Properties props = new Properties();
		
		//操作系统环境变量
		props.putAll(System.getenv());
		
		//参数location/locations指定本地配置文件
		loadProperties(props);
		
		//命令行参数（优先级高）
		props.putAll(System.getProperties());
		
		return props;
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
