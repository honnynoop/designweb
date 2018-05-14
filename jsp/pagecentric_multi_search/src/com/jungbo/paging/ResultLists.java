package com.jungbo.paging;

import java.util.List;

public class ResultLists<E> {
	private List<E> results;
	
	private PageBean pageBean;
	
	public ResultLists(List<E> results) {
		this.results = results;
	}
	
	public ResultLists() {
	}

	public List<E> getResults() {
		return results;
	}
	public void setResults(List<E> results) {
		this.results = results;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
	
}//
