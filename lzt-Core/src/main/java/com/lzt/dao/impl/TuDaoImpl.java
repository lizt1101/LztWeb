package com.lzt.dao.impl;

import com.lzt.dao.TuDao;
import com.lzt.entity.BjImage;
import com.lzt.entity.Tu;
import com.lzt.entity.User;
import com.lzt.util.Page;
import com.sun.org.apache.regexp.internal.RE;
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
 * @Create 2018/1/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("tuDao")
public class TuDaoImpl extends AllDaoImpl<Tu> implements TuDao {

    public TuDaoImpl() {
        super(Tu.class);
    }

    @Override
    public void updateStatus(String type) {
        String sql = "update lzt_tu tu set tu.tu_status='1',tu.tu_update=now() where tu.tu_type=:type and tu.tu_status='0'";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("type",type);
        query.executeUpdate();
    }

    @Override
    public Map<String, Object> getQtList(Integer page, Integer rows, String status,String type) {
        int pageSize = rows;
        int pageNo = (page-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Tu.class);
        criteria.add(Restrictions.ne("tuStatus","2"));
        if(status!=null && status.equals("1")){
            criteria.add(Restrictions.eq("tuStatus","1"));
        }
        if(status!=null && status.equals("0")){
            criteria.add(Restrictions.eq("tuStatus","0"));
        }
        if(type!=null && !type.equals("") && type!="" ){
            criteria.add(Restrictions.eq("tuType",type));
        }
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.desc("tuUpdate"));
        Page page1 = new Page(page.longValue(),rows.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("QtList",criteria.list());
        return map;
    }

    @Override
    public Integer updateQtStatus(String id, String status) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
        String sql = "update `lzt_tu` lt set lt.tu_status=:status,lt.tu_update=:updateDate,lt.tu_updateBy=:userId where lt.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id).setParameter("status",status).setParameter("updateDate",new Date())
        .setParameter("userId",user.getId());
        return query.executeUpdate();
    }

    @Override
    public List<Tu> getQtList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Tu.class);
        criteria.add(Restrictions.eq("tuStatus","0"));
        if(criteria.list().size()<1){
            return null;
        }
        return criteria.list();
    }
}
