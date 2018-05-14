package com.hk.mobile.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKMBoard;

public interface HKMBoardService {
	void writeBbs(HKMBoard mboard);
	List<HKMBoard> getBbsList();
	HKMBoard getBbs(HKMBoard mboard);
	void reply(HKMBoard mboard) throws Exception;
	void updateBbs(HKMBoard bbs);
	void bbsdelete(HKMBoard bbs);
	 List<HKMBoard> getBbsPageList(BbsParam param) throws Exception;
	 int getBbsTotalCount(BbsParam param) throws Exception ;
}
