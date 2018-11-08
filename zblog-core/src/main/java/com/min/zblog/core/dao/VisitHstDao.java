package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.min.zblog.data.entity.TmVisitHst;

@Repository
public interface VisitHstDao extends JpaRepository<TmVisitHst, Long> {
	@Transactional
	@Modifying
	@Query(value = "delete from tm_visit_hst where article_id = ?1", nativeQuery = true)
	public void deleteTmVisitHstByArticleId(Long id);
}
