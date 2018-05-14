package com.mirhenge.jyl.mboard.dao;

import java.util.List;
import com.mirhenge.jyl.mboard.help.BbsParam;
import com.mirhenge.jyl.mboard.model.JYLMBoard;
import com.mirhenge.jyl.member.model.JYLMember;

public interface JYLMBoardService {
	void writeBbs(JYLMBoard mboard);
	List<JYLMBoard> getBbsList();
	JYLMBoard getBbs(JYLMBoard mboard);
	
	void reply(JYLMBoard mboard) throws Exception;
	
	void updateBbs(JYLMBoard bbs);
	void bbsdelete(JYLMBoard bbs);
	public List<JYLMBoard> getBbsPageList(BbsParam param) throws Exception;
	public int getBbsTotalCount(BbsParam param)throws Exception;
	String preView(int seq);
}
