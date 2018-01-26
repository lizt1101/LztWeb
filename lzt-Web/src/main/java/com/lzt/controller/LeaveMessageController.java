package com.lzt.controller;

import com.lzt.entity.LeaveMessage;
import com.lzt.exception.LztException;
import com.lzt.service.LeaveMessageService;
import com.lzt.system.RestServer;
import com.lzt.util.IpUtil;
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
 * @Create 2018/1/6
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/LeaveMessage")
public class LeaveMessageController {

    public static final Logger log = LoggerFactory.getLogger(LeaveMessageController.class);

    @Autowired
    private LeaveMessageService leaveMessageService;

    @RequestMapping("/liuyan.do")
    public String liuyan(LeaveMessage leaveMessage, String code,HttpServletRequest req, HttpServletResponse res){
        log.info("留言");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
       /* String ip = IpUtil.getIpAddr(req);
        leaveMessage.setIP(ip);*/
        try {
            String sessionCode = (String)req.getSession().getAttribute("LiuyanImage");
            if(!code.equalsIgnoreCase(sessionCode)){
                messageVo.setCode(MessageVo.YCODE_ERROR);
                messageVo.setMessage("验证码错误！！！");
                restServer.send(JsonUtil.jsonToString(messageVo));
                return null;
            }
            leaveMessageService.save(leaveMessage);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("留言成功!");
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 留言展示列表
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getLiuyanShowList.do")
    public String getLiuyanManagerShowList(String start,String pageSize,HttpServletRequest req, HttpServletResponse res){
        log.info("留言展示列表");
        RestServer restServer = new RestServer(req,res);
        Map<String,Object> leaveMap = leaveMessageService.getLeaveMessageShowList(Integer.parseInt(start),Integer.parseInt(pageSize));
        List<LeaveMessage> leaveMessageList = (List<LeaveMessage>)leaveMap.get("leaveMessageList");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("list",leaveMessageList);
        restServer.send(JsonUtil.jsonToString(map));
        return null;
    }



}
