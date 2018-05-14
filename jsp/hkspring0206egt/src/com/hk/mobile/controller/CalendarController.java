package com.hk.mobile.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hk.mobile.dao.HKCalendarService;
import com.hk.mobile.help.CalendarParam;
import com.hk.mobile.help.CalendarUtil;
import com.hk.mobile.help.JiNaCal;
import com.hk.mobile.member.model.HKCalendar;
import com.hk.mobile.member.model.HKMember;

@Controller
public class CalendarController {

	private static final Logger logger = 
			LoggerFactory.getLogger(CalendarController.class);

	@Resource(name = "hKCalendarService")
	private HKCalendarService hKCalendarService;

	@RequestMapping(value = "calwrite.do", 
			method = RequestMethod.GET)
	public String calwrite(
			HttpServletRequest request,
			JiNaCal jcal,Model model) {
		logger.info("Welcome CalendarController calwrite! "+ new Date());
		jcal.calculate();
		model.addAttribute("jcal", jcal);
		model.addAttribute("doc_title", "CALENDAR");
		return "calwrite.tiles";
	}//
	@RequestMapping(value = "calwriteAf.do", 
			method = RequestMethod.POST)
	public String calwriteAf(HttpServletRequest request,
			CalendarParam calparam,Model model) throws Exception {
		logger.info("Welcome CalendarController calwriteAf! "+ new Date());

		String yyyyMmdd=CalendarUtil.yyyymmddhhmm(calparam.getYear(),
				calparam.getMonth(), calparam.getDay(),
				calparam.getHour(),calparam.getMin());
		HKCalendar fcal=new HKCalendar(
				calparam.getId(),
				calparam.getTitle(),
				calparam.getContent(),
				yyyyMmdd
				);
		
		hKCalendarService.writeCalendar(fcal);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		
		return "forward:/calendar.do";
	}//
	@RequestMapping(value = "calendar.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String calendar(HttpServletRequest request,
			JiNaCal jcal,Model model) throws Exception {
		logger.info("Welcome CalendarController calendar! "+ new Date());
		jcal.calculate();
		String id=((HKMember)request.getSession().getAttribute("login")).getId();
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		HKCalendar fcal=new HKCalendar();
		fcal.setId(id);
		fcal.setWdate(yyyyMm);
		List<HKCalendar> flist= hKCalendarService.getCalendarList(fcal);
		model.addAttribute("flist", flist);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("jcal", jcal);
		return "calendar.tiles";
	}//
	
	
	@RequestMapping(value = "calendar2.do", 
			method = RequestMethod.GET)
	public String calendar2(HttpServletRequest request,JiNaCal jcal,Model model) {
		logger.info("Welcome CalendarController calendar2! "+ new Date());
		model.addAttribute("doc_title", "Ajax Calendar");
		logger.info("Welcome CalendarController calendar! "+ new Date());
		jcal.calculate();
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		HKCalendar fcal=new HKCalendar();
		fcal.setWdate(yyyyMm);
		model.addAttribute("doc_title", "AJAX CALENDAR");
		model.addAttribute("jcal", jcal);
		return "calendar2.tiles";
	}//
	@RequestMapping(value = "calendar3.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String calendar3(HttpServletRequest request,
			JiNaCal jcal, Model model) throws Exception {
		logger.info("Welcome CalendarController callist! "+ new Date());
		jcal.calculate();
		String id=((HKMember)request.getSession().getAttribute("login")).getId();
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		HKCalendar fcal=new HKCalendar();
		fcal.setId(id);
		fcal.setWdate(yyyyMm);
		List<HKCalendar> flist= hKCalendarService.getCalendarList2(fcal);
		model.addAttribute("doc_title", "CALENDAR DAYLIST");
		model.addAttribute("callist", flist);
		model.addAttribute("year", jcal.getYear());
		model.addAttribute("month", jcal.getMonth());
		return "calendar3.tiles";
	}//
	@RequestMapping(value = "calendarjson.do", 
			method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<HKCalendar>> 
	calendarjson(
			HKCalendar fcal, Model model) throws Exception {
		logger.info("Welcome CalendarController calendarjson! "+ new Date());
		logger.info("Welcome CalendarController --------- ========================"+ fcal);
		//postman post
		//http://localhost:8090/hkspring0126/calendarjson.do?wdate=20160113&id=kim
		List<HKCalendar> flist= 
				hKCalendarService.getDayList(fcal);
		Map<String, List<HKCalendar>> 
		map=new HashMap<String, List<HKCalendar>>();
		map.put("jina", flist);
		logger.info("Welcome CalendarController---------------"+map);
		return map;
	}//
	@RequestMapping(value = "caldetail.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String caldetail(HttpServletRequest request,
			HKCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController caldetail! "+ new Date());
		
		HKCalendar flist= 
				hKCalendarService.getDay(fcal);
		String wdate=flist.getWdate();
		
		String year=wdate.substring(0,4);
		String month=CalendarUtil.toOne(wdate.substring(4,6))+"";
		String urls=String.format("%s?year=%s&month=%s",
				"calendar.do",year,month);
		logger.info("Welcome CalendarController caldetail--------------------------------! "+ urls);
		model.addAttribute("cal", flist);
		model.addAttribute("urls", urls);
		model.addAttribute("doc_title", "CALENDAR DETAIL");
		return "caldetail.tiles";
	}//
	@RequestMapping(value = "calupdate.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String calupdate(HttpServletRequest request,
			HKCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController calupdate! "+ new Date());
		
		HKCalendar flist= 
				hKCalendarService.getDay(fcal);

		logger.info("Welcome CalendarController calupdate! ");
		model.addAttribute("cal", flist);
		model.addAttribute("doc_title", "CALENDAR UPDATE");
		return "calupdate.tiles";
	}//
	
	@RequestMapping(value = "calupdateaf.do", 
			method = RequestMethod.POST)
	public String calupdateaf(HttpServletRequest request,
			CalendarParam calparam,Model model) throws Exception {
		logger.info("Welcome CalendarController calupdateaf! "+ new Date());

		String yyyyMmdd=CalendarUtil.yyyymmddhhmm(calparam.getYear(),
				calparam.getMonth(), calparam.getDay(),
				calparam.getHour(),calparam.getMin());
		logger.info("Welcome CalendarController yyyyMmdd! "+ yyyyMmdd);
		HKCalendar fcal=new HKCalendar(
				calparam.getSeq(),
				calparam.getId(),
				calparam.getTitle(),
				calparam.getContent(),
				yyyyMmdd,
				null
				);
		logger.info("Welcome CalendarController -------------------- "+ fcal);
		hKCalendarService.calupdate(fcal);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("seq", calparam.getSeq());
		return "forward:/caldetail.do";
	}//
	@RequestMapping(value = "callist.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String callist(HttpServletRequest request,
			CalendarParam calparam, Model model) throws Exception {
		logger.info("Welcome CalendarController callist! "+ new Date());
		String id=((HKMember)request.getSession().getAttribute("login")).getId();
		String yyyyMmdd=CalendarUtil.yyyymmdd(calparam.getYear(),
				calparam.getMonth(), calparam.getDay());
		HKCalendar fcal=new HKCalendar(
				-1,
				id,
				null,
				null,
				yyyyMmdd,
				null
				);
		
		List<HKCalendar> flist= 
				hKCalendarService.getDayList(fcal);
		model.addAttribute("callist", flist);
		model.addAttribute("doc_title", "CALENDAR DAYLIST");
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		return "callist.tiles";
	}//
	@RequestMapping(value = "caldel.do", 
			method = RequestMethod.POST)
	public String caldel(HttpServletRequest request,
			HKCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController caldel! "+ new Date());

		logger.info("Welcome CalendarController -------- "+ fcal);
		hKCalendarService.caldel(fcal);
		return "redirect:/callist.do";
	}//
	
}
