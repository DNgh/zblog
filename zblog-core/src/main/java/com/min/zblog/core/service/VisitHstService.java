package com.min.zblog.core.service;

import com.min.zblog.facility.enums.VisitType;

public interface VisitHstService {
	public void addVisitHst(Long id, String ip, String browser, VisitType type);
}
