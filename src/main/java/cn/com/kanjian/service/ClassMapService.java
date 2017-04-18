package cn.com.kanjian.service;

import java.util.Map;

public class ClassMapService {

	private Map<String, Object> maps;

	public Map<String, Object> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, Object> maps) {
		this.maps = maps;
	}
	
	public Object getBean(String className) {
		if (maps == null) {
			return null;
		}
		return maps.get(className);
	}
	
}
