package com.mirhenge.jyl.pds.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mirhenge.jyl.pds.model.JYLPds;

@Service
public class JYLPDSServiceImpl implements JYLPDSService {
    
	@Autowired
	private JYLPDSDao jYLPDSDao;
	
	@Override
	@Transactional
	public void uploadPDS(JYLPds dto) {
		jYLPDSDao.uploadPDS(dto);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLPds> getPDSList() {
		return jYLPDSDao.getPDSList();
	}

	@Override
	@Transactional
	public void pdsReadCount(int pdsid) {
		jYLPDSDao.pdsReadCount(pdsid);
	}

	@Override
	@Transactional
	public void downloadCount(int pdsid) {
		jYLPDSDao.downloadCount(pdsid);
	}

	@Override
	@Transactional(readOnly=true)
	public JYLPds getPDS(int pdsid) {
		return jYLPDSDao.getPDS(pdsid);
	}

	@Override
	@Transactional
	public void updatePDS(JYLPds pdsdto) {
		jYLPDSDao.updatePDS(pdsdto);
	}

	@Override
	@Transactional
	public void delPDS(int seq) {
		jYLPDSDao.delPDS(seq);
	}

}
