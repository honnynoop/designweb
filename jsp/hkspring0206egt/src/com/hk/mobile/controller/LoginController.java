package com.hk.mobile.controller;

import java.util.Date;

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

import com.hk.mobile.dao.HKMemberService;
import com.hk.mobile.member.model.HKMember;
import com.hk.mobile.member.model.YesMember;

@Controller
public class LoginController {

	private static final Logger logger = 
	  LoggerFactory.getLogger(LoginController.class);
	
	@Resource(name = "hKMemberService")
	private HKMemberService hKMemberService;
		
	
	@RequestMapping(value = "login.do", 
			method = RequestMethod.GET)
	public String login(Model model) {
		logger.info("Welcome LoginController login! "+ new Date());
		model.addAttribute("head", "WELCOME");
		return "login.tiles";
	}//

	@RequestMapping(value = "regi.do", 
	method = {RequestMethod.GET,RequestMethod.POST})
	public String regi(Model model) {
		logger.info("Welcome LoginController regi! "+ new Date());
		model.addAttribute("head", "REGISTRY");
		return "regi.tiles";
	}//
	@RequestMapping(value = "regiAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String regiAf(HKMember member,Model model) {
		logger.info("Welcome LoginController regiAf! "+ new Date());
		logger.info("Welcome LoginController --------- "+ member);
		hKMemberService.addMember(member);
		return "redirect:/login.do";
	}//
	
	@RequestMapping(value = "loginAf.do", 
			method = RequestMethod.POST)
	public String loginAf(HttpServletRequest request
			,HKMember member,Model model)  {
		logger.info("Welcome LoginController loginAf! "+ new Date());
		HKMember login=hKMemberService.checkMember(member);
		logger.info("Welcome LoginController loginAf!---- "+ login);
		if(login!=null && !login.getId().equals("")){
			request.getSession().setAttribute(
					"login", login);
			request.getSession().setMaxInactiveInterval(30*60);
		}else{
			request.getSession().invalidate();
		}
		model.addAttribute("head", "WELCOME");
		return "forward:/bbslist.do";
	}
	
	@RequestMapping(value = "logout.do", 
			method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		logger.info("Welcome LoginController logout! "+ new Date());
		request.getSession().invalidate();
		model.addAttribute("head", "WELCOME");
		return "login.tiles";
	}//
	
	@RequestMapping(value = "getID.do", 
			method = RequestMethod.POST)
	@ResponseBody
	public YesMember getID(
			HKMember member, Model model) throws Exception {
		logger.info("Welcome LoginController getID! "+ new Date());
		int count=hKMemberService.getID(member);
		
		YesMember yes=new YesMember();
		if(count>0){
			yes.setMessage("SUCS");
		}else{
			yes.setMessage("FAIL");
		}
		return yes;
	}//
}
