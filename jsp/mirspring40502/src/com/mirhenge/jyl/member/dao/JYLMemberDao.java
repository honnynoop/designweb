package com.mirhenge.jyl.member.dao;

import java.util.List;

import com.mirhenge.jyl.exception.JYLException;
import com.mirhenge.jyl.member.model.JYLMember;

public interface JYLMemberDao {
	void addMember(JYLMember member) throws JYLException ;
	JYLMember checkMember(JYLMember member);
	int getID(JYLMember member);
	List<JYLMember> getIDList();
}
