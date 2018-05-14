package com.hk.mobile.member.model;

import java.io.Serializable;
import java.util.Date;

public class HKBoard implements Serializable {
	private int seq;
	private String id;
	private String title;
	private String content;
	private Date wdate;
	public HKBoard() {
		
	}
	public HKBoard(int seq, String id, String title, String content, Date wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
	}
	public HKBoard(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "HKBoard [seq=" + seq + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", wdate=" + wdate + "]";
	}
	
	
	
	
}
/*
create table HKMOBILE_board(
seq number primary key,
id varchar2(50) not null,
title varchar2(100) not null,
content varchar2(4000) not null,
wdate date not null
);
ALTER TABLE HKMOBILE_board
	ADD CONSTRAINT FK_HKMOBILE_board_memeber
FOREIGN KEY (id)
		 REFERENCES hkmobile_memeber (id);

create sequence seq_HKMOBILE_board
start with 1 increment by 1;
*/