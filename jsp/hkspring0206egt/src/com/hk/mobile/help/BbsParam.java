package com.hk.mobile.help;

import java.io.Serializable;

public class BbsParam implements Serializable{
	private int seq;
	private String id;
	private String title;
	private String content;
	private String wdate;
	
	private int ref;
	private int step; 
	private int depth;
	private int parent;
	private int delflag; //0,1
	
	//private int readcount;
	//------------searching
	private String s_category;
	private String s_keyword;
	//------------paging 
	private int recordCountPerPage=10;
	private int pageNumber=0;
	
	private int start=1;
	private int end=10;

	public BbsParam() {
		
	}

	public BbsParam(int seq, String id, String title, String content,
			String wdate, int ref, int step, int depth, int parent,
			int delflag, String s_category, String s_keyword,
			int recordCountPerPage, int pageNumber, int start, int end) {
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
		this.s_category = s_category;
		this.s_keyword = s_keyword;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public BbsParam(String id, String title, String content, String s_category,
			String s_keyword, int recordCountPerPage, int pageNumber,
			int start, int end) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.s_category = s_category;
		this.s_keyword = s_keyword;
		this.recordCountPerPage = recordCountPerPage;
		this.pageNumber = pageNumber;
		this.start = start;
		this.end = end;
	}

	public BbsParam(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	@Override
	public String toString() {
		return "BbsParam [seq=" + seq + ", id=" + id + ", title=" + title
				+ ", content=" + content + ", wdate=" + wdate + ", ref=" + ref
				+ ", step=" + step + ", depth=" + depth + ", parent=" + parent
				+ ", delflag=" + delflag + ", s_category=" + s_category
				+ ", s_keyword=" + s_keyword + ", recordCountPerPage="
				+ recordCountPerPage + ", pageNumber=" + pageNumber
				+ ", start=" + start + ", end=" + end + "]";
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

	public String getS_category() {
		return s_category;
	}

	public void setS_category(String s_category) {
		this.s_category = s_category;
	}

	public String getS_keyword() {
		return s_keyword;
	}

	public void setS_keyword(String s_keyword) {
		this.s_keyword = s_keyword;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
		
}
