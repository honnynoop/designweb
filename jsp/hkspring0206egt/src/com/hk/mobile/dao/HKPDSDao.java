package com.hk.mobile.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.member.model.HKPds;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("hKPDSDao")
public class HKPDSDao extends EgovAbstractMapper{

	
	String ns="HKPds.";
	

	public void uploadPDS(HKPds dto) {
		insert(ns+"uploadPDS",dto);
	}


	public List<HKPds> getPDSList() {
		return selectList(ns+"getPDSList");
	}


	public void pdsReadCount(int seq) {
		update(ns+"pdsReadCount",seq);
	}



	public void downloadCount(int seq) {
		update(ns+"downloadCount",seq);
	}

	public HKPds getPDS(int seq) {
		return (HKPds)selectOne(ns+"getPDS",seq);
	}


	public void updatePDS(HKPds pdsdto) {
		update(ns+"updatePDS",pdsdto);
	}


	public void delPDS(int seq) {
		delete(ns+"delPDS",seq);
	}

}
