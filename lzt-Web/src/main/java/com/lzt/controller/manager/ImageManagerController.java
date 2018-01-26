package com.lzt.controller.manager;

import com.alibaba.fastjson.JSON;
import com.lzt.entity.BjImage;
import com.lzt.entity.LunBo;
import com.lzt.entity.Tu;
import com.lzt.exception.LztException;
import com.lzt.service.BjImageService;
import com.lzt.service.LunBoService;
import com.lzt.service.TuService;
import com.lzt.service.UploadService;
import com.lzt.system.RestServer;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.vo.MessageVo;
import org.hibernate.annotations.Entity;
import org.noggit.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/16
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/image")
public class ImageManagerController {

    private static final Logger log = LoggerFactory.getLogger(ImageManagerController.class);

    @Autowired
    private BjImageService bjImageService;
    @Autowired
    private LunBoService lunBoService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private TuService tuServicet;


    /**
     * 获取背景图片列表
     */
    @RequestMapping("/getImageList.do")
    public String getImageList(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows,
                               @RequestParam(value="status",required=false)String status,HttpServletRequest req, HttpServletResponse res){
        log.info("获取背景列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        if(status !=null && !status.equals("") && status!="" && status=="2"){
            status = null;
        }
        Map<String,Object> bjMap= bjImageService.getBjList(Integer.parseInt(page),Integer.parseInt(rows),status);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",bjMap.get("BjList"));
        Page page1 = (Page)bjMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 上传背景
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/uploadBj.do"},method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadbj(MultipartFile uploadFile, BjImage bjImage, HttpServletRequest req, HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("上传背景");
        Integer maxid = bjImageService.getMaxId();
        Integer newId = maxid+1;
        try {
            boolean flag = uploadService.uploadBjImage(uploadFile,maxid);
            if(flag){
                bjImage.setId(maxid+1);
                bjImage.setBjUrl("bj"+newId+uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf(".")));
                bjImageService.saveBjImage(bjImage);
                messageVo.setCode(MessageVo.SUCCESS);
                messageVo.setMessage("添加成功!");
            }else{
                messageVo.setCode(MessageVo.FTP_ERROR);
                messageVo.setMessage("ftp上传失败!");
            }
        } catch (Exception e) { //跳转到错误页面
            messageVo.setCode(MessageVo.PASSWORD_ERROR);
            messageVo.setMessage("上传失败!");
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 获取背景详情
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getImage.do")
    public String getImageList(String id, HttpServletRequest req, HttpServletResponse res){
        log.info("获取背景详情,入参id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        BjImage bjImage = bjImageService.getBjImage(Integer.parseInt(id));
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setData(bjImage);
        messageVo.setMessage("获取背景列表成功!");
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 更新背景详情，不能更改背景
     * @param bjImage
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateImage.do")
    public String updateImage(BjImage bjImage, HttpServletRequest req, HttpServletResponse res){
        log.info("获取背景详情,入参id:"+bjImage.toString());
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            bjImageService.udateBjImage(bjImage);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("更新背景详情成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 禁用启用背景
     * @param id
     * @param status
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateImageStatus.do")
    public String updateStatus(String id,String status,HttpServletRequest req, HttpServletResponse res){
        log.info("禁用启用背景,入参id:"+id+",状态:"+status);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            bjImageService.updateBjStatus(id,status);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("禁用启用背景成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 上传轮播图
     * @param file
     * @param lunBo
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/uploadLunBo.do"},method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadLunbo(MultipartFile file, LunBo lunBo, HttpServletRequest req, HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("上传轮播图");
        try {
            String oldname = file.getOriginalFilename();
            String newFileName = "lzt"+System.currentTimeMillis();
            lunBo.setLbTu(newFileName+oldname.substring(oldname.lastIndexOf(".")));
            Boolean flag = uploadService.uploadLBImage(file, lunBo.getLbTu(),"/lunbo");
            if(flag){
                lunBoService.saveLunbotu(lunBo);
                messageVo.setCode(MessageVo.SUCCESS);
                messageVo.setMessage("添加成功!");
            }else{
                    messageVo.setCode(MessageVo.FTP_ERROR);
                    messageVo.setMessage("ftp上传失败!");
            }
        } catch (IOException e) {
            messageVo.setCode(MessageVo.PASSWORD_ERROR);
            messageVo.setMessage("上传失败!");
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    @RequestMapping("/getLbImageList.do")
    public String getLbImageList(@RequestParam(value="page", required=false)String page,
                               @RequestParam(value="rows",required=false)String rows,
                               @RequestParam(value="status",required=false)String status,HttpServletRequest req, HttpServletResponse res){
        log.info("获取轮播列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        if(status !=null && !status.equals("") && status!="" && status=="2"){
            status = null;
        }
        Map<String,Object> LbMap= lunBoService.getLbList(Integer.parseInt(page),Integer.parseInt(rows),status);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",LbMap.get("LbList"));
        Page page1 = (Page)LbMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     *
     * 更新轮播图状态
     * @param id
     * @param status
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateLbImageStatus.do")
    public String updateLbImageStatus(String id,String status,HttpServletRequest req, HttpServletResponse res){
        log.info("禁用启用轮播图,入参id:"+id+",状态:"+status);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            lunBoService.updateLbStatus(id,status);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("更新背景成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 该文章是否已经添加了轮播图
     * @param id
     * @param status
     * @param req
     * @param res
     */
    @RequestMapping("isAddImage.do")
    public void isAddImage(String id,String status,HttpServletRequest req, HttpServletResponse res){
        log.info("该文章是否已经添加了轮播图,入参id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            lunBoService.getLbByAid(Integer.parseInt(id));
            messageVo.setMessage("无轮播图!");
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));

    }

    /**
     * 上传网站的一些优化图片
     * @param file
     * @param
     * @param req
     * @param res
     * @return
     */
    @RequestMapping(value={"/uploadImage.do"},method= RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadImage(MultipartFile file, Tu tu, HttpServletRequest req, HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("上传网站的一些优化图片");
        try {
            String oldname = file.getOriginalFilename();
            String newFileName = "lzt"+System.currentTimeMillis();
            tu.setTuUrl(newFileName+oldname.substring(oldname.lastIndexOf(".")));
            Boolean flag = uploadService.uploadLBImage(file, tu.getTuUrl(),"/style");
            if(flag){
                tuServicet.saveTu(tu);
                messageVo.setCode(MessageVo.SUCCESS);
                messageVo.setMessage("添加成功!");
            }else{
                messageVo.setCode(MessageVo.FTP_ERROR);
                messageVo.setMessage("ftp上传失败!");
            }
        } catch (IOException e) {
            messageVo.setCode(MessageVo.PASSWORD_ERROR);
            messageVo.setMessage("上传失败!");
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 获取其他图片列表
     * @param page
     * @param rows
     * @param status
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getQtImageList.do")
    public String getQtImageList(@RequestParam(value="page", required=false)String page,
                                 @RequestParam(value="rows",required=false)String rows,
                                 @RequestParam(value="status",required=false)String status,
                                 @RequestParam(value="type",required=false)String type,HttpServletRequest req, HttpServletResponse res){
        log.info("获取轮播列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        if(status !=null && !status.equals("") && status!="" && status=="2"){
            status = null;
        }
        if(type !=null && !type.equals("") && type!="" && type=="0"){
            type = null;
        }
        Map<String,Object> Map= tuServicet.getQtList(Integer.parseInt(page),Integer.parseInt(rows),status,type);
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",Map.get("QtList"));
        Page page1 = (Page)Map.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 禁用启用优化图
     * @param id
     * @param status
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateQtImageStatus.do")
    public String updateQtImageStatus(String id,String status,String type,HttpServletRequest req, HttpServletResponse res){
        log.info("禁用启用优化图,入参id:"+id+",状态:"+status);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
           tuServicet.updateQtStatus(id,status,type);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("更新图成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }


}
