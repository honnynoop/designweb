package com.hk.mobile.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.mobile.member.model.HKCalendar;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

@Repository("hKCalendarDao")
public class HKCalendarDao extends EgovAbstractMapper{

	
	String ns="HKCalendar.";
	
	
	public boolean writeCalendar(HKCalendar cal) throws Exception{
		insert(ns+"writeCalendar",cal);
		return true;
	}
	
	public List<HKCalendar> getCalendarList(HKCalendar fcal) throws Exception{
		List<HKCalendar> bbslist=new ArrayList<HKCalendar>();
		return bbslist=selectList(
				ns+"getCalendarList",fcal);
	}
	
	public List<HKCalendar> getDayList(HKCalendar fcal) throws Exception{
		List<HKCalendar> bbslist=new ArrayList<HKCalendar>();
		return bbslist=selectList(
				ns+"getDayList",fcal);
	}
	
	public HKCalendar getDay(HKCalendar fcal) throws Exception {
		return (HKCalendar)selectOne(
				ns+"getDay",fcal);
	}
	
	public void calupdate(HKCalendar fcal) throws Exception{
		update(ns+"calupdate",fcal);
	}
	
	public void caldel(HKCalendar fcal) throws Exception {
		delete(ns+"caldel",fcal);
	}
	
	public List<HKCalendar> getDayList2(HKCalendar fcal) {
		List<HKCalendar> bbslist=new ArrayList<HKCalendar>();
		return bbslist=selectList(
				ns+"getDayList2",fcal);
	}
	
	public List<HKCalendar> getCalendarList2(HKCalendar fcal) throws Exception{
		List<HKCalendar> bbslist=new ArrayList<HKCalendar>();
		return bbslist=selectList(
				ns+"getCalendarList2",fcal);
	}

}
