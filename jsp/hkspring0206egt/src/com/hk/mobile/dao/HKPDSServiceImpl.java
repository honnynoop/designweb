package com.hk.mobile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.member.model.HKPds;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
@Service("hKPDSService")
public class HKPDSServiceImpl extends EgovAbstractServiceImpl implements HKPDSService {
	
	@Resource(name="hKPDSDao")
	private HKPDSDao hKPDSDao;
	
	@Override
	@Transactional
	public void uploadPDS(HKPds dto) {
		hKPDSDao.uploadPDS(dto);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKPds> getPDSList() {
		return hKPDSDao.getPDSList();
	}

	@Override
	@Transactional
	public void pdsReadCount(int pdsid) {
		hKPDSDao.pdsReadCount(pdsid);
	}

	@Override
	@Transactional
	public void downloadCount(int pdsid) {
		hKPDSDao.downloadCount(pdsid);
	}

	@Override
	@Transactional(readOnly=true)
	public HKPds getPDS(int pdsid) {
		return hKPDSDao.getPDS(pdsid);
	}

	@Override
	@Transactional
	public void updatePDS(HKPds pdsdto) {
		hKPDSDao.updatePDS(pdsdto);
	}

	@Override
	@Transactional
	public void delPDS(int seq) {
		hKPDSDao.delPDS(seq);
	}

}
