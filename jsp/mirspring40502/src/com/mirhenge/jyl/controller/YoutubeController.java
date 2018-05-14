package com.mirhenge.jyl.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


import com.mirhenge.jyl.mboard.help.YesMember;
import com.mirhenge.jyl.member.model.JYLMember;
import com.mirhenge.jyl.you.Youtube;
import com.mirhenge.jyl.you.YoutupeParser;
import com.mirhenge.jyl.you.dao.YoutubeService;
import com.mirhenge.jyl.you.model.YoutubeSave;


@Controller
public class YoutubeController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(YoutubeController.class);
	
	@Autowired
	private YoutupeParser youtupeParser;
	
	@Autowired
	private YoutubeService youtubeService;
	
	@RequestMapping(value = "yutube.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String yutube(String s_keyword,Model model) throws Exception {
		logger.info("Welcome YoutubeController yutube! "+ new Date());
		if(s_keyword!=null &&  ! s_keyword.equals("")){
			logger.info("Welcome YoutubeController yutube!   "+ s_keyword);
			//YoutupeParser parser=new YoutupeParser();
			try{
				ArrayList<Youtube> getTitles=youtupeParser.getTitles(s_keyword);
				model.addAttribute("bbslist", getTitles);
			}catch (Exception e) {
				
			}
			model.addAttribute("s_keyword", s_keyword);
			//model.addAttribute("doc_title", "Youtube");
		}
		model.addAttribute("doc_title", "Youtube");
		return "yutube.tiles";
	}//
	
	@RequestMapping(value = "youtubesave.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public YoutubeSave youtubesave(YoutubeSave you,Model model) throws Exception {
		logger.info("Welcome YoutubeController youtubesave! "+ new Date());
		logger.info("Welcome YoutubeController youtubesave! -------------------------"+ you);
		youtubeService.writeYoutube(you);
		//model.addAttribute("doc_title", "Youtube");
		YoutubeSave ysave= youtubeService.getYoutube(you);
		return ysave;
	}//
	@RequestMapping(value = "getyoutube.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public YesMember getyoutube(YoutubeSave you,Model model) throws Exception {
		logger.info("Welcome YoutubeController getyoutube! "+ new Date());
		logger.info("Welcome YoutubeController getyoutube! -------------------------"+ you);
		YoutubeSave ysave= youtubeService.getYoutube(you);
		YesMember yes=new YesMember();
		if(ysave!=null && ysave.getId()!=null){
			yes.setMessage("SUCS");   //{"message","SUCS"}
		}else{
			yes.setMessage("FAIL");
		}
		logger.info("Welcome YoutubeController getyoutube! -------------------------"+ yes);
		return yes;
	}//
	private String getId(HttpServletRequest request) throws Exception{
		HttpSession session=request.getSession();
		Object oLogin=session.getAttribute("login");
		if(oLogin==null){
			throw new Exception("session이 없다.");
		}		
		return ((JYLMember)oLogin).getId();
	}
	@RequestMapping(value = "yutubelist.do", 
			method = {RequestMethod.POST,RequestMethod.GET})
	public String yutubelist(HttpServletRequest request,Model model) throws Exception {
		
		logger.info("Welcome YoutubeController yutubelist! "+ new Date());
		
		 String id="";
		 try{
				id=getId(request);//위에 메서드로 만듬
			}catch (Exception e) {
				return "redirect:/login.do";
			}
		YoutubeSave you =new YoutubeSave();
		you.setId(id);
		List<YoutubeSave> getTitles=youtubeService.getYoutubeList(you);
		model.addAttribute("bbslist", getTitles);
		model.addAttribute("doc_title", "Youtube Saved");
		return "yutubelist.tiles";
	}//
	
	
}
