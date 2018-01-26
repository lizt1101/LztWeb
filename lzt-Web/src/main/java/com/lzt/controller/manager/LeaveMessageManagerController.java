package com.lzt.controller.manager;

import com.lzt.entity.LeaveMessage;
import com.lzt.exception.LztException;
import com.lzt.service.LeaveMessageService;
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
 * @Title  留言管理控制器
 * @Description
 * @Author:lizitao
 * @Create 2018/1/8
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/leaveMessage")
public class LeaveMessageManagerController {

    public static final Logger log = LoggerFactory.getLogger(LeaveMessageManagerController.class);

    @Autowired
    private LeaveMessageService leaveMessageService;

    /**
     * 全部留言管理列表
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getLiuyanManagerList.do")
    public String getLiuyanManagerList(@RequestParam(value="page", required=false)String page,
                                       @RequestParam(value="rows",required=false)String rows,
                                       @RequestParam(value="type",required=false)String type,
                                       HttpServletRequest req, HttpServletResponse res){
        log.info("获取留言管理列表！入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        if(type!=null && type.equals("2")){
            type = null;
        }
        Map<String,Object> leaveMap = leaveMessageService.getLeaveMessageList(Integer.parseInt(page),Integer.parseInt(rows),type);
        Page page1 = (Page)leaveMap.get("page");
        List<LeaveMessage> leaveMessageList = (List<LeaveMessage>)leaveMap.get("leaveMessageList");

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",leaveMessageList);
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }



    /**
     * 删除留言
     * @param ids
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/deleteLeaveMessage.do")
    public String deleteLeaveMessage(String ids,HttpServletRequest req, HttpServletResponse res){
        log.info("批量删除留言！入参ids:"+ids);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            leaveMessageService.deleteMessage(ids);
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("删除成功！");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }


    /**
     * 更新展示状态
     * @param id
     * @param isShow
     * @param req
     * @param res
     */
    @RequestMapping("/updateLeave.do")
    public void updateLeave(String id,String isShow,HttpServletRequest req,HttpServletResponse res){
        log.info("更新状态展示！入参id:"+id+"isShow:"+isShow);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            leaveMessageService.updateMessage(id,isShow);
            messageVo.setMessage("更新状态展示成功！");
            messageVo.setCode(MessageVo.SUCCESS);
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
    }


}
