package com.lzt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class TestController {
	
	public static final Logger log = LoggerFactory.getLogger(TestController.class);  

	@RequestMapping("/test.do")
	public String test01(){
		return "index";
		
	}

	@RequestMapping("/login.do")
	public String test02(){
		return "login";

	}

}
