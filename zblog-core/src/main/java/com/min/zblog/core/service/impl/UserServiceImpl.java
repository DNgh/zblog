package com.min.zblog.core.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.zblog.api.rpc.UserService;
import com.min.zblog.core.dao.UserDao;
import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmArticleTagKey;
import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TsUser;
import com.min.zblog.data.view.UserInfo;
import com.min.zblog.facility.enums.ArticleState;
import com.min.zblog.facility.enums.Indicator;
import com.min.zblog.facility.exception.ProcessException;
import com.min.zblog.facility.utils.Constants;

@Service
public class UserServiceImpl implements UserService {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public TsUser findUserByUsername(String username) {
		logger.info("根据用户名查询用户："+username);
		
		return userDao.findByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.UserService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserInfo loadUserByUsername(String username) {
		TsUser tsUser = userDao.findByUsername(username);
		if(tsUser == null){
			return null;
		}
		
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(tsUser.getUsername());
		userInfo.setNickname(tsUser.getNickname());
		userInfo.setMobile(tsUser.getMobile());
		userInfo.setEmail(tsUser.getEmail());
		userInfo.setPortrait(tsUser.getPortrait());
		userInfo.setLocation(tsUser.getLocation());
		userInfo.setRegIp(tsUser.getRegIp());
		userInfo.setLastLoginIp(tsUser.getLastLoginIp());
		userInfo.setLastLoginTime(tsUser.getLastLoginTime());
		userInfo.setLoginCount(tsUser.getLoginCount());
		userInfo.setCreateTime(tsUser.getCreateTime());
		userInfo.setUpdateTime(tsUser.getUpdateTime());
		
		return userInfo;
	}

	/* (non-Javadoc)
	 * @see com.min.zblog.core.service.UserService#saveUser(java.util.Map)
	 */
	@Override
	public TsUser saveUser(Map<String, Object> map) throws ProcessException {
		TsUser tsUser = userDao.findByUsername((String)map.get("username"));
		if(tsUser == null){
			throw new ProcessException(Constants.ERRU001_CODE, Constants.ERRU001_MSG);
		}
		
		Date time = new Date();
		tsUser.setNickname((String)map.get("nickname"));
		tsUser.setMobile((String)map.get("mobile"));
		tsUser.setLocation((String)map.get("location"));
		tsUser.setUpdateTime(time);
    	
		userDao.save(tsUser);
		
		return tsUser;
	}

}
