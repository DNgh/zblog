package com.min.zblog.core.service;

import java.util.List;
import java.util.Map;

import com.min.zblog.data.entity.TmCategory;
import com.min.zblog.data.entity.TmTag;
import com.min.zblog.data.view.PageInfo;
import com.min.zblog.data.view.TagInfo;
import com.min.zblog.facility.exception.ProcessException;

public interface TagService {
	public List<TagInfo> fetchTagInfo();
	public PageInfo<TagInfo> queryTagByPage(long pageSize, long currentPage, Map<String, Object> map);
	public TagInfo findOneTag(Long id);
	public TmTag saveTag(Map<String, Object> map);
	public TmTag addTag(Map<String, Object> map);
	public void deleteTagById(Long id) throws ProcessException;
	public void initTagInfo();
}
