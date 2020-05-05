package com.portfolio.singleproject.index;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private final static Logger logger=LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping("/home2")
	public String index(Model model) {
		logger.info("메인화면보여주기");
		
		String basicDir="F:\\very";
		
		File file=new File(basicDir);
		File[] fileList=file.listFiles();
		List<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
		
		for(File tmp:fileList) {
			Map<String, Object> map=new HashMap<String, Object>();
			logger.info("파일명={}",tmp.getName());
			if(tmp.isDirectory()) {

				map.put("fileName", tmp.getName()+"?");
				map.put("size", FileUtils.sizeOfDirectory(new File(tmp.getAbsolutePath()))/1024);
				list.add(map);
			}else {
				map.put("fileName",tmp.getName());
				map.put("size", tmp.length()/1024);
				list.add(map);
			}
			
		}
		
		model.addAttribute("list", list);
		
		return "index";
	}
	
}
