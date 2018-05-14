package com.mirhenge.jyl.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mirhenge.jyl.member.model.JYLMember;
import com.mirhenge.jyl.poll.dao.JYLPollService;
import com.mirhenge.jyl.poll.help.PollBean;
import com.mirhenge.jyl.poll.model.JYLPoll;
import com.mirhenge.jyl.poll.model.JYLVoter;

@Controller
public class PollController {
	private static final Logger logger = 
			LoggerFactory.getLogger(PollController.class);
    @Autowired
	private JYLPollService jYLPollService;
    
    private String getId(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Object oLogin=session.getAttribute("login");
		if(oLogin==null){
			throw new Exception("session이 없다.");
		}		
		return ((JYLMember)oLogin).getId();
	}
    
	@RequestMapping(value = "polllist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String polllist(HttpServletRequest request,Model model) throws Exception {
		logger.info("Welcome PollController polllist! "+ new Date());
		//이사람이 두표를 했을까
		 String id="";
		 try{
				id=getId(request);//위에 메서드로 만듬
			}catch (Exception e) {
				return "redirect:/login.do";
			}
		
		model.addAttribute("doc_title", "POLL 투표문항");
		model.addAttribute("plists", 
				jYLPollService.getPollAllList(id));
		
		return "polllist.tiles";
	}//
	@RequestMapping(value = "pollmake.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmake(Model model) {
		logger.info("Welcome PollController pollmake! "+ new Date());
		model.addAttribute("doc_title", "POLL 투표문항만들기");
	
		return "pollmake.tiles";
	}//
	@RequestMapping(value = "pollmakeAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String pollmakeAf(PollBean pbean,Model model) {
		logger.info("Welcome PollController pollmakeAf! "+ new Date());
		jYLPollService.makePoll(pbean);
		return "redirect:/polllist.do";
	}//
	@RequestMapping(value = "polldetail.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
		public String polldetail(JYLPoll poll, Model model) {
			logger.info("Welcome PollController polldetail! "+ new Date());
			model.addAttribute("doc_title", "POLL 투표 상세내용");
			model.addAttribute("poll", 
					jYLPollService.getPoll(poll));
			model.addAttribute("pollsublist", 
					jYLPollService.getPollSubList(poll));
			
			return "polldetail.tiles";
		}//
	@RequestMapping(value = "polling.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String polling(JYLVoter voter,Model model) {
		logger.info("Welcome PollController polling! "+ new Date());
		logger.info("Welcome PollController voter! -----------------------------"+ voter);
		jYLPollService.polling(voter);
	
		return "redirect:/polllist.do";
	}//
	@RequestMapping(value = "pollresult.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
		public String pollresult(JYLPoll poll, Model model) {
			logger.info("Welcome PollController pollresult! "+ new Date());
			model.addAttribute("doc_title", "POLL 투표 결과");
			model.addAttribute("poll", 
					jYLPollService.getPoll(poll));
			model.addAttribute("pollsublist", 
					jYLPollService.getPollSubList(poll));
			
			return "pollresult.tiles";
		}//
}
