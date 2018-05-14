package com.mirhenge.jyl.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mirhenge.jyl.mboard.dao.JYLMBoardService;
import com.mirhenge.jyl.mboard.help.YesMember;
import com.mirhenge.jyl.member.dao.JYLMemberService;
import com.mirhenge.jyl.member.model.JYLMember;

@Controller
public class LoginController {

	private static final Logger logger = 
	  LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private JYLMemberService jylMemberService;
	
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
	//http://localhost:8090/mirspring40419/getID.do?id=kim
	@RequestMapping(value = "getID.do", 
			method = RequestMethod.POST)
	@ResponseBody
	public YesMember getID(
			JYLMember member, Model model) throws Exception {
		logger.info("Welcome LoginController getID! "+ new Date());
		int count=jylMemberService.getID(member);
		
		YesMember yes=new YesMember();
		if(count>0){
			yes.setMessage("SUCS");
		}else{
			yes.setMessage("FAIL");
		}
		return yes;
	}//
	@RequestMapping(value = "regiAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String regiAf(JYLMember member,Model model) {
		logger.info("Welcome LoginController regiAf! "+ new Date());
		logger.info("Welcome LoginController --------- "+ member);
		jylMemberService.addMember(member);
		return "redirect:/login.do";
	}//
	@RequestMapping(value = "loginAf.do", 
			method = RequestMethod.POST)
	public String loginAf(HttpServletRequest request
			,JYLMember member,Model model)  {
		logger.info("Welcome LoginController loginAf! "+ new Date());
		JYLMember login=jylMemberService.checkMember(member);
		logger.info("Welcome LoginController loginAf!---- "+ login);
		if(login!=null && !login.getId().equals("")){
			request.getSession().setAttribute(
					"login", login);
			request.getSession().setMaxInactiveInterval(10*60);
			model.addAttribute("head", "WELCOME");
			return "forward:/bbslist.do";
		}else{
			request.getSession().invalidate();
			return "redirect:/login.do";
		}
	}
	
	@RequestMapping(value = "logout.do", 
			method = RequestMethod.GET)
	public String logout(HttpServletRequest request,Model model) {
		logger.info("Welcome LoginController logout! "+ new Date());
		request.getSession().invalidate();
		model.addAttribute("head", "WELCOME");
		return "login.tiles";
	}//
	
}
