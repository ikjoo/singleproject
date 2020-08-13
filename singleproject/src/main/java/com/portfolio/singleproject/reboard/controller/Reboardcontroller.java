package com.portfolio.singleproject.reboard.controller;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.singleproject.common.FileUploadUtil;
import com.portfolio.singleproject.common.Utility;
import com.portfolio.singleproject.login.model.LoginServices;
import com.portfolio.singleproject.reboard.model.ReboardService;
import com.portfolio.singleproject.reboard.model.ReboardVO;
import com.portfolio.singleproject.register.model.RegisterVO;

@Controller
public class Reboardcontroller {
	
	private static final Logger logger=LoggerFactory.getLogger(Reboardcontroller.class);
	
	@Autowired
	private FileUploadUtil fileUtil;
	
	@Autowired
	private ReboardService reboardService;
	
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
		
		reboardVo.setFilename(fileName);
		reboardVo.setOriginalfilename(originalFileName);
		reboardVo.setFilesize(fileSize);
		reboardVo.setUserid(userid);
		
		logger.info("셋팅후 reboardVo={}",reboardVo);
		
		String ckimg=Utility.ckupimg.get(userid);
		
		logger.info("ckimg={}",ckimg);
		
		reboardVo.setCkimgup(ckimg);
		
		logger.info("기타 등등 setting하고 reboardVo={}",reboardVo);
		
		int res=reboardService.reboardWrite(reboardVo);
		
		String msg="", url="";
		
		if(res>0) {
			msg="글 등록 되었습니다.";
			url="/main";
		}else {
			msg="등록 중 오류 발생";
			url="/write";
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg",msg);
		
		return "common/message";
	}
	
	@RequestMapping("/readCnt")
	public Object readcountUp(@RequestParam int reNo) {
		logger.info("detail전 조회수 증가 파라미터 reboardNo={}",reNo);
		
		int res=reboardService.readcountUp(reNo);
		
		logger.info("조회수 증가 결과 res={}",res);
		
		return "redirect:/detail?reboardNo="+reNo;
		
	}
	
	@RequestMapping("/detail")
	public Object reboardDetail(@RequestParam int reboardNo,Model model) {
		logger.info("게시글 상세보기 파라미터 reboardNo={}",reboardNo);
		
		ReboardVO vo=reboardService.reboardSelByNo(reboardNo);
		
		logger.info("상세 보기 검색 결과 vo={}",vo);
		
		model.addAttribute("vo", vo);
		
		return "reboard/detail";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Object reboardEdit(@RequestParam int no,Model model) {
		logger.info("게시글 수정 파라미터 no={}",no);
		
		ReboardVO vo=reboardService.reboardSelByNo(no);
		
		logger.info("검색결과 vo={}",vo);
		
		model.addAttribute("vo", vo);
		
		return "reboard/edit";
		
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public Object reboardEditpost(@ModelAttribute ReboardVO reboardVo,HttpSession session,
			HttpServletRequest request,Model model,@RequestParam int boardNo
			,@RequestParam String brfck) {
		logger.info("게시글 수정하기 파라미터 reboardVo={},boardNo={}",reboardVo,boardNo);
		logger.info("brfck={}",brfck);
		
		String userid=(String) session.getAttribute("userid");
		
		ReboardVO vo=reboardService.reboardSelByNo(boardNo);
		
		String[] newCk=brfck.split("/");
		
		logger.info("newCk={}",newCk.toString());
		
		List<String> newCkList= new ArrayList<String>();
		
		for(String aa:newCk) {
			newCkList.add(aa);
		}
		
		String[] oldCk=vo.getCkimgup().split("|");
		
		logger.info("oldCk={}",oldCk.toString());
		
		List<String> delCkDir= new ArrayList<String>();
		String newCkDir="";
		for(String old:oldCk) {
			for(int i=0;i<newCkList.size();i++) {
				String newckdir=newCkList.get(i);
				if(!newckdir.equals(old)) {
					delCkDir.add(userid+"/"+old);
				}else {
					newCkDir+=old+"|";
					newCkList.remove(i);
				}
			}
		}
		
		if(newCkList.size()>0) {
			for(String aa:newCkList) {
				newCkDir+=aa+"|";
			}
		}
		newCkDir=newCkDir.substring(0, newCkDir.length()-1);
		
		logger.info("새로 등록할 newCkDir={}",newCkDir);
		reboardVo.setCkimgup(newCkDir);
		logger.info("delCkDir={}",delCkDir.toString());
		
		
		//아래 삭제 부분은 수정하고나서로 옮기기
		if(delCkDir.size()>0) {
			for(int i=0;i<delCkDir.size();i++) {
				String path=request.getSession().getServletContext().getRealPath("img")+"/"+delCkDir.get(i);
				File delfile=new File(path);
				if(delfile.exists()) {
					boolean bol=delfile.delete();
					logger.info("oldck삭제 결과 bol={}",bol);
				}
			}
		}
		
		MultipartFile uppartfile=(MultipartFile) request.getAttribute("upfile");
		if(!uppartfile.isEmpty()) {
			//파일 업로드
			List<Map<String, Object>> list=fileUtil.fileUpload(request,FileUploadUtil.FILE_UPLOAD);
			String fileName="", originalFileName="";
			long fileSize=0;
			
			for(Map<String, Object> map: list) {
				originalFileName=(String) map.get("originalFileName");
				fileName=(String) map.get("fileName");
				fileSize=(Long) map.get("fileSize");
				
			}//for
			
			reboardVo.setFilename(fileName);
			reboardVo.setOriginalfilename(originalFileName);
			reboardVo.setFilesize(fileSize);
			reboardVo.setDowncount(0);
		}else {
			reboardVo.setFilename(vo.getFilename());
			reboardVo.setOriginalfilename(vo.getOriginalfilename());
			reboardVo.setFilesize(vo.getFilesize());
			reboardVo.setDowncount(vo.getDowncount());
		}
		
		String url="", msg="";
		
		
		return "common/message";
		
	}
}
