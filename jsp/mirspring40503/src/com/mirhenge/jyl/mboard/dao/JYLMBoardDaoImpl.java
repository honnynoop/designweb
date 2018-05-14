package com.mirhenge.jyl.mboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mirhenge.jyl.mboard.help.BbsParam;
import com.mirhenge.jyl.mboard.model.JYLMBoard;
import com.mirhenge.jyl.member.model.JYLMember;
@Repository
public class JYLMBoardDaoImpl implements JYLMBoardDao {

	String ns="JYLMBoard.";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void writeBbs(JYLMBoard mboard) {
		sqlSession.insert(ns+"writeBbs",mboard);
	}

	@Override
	public List<JYLMBoard> getBbsList() {
		return sqlSession.selectList(ns+"getBbsList");
	}

	@Override
	public JYLMBoard getBbs(JYLMBoard mboard) {
		return (JYLMBoard)sqlSession.selectOne(ns+"getBbs",mboard);
	}

	@Override
	public void replyBbsUpdate(JYLMBoard mboard) throws Exception {
		sqlSession.update(ns+"replyBbsUpdate",mboard);
	}

	@Override
	public void replyBbsInsert(JYLMBoard mboard) throws Exception {
		sqlSession.insert(ns+"replyBbsInsert",mboard);
	}

	@Override
	public void updateBbs(JYLMBoard bbs) {
		sqlSession.update(ns+"updateBbs",bbs);
	}

	@Override
	public void bbsdelete(JYLMBoard bbs) {
		sqlSession.delete(ns+"bbsdelete",bbs);
	}

	@Override
	public List<JYLMBoard> getBbsPageList(BbsParam param) throws Exception {
		return sqlSession.selectList(ns+"getBbsPageList",param);
	}

	@Override
	public int getBbsTotalCount(BbsParam param) throws Exception {
		return (int)sqlSession.selectOne(ns+"getBbsTotalCount",param);
	}

	@Override
	public String preView(int seq) {
		return sqlSession.selectOne(ns+"preView",seq);
	}

	

}
