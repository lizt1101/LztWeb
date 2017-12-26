package com.lzt.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.lzt.entity.User;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lzt.service.UploadService;
import com.lzt.util.FtpUtil;

import javax.security.auth.Subject;

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
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		String admin = user.getUsername();
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

	@Override
	public boolean uploadHeadImage(String file) throws FileNotFoundException {
		//基础目录
		String baseUrl = baseImgUrl;
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		String admin = user.getUsername();
		String filePath = "/"+admin;
		boolean flag = FtpUtil.uploadFile(IP, Integer.parseInt(port),
				username, password, baseUrl, filePath,
				file.substring(4), new FileInputStream(file));
		if(flag){
			System.out.println("成功");
			return true;
		}else{
			return false;
		}
	}
}










