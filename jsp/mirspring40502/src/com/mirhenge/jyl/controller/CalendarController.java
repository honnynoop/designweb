package com.mirhenge.jyl.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.mirhenge.jyl.calendar.dao.JYLCalendarService;
import com.mirhenge.jyl.calendar.help.CalendarParam;
import com.mirhenge.jyl.calendar.help.CalendarUtil;
import com.mirhenge.jyl.calendar.help.JYLCal;
import com.mirhenge.jyl.calendar.model.JYLCalendar;
import com.mirhenge.jyl.calendar.model.NoticeDto;
import com.mirhenge.jyl.member.model.JYLMember;

@Controller
public class CalendarController {

	private static final Logger logger = 
			LoggerFactory.getLogger(CalendarController.class);
	@Autowired
	private JYLCalendarService jYLCalendarService;
	/*
	 String id="";
	 try{
			id=getId(request);//위에 메서드로 만듬
		}catch (Exception e) {
			return "redirect:/login.do";
		}
	 */
	private String getId(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Object oLogin=session.getAttribute("login");
		if(oLogin==null){
			throw new Exception("session이 없다.");
		}		
		return ((JYLMember)oLogin).getId();
	}
	
	
	
	
	@RequestMapping(value = "calendar.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String calendar(HttpServletRequest request,
			JYLCal jcal,Model model) throws Exception {
		logger.info("Welcome CalendarController calendar! "+ new Date());
		jcal.calculate();
		String id="";
		try{
			id=getId(request);//위에 메서드로 만듬
		}catch (Exception e) {
			return "redirect:/login.do";
		}
				
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		JYLCalendar fcal=new JYLCalendar();
		fcal.setId(id);
		fcal.setWdate(yyyyMm);
		List<JYLCalendar> flist= jYLCalendarService.getCalendarList(fcal);
		model.addAttribute("flist", flist);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("jcal", jcal);
		return "calendar.tiles";
	}//
	
	@RequestMapping(value = "calwrite.do", 
			method = RequestMethod.GET)
	public String calwrite(
			HttpServletRequest request,
			JYLCal jcal,Model model) {
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
		JYLCalendar fcal=new JYLCalendar(
				calparam.getId(),
				calparam.getTitle(),
				calparam.getContent(),
				yyyyMmdd
				);
		
		jYLCalendarService.writeCalendar(fcal);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		
		return "forward:/calendar.do";
	}//
	
