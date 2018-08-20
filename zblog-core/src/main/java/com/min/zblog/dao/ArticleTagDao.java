package com.min.zblog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.entity.TmArticleTag;

@Repository
public interface ArticleTagDao extends JpaRepository<TmArticleTag, Long> {

}
