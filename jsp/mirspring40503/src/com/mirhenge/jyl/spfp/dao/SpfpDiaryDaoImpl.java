package com.mirhenge.jyl.spfp.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mirhenge.jyl.spfp.model.SpfpDiary;
@Repository
public class SpfpDiaryDaoImpl implements SpfpDiaryDao {

	String ns = "SpfpDiary.";

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void writeSpfpDiary(SpfpDiary diary) {
		sqlSession.insert(ns+"writeSpfpDiary",diary);
	}

	@Override
	public List<SpfpDiary> getSpfpDiaryList() {
		return sqlSession.selectList(ns+"getSpfpDiaryList");
	}

	@Override
	public SpfpDiary getSpfpDiary(SpfpDiary diary) {
		return sqlSession.selectOne(ns+"getSpfpDiary",diary);
	}
	
	@Override
	public List<SpfpDiary> getSpfpDay(String yyyymmdd) {
		return sqlSession.selectList(ns+"getSpfpDay", yyyymmdd);
	}
	
	@Override
	public List<SpfpDiary> getSpfpMonth(String yyyymm) {
		return sqlSession.selectList(ns+"getSpfpMonth", yyyymm);
	}

	@Override
	public void updateSpfpDiary(SpfpDiary diary) {
		sqlSession.update(ns+"updateSpfpDiary",diary);
	}

	@Override
	public void deleteSpfpDiary(SpfpDiary diary) {
		sqlSession.delete(ns+"deleteSpfpDiary",diary);
	}

	@Override
	public void updateTeam(SpfpDiary diary) {
		sqlSession.update(ns+"updateTeam",diary);
	}

}
