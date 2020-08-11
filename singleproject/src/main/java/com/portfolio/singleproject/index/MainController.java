package com.portfolio.singleproject.index;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.singleproject.common.PaginationInfo;
import com.portfolio.singleproject.common.Utility;
import com.portfolio.singleproject.reboard.model.ReboardService;
import com.portfolio.singleproject.reboard.model.ReboardVO;

@Controller
public class MainController {
	private static final Logger logger=LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ReboardService reboardService;
	
	@RequestMapping("/main")
	public Object Mainhome(@ModelAttribute ReboardVO reboardVo,Model model) {
		logger.info("메인화면 보이기");
		logger.info("목록 파라미터 reboardVo={}",reboardVo);
		
		PaginationInfo pagingInfo=new PaginationInfo();
		pagingInfo.setBlockSize(Utility.BLOCK_SIZE);
		pagingInfo.setRecordCountPerPage(Utility.RECORD_COUNT);
		pagingInfo.setCurrentPage(reboardVo.getCurrentPage());
		
		reboardVo.setRecordCountPerPage(Utility.RECORD_COUNT);
		reboardVo.setFirstRecordIndex(pagingInfo.getFirstRecordIndex());
		
		logger.info("셋팅후 reboardVo={}",reboardVo);
		
		List<ReboardVO> list=reboardService.reboardSearch(reboardVo);
		logger.info("검색결과 list.size()={}",list.size());
		
		int totalRecord=reboardService.searchTotal(reboardVo);
		logger.info("totalRecord={}",totalRecord);
		
		pagingInfo.setTotalRecord(totalRecord);
		
		logger.info("pagingInfo={}",pagingInfo);
		
		model.addAttribute("list", list);
		model.addAttribute("pagingInfo", pagingInfo);
		
		return "Main";
	}
	
}
