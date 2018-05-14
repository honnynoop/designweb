package com.hk.mobile.dao;

import java.util.List;

import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKBoard;

public interface HKBoardService {
	List<HKBoard> getBoardList();
	List<HKBoard> getBoardPageList(BbsParam param) throws Exception;
	int getBoardTotalCount(BbsParam param)throws Exception;
	void writeboard(HKBoard board);
}
