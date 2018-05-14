package com.hk.mobile.dao;

import com.hk.mobile.exception.BSQLException;
import com.hk.mobile.member.model.HKMember;

public interface HKMemberService {
	void addMember(HKMember member) throws BSQLException;
	HKMember checkMember(HKMember member);
	int getID(HKMember member);
}
