package com.hk.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.mobile.member.model.HKPoll;
import com.hk.mobile.member.model.HKPollSub;
import com.hk.mobile.member.model.HKVoter;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
@Repository("hKPollDao")
public class HKPollDao extends EgovAbstractMapper{


	
	private String ns="HKPoll.";
	

	public void makePoll(HKPoll poll) {
		insert(ns+"makePoll",poll);
	}


	public void makePollSub(HKPollSub pollsub) {
		insert(ns+"makePollSub",pollsub);
	}

	
	public List<HKPoll> getPollAllList() {
		List<HKPoll> list=new ArrayList<HKPoll>();
		return list=selectList(ns+"getPollAllList");
	}


	public int isVote(HKVoter hkVoter) {
		return (Integer)selectOne(ns+"isVote",hkVoter);
	}


	public HKPoll getPoll(HKPoll poll) {
		HKPoll hp=new HKPoll();
		return hp =(HKPoll)selectOne(ns+"getPoll",poll);
	}


	public List<HKPollSub> getPollSubList(HKPoll poll) {
		List<HKPollSub> lists=new ArrayList<HKPollSub>();
		return lists=selectList(ns+"getPollSubList",poll);
	}


	public void pollingVote(HKVoter voter) {
		insert(ns+"pollingVote",voter);
	}

	
	public void pollingSub(HKVoter voter) {
		update(ns+"pollingSub",voter);
	}

	
	public void pollingPoll(HKVoter voter) {
		update(ns+"pollingPoll",voter);
	}


}
