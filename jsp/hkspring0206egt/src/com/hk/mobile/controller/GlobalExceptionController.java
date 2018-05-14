package com.hk.mobile.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import com.hk.mobile.exception.BSQLException;

@ControllerAdvice
public class GlobalExceptionController  {

	@ExceptionHandler(BSQLException.class)
	public ModelAndView handleCustomException(BSQLException ex) {
		ModelAndView model = new ModelAndView("error.tiles");
		model.addObject("errCode", ex.getErrCode());
		model.addObject("errMsg", ex.getErrMsg());
		return model;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(Exception ex) {
		ModelAndView model = new ModelAndView("error.tiles");
		model.addObject("errMsg", "this is Exception.class");
		return model;
	}

	
}