package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.entity.TmTag;

@Repository
public interface TagDao extends JpaRepository<TmTag, Long> {

}
