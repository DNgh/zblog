package com.min.zblog.api;

import java.util.Map;

import com.min.zblog.data.entity.TsUser;
import com.min.zblog.data.view.UserInfo;
import com.min.zblog.facility.exception.ProcessException;

public interface UserService {
	public TsUser findUserByUsername(String username);
	public UserInfo loadUserByUsername(String username);
	public TsUser saveUser(Map<String, Object> map) throws ProcessException;
}
