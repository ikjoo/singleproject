package com.portfolio.singleproject.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmailController {
	
	private static final Logger logger=LoggerFactory.getLogger(EmailController.class);
	
	@RequestMapping("/register/useremail")
	@ResponseBody
	public int emailsend(@RequestParam String email1,@RequestParam String email2
			,@RequestParam(required = false) String email3) {
		
		logger.info("이메일 보내기 시작 파라미터 email1={},email2={}",email1,email2);
		logger.info("email3={}",email3);
		
		
		
		
	}
	
}
