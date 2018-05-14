package com.mirhenge.jyl.poll.model;

import java.io.Serializable;
import java.util.Date;
//com.mirhenge.jyl.poll.model.JYLVoter
public class JYLVoter implements Serializable{

	private int vogterid;
	private int pollid;
	private int pollsubid;
	private String id;
	private Date regdate;
	public JYLVoter() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JYLVoter(int vogterid, int pollid, int pollsubid, String id,
			Date regdate) {
		super();
		this.vogterid = vogterid;
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
		this.regdate = regdate;
	}
	public JYLVoter(int vogterid, int pollid, int pollsubid, String id) {
		super();
		this.vogterid = vogterid;
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
	}
	
	public JYLVoter(int pollid, int pollsubid, String id) {
		super();
		this.pollid = pollid;
		this.pollsubid = pollsubid;
		this.id = id;
	}
	@Override
	public String toString() {
		return "HKVoter [vogterid=" + vogterid + ", pollid=" + pollid
				+ ", pollsubid=" + pollsubid + ", id=" + id + ", regdate="
				+ regdate + "]";
	}
	public int getVogterid() {
		return vogterid;
	}
	public void setVogterid(int vogterid) {
		this.vogterid = vogterid;
	}
	public int getPollid() {
		return pollid;
	}
	public void setPollid(int pollid) {
		this.pollid = pollid;
	}
	public int getPollsubid() {
		return pollsubid;
	}
	public void setPollsubid(int pollsubid) {
		this.pollsubid = pollsubid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
