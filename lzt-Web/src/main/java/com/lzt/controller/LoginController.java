package com.lzt.controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lzt.entity.User;
import com.lzt.service.UserService;
import com.lzt.system.RestServer;
import com.lzt.util.ImageUtil;
import com.lzt.util.JsonUtil;
import com.lzt.vo.MessageVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lzt.util.MD5Util;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping(value="/Login")
public class LoginController {
	
	public static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

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
		log.info("用户登录");
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
		}catch(AuthenticationException e){
			req.setAttribute("error","用户名或者密码错误!");
			return "login";
		}
		return "redirect:/manager/tomain.do";
	}

	@RequestMapping(value={"/logout.do"})
	public String  logout()throws Exception{
		log.info("用户退出");
		SecurityUtils.getSubject().logout();
		return "redirect:/Login/toLogin.do";
	}

	@RequestMapping(value={"/updatePassword.do"})
	@ResponseBody
	public void  updatePassword(HttpServletRequest req,HttpServletResponse res)throws Exception{
		log.info("用户修改密码");
		RestServer restServer = new RestServer(req,res);
		MessageVo messageVo = new MessageVo();
		String oldPassword = req.getParameter("oldPassword");
		User user = (User)SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		if(!MD5Util.MD5(oldPassword).equals(user.getPassword())){
			messageVo.setCode(MessageVo.PASSWORD_ERROR);
			messageVo.setMessage("原密码错误!");
			restServer.send(JsonUtil.jsonToString(messageVo));
		}else{
			userService.updatePassword(req.getParameter("newPassword"));
			SecurityUtils.getSubject().getSession().setAttribute("CurrentUser", user);
			restServer.send(JsonUtil.jsonToString(messageVo));
		}
	}
}











