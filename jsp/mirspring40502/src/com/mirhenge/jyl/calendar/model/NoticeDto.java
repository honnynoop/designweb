package com.mirhenge.jyl.calendar.model;

import java.io.Serializable;
//com.mirhenge.jyl.calendar.model.NoticeDto
public class NoticeDto implements Serializable{

	public NoticeDto() {
		
	}
	private int year;       
	private int month;      
	private int day;        
	private String title;  
	private String content; 
	
	public NoticeDto(int year, int month, int day, String title, String content) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.title = title;
		this.content = content;
	}
	

	@Override
	public String toString() {
		return "NoticeDto [year=" + year + ", month=" + month + ", day=" + day
				+ ", title=" + title + ", content=" + content + "]";
	}


	public NoticeDto(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}
	
	

	public NoticeDto(int year, int month, int day) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
	}


	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
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
	
	
}
