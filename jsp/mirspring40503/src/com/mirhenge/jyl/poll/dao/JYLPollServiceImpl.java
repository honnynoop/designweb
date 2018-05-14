package com.mirhenge.jyl.poll.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirhenge.jyl.poll.help.PollBean;
import com.mirhenge.jyl.poll.model.JYLPoll;
import com.mirhenge.jyl.poll.model.JYLPollSub;
import com.mirhenge.jyl.poll.model.JYLVoter;

@Service
public class JYLPollServiceImpl implements JYLPollService {
	
	@Autowired
	private JYLPollDao jYLPollDao;
	
	@Override
	@Transactional
	public void makePoll(PollBean pbean) {
		
		JYLPoll poll=new JYLPoll(
				pbean.getId(),
				pbean.getQuestion(),
				pbean.getSdate(),
				pbean.getEdate(),
				pbean.getItemcount(),0
				);
		jYLPollDao.makePoll(poll);
		
		int itemcount=pbean.getItemcount();
		String[] answer=pbean.getPollnum();
		for (int i = 0; i < itemcount; i++) {
			JYLPollSub pollsub=new JYLPollSub();
			pollsub.setAnswer(answer[i]);
			jYLPollDao.makePollSub(pollsub);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLPoll> getPollAllList(String id) {
		List<JYLPoll> plists=jYLPollDao.getPollAllList();
		List<JYLPoll> plists2=new ArrayList<JYLPoll>();
		for (JYLPoll poll:plists) {
			//String id=poll.getId();
			int pollid=poll.getPollid();
			int count=jYLPollDao.isVote(new JYLVoter(pollid,-1,id));
			//System.out.println("-------------------------------------::::"+count);
			if(count>0){
				poll.setVote(true);//투표했다.
			}else{
				poll.setVote(false);//
			}
			plists2.add(poll);
		}
		return plists2;
	}

	@Override
	@Transactional(readOnly=true)
	public JYLPoll getPoll(JYLPoll poll) {
		return jYLPollDao.getPoll(poll);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLPollSub> getPollSubList(JYLPoll poll) {
		return jYLPollDao.getPollSubList(poll);
	}

	@Override
	@Transactional
	public void polling(JYLVoter voter) {
		jYLPollDao.pollingVote(voter);
		jYLPollDao.pollingSub(voter);
		jYLPollDao.pollingPoll(voter);
	}

}