	@RequestMapping(value = "calendar2.do", 
			method = RequestMethod.GET)
	public String calendar2(HttpServletRequest request,JYLCal jcal,Model model) {
		logger.info("Welcome CalendarController calendar2! "+ new Date());
		model.addAttribute("doc_title", "Ajax Calendar");
		logger.info("Welcome CalendarController calendar! "+ new Date());
		jcal.calculate();
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		JYLCalendar fcal=new JYLCalendar();
		fcal.setWdate(yyyyMm);
		model.addAttribute("doc_title", "AJAX CALENDAR");
		model.addAttribute("jcal", jcal);
		return "calendar2.tiles";
	}//
	@RequestMapping(value = "calendar3.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String calendar3(HttpServletRequest request,
			JYLCal jcal, Model model) throws Exception {
		logger.info("Welcome CalendarController callist! "+ new Date());
		jcal.calculate();
		String id="";
		try{
			id=getId(request);//위에 메서드로 만듬
		}catch (Exception e) {
			return "redirect:/login.do";
		}
		String yyyyMm=CalendarUtil.yyyymm(
				jcal.getYear(), jcal.getMonth());
		JYLCalendar fcal=new JYLCalendar();
		fcal.setId(id);
		fcal.setWdate(yyyyMm);
		List<JYLCalendar> flist= jYLCalendarService.getCalendarList2(fcal);
		model.addAttribute("doc_title", "CALENDAR DAYLIST");
		model.addAttribute("callist", flist);
		model.addAttribute("year", jcal.getYear());
		model.addAttribute("month", jcal.getMonth());
		return "calendar3.tiles";
	}//
	@RequestMapping(value = "calendarjson.do", 
			method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<JYLCalendar>> 
	calendarjson(
			JYLCalendar fcal, Model model) throws Exception {
		logger.info("Welcome CalendarController calendarjson! "+ new Date());
		logger.info("Welcome CalendarController --------- ========================"+ fcal);
		//postman post
		//http://localhost:8090/hkspring0126/calendarjson.do?wdate=20160113&id=kim
		List<JYLCalendar> flist= 
				jYLCalendarService.getDayList(fcal);
		Map<String, List<JYLCalendar>> 
		map=new HashMap<String, List<JYLCalendar>>();
		map.put("jina", flist);
		logger.info("Welcome CalendarController---------------"+map);
		return map;
	}//
	@RequestMapping(value = "caldetail.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String caldetail(HttpServletRequest request,
			JYLCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController caldetail! "+ new Date());
		
		JYLCalendar flist= 
				jYLCalendarService.getDay(fcal);
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
			JYLCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController calupdate! "+ new Date());
		
		JYLCalendar flist= 
				jYLCalendarService.getDay(fcal);

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
		JYLCalendar fcal=new JYLCalendar(
				calparam.getSeq(),
				calparam.getId(),
				calparam.getTitle(),
				calparam.getContent(),
				yyyyMmdd,
				null
				);
		logger.info("Welcome CalendarController -------------------- "+ fcal);
		jYLCalendarService.calupdate(fcal);
		model.addAttribute("doc_title", "CALENDAR");
		model.addAttribute("seq", calparam.getSeq());
		return "forward:/caldetail.do";
	}//
	@RequestMapping(value = "callist.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String callist(HttpServletRequest request,
			CalendarParam calparam, Model model) throws Exception {
		logger.info("Welcome CalendarController callist! "+ new Date());
		String id="";
		try{
			id=getId(request);//위에 메서드로 만듬
		}catch (Exception e) {
			return "redirect:/login.do";
		}
		String yyyyMmdd=CalendarUtil.yyyymmdd(calparam.getYear(),
				calparam.getMonth(), calparam.getDay());
		JYLCalendar fcal=new JYLCalendar(
				-1,
				id,
				null,
				null,
				yyyyMmdd,
				null
				);
		
		List<JYLCalendar> flist= 
				jYLCalendarService.getDayList(fcal);
		model.addAttribute("callist", flist);
		model.addAttribute("doc_title", "CALENDAR DAYLIST");
		model.addAttribute("year", calparam.getYear());
		model.addAttribute("month", calparam.getMonth());
		return "callist.tiles";
	}//
	@RequestMapping(value = "caldel.do", 
			method = RequestMethod.POST)
	public String caldel(HttpServletRequest request,
			JYLCalendar fcal,Model model) throws Exception {
		logger.info("Welcome CalendarController caldel! "+ new Date());

		logger.info("Welcome CalendarController -------- "+ fcal);
		jYLCalendarService.caldel(fcal);
		return "redirect:/callist.do";
	}//
	
	@RequestMapping(value = "/noctice_list.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<NoticeDto>> noctice_list(Model model, String year, String month){
		logger.info("Welcome Notice_Control_ajax noctice_list! "+ new Date());
		logger.info("Welcome Notice_Control_ajax noctice_list! ------------------------------------------"+ year);
		logger.info("Welcome Notice_Control_ajax noctice_list! ------------------------------------------"+ year);
		int syear=Integer.parseInt(year);
		int smon=Integer.parseInt(month);
		HashMap<String, Integer> map =new HashMap<>();
		map.put("syear", syear);
		map.put("eyear", syear);
		map.put("smon", smon);
		map.put("emon", smon);
		
		HashMap<String, List<NoticeDto>> maps =new HashMap<>();
		maps.put("list", jYLCalendarService.getNotice(map));
		
		return maps;
	}
	@RequestMapping(value = "/getNotice.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, List<NoticeDto>> getNotice(Model model, String start, String end){
		logger.info("Welcome Notice_Control_ajax getNotice! "+ new Date());
		//logger.info("Welcome Notice_Control_ajax 1 start!------------------------------ "+ start);
		//logger.info("Welcome Notice_Control_ajax 2 end!-------------------------------- "+ end);
		//logger.info("Welcome Notice_Control_ajax getNotice! ------------------------------------------"+ start.replaceAll("[.]", "-").replaceAll(" ", ""));
		//logger.info("Welcome Notice_Control_ajax getNotice! ------------------------------------------"+ end.replaceAll("[.]", "-").replaceAll(" ", ""));
		//logger.info("Welcome Notice_Control_ajax IE! "+ start.indexOf("\\?"));
		String pattern="";
		if(start.contains("년") || start.contains("월")|| start.contains("일")){//IE
			logger.info("Welcome Notice_Control_ajax IE! ");
			start=delque(start);
			end=delque(end);
			pattern="yyyy-MM-dd";
		}
		else{//Crome
			logger.info("Welcome Notice_Control_ajax NIE! "+ start.indexOf("."));
			start=start.replaceAll("[.]", "-").replaceAll(" ", "");
			end=end.replaceAll("[.]", "-").replaceAll(" ", "");
			pattern="yyyy-MM-dd-";
			//pattern=pattern.substring(0, pattern.lastIndexOf("-"));
		}
		logger.info("Welcome Notice_Control_ajax pattern!=============================================> ");
		logger.info("Welcome Notice_Control_ajax pattern!------------------------------ "+ pattern);
		logger.info("Welcome Notice_Control_ajax 2 start!------------------------------ "+ start);
		logger.info("Welcome Notice_Control_ajax 2 end!------------------------------ "+ end);
		//?2017?년 ?7?월 ?30?일
		//?2017?년 ?10?월 ?29?일
		//logger.info("Welcome Notice_Control_ajax getNotice????! ------------------------------------------"+ start.indexOf("?"));
//		String [] mm=start.split("?");
//		String tt="";
//		for(int i=0; i<mm.length; i++){
//			tt+=mm[i];
//		}
//		logger.info("Welcome Notice_Control_ajax getNotice    tt! ------------------------------------------"+ tt);
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date sdd=new Date();
		try {
			sdd=sdf.parse(start);
			//logger.info("Welcome Notice_Control_ajax 99! ----------=-------------------"+ sdd);
		} catch (ParseException e) {
			//logger.info("Welcome Notice_Control_ajax e! ----------=-------------------"+ e);
		}
		Calendar scal=Calendar.getInstance();
		scal.setTime(sdd);
		int syear=scal.get(Calendar.YEAR);
		int smon=scal.get(Calendar.MONTH)+1;
		logger.info("Welcome Notice_Control_ajax getNotice! ------------------------------------------"+ syear+"-----------"+smon);
		SimpleDateFormat edf=new SimpleDateFormat(pattern);
		Date edd=new Date();
		try {
			edd=edf.parse(end);
			//logger.info("Welcome Notice_Control_ajax 99! ----------=-------------------"+ edd);
		} catch (ParseException e) {
			//logger.info("Welcome Notice_Control_ajax getNotice! ------=---------"+ e);
		}
		Calendar ecal=Calendar.getInstance();
		ecal.setTime(edd);
		int eyear=ecal.get(Calendar.YEAR);
		int emon=ecal.get(Calendar.MONTH)+1;
		//logger.info("Welcome Notice_Control_ajax getNotice! ------------------------------------------"+ eyear+"-----------"+emon);
//		int syear=Integer.parseInt(start.substring(start.indexOf('년')-4,start.indexOf('년')).trim());
//		int eyear=Integer.parseInt(end.substring(end.indexOf('년')-4,end.indexOf('년')).trim());
//		int smon=Integer.parseInt( start.substring((start.indexOf('월')-2),start.indexOf('월')).trim());
//		int emon=Integer.parseInt( end.substring((end.indexOf('월')-2),end.indexOf('월')).trim());
		if (smon==12) {
			smon=1;
		}
//		System.out.println(syear+"@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
//		System.out.println(eyear);
//		System.out.println(smon);
//		System.out.println(emon);
		
		HashMap<String, Integer> map =new HashMap<>();
		map.put("syear", syear);
		map.put("eyear", eyear);
		map.put("smon", smon);
		map.put("emon", emon);
		List<NoticeDto> lists = new ArrayList<NoticeDto>();
		lists=jYLCalendarService.getNotice(map);
		for (int i = 0; i < lists.size(); i++) {
			if(lists.get(i).getMonth()-1==0){
				lists.get(i).setYear(lists.get(i).getYear()-1);
				lists.get(i).setMonth(12);
			}else{
				lists.get(i).setMonth(lists.get(i).getMonth()-1);				
			}
		}
		
		Map<String, List<NoticeDto>> maps=new HashMap<>();
		maps.put("list", lists);
		
		return maps;
	}
	public static String del(String msg){
		String s="";
		String bf="";
		String af="";
		if(msg.indexOf("?")>-1){
			System.out.println("과정:"+msg+"  "+msg.indexOf("?"));
			bf=msg.substring(0, msg.indexOf("?"));
			af=msg.substring(msg.indexOf("?")+1);
			return del(bf+af);
		}else{
			return msg;
		}
	}
	public static String delque(String msg){
		String s="";
		for (int i = 0; i < msg.length(); i++) {
			if(Character.isDigit(msg.charAt(i))){
				s+=msg.charAt(i);
			}
			if(msg.charAt(i)=='년' || msg.charAt(i)=='월'){
				s+="-";
			}
		}
		return s;	
	}
}
