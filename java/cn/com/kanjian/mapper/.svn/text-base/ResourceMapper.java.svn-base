package cn.com.kanjian.mapper;

import java.util.Map;

import cn.com.kanjian.base.Pager;
import cn.com.kanjian.model.Resource;

public interface ResourceMapper {

	void add(Resource resource);

	Resource load(Long id);
	
	/**
	 * 分页获取资源列表
	 * @param params
	 * 	pageOffset 开始的页码
	 *  
	 * @return
	 */
	Pager<Resource> findList(Map<String, Object> params);
	
}
