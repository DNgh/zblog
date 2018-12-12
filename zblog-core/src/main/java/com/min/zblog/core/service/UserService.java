package com.min.zblog.core.service;

import com.min.zblog.data.entity.TsUser;

public interface UserService {
	public TsUser findUserByUsername(String username);
}
