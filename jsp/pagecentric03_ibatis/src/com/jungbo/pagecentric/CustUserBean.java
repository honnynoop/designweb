package com.jungbo.pagecentric;

import java.io.Serializable;
import java.util.Vector;

public class CustUserBean implements Serializable{

	private static final long serialVersionUID = 2273334650492328953L;
	private String id;
	private String name;
	private String address;
	private String[] delck;
	
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
	public String[] getDelck() {
		return delck;
	}
	public void setDelck(String[] delck) {
		this.delck = delck;
	}
	public String toString() {
		return "["+id+"/"+name+"/"+address+"]";
	}//
}//
