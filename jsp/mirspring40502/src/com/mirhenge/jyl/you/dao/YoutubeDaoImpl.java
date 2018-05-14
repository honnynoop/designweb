package com.mirhenge.jyl.you.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mirhenge.jyl.you.model.YoutubeSave;

@Repository
public class YoutubeDaoImpl implements YoutubeDao {
	
	String ns="YoutubeSave.";
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<YoutubeSave> getYoutubeList(YoutubeSave you) {
		return sqlSession.selectList(ns+"getYoutubeList",you);
	}

	@Override
	public YoutubeSave getYoutube(YoutubeSave you) {
		return sqlSession.selectOne(ns+"getYoutube",you);
	}

	@Override
	public void writeYoutube(YoutubeSave you) {
		sqlSession.insert(ns+"writeYoutube",you);
	}

}
