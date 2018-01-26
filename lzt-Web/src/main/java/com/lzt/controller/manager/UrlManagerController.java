package com.lzt.controller.manager;

import com.lzt.entity.Url;
import com.lzt.exception.LztException;
import com.lzt.service.UrlService;
import com.lzt.system.RestServer;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/25
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller()
@RequestMapping("/manager/url")
public class UrlManagerController {

    @Autowired
    private UrlService urlService;

    private final Logger log = LoggerFactory.getLogger(UrlManagerController.class);

    @RequestMapping("/getUrlList.do")
    public String getListType(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows, HttpServletRequest req, HttpServletResponse res){
        log.info("获取分类列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        Map<String,Object> typeMap= urlService.getPageUrlList(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",typeMap.get("urlList"));
        Page page1 = (Page)typeMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 删除链接
     * @param id
     * @param req
     * @param res
     */
    @RequestMapping("/deleteUrl.do")
    public void deleteUrl(String id, HttpServletRequest req, HttpServletResponse res){
        log.info("删除链接,入参id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            urlService.deleteUrl(Integer.parseInt(id));
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("删除失败!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
    }

    /**
     * 添加更新链接
     * @param url
     * @param req
     * @param res
     */
    @RequestMapping("/addUrl.do")
    public void addUrl(Url url, HttpServletRequest req, HttpServletResponse res){
        log.info("添加链接,入参url:"+url.getUrl()+","+url.getUrlName());
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        urlService.saveUrl(url);
        restServer.send(JsonUtil.jsonToString(messageVo));
    }



}
