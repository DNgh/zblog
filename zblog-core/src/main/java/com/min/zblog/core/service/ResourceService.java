package com.min.zblog.core.service;

import java.util.Collection;
import java.util.LinkedHashMap;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;

public interface ResourceService {
	public LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>> buildResourceMap();
}
