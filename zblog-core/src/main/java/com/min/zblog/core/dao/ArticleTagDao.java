package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.data.entity.TmArticleTag;
import com.min.zblog.data.entity.TmArticleTagKey;

@Repository
public interface ArticleTagDao extends JpaRepository<TmArticleTag, TmArticleTagKey> {
	@Transactional
	@Modifying
	@Query(value = "delete from tm_article_tag where article_id = ?1", nativeQuery = true)
	public void deleteTmArticleTagByArticleId(Long id);
}
