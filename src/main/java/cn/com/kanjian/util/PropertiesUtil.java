package cn.com.kanjian.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 更新Properties文件的工具类
 * @author liuyk
 */
public class PropertiesUtil {

	private Properties prop = new Properties();
	private String realPath;

	private void init(String properties) {
		if (realPath != null)
			throw new RuntimeException("Properties info has been inited!");
		try {
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			prop.load(loader.getResourceAsStream(properties));
			realPath = loader.getResource(properties).getPath();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public PropertiesUtil(String propertiesName) {
		init(propertiesName);
	}

	/**
	 * 根据key获取value
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		return prop.getProperty(key);
	}

	/**
	 * 根据key删除对应的键
	 * 
	 * @param key
	 */
	public void remove(String key) {
		prop.remove(key);
		store();
	}

	/**
	 * 更新properties文件。如果传入的key存在，则传入的value将覆盖原有的值key
	 * 如果传入的key不存在，则在properties文件中新增加相应的键值信息
	 * 
	 * @param key
	 * @param val
	 */
	public void set(String key, String val) {
		prop.setProperty(key, val);
		store();
	}
	
	/**
	 * 批量更新
	 * @param values
	 */
	public void set(Map<String, String> values) {
		for (Entry<String, String> e : values.entrySet()) {
			prop.setProperty(e.getKey(), e.getValue());
		}
		store();
	}

	/**
	 * 保存properties文件
	 */
	private void store() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(realPath);
			prop.store(fos, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 判断properties文件中是否存在某个键
	 * 
	 * @param key
	 * @return
	 */
	public boolean hasKey(String key) {
		return prop.containsKey(key);
	}

	/**
	 * 判断properties文件中是否存在某个value
	 * 
	 * @param value
	 * @return
	 */
	public boolean hasValue(String value) {
		return prop.containsValue(value);
	}

	/**
	 * 获取键值对信息
	 * 
	 * @return
	 */
	public Map<String, String> get() {
		Map<String, String> map = new HashMap<String, String>();
		Set<Entry<Object, Object>> entrys = prop.entrySet();
		for (Entry<Object, Object> en : entrys) {
			map.put((String) en.getKey(), (String) en.getValue());
		}
		return map;
	}
}