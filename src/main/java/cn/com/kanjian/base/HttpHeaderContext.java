package cn.com.kanjian.base;

public class HttpHeaderContext {

	/**版本号*/
	private static ThreadLocal<String> version = new ThreadLocal<String>();
	/**平台类型，0：Android，1：IOS*/
	private static ThreadLocal<String> systype = new ThreadLocal<String>();
	/**渠道号*/
	private static ThreadLocal<String> channel = new ThreadLocal<String>();
	/**访问标识*/
	private static ThreadLocal<String> token = new ThreadLocal<String>();
	/**用户ID*/
	private static ThreadLocal<Long> userid = new ThreadLocal<Long>();
	/**设备标识*/
	private static ThreadLocal<String> device = new ThreadLocal<String>();

	
	public static String getVersion() {
		return version.get();
	}

	public static void setVersion(String _version) {
		version.set(_version);
	}

	public static void removeVersion() {
		version.remove();
	}

	/**平台类型，0：Android，1：IOS*/
	public static String getSystype() {
		return systype.get();
	}

	public static void setSystype(String _systype) {
		systype.set(_systype);
	}

	public static void removeSystype() {
		systype.remove();
	}

	public static String getToken() {
		return token.get();
	}
	
	public static void setToken(String _token) {
		token.set(_token);
	}
	
	public static void removeToken() {
		token.remove();
	}
	
	/**
	 * 获取渠道号
	 */
	public static String getChannel() {
		return channel.get();
	}
	
	public static void setChannel(String _channel) {
		channel.set(_channel);
	}
	
	public static void removeChannel() {
		channel.remove();
	}
	
	/**
	 * 获取用户ID
	 */
	public static Long getuserid() {
		return userid.get();
	}
	
	public static void setUserid(Long _userid) {
		userid.set(_userid);
	}
	
	public static void removeUserid() {
		userid.remove();
	}
	
	/**
	 * 获取设备标识
	 * @return
	 */
	public static String getDevice() {
		return device.get();
	}
	
	public static void setDevice(String _device) {
		device.set(_device);
	}
	
	public static void removeDevice() {
		device.remove();
	}
	
}
