package com.hk.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKBoard;
import com.hk.mobile.member.model.HKMBoard;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;
@Repository("hKBoardDao")
public class HKBoardDao extends EgovAbstractMapper{
	

	String ns="HKBoard.";
	
	
	public List<HKBoard> getBoardList() {
		return selectList(ns+"getBoardList");
	}

	
	public List<HKBoard> getBoardPageList(BbsParam param) throws Exception{
		List<HKBoard> bbslist=new ArrayList<HKBoard>();
		return bbslist=selectList(
				ns+"getBoardPageList",param);
	}
	
	public int getBoardTotalCount(BbsParam param)throws Exception{
		return (Integer)selectOne(
				ns+"getBoardTotalCount",param);
	}

	
	public void writeboard(HKBoard board) {
		insert(ns+"writeBoard",board);
	}
	

}
