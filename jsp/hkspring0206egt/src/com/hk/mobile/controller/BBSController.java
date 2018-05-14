package com.hk.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.mobile.dao.HKMBoardService;
import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKMBoard;

@Controller
public class BBSController {

	private static final Logger logger = 
LoggerFactory.getLogger(BBSController.class);
	
	//
	//@Autowired
	@Resource(name = "hKMBoardService")
	private HKMBoardService hKMBoardService;
	
	@RequestMapping(value = "bbslist2.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbslist2(Model model) {
		logger.info("Welcome BBSController bbslist! "+ new Date());
		model.addAttribute("doc_title", "BBS");
		model.addAttribute("bbslist", 
				hKMBoardService.getBbsList());
		return "bbslist.tiles";
	}//

	@RequestMapping(value = "bbsdetail.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsdetail(HKMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsdetail! "+ new Date());
		model.addAttribute("doc_title", "BBS 상세보기");
		model.addAttribute("bbs", 
				hKMBoardService.getBbs(mboard));
		return "bbsdetail.tiles";
	}//
	@RequestMapping(value = "bbsreply.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsreply(HKMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsreply! "+ new Date());
		model.addAttribute("doc_title", "BBS 답글달기");
		model.addAttribute("bbs", 
				hKMBoardService.getBbs(mboard));
		return "bbsreply.tiles";
	}//
	
	
		@RequestMapping(value = "bbsupdate.do", 
		method = {RequestMethod.GET,RequestMethod.POST})
public String bbsupdate(HKMBoard mboard, Model model) {
	logger.info("Welcome BBSController bbsupdate! "+ new Date());
	logger.info("Welcome BBSController HKMBoard! "+ mboard);
	model.addAttribute("doc_title", "BBS 수정");
	model.addAttribute("bbs", 
			hKMBoardService.getBbs(mboard));
	return "bbsupdate.tiles";
}//
	
	
	@RequestMapping(value = "bbsreplyAf.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String bbsreplyAf(HKMBoard mboard, Model model) {
		logger.info("Welcome BBSController bbsreplyAf! "+ new Date());
	
		boolean isS=false;
		try {
			hKMBoardService.reply(mboard);
			isS=true;
		} catch (Exception e) {
		}
		if(isS){
			return "redirect:/bbslist.do";
		}else{
			return "redirect:/bbslist.do";
		}
	}//
	
	
	
	//bbswrite.do
	@RequestMapping(value = "bbswrite.do", 
			method = RequestMethod.GET)
	public String bbswrite(Model model) {
		logger.info("Welcome BBSController bbswrite! "+ new Date());
		model.addAttribute("doc_title", "BBS 글쓰기");
		return "bbswrite.tiles";
	}//
	
	@RequestMapping(value = "bbswriteAf.do", 
			method = RequestMethod.POST)
	public String bbswriteAf(HKMBoard bbs,Model model) throws Exception {
		logger.info("Welcome BBSController bbswriteAf! "+ new Date());
		hKMBoardService.writeBbs(bbs);
		return "redirect:/bbslist.do";
	}//
	@RequestMapping(value = "bbsupdateAf.do", 
			method = RequestMethod.POST)
	public String bbsupdateAf(HKMBoard bbs,Model model) throws Exception {
		logger.info("Welcome BBSController bbsupdateAf! "+ new Date());
		hKMBoardService.updateBbs(bbs);
		return "redirect:/bbslist.do";
	}//
	@RequestMapping(value = "bbsdelete.do", 
			method = RequestMethod.POST)
	public String bbsdelete(HKMBoard bbs,Model model) throws Exception {
		logger.info("Welcome BBSController bbsdelete! "+ new Date());
		hKMBoardService.bbsdelete(bbs);
		return "redirect:/bbslist.do";
	}//
	
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
				hKMBoardService.getBbsTotalCount(param);
		logger.info("Welcome FintechBBSController totalRecordCount! "+ totalRecordCount);
		List<HKMBoard> bbslist=
				hKMBoardService.getBbsPageList(param);
		logger.info("Welcome FintechBBSController bbslist! "+ bbslist.size());
		model.addAttribute("doc_title", "BBS 목록");
		model.addAttribute("bbslist", bbslist);

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		model.addAttribute("s_category", param.getS_category());
		model.addAttribute("s_keyword", param.getS_keyword());
		
		return "bbslist.tiles";
	}//
	
	
	
}
