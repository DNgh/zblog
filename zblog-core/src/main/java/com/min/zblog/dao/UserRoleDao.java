package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.entity.TsUserRole;

@Repository
public interface UserRoleDao extends JpaRepository<TsUserRole, Long>{

}
