package com.min.zblog.api.rpc;

import java.util.Map;

import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.VisitHstInfo;
import com.min.zblog.facility.enums.VisitType;

public interface VisitHstService {
	public void addVisitHst(Long id, String ip, String browser, VisitType type);
	public PageInfo<VisitHstInfo> queryVisitHstByPage(long pageSize, long currentPage, Map<String, Object> map);
}
