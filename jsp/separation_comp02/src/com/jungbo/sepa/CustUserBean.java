package com.jungbo.sepa;

import java.io.Serializable;

public class CustUserBean implements Serializable{
	private String id;
	private String[] ids;
	private String name;
	private String address;
	public CustUserBean() {}//기본생성자 반드시 필요
	public CustUserBean(String id, String name, String address) {
		this.id = id;
		this.name = name;
		this.address = address;
	}//
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	public String toString() {
		return "["+id+"/"+name+"/"+address+"]";
	}//
}//
