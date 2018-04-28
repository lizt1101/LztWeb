package com.lzt.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class MainManagerController {
	
	@RequestMapping("/tomain.do")
	public String toMain(){
		return "manager/main";
	}



}
