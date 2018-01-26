package com.lzt.controller;

import com.lzt.entity.Comment;
import com.lzt.entity.LeaveMessage;
import com.lzt.exception.LztException;
import com.lzt.service.CommentService;
import com.lzt.service.LeaveMessageService;
import com.lzt.system.RestServer;
import com.lzt.util.*;
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
@RequestMapping("/Comment")
public class CommentController {

    public static final Logger log = LoggerFactory.getLogger(CommentController.class);

    @Autowired
    private CommentService commentService;

    /**
     * 用户评论
     * @param comment
     * @param code
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/comment.do")
    public String comment(Comment comment, String code, HttpServletRequest req, HttpServletResponse res){
        log.info("评论");
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        String ip = IpUtil.getIpAddr(req);
        comment.setCreateBy(ip);
        String sessionCode = (String)req.getSession().getAttribute("CommentImage");
        if(!code.equalsIgnoreCase(sessionCode)){
            messageVo.setCode(MessageVo.YCODE_ERROR);
            messageVo.setMessage("验证码错误！！！");
            restServer.send(JsonUtil.jsonToString(messageVo));
            return null;
        }
        String name;
        if(comment.getName()==null || comment.getName().equals("") || comment.getName()==""){
            name = NameUtil.getName();
            comment.setName(name);
        }
        commentService.saveComment(comment);
        messageVo.setCode(MessageVo.SUCCESS);
        messageVo.setMessage("评论成功,代号【"+comment.getName()+"】,请等待管理员审核通过后显示!");
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 根据文章id获取评论
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/commentList.do")
    public void commentList(String id,HttpServletRequest req, HttpServletResponse res){
        log.info("根据文章id获取评论！id:"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        try {
            List<Comment> list= commentService.getCommentListById(Integer.parseInt(id));
            for(Comment c:list){
                c.setComment(CommentConentUtil.getNewComment(c.getComment()));
            }
            messageVo.setCode(MessageVo.SUCCESS);
            messageVo.setMessage("获取评论成功!");
            messageVo.setData(list);
        } catch (LztException e) {
            messageVo.setMessage(e.getMessage());
            messageVo.setCode(e.getCode());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
    }



}
