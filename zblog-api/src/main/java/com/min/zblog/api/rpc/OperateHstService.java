package com.min.zblog.api.rpc;

import java.util.Map;

import com.min.zblog.data.view.OperateHstInfo;
import com.min.zblog.data.view.PageInfo;

public interface OperateHstService {
	//public void addOperateHst(Long id, String ip, String browser, VisitType type);
	public PageInfo<OperateHstInfo> queryOperateHstByPage(long pageSize, long currentPage, Map<String, Object> map);
}
