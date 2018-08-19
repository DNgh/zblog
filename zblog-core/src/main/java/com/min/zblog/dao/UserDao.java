package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.entity.TsUser;

@Repository
public interface UserDao extends JpaRepository<TsUser, Long> {

}
