package com.min.zblog.core.service;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.core.dao.BlogQueryDsl;
import com.min.zblog.data.entity.TsRole;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.facility.enums.UserState;

@Component("userDetailsService")
public class SystemUserDetailsService implements UserDetailsService{

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogQueryDsl blogQueryDsl;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		TsUser user = userService.findUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Username not found");
		}
		
		List<GrantedAuthority>  authorities =  getUserAuthorities(user.getId());
		User result = new User(user.getUsername(), user.getPassword(), 
				(user.getState() == UserState.NORMAL?true:false), true, true, true, authorities);

		return result;
	}

	
	private List<GrantedAuthority> getUserAuthorities(Long userId){
		logger.info("获取用户角色");
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