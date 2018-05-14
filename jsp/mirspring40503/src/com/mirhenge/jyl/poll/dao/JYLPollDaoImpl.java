package com.mirhenge.jyl.poll.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.mirhenge.jyl.poll.model.JYLPoll;
import com.mirhenge.jyl.poll.model.JYLPollSub;
import com.mirhenge.jyl.poll.model.JYLVoter;
@Repository
public class JYLPollDaoImpl implements JYLPollDao {
	
	String ns="JYLPoll.";
	
	@Autowired
	private SqlSession  sqlSession;
	
	@Override
	public void makePoll(JYLPoll poll) {
		sqlSession.insert(ns+"makePoll",poll);
	}

	@Override
	public void makePollSub(JYLPollSub pollsub) {
		sqlSession.insert(ns+"makePollSub",pollsub);
	}

	@Override
	public List<JYLPoll> getPollAllList() {
		return sqlSession.selectList(ns+"getPollAllList");
	}

	@Override
	public int isVote(JYLVoter hkVoter) {
		return (Integer)sqlSession.selectOne(ns+"isVote",hkVoter);
	}

	@Override
	public JYLPoll getPoll(JYLPoll poll) {
		return sqlSession.selectOne(ns+"getPoll",poll);
	}

	@Override
	public List<JYLPollSub> getPollSubList(JYLPoll poll) {
		return sqlSession.selectList(ns+"getPollSubList",poll);
	}

	@Override
	public void pollingVote(JYLVoter voter) {
		sqlSession.insert(ns+"pollingVote",voter);
	}

	@Override
	public void pollingSub(JYLVoter voter) {
		sqlSession.update(ns+"pollingSub",voter);
	}

	@Override
	public void pollingPoll(JYLVoter voter) {
		sqlSession.update(ns+"pollingPoll",voter);
	}

}
