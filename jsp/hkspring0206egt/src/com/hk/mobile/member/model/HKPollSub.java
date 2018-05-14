package com.hk.mobile.member.model;

public class HKPollSub {

	private int pollsubid;
	private int pollid;
	private String answer;
	private int acount;
	public HKPollSub() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HKPollSub(int pollsubid, int pollid, String answer, int acount) {
		super();
		this.pollsubid = pollsubid;
		this.pollid = pollid;
		this.answer = answer;
		this.acount = acount;
	}
	public HKPollSub(int pollid, String answer, int acount) {
		super();
		this.pollid = pollid;
		this.answer = answer;
		this.acount = acount;
	}
	@Override
	public String toString() {
		return "HKPollSub [pollsubid=" + pollsubid + ", pollid=" + pollid
				+ ", answer=" + answer + ", acount=" + acount + "]";
	}
	public int getPollsubid() {
		return pollsubid;
	}
	public void setPollsubid(int pollsubid) {
		this.pollsubid = pollsubid;
	}
	public int getPollid() {
		return pollid;
	}
	public void setPollid(int pollid) {
		this.pollid = pollid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getAcount() {
		return acount;
	}
	public void setAcount(int acount) {
		this.acount = acount;
	}

}
