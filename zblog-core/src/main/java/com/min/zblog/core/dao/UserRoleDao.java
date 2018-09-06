package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TsUserRole;
import com.min.zblog.data.entity.TsUserRoleKey;

@Repository
public interface UserRoleDao extends JpaRepository<TsUserRole, TsUserRoleKey>{

}
