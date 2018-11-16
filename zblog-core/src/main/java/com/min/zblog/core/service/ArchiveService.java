package com.min.zblog.core.service;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.PageInfo;

/**
 * <p>Title: ArchiveService</p>
 * <p>Description: </p>
 * @author	zhouzm
 * @date	2018年9月7日
 * @version 1.0
 */
public interface ArchiveService {
	public List<ArchiveInfo> fetchArchiveInfo();
	public PageInfo<ArchiveInfo> queryArchiveByPage(long pageSize, long currentPage, Map<String, Object> map);
}
