package com.portfolio.singleproject.index;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.singleproject.index.common.ZipUtil;
import com.portfolio.singleproject.index.model.IndexVOList;

@Controller
public class IndexController {

	private final static Logger logger=LoggerFactory.getLogger(IndexController.class);
	
	private final static String BASIC="F:/very/";
	
	private static String zipname="lecture";
	private String zippath="";
	
	@RequestMapping("/home2")
	public Object index(@RequestParam(required = false) String filedir,
			@RequestParam(required = false) String dirname,
			@RequestParam(defaultValue = "1") String updirtype,
			HttpServletResponse response,
			HttpServletRequest request,Model model) {
		logger.info("메인화면보여주기");
		String basicDir="";
		
			if((filedir==null || filedir.isEmpty())) {
				basicDir=BASIC;
				logger.info("basicDir={}",basicDir);
			}else if((filedir!=null&&!filedir.isEmpty())) {
				basicDir=filedir;
				logger.info("basicDir={}",basicDir);
			}
			
			if(updirtype.equals("2")) {
				logger.info("updirtype={}",updirtype);
				logger.info("1. dirname={}",dirname);
				logger.info("바뀌기 전 basicDir={}",basicDir);
				basicDir=basicDir.substring(0, basicDir.lastIndexOf("/"+dirname));
				logger.info("바뀐 후 basicDir={}",basicDir);
				dirname=basicDir.substring(basicDir.lastIndexOf("/")+1);
				logger.info("2. dirname={}",dirname);
				if(filedir.length()<=BASIC.length()) {
					basicDir=BASIC;
					dirname="";
				}
			}
			
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
					
				}//for
				
				model.addAttribute("list", list);
				model.addAttribute("dir",basicDir);
				model.addAttribute("dirname",dirname);
				return "index";
		

			
		
		
		/*else {
			File downfile=new File(filedir);
			
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("file", downfile);
			
			
			ModelAndView mav=new ModelAndView("downloadView", map);
			
			return mav;
			
		}*/
		
		
		
	}
	
	@RequestMapping("/download")
	public Object filedown(@RequestParam(required = false) String downdir) {
		File downfile=new File(downdir);
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		map.put("file", downfile);
		
		
		ModelAndView mav=new ModelAndView("downloadView", map);
		
		return mav;
	}
	
	
	@RequestMapping("/confirm")
	@ResponseBody
	public Object fileconfirm(@RequestParam String godir) {
		logger.info("파일 확인 시작 파라미터 godir={}",godir);
		
		int res=0;
		
		File file=new File(godir);
		
		if(file.isDirectory()) {
			res=1;
		}else {
			res=2;
		}
		
		
		return res;
	}
	
	@RequestMapping("/compressiondown")
	public Object compressiondown(@ModelAttribute IndexVOList filenamelist,@RequestParam String filedir) {
		logger.info("압축 다운 시작 filenamelist={},filedir={}",filenamelist,filedir);
		
		ZipUtil zipmake=new ZipUtil();
		
		List<String> list=filenamelist.getFilenamelist();
		
		String[] path=new String[9999999];
		
		for(int i=0;i<list.size();i++) {
			logger.info(i+"번째 list={}",list.get(i));
			if(list.get(i)!=null && !list.get(i).isEmpty()) {
				String plus=filedir+"/"+list.get(i).trim();
				path[i]=plus;
			}
		}
		
		try {
			boolean bool=zipmake.compress(path, filedir, "lec");
			logger.info("압축 유무 bool={}",bool);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		
		File file=new File(filedir+"/lec.zip");
		
		Map<String, Object> map=new HashMap<String, Object>();
		
		if(file.exists()) {
			map.put("file", file);
		}
		
		ModelAndView mav=new ModelAndView("downloadView", map);
		
		return mav;
	}
	
	@RequestMapping("/authselect")
	public Object authSelect() {
		logger.info("공유폴더 설정 화면 보여주기");
		
		return "user/authFolderSelect";
	}
	
}
