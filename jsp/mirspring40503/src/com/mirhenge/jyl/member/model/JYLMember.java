package com.mirhenge.jyl.member.model;

import java.io.Serializable;
//com.mirhenge.jyl.member.model.JYLMember
public class JYLMember implements Serializable{
	private static final long serialVersionUID = -5076656664574067436L;

	private String id;
	private String name;
	private String email;
	private String pwd;
	private int delflag;
	//20160106 수정
	private int auth=3;
	
	// 20174 수정
	private int team;
	
	public JYLMember() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JYLMember(String id, String name, String email, String pwd,
			int delflag, int auth) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.delflag = delflag;
		this.auth = auth;
	}
	public JYLMember(String id, String name, String email, String pwd) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
	}
	//추가
	public JYLMember(String id, String name, String email, String pwd, int delflag, int auth, int team) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.pwd = pwd;
		this.delflag = delflag;
		this.auth = auth;
		this.team = team;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	//추가
	@Override
	public String toString() {
		return "JYLMember [id=" + id + ", name=" + name + ", email=" + email + ", pwd=" + pwd + ", delflag=" + delflag
				+ ", auth=" + auth + ", team=" + team + "]";
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
