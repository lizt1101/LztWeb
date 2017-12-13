package com.lzt.controller;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lzt.service.UploadService;

@Controller
public class FtpController {
	
	public static final Logger log = LoggerFactory.getLogger(FtpController.class);  
	
	@Autowired
	private UploadService uploadService;
	
	@RequestMapping(value={"/upload.do"},method=RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public void uploadFile(MultipartFile uploadFile) throws NumberFormatException, IOException{
		log.info("文件上传");
		boolean flag = uploadService.upload(uploadFile);
	}
	
	@RequestMapping(value={"/down.do"},method=RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public void downFile(String url) throws NumberFormatException, IOException{
		log.info("文件下载:"+url);
		
		
	}
	
	

}











