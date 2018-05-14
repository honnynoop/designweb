package com.hk.mobile.member.model;

import java.io.Serializable;

public class HKMember implements Serializable{
	private static final long serialVersionUID = -5076656664574067436L;

	private String id;
	private String name;
	private String email;
	private String pwd;
	private int delflag;
	//20160106 ¼öÁ¤
	private int auth=3;
	public HKMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HKMember(String id, String name, String email, String pwd,
			int delflag, int auth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.delflag = delflag;
		this.auth = auth;
	}
	public HKMember(String id, String name, String email, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "HKMember [id=" + id + ", name=" + name + ", email=" + email
				+ ", pwd=" + pwd + ", delflag=" + delflag + ", auth=" + auth
				+ "]";
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getDelflag() {
		return delflag;
	}
	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}
	public int getAuth() {
		return auth;
	}
	public void setAuth(int auth) {
		this.auth = auth;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
