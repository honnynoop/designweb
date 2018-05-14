package com.mirhenge.jyl.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mirhenge.jyl.mboard.dao.JYLMBoardService;
import com.mirhenge.jyl.mboard.help.BbsParam;
import com.mirhenge.jyl.mboard.model.JYLMBoard;

@Controller
public class BbsController {
	
	private static final Logger logger = 
			LoggerFactory.getLogger(BbsController.class);
	
	@Autowired
	private JYLMBoardService jYLMBoardService;
	
	
	@RequestMapping(value = "bbslist.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbslist(BbsParam param,Model model) throws Exception {
		logger.info("Welcome FintechBBSController bbslist! "+ new Date());
		
		int sn=param.getPageNumber();
		int start=(sn)*param.getRecordCountPerPage()+1;
		int end=(sn+1)*param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		logger.info("Welcome FintechBBSController param! "+ param);
		int totalRecordCount=
				jYLMBoardService.getBbsTotalCount(param);
		logger.info("Welcome FintechBBSController totalRecordCount! "+ totalRecordCount);
		List<JYLMBoard> bbslist=
				jYLMBoardService.getBbsPageList(param);
		logger.info("Welcome FintechBBSController bbslist! "+ bbslist.size());
		model.addAttribute("doc_title", "BBS 글목록");
		model.addAttribute("bbslist", bbslist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		model.addAttribute("s_category", param.getS_category());
		model.addAttribute("s_keyword", param.getS_keyword());
		
		return "bbslist.tiles";
	}//
	
	
	@RequestMapping(value = "bbswrite.do", 
			method = RequestMethod.GET)
	public String bbswrite(Model model) {
		logger.info("Welcome BBSController bbswrite! "+ new Date());
		model.addAttribute("doc_title", "BBS 글쓰기");
		return "bbswrite.tiles";
	}//
	@RequestMapping(value = "bbswriteAf.do", 
			method = RequestMethod.POST)
	public String bbswriteAf(JYLMBoard bbs,Model model) throws Exception {
		logger.info("Welcome BBSController bbswriteAf! "+ new Date());
		jYLMBoardService.writeBbs(bbs);
		return "redirect:/bbslist.do";
	}//
	@RequestMapping(value = "bbsdetail.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsdetail(JYLMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsdetail! "+ new Date());
		model.addAttribute("doc_title", "BBS 상세보기");
		model.addAttribute("bbs", 
				jYLMBoardService.getBbs(mboard));
		return "bbsdetail.tiles";
	}//
	@RequestMapping(value = "bbsreply.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsreply(JYLMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsreply! "+ new Date());
		model.addAttribute("doc_title", "BBS 답글달기");
		model.addAttribute("bbs", 
				jYLMBoardService.getBbs(mboard));
		return "bbsreply.tiles";
	}//
	
	
		@RequestMapping(value = "bbsupdate.do", 
		method = {RequestMethod.GET,RequestMethod.POST})
public String bbsupdate(JYLMBoard mboard, Model model) {
	logger.info("Welcome BBSController bbsupdate! "+ new Date());
	logger.info("Welcome BBSController HKMBoard! "+ mboard);
	model.addAttribute("doc_title", "BBS 수정하기");
	model.addAttribute("bbs", 
			jYLMBoardService.getBbs(mboard));
	return "bbsupdate.tiles";
}//
	
	
	@RequestMapping(value = "bbsreplyAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsreplyAf(JYLMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsreplyAf! "+ new Date());
	
		boolean isS=false;
		try {
			jYLMBoardService.reply(mboard);
			isS=true;
		} catch (Exception e) {
		}
		if(isS){
			return "redirect:/bbslist.do";
		}else{
			return "redirect:/bbslist.do";
		}
	}//
	@RequestMapping(value = "bbsupdateAf.do", 
			method = RequestMethod.POST)
	public String bbsupdateAf(JYLMBoard bbs,Model model) throws Exception {
		logger.info("Welcome BBSController bbsupdateAf! "+ new Date());
		jYLMBoardService.updateBbs(bbs);
		return "redirect:/bbslist.do";
	}//
}
