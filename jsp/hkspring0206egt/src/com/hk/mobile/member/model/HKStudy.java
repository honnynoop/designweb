package com.hk.mobile.member.model;
import java.io.Serializable;
import java.util.Date;

public class HKStudy implements Serializable {

	private int seq;
	private int num;
	private int category=1;
	private String id;
	private String title ;
	private String content ;
	private String filename;
	private Date wdate;
	private int readcount ;
	
	//¡∂¿Œ---
	private String categoryname ;
	
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	//------------

	public HKStudy() {}
	public HKStudy(int seq, int num, int category, String id, String title,
			String content, String filename, Date wdate, int readcount) {
		super();
		this.seq = seq;
		this.num = num;
		this.category = category;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.wdate = wdate;
		this.readcount = readcount;
	}
	public HKStudy(int num, int category, String id, String title,
			String content, String filename) {
		super();
		this.num = num;
		this.category = category;
		this.id = id;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "HKStudy [seq=" + seq + ", num=" + num + ", category="
				+ category + ", id=" + id + ", title=" + title + ", content="
				+ content + ", filename=" + filename + ", wdate=" + wdate
				+ ", readcount=" + readcount + "]";
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	
	
	
}
