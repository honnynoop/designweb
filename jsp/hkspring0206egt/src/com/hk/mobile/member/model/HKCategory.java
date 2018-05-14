package com.hk.mobile.member.model;

import java.io.Serializable;

public class HKCategory implements Serializable {

	private int category;
	private String categoryname ;
	public HKCategory() {
	}
	public HKCategory(int category, String categoryname) {
		this.category = category;
		this.categoryname = categoryname;
	}
	@Override
	public String toString() {
		return "HKCategory [category=" + category + ", categoryname="
				+ categoryname + "]";
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	
}
