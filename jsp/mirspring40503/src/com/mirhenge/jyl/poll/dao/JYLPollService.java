package com.mirhenge.jyl.poll.dao;

import java.util.List;

import com.mirhenge.jyl.poll.help.PollBean;
import com.mirhenge.jyl.poll.model.JYLPoll;
import com.mirhenge.jyl.poll.model.JYLPollSub;
import com.mirhenge.jyl.poll.model.JYLVoter;

public interface JYLPollService {

	void makePoll(PollBean pbean);
	List<JYLPoll> getPollAllList(String id);
	JYLPoll getPoll(JYLPoll poll);
	List<JYLPollSub> getPollSubList(JYLPoll poll);
	void polling(JYLVoter voter);

}
