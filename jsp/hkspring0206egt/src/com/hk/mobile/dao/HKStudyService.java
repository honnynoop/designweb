package com.hk.mobile.dao;

import java.util.List;

import com.hk.mobile.member.model.HKCategory;
import com.hk.mobile.member.model.HKStudy;

public interface HKStudyService {
	List<HKStudy> getStudyList(HKStudy study);
	   void writeStudy(HKStudy study);
	   List<HKCategory> getCategoyList();
	   HKStudy getStudy(HKStudy study);
}
