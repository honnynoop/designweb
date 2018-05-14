package com.hk.mobile.member.model;

import java.io.Serializable;
import java.util.Date;

public class HKMBoard implements Serializable {

	private int seq;
	private String id;
	private String title;
	private String content;
	private Date wdate;
	private int ref;
	private int step;
	private int depth;
	private int parent;
	private int delflag;
	public HKMBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HKMBoard(int seq, String id, String title, String content,
			Date wdate, int ref, int step, int depth, int parent, int delflag) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.parent = parent;
		this.delflag = delflag;
	}
	public HKMBoard(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	
	public HKMBoard(int seq, String id, String title, String content, Date wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
	}
	@Override
	public String toString() {
		return "KHMBoard [seq=" + seq + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", wdate=" + wdate + ", ref=" + ref
				+ ", step=" + step + ", depth=" + depth + ", parent=" + parent
				+ ", delflag=" + delflag + "]";
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
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParent() {
		return parent;
	}
	public void setParent(int parent) {
		this.parent = parent;
	}
	public int getDelflag() {
		return delflag;
	}
	public void setDelflag(int delflag) {
		this.delflag = delflag;
	}

}
