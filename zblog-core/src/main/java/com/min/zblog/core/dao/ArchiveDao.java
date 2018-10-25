package com.min.zblog.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.min.zblog.data.entity.TmArchive;

@Repository
public interface ArchiveDao extends JpaRepository<TmArchive, Long> {
	public TmArchive findByName(String name);
}
