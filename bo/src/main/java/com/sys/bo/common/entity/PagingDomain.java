package com.sys.bo.common.entity;

import lombok.Data;

@Data
public class PagingDomain {
	private int page;
	private int size;
	private int offset;
	private int totalCnt;
	private String sort;
	private String sortName;
	private int totalPages;
	private String isPaging;
	
	public void setSort(String sort) {
		this.sortName = sort.split(",")[0];
		this.sort = sort.split(",")[1];
		this.offset = this.page * this.size;
	}
}
