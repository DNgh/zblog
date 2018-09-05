package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmVisitHst;

@Repository
public interface VisitHstDao extends JpaRepository<TmVisitHst, Long> {

}
