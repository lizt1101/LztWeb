package com.lzt.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
	
	public boolean upload(MultipartFile uploadFile) throws NumberFormatException, IOException;
	
	public boolean down(String url) throws NumberFormatException, IOException;

	public boolean uploadHeadImage(String file) throws FileNotFoundException;

	public boolean uploadBjImage(MultipartFile  file,Integer maxId) throws IOException;

	public boolean uploadLBImage(MultipartFile  file,String newFileName,String filePath) throws IOException;

}
