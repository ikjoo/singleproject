package com.portfolio.singleproject.register.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.singleproject.register.model.RegisterServicesImpl;

@Controller
public class RegisterController {
	private final static Logger logger=LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private RegisterServicesImpl registerService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public Object register() {
		logger.info("회원가입 화면");
		
		return "user/register";
	}
	
	@RequestMapping("/register/idChk")
	@ResponseBody
	public int useridDuplChk(@RequestParam String useridChk) {
		
		logger.info("아이디 중복 확인 파라미터 useridChk={}",useridChk);
		
		int res=2;
		
		res=registerService.userIdChk(useridChk);
		
		logger.info("검색 결과 res={}",res);
		
		return res;
	}
	
}
