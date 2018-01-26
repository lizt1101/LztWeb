package com.lzt.dao;

import com.lzt.entity.Comment;

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
public interface CommentDao extends AllDao<Comment>{

    public Map<String,Object> getCommentList(Integer start,Integer pageSize,String status);

    public Integer checkComment(List<Integer> ids);

    public List<Comment> getCommentListById(Integer id);

    public Integer updateReplyComment(Integer id,String reply);

}
