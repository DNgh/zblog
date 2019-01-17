package com.min.zblog.api.security;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.min.zblog.api.rpc.UserService;
import com.min.zblog.api.rpc.RoleService;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.facility.enums.UserState;

public class SystemUserDetailsService implements UserDetailsService{

	private UserService userService;
	
	private RoleService roleService;
	
	@Override
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