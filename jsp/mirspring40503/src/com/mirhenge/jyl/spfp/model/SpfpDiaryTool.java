package com.mirhenge.jyl.spfp.model;

import java.io.Serializable;
import java.util.Date;

//com.mirhenge.jyl.spfp.model.SpfpDiary

public class SpfpDiaryTool implements Serializable {

	private int seq;
	private String id;
	private Date wdate;
	private String content;
	private String ref;
	private String img;
	private int team;
	private String path;
	public SpfpDiaryTool() {
		super();
	}
	public SpfpDiaryTool(SpfpDiary diary, String path) {
		super();
		this.seq = diary.getSeq();
		this.id = diary.getId();
		this.wdate = diary.getWdate();
		this.content = diary.getContent();
		this.ref = diary.getRef();
		if(diary.getImg().contains("back")) {
			this.img=null;
		}else{
			this.img = diary.getImg();
		}
		this.team=diary.getTeam();
		this.path = path;
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
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
	
}
