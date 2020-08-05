package com.portfolio.singleproject.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxFileUploadController {

	private static final Logger logger=LoggerFactory.getLogger(AjaxFileUploadController.class);

	@Resource(name = "fileUpProperties")
	private Properties props;

	@RequestMapping("/fileupload")
	public ModelAndView uploadDate(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		logger.info("ajax 업로드 시작");

		String serverFileName = "";
		String serverFileKeyVal = "";
		String fileSize = "";
		String userid=(String) session.getAttribute("userid");


		String type=props.getProperty("file.upload.type");
		logger.info("type={}",type);
		String upDir=props.getProperty("file.upload.path");

		String upPath = request.getSession().getServletContext().getRealPath(upDir);
		//String upPath = "D:/lecture/delight/delight2/delight/src/main/webapp/resources/img";
		logger.info("현재 upPath={}",upPath);
		upPath=upPath+"/"+userid;

		logger.info("유저 아이디 추가후 upPath={}",upPath);

		File uploadFile = new File(upPath);
		if(!uploadFile.exists()){
			uploadFile.mkdirs();
		}

		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		logger.info("isMultipart={}",isMultipart);
		if( isMultipart ) {
			logger.info("여긴거 같은데?");
			File temporaryDir = new File(upPath);
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			factory.setSizeThreshold(1024*100);
			factory.setRepository(temporaryDir);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint
			upload.setSizeMax(1000*1024*1024);

			UploadProgressListener progressListener = new UploadProgressListener();
			//현재의 세션을 넘겨서 세팅해준후..
			progressListener.setHttpSession(session);
			//프로그레스 리스너를 파일업로드시 추가한다.
			upload.setProgressListener(progressListener);		    
			
			// Parse the request
			System.out.println("request="+request.toString());
			List items = upload.parseRequest(request);
			System.out.println("parseReq="+upload.parseRequest(request).toString());
			System.out.println(items.isEmpty());
			// Process the uploaded items
			Iterator iter = items.iterator();
			logger.info("여기까진 오나?");
			System.out.println(iter.hasNext());
			
			while (iter.hasNext()) {
				FileItem fileItem = (FileItem) iter.next();
				if (fileItem.isFormField()) {
					//multipart로 넘어온 폼 필드의 일반 파라미터들
					logger.info("혹시여긴가?");
				} else {				
					try{
						File uploadedFile = new File(upPath, fileItem.getName());
						logger.info("fileGetName={}",fileItem.getName());
						fileItem.write(uploadedFile);		        				        	
					}catch(IOException ioe){
						System.out.println(ioe);
					}
				}
			}
		} else {
			System.out.println("인코딩 타입이 multipart/form-data 가 아님.");
			//enctype이 multipart가 아닐때 처리 해 줄수 있는 부분(request.getParameter()처리)
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("reboard/write");	//progressbardemo.jsp
		return mav;


	}


	//파일업로드 상태를 가져온다.
	@RequestMapping("/uploadstatus")
	public ModelAndView getFileUploadStatus(HttpServletRequest request, HttpServletResponse response, HttpSession session){
		UploadProgressListener progressListener = new UploadProgressListener();
		session = progressListener.getHttpSession();		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("common/upstatus");	
		return mav;
	}

		



}
