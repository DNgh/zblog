package com.min.zblog.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmTag;

@Repository
public interface TagDao extends JpaRepository<TmTag, Long> {
	public List<TmTag> findAllByOrderByCreateTimeAsc();
}
