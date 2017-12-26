package com.lzt.controller.manager;

import com.alibaba.fastjson.JSON;
import com.lzt.controller.LoginController;
import com.lzt.entity.User;
import com.lzt.exception.LztException;
import com.lzt.service.UploadService;
import com.lzt.service.UserService;
import com.lzt.system.RestServer;
import com.lzt.util.Base64Util;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.vo.HeadImgVo;
import com.lzt.vo.MessageVo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/20
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/user")
public class UserManagerController {

    public static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private UploadService uploadService;

    /**
     * 修改头像
     * @param image
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/uploadImg.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String uploadHeadimage(String image, HttpServletRequest req, HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        HeadImgVo headImgVo = new HeadImgVo();
        try {
            String imgFilePath = Base64Util.GenerateImage(image); //生成头像图片
            if(imgFilePath == null){
                headImgVo.setResult("No");
            }else{
                boolean flag = uploadService.uploadHeadImage(imgFilePath);
                if(flag){
                    //更新用户信息
                    userService.updateHeadImg(imgFilePath.substring(4));
                    //删除本地生成的文件
                    File file = new File(imgFilePath);
                    delete(file);
                    headImgVo.setResult("ok");
                    headImgVo.setFile(imgFilePath.substring(4));
                    headImgVo.setMesg("上传头像成功!");
                }else{
                    headImgVo.setResult("No");
                    headImgVo.setMesg("上传头像失败!");
                }

            }
        }catch (LztException le){
            headImgVo.setResult("No");
            headImgVo.setMesg("上传头像失败!");
        }
        catch (Exception e){
            headImgVo.setResult("No");
            headImgVo.setMesg("上传头像失败!");
        }
        return JsonUtil.jsonToString(headImgVo);
    }


    public static void delete(File file) {
        if(file.isDirectory()){
            File[] arr = file.listFiles();
            for(File f:arr){
                delete(f);
            }
        }
        file.delete();
    }

    /**
     * 修改自己的信息
     * @param nickname
     * @param description
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/updateInfo.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String updateInfo(String nickname, String description,HttpServletRequest req, HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            User user = new User();
            user.setDescription(description);
            user.setNickname(nickname);
            userService.updateInfo(user);
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        return JsonUtil.jsonToString(messageVo);
    }

    /**
     * 获取用户列表
     * @param page
     * @param rows
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/getUserList.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getUserList(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows,HttpServletRequest req, HttpServletResponse res){
        log.info("获取用户列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        Map<String,Object> userMap = userService.getPageList(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",userMap.get("userList"));
        Page page1 = (Page)userMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 添加管理员
     * @param username
     * @param password
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/addUser.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String addUser(String username,String password,HttpServletRequest req, HttpServletResponse res){
        log.info("添加管理员,入参userName:"+username+",password:"+password);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            userService.insertUser(username,password);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("添加管理员成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        return JsonUtil.jsonToString(messageVo);
    }

    /**
     * 禁止(开启)管理员登陆
     * @return
     */
    @RequestMapping(value={"/StopOrStartLogin.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String StopOrStartLogin(String id,String type,HttpServletRequest req,HttpServletResponse res){
        log.info("禁止管理员登陆,入参管理员id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            userService.updateStatus(Integer.parseInt(id),type);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("成功!");
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        return JsonUtil.jsonToString(messageVo);
    }

    /**
     * 删除管理员
     * @param id
     * @return
     */
    @RequestMapping(value={"/deleteUser.do"},method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteUser(String id,HttpServletRequest req,HttpServletResponse res){
        log.info("删除管理员,入参管理员id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try{
            userService.deleteUser(Integer.parseInt(id));
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("删除成功!");
        }catch(Exception e){
            messageVo.setCode(MessageVo.PASSWORD_ERROR);
            messageVo.setMessage("删除失败!");
        }
        return JsonUtil.jsonToString(messageVo);
    }




}
