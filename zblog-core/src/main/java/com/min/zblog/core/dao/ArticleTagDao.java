package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmArticleTagKey;

@Repository
public interface ArticleTagDao extends JpaRepository<TmArticleTag, TmArticleTagKey> {

}
