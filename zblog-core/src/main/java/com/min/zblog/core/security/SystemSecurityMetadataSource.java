package com.min.zblog.core.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.min.zblog.api.rpc.ResourceService;

public class SystemSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private ResourceService resourceService;
	
    //资源权限集合
    private Map<RequestMatcher,Collection<ConfigAttribute>> resourceMap;
    
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//根据请求，加载所需权限
		final HttpServletRequest request = ((FilterInvocation) object) .getRequest();
		for(Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry:resourceMap.entrySet()) {
			if(entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> attributes = new HashSet<ConfigAttribute>();
        for(Map.Entry<RequestMatcher,Collection<ConfigAttribute>> entry : resourceMap.entrySet()){
            attributes.addAll(entry.getValue());
        }
        logger.debug("所有权限："+attributes.toString());
        return attributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		 return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//初始化Bean完成，加载参数
		this.resourceMap = resourceService.buildResourceMap();
	}

	public ResourceService getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService resourceService) {
		this.resourceService = resourceService;
	}
	
}
