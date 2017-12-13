package com.lzt.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lzt.service.UploadService;
import com.lzt.util.FtpUtil;

@Service("uploadService")
public class UploadServiceImpl implements UploadService {
		
	@Value("${ftp.IP}")
	private String IP;
	@Value("${ftp.port}")
	private String port;
	@Value("${ftp.username}")
	private String username;
	@Value("${ftp.password}")
	private String password;
	@Value("${ftp.baseImgUrl}")
	private String baseImgUrl;

	@Override
	public boolean upload(MultipartFile uploadFile) throws NumberFormatException, IOException {
		String oldname = uploadFile.getOriginalFilename();
		//基础目录
		String baseUrl = baseImgUrl;
		//保存路径名(一个用户一个文件夹)模拟用户名:admin
		String admin = "admin";
		String filePath = "/"+admin;
		//新的名称
		String newname = username+System.currentTimeMillis()+oldname.substring(oldname.lastIndexOf(".")); 
		boolean flag = FtpUtil.uploadFile(IP, Integer.parseInt(port), 
				username, password, baseUrl, filePath, 
				newname, uploadFile.getInputStream());
		if(flag){
			System.out.println("成功");
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean down(String url) throws NumberFormatException, IOException {
		
		return false;
	}

}










