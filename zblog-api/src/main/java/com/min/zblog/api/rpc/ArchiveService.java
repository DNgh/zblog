package com.min.zblog.api.rpc;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.view.ArchiveInfo;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.facility.exception.ProcessException;

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
	public void deleteArchiveById(Long archiveId) throws ProcessException;
	public void initArchiveInfo();
}
