package com.hk.mobile.dao;

import java.util.List;

import com.hk.mobile.member.model.HKCalendar;

public interface HKCalendarService {
	boolean writeCalendar(HKCalendar fcal) 
			throws Exception;
	List<HKCalendar> getCalendarList(
			HKCalendar fcal) throws Exception;
	List<HKCalendar> getDayList(
			HKCalendar fcal) throws Exception;
	HKCalendar getDay(HKCalendar fcal) throws Exception;
	void calupdate(HKCalendar fcal)throws Exception;
	void caldel(HKCalendar fcal)throws Exception;
	List<HKCalendar> getDayList2(HKCalendar fcal);
	List<HKCalendar> getCalendarList2(HKCalendar fcal) throws Exception;
}
