package com.hk.mobile.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.mobile.dao.HKBoardService;
import com.hk.mobile.help.BbsParam;
import com.hk.mobile.member.model.HKBoard;

@Controller
public class BoarderController {

	private static final Logger logger = 
			LoggerFactory.getLogger(BoarderController.class);
	
	@Resource(name = "hKBoardService")
	private HKBoardService hKBoardService;
	
	@RequestMapping(value = "board2.do", 
			method = RequestMethod.GET)
	public String board2(Model model) throws Exception{
		logger.info("Welcome BoarderController board! "+ new Date());
		model.addAttribute("head", "글목록");
		model.addAttribute("boardlist",
				hKBoardService.getBoardList());
		return "board.tiles";
	}//
	
	@RequestMapping(value = "board.do", 
			method = {RequestMethod.GET,RequestMethod.POST})
	public String board(BbsParam param,Model model) throws Exception {
		logger.info("Welcome BoarderController board! "+ new Date());
		logger.info("Welcome BoarderController param! "+ param);
		int sn=param.getPageNumber();
		int start=(sn)*param.getRecordCountPerPage()+1;
		int end=(sn+1)*param.getRecordCountPerPage();
		
		param.setStart(start);
		param.setEnd(end);
		
		int totalRecordCount=
				hKBoardService.getBoardTotalCount(param);
		List<HKBoard> bbslist=
				hKBoardService.getBoardPageList(param);
		
		model.addAttribute("head", "BBS");
		model.addAttribute("boardlist", bbslist);//

		model.addAttribute("pageNumber", sn);
		model.addAttribute("pageCountPerScreen", 10);
		model.addAttribute("recordCountPerPage", param.getRecordCountPerPage());
		model.addAttribute("totalRecordCount", totalRecordCount);
		
		model.addAttribute("s_category", param.getS_category());
		model.addAttribute("s_keyword", param.getS_keyword());
		
		return "board.tiles";//
	}//
	@RequestMapping(value = "writeboard.do", 
			method = RequestMethod.GET)
	public String writeboard(Model model) throws Exception{
		logger.info("Welcome BoarderController writeboard! "+ new Date());
		model.addAttribute("head", "글쓰기");
		return "writeboard.tiles";
	}//
	@RequestMapping(value = "writeboardAf.do", 
			method = RequestMethod.POST)
	public String writeboardAf(HKBoard board,Model model) throws Exception{
		logger.info("Welcome BoarderController writeboardAf! "+ new Date());
		model.addAttribute("head", "글쓰기");
		hKBoardService.writeboard(board);
		return "redirect:/board.do";
	}//
}
