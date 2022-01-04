package com.github.hmld.common.core.page;

import java.io.Serializable;
import java.util.List;

public class TableDataInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long total;
	private List<?> rows;
	private int code;
	private String msg;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public TableDataInfo() {
	}
	public TableDataInfo(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}
}
