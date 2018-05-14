package com.mirhenge.jyl.calendar.dao;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mirhenge.jyl.calendar.model.JYLCalendar;
import com.mirhenge.jyl.calendar.model.NoticeDto;

@Service
public class JYLCalendarServiceImpl implements JYLCalendarService {
	
    @Autowired
	private JYLCalendarDao hKCalendarDao;
    
	@Override
	@Transactional
	public boolean writeCalendar(JYLCalendar fcal) throws Exception {
		return hKCalendarDao.writeCalendar(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLCalendar> getCalendarList(JYLCalendar fcal)
			throws Exception {
		return hKCalendarDao.getCalendarList(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLCalendar> getDayList(JYLCalendar fcal) throws Exception {
		return hKCalendarDao.getDayList(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public JYLCalendar getDay(JYLCalendar fcal) throws Exception{
		return hKCalendarDao.getDay(fcal);
	}

	@Override
	@Transactional
	public void calupdate(JYLCalendar fcal) throws Exception{
		hKCalendarDao.calupdate(fcal);
	}

	@Override
	@Transactional
	public void caldel(JYLCalendar fcal) throws Exception{
		hKCalendarDao.caldel(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLCalendar> getDayList2(JYLCalendar fcal) {
		return hKCalendarDao.getDayList2(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<JYLCalendar> getCalendarList2(JYLCalendar fcal)
			throws Exception {
		return hKCalendarDao.getCalendarList2(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<NoticeDto> getNotice(HashMap<String, Integer> map) {
		return hKCalendarDao.getNotice(map);
	}

}
