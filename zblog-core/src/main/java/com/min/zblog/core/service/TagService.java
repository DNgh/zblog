package com.min.zblog.core.service;

import java.util.List;

import com.min.zblog.data.view.TagInfo;

public interface TagService {
	public List<TagInfo> fetchTagInfo();
}
