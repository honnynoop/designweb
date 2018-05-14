package com.jungbo.paging;
import java.io.Serializable;
import java.util.List;
public class ResultLists<E> implements Serializable{
	//DTO와 Page 정보를 한 번에
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
