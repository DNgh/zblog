package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TsOperateHst;

@Repository
public interface OperateHst extends JpaRepository<TsOperateHst, Long> {

}
