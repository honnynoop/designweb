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

import com.hk.mobile.dao.HKPollService;
import com.hk.mobile.help.PollBean;
import com.hk.mobile.member.model.HKMember;
import com.hk.mobile.member.model.HKPoll;
import com.hk.mobile.member.model.HKVoter;


@Controller
public class PollController {

	private static final Logger logger = 
LoggerFactory.getLogger(PollController.class);
	
	@Resource(name = "hKPollService")
	private HKPollService hKPollService;
	
	@RequestMapping(value = "pollmake.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmake(Model model) {
		logger.info("Welcome PollController pollmake! "+ new Date());
		model.addAttribute("doc_title", "POLL ��ǥ���׸����");
	
		return "pollmake.tiles";
	}//
	
	@RequestMapping(value = "pollmakeAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmakeAf(PollBean pbean,Model model) {
		logger.info("Welcome PollController pollmakeAf! "+ new Date());
		
		hKPollService.makePoll(pbean);
	
		return "redirect:/polllist.do";
	}//
	@RequestMapping(value = "polling.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String polling(HKVoter voter,Model model) {
		logger.info("Welcome PollController polling! "+ new Date());
		logger.info("Welcome PollController voter! -----------------------------"+ voter);
		hKPollService.polling(voter);
	
		return "redirect:/polllist.do";
	}//
	@RequestMapping(value = "polllist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String polllist(HttpServletRequest request,Model model) {
		logger.info("Welcome PollController polllist! "+ new Date());
		//�̻���� ��ǥ�� ������
		String id=((HKMember)request.getSession().getAttribute("login")).getId();
		model.addAttribute("doc_title", "POLL ��ǥ����");
		model.addAttribute("plists", 
				hKPollService.getPollAllList(id));
		
		return "polllist.tiles";
	}//
	
		@RequestMapping(value = "polldetail.do", 
		method = {RequestMethod.GET,RequestMethod.POST})
	public String polldetail(HKPoll poll, Model model) {
		logger.info("Welcome PollController polldetail! "+ new Date());
		model.addAttribute("doc_title", "POLL ��ǥ �󼼳���");
		model.addAttribute("poll", 
				hKPollService.getPoll(poll));
		model.addAttribute("pollsublist", 
				hKPollService.getPollSubList(poll));
		
		return "polldetail.tiles";
	}//
		@RequestMapping(value = "pollresult.do", 
				method = {RequestMethod.GET,RequestMethod.POST})
			public String pollresult(HKPoll poll, Model model) {
				logger.info("Welcome PollController pollresult! "+ new Date());
				model.addAttribute("doc_title", "POLL ��ǥ ���");
				model.addAttribute("poll", 
						hKPollService.getPoll(poll));
				model.addAttribute("pollsublist", 
						hKPollService.getPollSubList(poll));
				
				return "pollresult.tiles";
			}//
}
