package com.jungbo.paging;
import java.io.Serializable;
import java.util.Vector;
public class ResultLists<E> implements Serializable{
	//DTO�� Page ������ �� ���� ����
	private Vector<E> results;
	private PageBean pageBean;
	
	public ResultLists(Vector<E> results) {
		this.results = results;
	}
	public ResultLists() {
	}
	public Vector<E> getResults() {
		return results;
	}
	public void setResults(Vector<E> results) {
		this.results = results;
	}
	public PageBean getPageBean() {
		return pageBean;
	}
	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}
}//
