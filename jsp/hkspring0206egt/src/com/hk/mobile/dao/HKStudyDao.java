package com.hk.mobile.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.mobile.member.model.HKCategory;
import com.hk.mobile.member.model.HKStudy;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
@Repository("hKStudyDao")
public class HKStudyDao extends EgovAbstractMapper{


	
	String ns="HKStudy.";

	
	public List<HKStudy> getStudyList(HKStudy study) {
		return selectList(
				ns+"getStudyList",study);
	}

	
	public void writeStudy(HKStudy study) {
		insert(ns+"writeStudy",study);
	}

	
	public List<HKCategory> getCategoyList() {
		return selectList(ns+"getCategoyList");
	}

	
	public HKStudy getStudy(HKStudy study) {
		return (HKStudy)selectOne(
				ns+"getStudy",study);
	}

}
