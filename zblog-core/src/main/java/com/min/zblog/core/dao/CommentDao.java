package com.min.zblog.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmComment;

@Repository
public interface CommentDao extends JpaRepository<TmComment, Long> {
	public List<TmComment> findByRidOrderByCreateTimeDesc(Long rid);
}
