package cn.com.kanjian.base;

import java.util.List;

public class Pager<T> {

	//总计多少条数据
	private List<T> datas;
	//开始页
	private int pageOffset;
	//每页多少条数据
	private int pageSize;
	
	/*
	private long totalRecord;
	private Map<String, String> requestParameters;

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public Map<String, String> getRequestParameters() {
		return requestParameters;
	}

	public void setRequestParameters(String key, String value) {
		if (this.requestParameters == null) {
			requestParameters = new HashMap<String, String>();
		}
		this.requestParameters.put(key, value);
	}
	 */
	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}

	public int getPageOffset() {
		return pageOffset;
	}

	public void setPageOffset(int pageOffset) {
		this.pageOffset = pageOffset;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
