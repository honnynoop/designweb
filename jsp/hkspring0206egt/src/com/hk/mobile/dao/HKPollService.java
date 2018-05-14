package com.hk.mobile.dao;

import java.util.List;

import com.hk.mobile.help.PollBean;
import com.hk.mobile.member.model.HKPoll;
import com.hk.mobile.member.model.HKPollSub;
import com.hk.mobile.member.model.HKVoter;

public interface HKPollService {

	void makePoll(PollBean pbean);

	List<HKPoll> getPollAllList(String id);

	HKPoll getPoll(HKPoll poll);

	 List<HKPollSub> getPollSubList(HKPoll poll);

	void polling(HKVoter voter);

}
