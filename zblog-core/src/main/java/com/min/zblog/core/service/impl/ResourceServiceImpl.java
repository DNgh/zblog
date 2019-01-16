package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.api.rpc.ResourceService;
import com.min.zblog.data.entity.SecuritySource;

@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Override
	public LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>> buildResourceMap(){
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap =
                new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        //查询权限资源信息
        List<SecuritySource> resourcesList = blogQueryDsl.fetchSecuritySource();
        //遍历资源
        Map<String, Set<String>> resourceMap = new HashMap<String, Set<String>>();
        if(resourcesList != null) {//url对应role放入map，去除重复role
        	for(SecuritySource resource : resourcesList) {
        		Set<String> authSet = resourceMap.get(resource.getUrl());
        		if(authSet != null) {
        			authSet.add(resource.getRole());
        		}else {
        			authSet = new HashSet<String>();
        			authSet.add(resource.getRole());
        		}
        		resourceMap.put(resource.getUrl(), authSet);
        	}
        }
        
        //存入requestMap
        for(Map.Entry<String, Set<String>> entry : resourceMap.entrySet()) {
            RequestMatcher requestMatcher = getRequestMatcher(entry.getKey());
            List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
            
            Set<String> valueSet = entry.getValue();
            Iterator<String> iter = valueSet.iterator();
            while(iter.hasNext()) {
              list.add(new SecurityConfig(iter.next()));
            }
            requestMap.put(requestMatcher, list);
        }
        
        return requestMap;
    }

    //通过一个字符串地址构建一个AntPathRequestMatcher对象
    //getRequestMatcher方法就是用来创建RequestMatcher对象的
    protected RequestMatcher getRequestMatcher(String url){
        return new AntPathRequestMatcher(url);
    }
}
