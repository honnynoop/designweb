package com.hk.mobile.dao;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKMBoard;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("hKMBoardService")
public class HKMBoardServiceImpl extends EgovAbstractServiceImpl implements HKMBoardService {
	
	@Resource(name="hKMBoardDao")
	private HKMBoardDao hKMBoardDao;
	
	@Override
	@Transactional
	public void writeBbs(HKMBoard mboard) {
		hKMBoardDao.writeBbs(mboard);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKMBoard> getBbsList() {
		return hKMBoardDao.getBbsList();
	}

	@Override
	@Transactional(readOnly=true)
	public HKMBoard getBbs(HKMBoard mboard) {
		return hKMBoardDao.getBbs(mboard);
	}

	@Override
	@Transactional
	public void reply(HKMBoard mboard) throws Exception {
		hKMBoardDao.replyBbsUpdate(mboard);
		hKMBoardDao.replyBbsInsert(mboard);
		//@Transactional test
		//throw new BSQLException("reply", "BSQLException");
	}

	@Override
	@Transactional
	public void updateBbs(HKMBoard bbs) {
		hKMBoardDao.updateBbs(bbs);
	}

	@Override
	@Transactional
	public void bbsdelete(HKMBoard bbs) {
		hKMBoardDao.bbsdelete(bbs);
	}
	@Override
	@Transactional(readOnly=true)
	public List<HKMBoard> getBbsPageList(BbsParam param) throws Exception {
		return hKMBoardDao.getBbsPageList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBbsTotalCount(BbsParam param) throws Exception {
		return hKMBoardDao.getBbsTotalCount(param);
	}
}
