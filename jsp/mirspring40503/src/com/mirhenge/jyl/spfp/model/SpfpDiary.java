package com.mirhenge.jyl.spfp.model;

import java.io.Serializable;
import java.util.Date;

//com.mirhenge.jyl.spfp.model.SpfpDiary

public class SpfpDiary implements Serializable {

	private int seq;
	private String id;
	private Date wdate;
	private String content;
	private String ref;
	private String img;
	private int team;
	
	public SpfpDiary(int seq, String id, Date wdate, String content, String ref, String img, int team) {
		super();
		this.seq = seq;
		this.id = id;
		this.wdate = wdate;
		this.content = content;
		this.ref = ref;
		this.img = img;
		this.team = team;
	}

	public SpfpDiary(int seq, String id, Date wdate, String content, String ref, String img) {
		super();
		this.seq = seq;
		this.id = id;
		this.wdate = wdate;
		this.content = content;
		this.ref = ref;
		this.img = img;
	}

	public SpfpDiary(String id, String content, String ref, String img) {
		super();
		this.id = id;
		this.content = content;
		this.ref = ref;
		this.img = img;
	}

	public SpfpDiary() {
		super();
	}

	@Override
	public String toString() {
		return "SpfpDiary [seq=" + seq + ", id=" + id + ", wdate=" + wdate + ", content=" + content + ", ref=" + ref
				+ ", img=" + img + ", team=" + team + "]";
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

	public Date getWdate() {
		return wdate;
	}

	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}
}
