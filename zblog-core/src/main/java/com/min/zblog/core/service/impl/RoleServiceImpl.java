package com.min.zblog.core.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.api.rpc.RoleService;
import com.min.zblog.data.entity.TsRole;

@Service
public class RoleServiceImpl implements RoleService {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Override
	public List<GrantedAuthority> getUserAuthorities(Long userId){
		logger.debug("获取用户角色");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		//查询用户角色
		List<TsRole> tsRoleList = blogQueryDsl.fetchRoleByUserId(userId);
		
		if(tsRoleList != null) {
			Set<SimpleGrantedAuthority> authoritySet = new HashSet<SimpleGrantedAuthority>();
			for(TsRole role : tsRoleList){
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(role.getName());
				authoritySet.add(auth);
			}
			
			Iterator<SimpleGrantedAuthority> iter = authoritySet.iterator();
			while(iter.hasNext()) {
				authorities.add(iter.next());
			}
			
			return authorities;
		}else {
			return authorities;
		}
	}

}
