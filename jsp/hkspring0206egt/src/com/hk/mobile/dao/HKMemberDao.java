package com.hk.mobile.dao;


import org.springframework.stereotype.Repository;

import com.hk.mobile.exception.BSQLException;
import com.hk.mobile.member.model.HKMember;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
@Repository("hKMemberDao")
public class HKMemberDao extends EgovAbstractMapper{


	
	String ns="HKMember.";
	
	public void addMember(HKMember member)  throws BSQLException {
		insert(ns+"addMember",member);
	}
	
	public HKMember checkMember(HKMember member) {
		return (HKMember)selectOne(ns+"checkMember",member);
	}
	
	public int getID(HKMember member) {
		return (int)selectOne(ns+"getID",member);
	}

}
