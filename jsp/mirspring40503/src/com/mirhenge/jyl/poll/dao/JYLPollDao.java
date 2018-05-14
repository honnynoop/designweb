package com.mirhenge.jyl.poll.dao;
import java.util.List;

import com.mirhenge.jyl.poll.model.JYLPoll;
import com.mirhenge.jyl.poll.model.JYLPollSub;
import com.mirhenge.jyl.poll.model.JYLVoter;

public interface JYLPollDao {

	void makePoll(JYLPoll poll);
	void makePollSub(JYLPollSub pollsub);
	List<JYLPoll> getPollAllList();
	int isVote(JYLVoter hkVoter);
	JYLPoll getPoll(JYLPoll poll);
	List<JYLPollSub> getPollSubList(JYLPoll poll);
	void pollingVote(JYLVoter voter);
	void pollingSub(JYLVoter voter);
	void pollingPoll(JYLVoter voter);

}
