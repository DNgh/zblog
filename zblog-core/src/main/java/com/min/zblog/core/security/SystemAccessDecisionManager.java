package com.min.zblog.core.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class SystemAccessDecisionManager implements AccessDecisionManager {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		if(null == configAttributes){
		    return;
		}
		
		//所请求的资源拥有的权限(一个资源对多个权限)
		Iterator<ConfigAttribute> iterable = configAttributes.iterator();
		while (iterable.hasNext()){
		    ConfigAttribute configAttribute = iterable.next();
		    //访问所请求的资源所需要的权限
		    String needPermission = configAttribute.getAttribute();
		    logger.debug("访问"+object.toString()+"需要的权限是："+needPermission);
		
		    //用户所拥有的权限authentication
		    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		    for (GrantedAuthority ga : authorities) {
		        if (needPermission.equals(ga.getAuthority())) {
		            return;
		        }
		    }
		}
		//没有权限
		throw new AccessDeniedException("没有权限访问！");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
