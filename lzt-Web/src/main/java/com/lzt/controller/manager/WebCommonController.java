package com.lzt.controller.manager;

import com.lzt.entity.*;
import com.lzt.exception.LztException;
import com.lzt.service.*;
import com.lzt.system.RestServer;
import com.lzt.util.JsonUtil;
import com.lzt.util.mathSzUtil;
import com.lzt.vo.MessageVo;
import com.lzt.vo.WebVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/9
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/common")
public class WebCommonController {

    @Autowired
    private WebService webService;
    @Autowired
    private UserService userService;
    @Autowired
    private BjImageService bjImageService;
    @Autowired
    private LunBoService lunBoService;
    @Autowired
    private TuService tuService;

    public static final Logger log = LoggerFactory.getLogger(WebCommonController.class);

    /**
     * 获得介绍内容,滚动内容
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getContent.do")
    public String getContent(String id, HttpServletRequest req, HttpServletResponse res){
        log.info("获得介绍内容,滚动内容,入参id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        WebContent webContent = webService.getWebConttent(Integer.parseInt(id));
        messageVo.setData(webContent.getWebContent());
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setMessage("获取成功！");
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 修改本站介绍滚动内容
     * @param webContent
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateContent.do")
    public String updateContent(WebContent webContent, HttpServletRequest req, HttpServletResponse res){
        log.info("修改本站介绍滚动内容,入参:"+webContent.getWebContent()+","+webContent.getId());
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            webService.updateContent(webContent);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("修改成功！");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }

        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     *
     *刷新图片缓存
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateHC.do")
    public String updateHC(HttpServletRequest req, HttpServletResponse res){
        log.info("刷新图片缓存");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        List<BjImage> list = bjImageService.getQBjList();
        ServletContext application= RequestContextUtils.getWebApplicationContext(req).getServletContext();
        application.setAttribute("bjList",list);
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 刷新轮播缓存
     */
    @RequestMapping("/updateLb.do")
    public String updateLb(HttpServletRequest req, HttpServletResponse res){
        log.info("刷新图片缓存");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        List<LunBo> list = lunBoService.getLbList();
        ServletContext application= RequestContextUtils.getWebApplicationContext(req).getServletContext();
        application.setAttribute("LbList",list);
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 刷新优化图片缓存
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/updateQt.do")
    public String updateQt(HttpServletRequest req, HttpServletResponse res){
        log.info("刷新优化图片缓存");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        ServletContext application= RequestContextUtils.getWebApplicationContext(req).getServletContext();
        List<Tu> tuList = tuService.getQtList();
        application.setAttribute("tuList",tuList);
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

}
