package com.lzt.service;

import com.lzt.entity.Comment;
import com.lzt.exception.LztException;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/11
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface CommentService {

    public void saveComment(Comment comment);

    public Map<String,Object> getCommentList(Integer start, Integer pageSize, String status);

    public void updateCheckComment(String ids,String aids) throws LztException;

    public void updateReplyComment(String id,String reply) throws LztException;

    public List<Comment> getCommentListById(Integer id) throws LztException;

    public Comment getCommentById(Integer id);
}
