package com.hk.mobile.member.model;

import java.io.Serializable;

public class HKCalendar implements Serializable{
	private int seq;
	private String id;
	private String title;
	private String content;
	private String wdate;    //201509161530
	private String regdate;
	public HKCalendar() {
	}
	public HKCalendar(int seq, String id, String title, String content,
			String wdate, String regdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.regdate = regdate;
	}
	public HKCalendar(String id, String title, String content, String wdate) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "FintechCalendar [seq=" + seq + ", id=" + id + ", title="
				+ title + ", content=" + content + ", wdate=" + wdate
				+ ", regdate=" + regdate + "]";
	}

}
