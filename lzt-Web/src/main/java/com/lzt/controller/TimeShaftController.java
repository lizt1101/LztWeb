package com.lzt.controller;

import com.lzt.entity.WebContent;
import com.lzt.entity.WebDevelop;
import com.lzt.service.WebDevelopService;
import com.lzt.service.WebService;
import com.lzt.util.DateUtil;
import com.lzt.vo.WebDevelopListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/30
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/time")
public class TimeShaftController {

    private final Logger log = LoggerFactory.getLogger(TimeShaftController.class);

    @Autowired
    private WebDevelopService webDevelopService;
    @Autowired
    private WebService webService;

    @RequestMapping("/toTime.do")
    public String ToTime(HttpServletRequest req, HttpServletResponse res){
        log.info("获取关于网站时间轴数据");
        List<WebDevelop> list = webDevelopService.getDevList();
        Map<String,List<WebDevelop>> resultMap = new HashMap<String,List<WebDevelop>>();
        for(WebDevelop dev:list){
            if(resultMap.containsKey(DateUtil.getYear(dev.getWebCreateDate()))){
                List<WebDevelop> list1 = resultMap.get(DateUtil.getYear(dev.getWebCreateDate()));
                list1.add(dev);
            }else{
               List<WebDevelop> list2 = new ArrayList<WebDevelop>();
               list2.add(dev);
               resultMap.put(DateUtil.getYear(dev.getWebCreateDate()),list2);
            }
        }
        List<WebDevelopListVo> listVoList = new ArrayList<WebDevelopListVo>();
        for (Map.Entry<String,List<WebDevelop>> entry : resultMap.entrySet()) {
            WebDevelopListVo webDevelopListVo = new WebDevelopListVo();
            webDevelopListVo.setYear(entry.getKey());
            webDevelopListVo.setWebDevelopList(entry.getValue());
            listVoList.add(webDevelopListVo);
        }
        req.setAttribute("webDevList",listVoList);
        WebContent webContent = webService.getWebConttent(1);
        req.setAttribute("webContent",webContent);
        return "mainPage/timerShaft";
    }


}
