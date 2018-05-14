package com.mirhenge.jyl.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mirhenge.jyl.mboard.dao.JYLMBoardService;

@Controller
public class AJAXBBSController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private JYLMBoardService jYLMBoardService;

	@RequestMapping(value="boardAjax.do")
	public void ajaxlist(HttpServletRequest req, HttpServletResponse res) throws IOException {
		int seq = Integer.parseInt(req.getParameter("seq"));
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=utf-8");
		res.setHeader("Cache Controll", "no-cache");
		
		String preContent = jYLMBoardService.preView(seq);
		PrintWriter out = res.getWriter();
		out.print(preContent);
	}
	
}
