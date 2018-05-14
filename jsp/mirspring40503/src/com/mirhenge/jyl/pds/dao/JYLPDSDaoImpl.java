package com.mirhenge.jyl.pds.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

import com.mirhenge.jyl.pds.model.JYLPds;

import oracle.net.aso.p;
@Repository
public class JYLPDSDaoImpl implements JYLPDSDao {
	
	String ns="JYLPds.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void uploadPDS(JYLPds dto) {
		sqlSession.insert(ns+"uploadPDS",dto);
	}

	@Override
	public List<JYLPds> getPDSList() {
		return sqlSession.selectList(ns+"getPDSList");
	}

	@Override
	public void pdsReadCount(int pdsid) {
		sqlSession.update(ns+"pdsReadCount",pdsid);
	}

	@Override
	public void downloadCount(int pdsid) {
		sqlSession.update(ns+"downloadCount",pdsid);
	}

	@Override
	public JYLPds getPDS(int pdsid) {
		return sqlSession.selectOne(ns+"getPDS",pdsid);
	}

	@Override
	public void updatePDS(JYLPds pdsdto) {
		sqlSession.update(ns+"updatePDS",pdsdto);
	}

	@Override
	public void delPDS(int seq) {
		sqlSession.delete(ns+"delPDS",seq);
	}

}
