package com.class430.configuration;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleException {

	@ExceptionHandler(Exception.class)
	public ModelAndView handler(Exception e){
		ModelAndView model = new ModelAndView("error");
		e.printStackTrace();
		return model;
	}
}
