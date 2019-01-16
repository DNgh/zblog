package com.min.zblog.api.rpc;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public interface RoleService {
	public List<GrantedAuthority> getUserAuthorities(Long userId);
}
