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

import com.mirhenge.jyl.mboard.dao.JYLMBoardService;


@Controller
public class PdfController {

	private static final Logger logger = 
			LoggerFactory.getLogger(PdfController.class);
	
	@Autowired
	private JYLMBoardService jYLMBoardService;
	
	@RequestMapping(value = "downloadPDF.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String downloadPDF(Model model) throws Exception {
		logger.info("Welcome PdfController downloadPDF! "+ new Date());
		model.addAttribute("bbslist", jYLMBoardService.getBbsList());
		return "pdfView";
	}
	
	@RequestMapping(value = "downloadExcel.do", method = {RequestMethod.GET,RequestMethod.POST})
	public String downloadExcel(Model model) throws Exception {
		logger.info("Welcome PdfController downloadExcel! "+ new Date());
		    model.addAttribute("bbslist", jYLMBoardService.getBbsList());
		return "excelView";
	}

	
}
