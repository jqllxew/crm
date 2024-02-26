package com.zhidi.crm.common;

import java.util.List;

public class Pager<T> {

	private Integer page = 1;
	private Integer rows = 1;
	private Integer totalPage = 0;
	private Integer totalRows = 0;

	private List<T> data;
	
	public Pager() {
		super();
	}
	public Pager(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}

	public Integer getPage() {
		if(page < 1)
			page = 1;
		if(page > getTotalPage())
			page = getTotalPage();
		
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
		
	}
	public void setTotalRows(Integer totalRows) {
		if (totalRows != null){
			this.totalRows = totalRows;
		}
		getTotalPage();
	}

	public Integer getTotalPage() {
		if(rows > 0){
			totalPage = (totalRows-1)/rows + 1;
		}
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getTotalRows() {
		return totalRows;
	}


	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getLimits(){
		return  (this.page-1) * this.rows;
	}

}
