package com.min.zblog.core.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.core.service.RoleService;
import com.min.zblog.api.UserService;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.facility.enums.UserState;

public class SystemUserDetailsService implements UserDetailsService{

	private UserService userService;
	
	private RoleService roleService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		TsUser user = userService.findUserByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("Username not found");
		}
		
		List<GrantedAuthority>  authorities =  roleService.getUserAuthorities(user.getId());
		User result = new User(user.getUsername(), user.getPassword(), 
				(user.getState() == UserState.NORMAL?true:false), true, true, true, authorities);

		return result;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
}