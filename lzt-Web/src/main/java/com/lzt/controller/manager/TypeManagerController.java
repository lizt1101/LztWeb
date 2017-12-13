package com.lzt.controller.manager;

import com.alibaba.fastjson.JSON;
import com.lzt.controller.ArticleController;
import com.lzt.entity.Type;
import com.lzt.exception.LztException;
import com.lzt.service.ArticleService;
import com.lzt.service.TypeService;
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
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/8
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller()
@RequestMapping("/manager/type")
public class TypeManagerController {

    private final Logger log = LoggerFactory.getLogger(TypeManagerController.class);

    @Autowired
    private TypeService typeService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getTypeList.do")
    public String getListType(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows, HttpServletRequest req, HttpServletResponse res){
        log.info("获取分类列表,入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        Map<String,Object> typeMap= typeService.getPageTypeList(Integer.parseInt(page),Integer.parseInt(rows));
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",typeMap.get("typeList"));
        Page page1 = (Page)typeMap.get("page");
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    @RequestMapping("/saveOrUpdate.do")
    public String saveOrUpdate(Type type,HttpServletRequest req, HttpServletResponse res){
        log.info("保存OR更新分类,入参"+type.getTypeName());
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            typeService.saveOrUpdate(type);
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    @RequestMapping("/deleteType.do")
    public String deleteType(String id,HttpServletRequest req, HttpServletResponse res){
        log.info("删除分类,入参"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            articleService.getCountByTypeId(Integer.parseInt(id));
            typeService.deleteType(Integer.parseInt(id));
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

}
