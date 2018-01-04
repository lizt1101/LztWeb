package com.lzt.controller;

import com.alibaba.fastjson.JSON;
import com.lzt.Bo.TimeCountBo;
import com.lzt.Bo.typeCountBo;
import com.lzt.dao.TypeDao;
import com.lzt.entity.Type;
import com.lzt.service.ArticleService;
import com.lzt.service.TypeService;
import com.lzt.system.RestServer;
import com.lzt.vo.MessageVo;
import com.lzt.vo.TimeCountVo;
import com.lzt.vo.typeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/11/27
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/type")
public class TypeController {

    private final Logger log = LoggerFactory.getLogger(TypeController.class);

    @Autowired
    private TypeService typeService;

    @Autowired
    private ArticleService articleService;

    /**
     * 获取类型分类列表
     * @param req
     * @param res
     */
    @RequestMapping(value = "/typeList.do",method = RequestMethod.POST,produces="application/json")
    private void getList(HttpServletRequest req,HttpServletResponse res){
        log.info("获取类型分类列表");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        List<Type> typeList = typeService.getTypeList();

        List<typeCountBo> typeCountBoList = articleService.getTypeCount();
        Map<Integer,Integer> mapType = new HashMap<Integer,Integer>();
        for(typeCountBo typeCountBo:typeCountBoList){
            mapType.put(typeCountBo.getTypeId(),typeCountBo.getCounts());
        }
        List<typeVo> typeVoList = new ArrayList<typeVo>();
        for(Type type:typeList){
            typeVo typeVo = new typeVo();
            typeVo.setId(type.getId());
            typeVo.setTypeName(type.getTypeName());
            if(mapType.containsKey(type.getId())){
                typeVo.setCount(mapType.get(type.getId()));
            }else{
                typeVo.setCount(0);
            }
            typeVoList.add(typeVo);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("typeList",typeVoList);
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setData(map);
        messageVo.setMessage("获取成功");
        restServer.send(JSON.toJSONString(messageVo));
    }

    /**
     * 获取时间分类
     * @param req
     * @param res
     */
    @RequestMapping(value = "/timeList.do",method = RequestMethod.POST,produces="application/json")
    private void getTimeList(HttpServletRequest req,HttpServletResponse res){
        log.info("获取时间分类列表");
        SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy年MM月");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        List<TimeCountBo> list = articleService.getTimeCount();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("timeList",list);
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setData(map);
        messageVo.setMessage("获取成功");
        restServer.send(JSON.toJSONString(messageVo));
    }


}
