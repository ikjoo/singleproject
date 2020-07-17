package com.portfolio.singleproject.reboard.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.singleproject.common.Utility;
import com.portfolio.singleproject.reboard.model.ReboardVO;

@Controller
public class Reboardcontroller {
	
	private static final Logger logger=LoggerFactory.getLogger(Reboardcontroller.class);
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public Object reboardWrite(HttpSession session) {
		String userid=(String) session.getAttribute("userid");
		logger.info("답변형 게시판 화면 띄우기");
		
		if(Utility.ckupimg.containsKey(userid)) {
			Utility.ckupimg.remove(userid);
		}
		if(Utility.urltag.containsKey(userid)) {
			Utility.urltag.remove(userid);
		}
		return "reboard/write";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public Object reboardWritePost(@ModelAttribute ReboardVO reboardVo,HttpSession session) {
		
		logger.info("글등록 파라미터 reboardVo={}",reboardVo);
		
		
		
		return "";
	}
	
	

}
