package com.hk.mobile.dao;

import java.util.List;

import com.hk.mobile.member.model.HKPds;

public interface HKPDSService {
	void uploadPDS(HKPds dto);
	List<HKPds> getPDSList ();
	void pdsReadCount(int seq);
	void downloadCount(int seq);
	HKPds getPDS(int seq);
	void updatePDS(HKPds pdsdto) ;
	void delPDS(int seq);
}
