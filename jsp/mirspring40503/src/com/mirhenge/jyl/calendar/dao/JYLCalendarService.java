package com.mirhenge.jyl.calendar.dao;
import java.util.HashMap;
import java.util.List;

import com.mirhenge.jyl.calendar.model.JYLCalendar;
import com.mirhenge.jyl.calendar.model.NoticeDto;
public interface JYLCalendarService {
	
	public boolean writeCalendar(JYLCalendar cal) throws Exception;
	public List<JYLCalendar> getCalendarList(JYLCalendar fcal) throws Exception;
	public List<JYLCalendar> getDayList(JYLCalendar fcal) throws Exception;
	public JYLCalendar getDay(JYLCalendar fcal)throws Exception;
	public void calupdate(JYLCalendar fcal)throws Exception;
	public void caldel(JYLCalendar fcal)throws Exception;
	
	public List<JYLCalendar> getDayList2(JYLCalendar fcal);
	public List<JYLCalendar> getCalendarList2(JYLCalendar fcal) throws Exception;
	List<NoticeDto> getNotice(HashMap<String, Integer> map);
}
