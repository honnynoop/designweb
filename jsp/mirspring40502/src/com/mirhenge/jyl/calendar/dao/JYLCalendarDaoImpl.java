package com.mirhenge.jyl.calendar.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mirhenge.jyl.calendar.model.JYLCalendar;
import com.mirhenge.jyl.calendar.model.NoticeDto;

@Repository
public class JYLCalendarDaoImpl implements JYLCalendarDao{

	@Autowired
	private SqlSession sqlSession;
	
	String ns="JYLCalendar.";
	@Override
	public boolean writeCalendar(JYLCalendar cal) throws Exception{
		sqlSession.insert(ns+"writeCalendar",cal);
		return true;
	}
	@Override
	public List<JYLCalendar> getCalendarList(JYLCalendar fcal) throws Exception{
		List<JYLCalendar> bbslist=new ArrayList<JYLCalendar>();
		return bbslist=sqlSession.selectList(
				ns+"getCalendarList",fcal);
	}
	@Override
	public List<JYLCalendar> getDayList(JYLCalendar fcal) throws Exception{
		List<JYLCalendar> bbslist=new ArrayList<JYLCalendar>();
		return bbslist=sqlSession.selectList(
				ns+"getDayList",fcal);
	}
	@Override
	public JYLCalendar getDay(JYLCalendar fcal) throws Exception {
		return (JYLCalendar)sqlSession.selectOne(
				ns+"getDay",fcal);
	}
	@Override
	public void calupdate(JYLCalendar fcal) throws Exception{
		sqlSession.update(ns+"calupdate",fcal);
	}
	@Override
	public void caldel(JYLCalendar fcal) throws Exception {
		sqlSession.delete(ns+"caldel",fcal);
	}
	@Override
	public List<JYLCalendar> getDayList2(JYLCalendar fcal) {
		List<JYLCalendar> bbslist=new ArrayList<JYLCalendar>();
		return bbslist=sqlSession.selectList(
				ns+"getDayList2",fcal);
	}
	@Override
	public List<JYLCalendar> getCalendarList2(JYLCalendar fcal) throws Exception{
		List<JYLCalendar> bbslist=new ArrayList<JYLCalendar>();
		return bbslist=sqlSession.selectList(ns+"getCalendarList2",fcal);
	}
	@Override
	public List<NoticeDto> getNotice(HashMap<String, Integer> map) {
		return sqlSession.selectList(ns+"getNotice",map);
	}

}
