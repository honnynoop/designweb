package com.hk.mobile.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.mobile.member.model.HKCalendar;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("hKCalendarService")
public class HKCalendarServiceImpl extends EgovAbstractServiceImpl implements HKCalendarService {
	
	@Resource(name="hKCalendarDao")
	private HKCalendarDao hKCalendarDao;
    
	@Override
	@Transactional
	public boolean writeCalendar(HKCalendar fcal) throws Exception {
		return hKCalendarDao.writeCalendar(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKCalendar> getCalendarList(HKCalendar fcal)
			throws Exception {
		return hKCalendarDao.getCalendarList(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKCalendar> getDayList(HKCalendar fcal) throws Exception {
		return hKCalendarDao.getDayList(fcal);
	}

	@Override
	public HKCalendar getDay(HKCalendar fcal) throws Exception{
		return hKCalendarDao.getDay(fcal);
	}

	@Override
	public void calupdate(HKCalendar fcal) throws Exception{
		hKCalendarDao.calupdate(fcal);
	}

	@Override
	public void caldel(HKCalendar fcal) throws Exception{
		hKCalendarDao.caldel(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKCalendar> getDayList2(HKCalendar fcal) {
		return hKCalendarDao.getDayList2(fcal);
	}

	@Override
	@Transactional(readOnly=true)
	public List<HKCalendar> getCalendarList2(HKCalendar fcal)
			throws Exception {
		return hKCalendarDao.getCalendarList2(fcal);
	}

}
