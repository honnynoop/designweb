package com.hk.mobile.member.model;

import java.io.Serializable;
import java.util.Date;

public class HKPoll implements Serializable {

	private int pollid;
	private String id;
	private String question;
	private Date sdate;
	private Date edate;
	private int itemcount;
	private int polltotal;
	private Date regdate;
	//20160308
	private boolean vote;//투표를 했는가?

	
	public boolean isVote() {
		return vote;
	}
	public void setVote(boolean vote) {
		this.vote = vote;
	}
	public HKPoll() {
	}
	public HKPoll(int pollid, String id, String question, Date sdate,
			Date edate, int itemcount, int polltotal, Date regdate) {
		super();
		this.pollid = pollid;
		this.id = id;
		this.question = question;
		this.sdate = sdate;
		this.edate = edate;
		this.itemcount = itemcount;
		this.polltotal = polltotal;
		this.regdate = regdate;
	}
	public HKPoll(String id, String question, Date sdate, Date edate,
			int itemcount, int polltotal) {
		super();
		this.id = id;
		this.question = question;
		this.sdate = sdate;
		this.edate = edate;
		this.itemcount = itemcount;
		this.polltotal = polltotal;
	}
	public HKPoll(String id, String question, int itemcount, int polltotal) {
		super();
		this.id = id;
		this.question = question;
		this.itemcount = itemcount;
		this.polltotal = polltotal;
	}
	@Override
	public String toString() {
		return "HKPoll [pollid=" + pollid + ", id=" + id + ", question="
				+ question + ", sdate=" + sdate + ", edate=" + edate
				+ ", itemcount=" + itemcount + ", polltotal=" + polltotal
				+ ", regdate=" + regdate + "]";
	}
	public int getPollid() {
		return pollid;
	}
	public void setPollid(int pollid) {
		this.pollid = pollid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public int getItemcount() {
		return itemcount;
	}
	public void setItemcount(int itemcount) {
		this.itemcount = itemcount;
	}
	public int getPolltotal() {
		return polltotal;
	}
	public void setPolltotal(int polltotal) {
		this.polltotal = polltotal;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
