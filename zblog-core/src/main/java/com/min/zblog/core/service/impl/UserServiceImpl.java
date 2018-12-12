package com.min.zblog.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.core.dao.UserDao;
import com.min.zblog.core.service.UserService;
import com.min.zblog.data.entity.TsUser;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	public TsUser findUserByUsername(String username) {
		return userDao.findByUsername(username);
	}

}
