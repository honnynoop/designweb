package com.hk.mobile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.member.model.HKCategory;
import com.hk.mobile.member.model.HKStudy;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
@Service("hKStudyService")
public class HKStudyServiceImpl extends EgovAbstractServiceImpl implements HKStudyService {
	
	@Resource(name="hKStudyDao")
	private HKStudyDao hKStudyDao;
	

	@Override
	@Transactional(readOnly=true)
	public List<HKStudy> getStudyList(HKStudy study) {
		return hKStudyDao.getStudyList(study);
	}

	@Override
	@Transactional
	public void writeStudy(HKStudy study) {
		hKStudyDao.writeStudy(study);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKCategory> getCategoyList() {
		return hKStudyDao.getCategoyList();
	}

	@Override
	@Transactional(readOnly=true)
	public HKStudy getStudy(HKStudy study) {
		return hKStudyDao.getStudy(study);
	}

}
