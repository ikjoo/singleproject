package com.portfolio.singleproject.reboard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.portfolio.singleproject.common.FileUploadUtil;
import com.portfolio.singleproject.common.Utility;
import com.portfolio.singleproject.reboard.model.ReboardVO;

@Controller
public class Reboardcontroller {
	
	private static final Logger logger=LoggerFactory.getLogger(Reboardcontroller.class);
	
	@Autowired
	private FileUploadUtil fileUtil;
	
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
	public Object reboardWritePost(@ModelAttribute ReboardVO reboardVo,HttpSession session,
			HttpServletRequest request,Model model) {
		
		logger.info("글등록 파라미터 reboardVo={}",reboardVo);
		
		String userid=(String) session.getAttribute("userid");
		
		logger.info("유저아이디 userid={}",userid);
		
		//파일 업로드
		List<Map<String, Object>> list=fileUtil.fileUpload(request,FileUploadUtil.FILE_UPLOAD);
		String fileName="", originalFileName="";
		long fileSize=0;
		
		for(Map<String, Object> map: list) {
			originalFileName=(String) map.get("originalFileName");
			fileName=(String) map.get("fileName");
			fileSize=(Long) map.get("fileSize");
			
		}//for
		
		
		return "";
	}
	
	

}
