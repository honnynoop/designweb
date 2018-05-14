package com.hk.mobile.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.exception.BSQLException;
import com.hk.mobile.member.model.HKMember;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
@Service("hKMemberService")
public class HKMemberServiceImpl extends EgovAbstractServiceImpl implements HKMemberService {
	
	@Resource(name="hKMemberDao")
	private HKMemberDao hKMemberDao;
	
	@Override
	@Transactional
	public void addMember(HKMember member) throws BSQLException {
		hKMemberDao.addMember(member);
	}

	@Override
	@Transactional(readOnly=true)
	public HKMember checkMember(HKMember member) {
		return hKMemberDao.checkMember(member);
	}

	@Override
	@Transactional(readOnly=true)
	public int getID(HKMember member) {
		return hKMemberDao.getID(member);
	}

}
