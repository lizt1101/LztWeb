package com.lzt.controller.manager;

import com.lzt.entity.WebDevelop;
import com.lzt.exception.LztException;
import com.lzt.service.WebDevelopService;
import com.lzt.service.WebService;
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
 * @Create 2018/1/31
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/devlop")
public class DevlopManagerController {

    private final Logger log = LoggerFactory.getLogger(DevlopManagerController.class);

    @Autowired
    private WebDevelopService webDevelopService;

    /**
     * 添加网站发展记录
     * @param webDevelop
     * @param req
     * @param res
     */
    @RequestMapping("/addDevlop.do")
    public void addDev(WebDevelop webDevelop, HttpServletRequest req, HttpServletResponse res){
        log.info("添加网站发展记录");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            webDevelopService.saveDev(webDevelop);
            messageVo.setMessage("添加更新网站发展记录成功");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
    }

    /**
     * 网站发展列表
     * @param page
     * @param rows
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getDevList.do")
    public String getListType(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows, HttpServletRequest req, HttpServletResponse res){
        log.info("获取网站发展列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        Map<String,Object> devMap= webDevelopService.getPageDevlop(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",devMap.get("DevList"));
        Page page1 = (Page)devMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    @RequestMapping("/deleteDev.do")
    public void deleteDev(String id,HttpServletRequest req,HttpServletResponse res){
        log.info("删除发展记录,入参id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            webDevelopService.updateDev(id);
            messageVo.setMessage("删除成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
    }

}
