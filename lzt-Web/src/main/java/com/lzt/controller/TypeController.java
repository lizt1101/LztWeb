package com.lzt.controller;

import com.alibaba.fastjson.JSON;
import com.lzt.dao.TypeDao;
import com.lzt.entity.Type;
import com.lzt.service.TypeService;
import com.lzt.system.RestServer;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private TypeService typeService;

    @RequestMapping(value = "/typeList.do",method = RequestMethod.POST,produces="application/json")
    private void getList(HttpServletRequest req,HttpServletResponse res){
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        List<Type> typeList = typeService.getTypeList();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("typeList",typeList);
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setData(map);
        messageVo.setMessage("获取成功");
        restServer.send(JSON.toJSONString(messageVo));
    }

}
