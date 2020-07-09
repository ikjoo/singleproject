package com.portfolio.singleproject.reboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Reboardcontroller {
	
	private static final Logger logger=LoggerFactory.getLogger(Reboardcontroller.class);
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public Object reboardWrite() {
		
		logger.info("답변형 게시판 화면 띄우기");
		
		return "reboard/write";
	}

}
