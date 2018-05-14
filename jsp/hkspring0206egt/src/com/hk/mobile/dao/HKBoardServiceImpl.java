package com.hk.mobile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKBoard;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
@Service("hKBoardService")
public class HKBoardServiceImpl extends EgovAbstractServiceImpl implements HKBoardService {
	
	@Resource(name="hKBoardDao")
	private HKBoardDao hKBoardDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<HKBoard> getBoardList() {
		return hKBoardDao.getBoardList();
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKBoard> getBoardPageList(BbsParam param) throws Exception {
		return hKBoardDao.getBoardPageList(param);
	}

	@Override
	@Transactional(readOnly=true)
	public int getBoardTotalCount(BbsParam param) throws Exception {
		return hKBoardDao.getBoardTotalCount(param);
	}

	@Override
	@Transactional
	public void writeboard(HKBoard board) {
		hKBoardDao.writeboard(board);
	}

}
