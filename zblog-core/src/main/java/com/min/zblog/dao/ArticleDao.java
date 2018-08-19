package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.entity.TmArticle;

@Repository
public interface ArticleDao extends JpaRepository<TmArticle, Long> {

}
