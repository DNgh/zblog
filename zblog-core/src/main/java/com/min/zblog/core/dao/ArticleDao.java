package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmArticle;
import com.min.zblog.facility.enums.ArticleState;

@Repository
public interface ArticleDao extends JpaRepository<TmArticle, Long> {
	public long countByState(ArticleState state);
}
