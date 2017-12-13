package com.lzt.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzt.util.ImageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzt.util.MD5Util;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping(value="/Login")
public class LoginController {
	
	public static final Logger log = LoggerFactory.getLogger(LoginController.class);


	@RequestMapping("/toLogin.do")
	public String test02(){
		return "login";

	}

	@RequestMapping(value={"/getCode.do"})
	public void getImage(HttpServletRequest req,HttpServletResponse res) throws IOException {
		//生成验证码及图片
		Object[] objs = ImageUtil.createImage();
		//验证码存入session
		HttpSession session = req.getSession();
		session.setAttribute("image", objs[0]);
		//将图片输出给浏览器
		res.setContentType("image/png");
		//获取输出流,该流由服务器自动创建,
		//它所输出的目标就是当前访问的浏览器.
		OutputStream os = res.getOutputStream();
		BufferedImage img =
				(BufferedImage) objs[1];
		ImageIO.write(img, "png", os);
		os.close();
	}

	@RequestMapping(value={"/login.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public String Login(HttpServletRequest req,HttpServletResponse res){
		String code = req.getParameter("code");
		String sessionCode = (String)req.getSession().getAttribute("image");
		if(!code.equalsIgnoreCase(sessionCode)){
			req.setAttribute("error","验证码错误!");
			return "login";
		}
		String username = req.getParameter("userName");
		String password = MD5Util.MD5( req.getParameter("password"));
		log.info("用户登录,用户名:"+username+",密码:"+password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,password);
		Subject subject = SecurityUtils.getSubject();
		try{
			subject.login(token);
		}catch(Exception e){
			req.setAttribute("error","用户名或者密码错误!");
			return "login";
		}
		return "redirect:/manager/tomain.do";
	}

	@RequestMapping("/logout")
	public String  logout()throws Exception{
		SecurityUtils.getSubject().logout();
		return "redirect:/Login/toLogin.do";
	}

}











