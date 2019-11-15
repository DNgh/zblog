package com.min.zblog.facility.utils;

import java.util.Properties;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * #产生一个配置属性，如果在命令行指定了process.code属性，则从服务中取对应的配置信息，整合properties、操作系统环境变量、jvm系统属性参数
 * #否则（例如本机开发环境）则取 {@link #setLocations(org.springframework.core.io.Resource[])}中指定的文件，就像 {@link PropertiesFactoryBean}一样。
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
		
		//命令行上的参数更优先
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
