package com.mirhenge.jyl.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	private static final Logger logger = 
			LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "home.do", 
			method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("Welcome HomeController home! "+ new Date());
		model.addAttribute("hello", "hello i'm jyl");
		return "home";
	}//			
}
