package com.lzt.controller.manager;

import com.lzt.entity.BjImage;
import com.lzt.entity.Comment;
import com.lzt.exception.LztException;
import com.lzt.service.BjImageService;
import com.lzt.service.CommentService;
import com.lzt.system.RestServer;
import com.lzt.util.CommentConentUtil;
import com.lzt.util.JsonUtil;
import com.lzt.util.Page;
import com.lzt.vo.MessageVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/12
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Controller
@RequestMapping("/manager/comment")
public class CommentManagerController {

    public static final Logger log = LoggerFactory.getLogger(CommentManagerController.class);

    @Autowired
    private CommentService commentService;


    /**
     * 获取评论列表
     * @param page
     * @param rows
     * @param status
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/commentList.do")
    public String commentList(@RequestParam(value="page", required=false)String page,
                              @RequestParam(value="rows",required=false)String rows,
                              @RequestParam(value="status",required=false)String status, HttpServletRequest req, HttpServletResponse res){
        log.info("获取评论管理列表！入参page:"+page+",rows:"+rows);
        RestServer restServer = new RestServer(req,res);
        if(status!=null && status.equals("2")){
            status = null;
        }
        Map<String,Object> commentMap = commentService.getCommentList(Integer.parseInt(page),Integer.parseInt(rows),status);
        Page page1 = (Page)commentMap.get("page");
        List<Comment> commentList = (List<Comment>)commentMap.get("commentList");
        for(Comment c:commentList){
            c.setComment(CommentConentUtil.getNewComment(c.getComment()));
        }
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("rows",commentList);
        resultMap.put("total",page1.getAllCount());
        restServer.send(JsonUtil.jsonToString(resultMap));
        return null;
    }

    /**
     * 审核评论
     * @param ids
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/checkComment.do")
    public String checkComment(String ids,String aids,HttpServletRequest req, HttpServletResponse res){
        log.info("审核评论！入参ids:"+ids+",aids:"+aids);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("审核评论,入参:"+ ids);
        try {
            commentService.updateCheckComment(ids,aids);
            messageVo.setMessage("审核成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 站长回复
     * @param id
     * @param reply
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/replyComment.do")
    public String replyComment(String id,String reply,HttpServletRequest req, HttpServletResponse res){
        log.info("站长回复,评论id:"+id+"回复内容"+reply);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("审核评论,入参:"+ id+","+reply);
        try {
            commentService.updateReplyComment(id,reply);
            messageVo.setMessage("回复成功!");
        } catch (LztException e) {
            messageVo.setCode(e.getCode());
            messageVo.setMessage(e.getMessage());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }

    /**
     * 获取超长评论内容
     * @param id
     * @param req
     * @param res
     * @return
     */
    @RequestMapping("/getComment.do")
    public String getComment(String id,String type,HttpServletRequest req, HttpServletResponse res){
        log.info("获取超长内容,评论id"+id);
        RestServer restServer = new RestServer(req,res);
        MessageVo messageVo = new MessageVo();
        log.info("审核评论,入参:"+ id);
        Comment comment = commentService.getCommentById(Integer.parseInt(id));
        messageVo.setMessage("获取成功!");
        if(type=="1" || type.equals("1")){
            messageVo.setData(comment.getReply());
        }else{
            messageVo.setData(comment.getComment());
        }
        restServer.send(JsonUtil.jsonToString(messageVo));
        return null;
    }



}
