package com.hk.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKMBoard;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("hKMBoardDao")
public class HKMBoardDao extends EgovAbstractMapper{


	
	String ns="HKMBoard.";
	

	public void writeBbs(HKMBoard mboard) {
		insert(ns+"writeBbs",mboard);
	}

	
	public List<HKMBoard> getBbsList() {
		List<HKMBoard>  lists=new ArrayList<HKMBoard>();
		return lists=selectList(ns+"getBbsList");
	}

	
	public HKMBoard getBbs(HKMBoard mboard) {
		return (HKMBoard)selectOne(ns+"getBbs",mboard);
	}
	
	public void replyBbsUpdate(HKMBoard mboard) throws Exception{
		update(ns+"replyBbsUpdate",mboard);
	}
	
	public void replyBbsInsert(HKMBoard mboard) throws Exception{
		insert(ns+"replyBbsInsert",mboard);
	}

	
	public void updateBbs(HKMBoard bbs) {
		update(ns+"updateBbs",bbs);
		
	}

	
	public void bbsdelete(HKMBoard bbs) {
		update(ns+"bbsdelete",bbs);
	}
	
	public List<HKMBoard> getBbsPageList(BbsParam param) throws Exception{
		List<HKMBoard> bbslist=new ArrayList<HKMBoard>();
		return bbslist=selectList(
				ns+"getBbsPageList",param);
	}
	
	public int getBbsTotalCount(BbsParam param)throws Exception{
		return (Integer)selectOne(
				ns+"getBbsTotalCount",param);
	}
}
