package com.hk.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.help.PollBean;
import com.hk.mobile.member.model.HKPoll;
import com.hk.mobile.member.model.HKPollSub;
import com.hk.mobile.member.model.HKVoter;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
@Service("hKPollService")
public class HKPollServiceImpl extends EgovAbstractServiceImpl implements HKPollService {
	
	@Resource(name="hKPollDao")
	private HKPollDao hKPollDao;
	
	@Override
	@Transactional
	public void makePoll(PollBean pbean) {
		HKPoll poll=new HKPoll(
				pbean.getId(),
				pbean.getQuestion(),
				pbean.getSdate(),
				pbean.getEdate(),
				pbean.getItemcount(),0
				);
		int itemcount=pbean.getItemcount();
		String[] answer=pbean.getPollnum();
		
		hKPollDao.makePoll(poll);
		for (int i = 0; i < itemcount; i++) {
			HKPollSub pollsub=new HKPollSub();
			pollsub.setAnswer(answer[i]);
			hKPollDao.makePollSub(pollsub);
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKPoll> getPollAllList(String id) {
		List<HKPoll> plists=hKPollDao.getPollAllList();
		List<HKPoll> plists2=new ArrayList<HKPoll>();
		for (HKPoll poll:plists) {
			//String id=poll.getId();
			int pollid=poll.getPollid();
			int count=hKPollDao.isVote(new HKVoter(pollid,-1,id));
			//System.out.println("-------------------------------------::::"+count);
			if(count>0){
				poll.setVote(true);//��ǥ�ߴ�.
			}else{
				poll.setVote(false);//
			}
			plists2.add(poll);
		}
		return plists2;
	}

	@Override
	@Transactional(readOnly=true)
	public HKPoll getPoll(HKPoll poll) {
		return hKPollDao.getPoll( poll);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKPollSub> getPollSubList(HKPoll poll) {
		return hKPollDao.getPollSubList( poll);
	}

	@Override
	@Transactional
	public void polling(HKVoter voter) {
		hKPollDao.pollingVote(voter);
		hKPollDao.pollingSub(voter);
		hKPollDao.pollingPoll(voter);
	}
}
