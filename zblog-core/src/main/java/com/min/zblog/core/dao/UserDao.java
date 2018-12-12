package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TsUser;

@Repository
public interface UserDao extends JpaRepository<TsUser, Long> {
	public TsUser findByUsername(String username);
}
