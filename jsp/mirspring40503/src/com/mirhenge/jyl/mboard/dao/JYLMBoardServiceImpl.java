package com.mirhenge.jyl.mboard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirhenge.jyl.mboard.help.BbsParam;
import com.mirhenge.jyl.mboard.model.JYLMBoard;
import com.mirhenge.jyl.member.model.JYLMember;
@Service
public class JYLMBoardServiceImpl implements JYLMBoardService {
	
	@Autowired
	private JYLMBoardDao jYLMBoardDao;
	
	@Override
	@Transactional
	public void writeBbs(JYLMBoard mboard) {
		jYLMBoardDao.writeBbs(mboard);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLMBoard> getBbsList() {
		return jYLMBoardDao.getBbsList();
	}

	@Override
	@Transactional(readOnly=true)
	public JYLMBoard getBbs(JYLMBoard mboard) {
		return jYLMBoardDao.getBbs(mboard);
	}

	@Override
	@Transactional
	public void reply(JYLMBoard mboard) throws Exception {
		jYLMBoardDao.replyBbsUpdate(mboard);
		jYLMBoardDao.replyBbsInsert(mboard);
	}

	@Override
	@Transactional
	public void updateBbs(JYLMBoard bbs) {
		jYLMBoardDao.updateBbs(bbs);
	}

	@Override
	@Transactional
	public void bbsdelete(JYLMBoard bbs) {
		jYLMBoardDao.bbsdelete(bbs);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLMBoard> getBbsPageList(BbsParam param) throws Exception {
		return jYLMBoardDao.getBbsPageList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBbsTotalCount(BbsParam param) throws Exception {
		return (int)jYLMBoardDao.getBbsTotalCount(param);
	}

	@Override
	@Transactional(readOnly=true)
	public String preView(int seq) {
		return jYLMBoardDao.preView(seq);
	}



}
