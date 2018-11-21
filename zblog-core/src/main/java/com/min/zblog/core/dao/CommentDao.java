package com.min.zblog.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.data.entity.TmComment;

@Repository
public interface CommentDao extends JpaRepository<TmComment, Long> {
	public List<TmComment> findByRidOrderByCreateTimeDesc(Long rid);
	
	@Transactional
	@Modifying
	@Query(value = "delete from tm_comment where article_id = ?1", nativeQuery = true)
	public void deleteTmCommentByArticleId(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "delete from tm_comment where rid = ?1", nativeQuery = true)
	public void deleteTmCommentByRid(Long id);
}
