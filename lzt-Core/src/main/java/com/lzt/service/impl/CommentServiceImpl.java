package com.lzt.service.impl;

import com.lzt.dao.ArtCountDao;
import com.lzt.dao.CommentDao;
import com.lzt.entity.ArtCount;
import com.lzt.entity.Comment;
import com.lzt.exception.LztException;
import com.lzt.service.CommentService;
import com.lzt.util.NameUtil;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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
@Service("commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArtCountDao artCountDao;

    @Override
    public void saveComment(Comment comment) {
        comment.setStatus(0);
        comment.setCreateDate(new Date());
        comment.setUpdateDate(new Date());
        comment.setHeadImg((int)(Math.random()*37+1)+".jpg");
        commentDao.save(comment);
    }

    @Override
    public Map<String, Object> getCommentList(Integer start, Integer pageSize, String status) {
        return commentDao.getCommentList(start,pageSize,status);
    }

    @Override
    public void updateCheckComment(String ids,String aids) throws LztException {
        String[] idds = ids.split(",");
        List<Integer> integerList = new ArrayList<Integer>();
        for(String id:idds){
            integerList.add(Integer.parseInt(id));
        }
        Integer i = commentDao.checkComment(integerList);
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"审核失败");
        }
        /*评论次数加一*/
        String[] aidds = aids.split(",");
        for(String aid:aidds){
            ArtCount artCount = new ArtCount();
            artCount.setArtId(Integer.parseInt(aid));
            ArtCount artCount1 = artCountDao.getArtCount(Integer.parseInt(aid));  //查询是否存在了该记录
            if(artCount1 !=null && artCount1.getPing()!=null){
                artCount.setPing(artCount1.getPing()+1);
                Integer i1 = artCountDao.updateArtCount(artCount);
                if(i1==null || i1<1){
                    throw new LztException(MessageVo.PASSWORD_ERROR,"更新评论次数失败!");
                }
            }else{
                artCount.setPing(1);
                artCount.setLook(0);
                artCount.setZan(0);
                artCountDao.save(artCount);
            }
        }
    }

    @Override
    public List<Comment> getCommentListById(Integer id) throws LztException {
        List<Comment> commentList = commentDao.getCommentListById(id);
        if(commentList==null){
            throw new LztException(MessageVo.PING_ERROR,"暂无评论!");
        }
        return commentDao.getCommentListById(id);
    }

    @Override
    public Comment getCommentById(Integer id) {
        return commentDao.get(id);
    }

    @Override
    public void updateReplyComment(String id, String reply) throws LztException {
        Integer i = commentDao.updateReplyComment(Integer.parseInt(id),reply);
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"回复失败!");
        }
    }
}
