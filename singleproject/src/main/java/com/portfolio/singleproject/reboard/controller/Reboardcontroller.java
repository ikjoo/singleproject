package com.portfolio.singleproject.reboard.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.singleproject.reboard.model.ReboardVO;

@Controller
public class Reboardcontroller {
	
	private static final Logger logger=LoggerFactory.getLogger(Reboardcontroller.class);
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public Object reboardWrite() {
		
		logger.info("답변형 게시판 화면 띄우기");
		
		return "reboard/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public Object reboardWritePost(@ModelAttribute ReboardVO reboardVo,HttpSession session) {
		
		
		
		return "";
	}

}
