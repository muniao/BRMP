package com.wondersgroup.empi.po.webservicepo;

import java.util.List;

/**
 * 响应申请数据的批量返回数据
 *
 * @param <T>
 */
public class ResApplyBatchParams<T> extends ResApplyParams {
	/**
	 * 批量数据
	 */
	private List<T> data;
	/**
	 * 第几页
	 */
	private int pages;
	/**
	 * 每页大小
	 */
	private int pageSize;
	/**
	 * 总记录数
	 */
	private int records;
	
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getRecords() {
		return records;
	}
	public void setRecords(int records) {
		this.records = records;
	}
	
}
