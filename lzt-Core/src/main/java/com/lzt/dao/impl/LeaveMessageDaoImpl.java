package com.lzt.dao.impl;

import com.lzt.dao.LeaveMessageDao;
import com.lzt.entity.LeaveMessage;
import com.lzt.entity.Type;
import com.lzt.entity.User;
import com.lzt.util.DateUtil;
import com.lzt.util.Page;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.Date;
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
@Repository("leaveMessageDao")
public class LeaveMessageDaoImpl extends AllDaoImpl<LeaveMessage> implements LeaveMessageDao {

    public LeaveMessageDaoImpl() {
        super(LeaveMessage.class);
    }

    @Override
    public Integer getNowCount(LeaveMessage leaveMessage) {
        String sql = "SELECT a.id FROM lzt_leave_message a \n" +
                "WHERE DATE_FORMAT(a.`create_date`,'%Y-%m-%d') = DATE_FORMAT(NOW(),'%Y-%m-%d') AND a.`ip`=:ip";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("ip",leaveMessage.getkIP());
        if(query.list().size()<1){
         return 0;
        }
        return query.list().size();
    }

    @Override
    public Map<String, Object> getLeaveMessageList(Integer start, Integer pageSize,String type) {
        int pageNo = (start-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(LeaveMessage.class);
        criteria.add(Restrictions.eq("isDelete","0"));
        if(type != null){
            criteria.add(Restrictions.eq("isShow",type));
        }
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.desc("updateDate"));
        Page page1 = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("leaveMessageList",criteria.list());
        return map;
    }

    @Override
    public Integer deleteLeaveMessage(List<Integer> integerList) {
        String hql = "update LeaveMessage l set l.isDelete=1,l.updateDate=:updateDate where l.id in (:idList)";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
                .setParameterList("idList",integerList).setParameter("updateDate",new Date());
        return query.executeUpdate();
    }

    @Override
    public Integer updateLeaveMessage(Integer id, String isShow) {
        String hql = "update LeaveMessage l set";
        if(isShow.equals("0")){
            hql += " l.isShow=1";
        }
        if(isShow.equals("1")){
            hql += " l.isShow=0";
        }
        hql += " ,l.updateDate=:updateDate where l.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
                .setParameter("id",id).setParameter("updateDate",new Date());
        return query.executeUpdate();
    }
}
