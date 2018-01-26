package com.lzt.dao.impl;

import com.lzt.dao.CommentDao;
import com.lzt.entity.Comment;
import com.lzt.entity.LeaveMessage;
import com.lzt.entity.User;
import com.lzt.util.Page;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
@Repository("commentDaoImpl")
public class CommentDaoImpl extends AllDaoImpl<Comment> implements CommentDao {


    public CommentDaoImpl() {
        super(Comment.class);
    }

    @Override
    public Map<String, Object> getCommentList(Integer start, Integer pageSize, String status) {
        int pageNo = (start-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.ne("status",2));
        if(status != null){
            criteria.add(Restrictions.eq("status",Integer.parseInt(status)));
        }
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.desc("updateDate"));
        Page page1 = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("commentList",criteria.list());
        return map;
    }

    @Override
    public Integer checkComment(List<Integer> ids) {
        User user =  (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        String sql = "update lzt_comment c set c.status=1,c.update_date=now(),c.update_by=:currentUser where c.id in(:ids)";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter("currentUser",user.getId()).setParameterList("ids",ids);
        return query.executeUpdate();
    }

    @Override
    public List<Comment> getCommentListById(Integer id) {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Comment.class);
        criteria.add(Restrictions.eq("aid",id));
        if(criteria.list().size()<1){
            return null;
        }
        return criteria.list();
    }

    @Override
    public Integer updateReplyComment(Integer id, String reply) {
        User user =  (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        String sql = "update lzt_comment c set c.status=3,c.update_date=now(),c.update_by=:currentUser,c.reply=:reply where c.id =:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
                .setParameter("currentUser",user.getId()).setParameter("reply",reply).setParameter("id",id);
        return query.executeUpdate();
    }
}
