package com.lzt.dao.impl;

import com.lzt.dao.LunBoDao;
import com.lzt.entity.BjImage;
import com.lzt.entity.LunBo;
import com.lzt.util.Page;
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
 * @Create 2018/1/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("lunBoDao")
public class LunBoDaoImpl extends AllDaoImpl<LunBo> implements LunBoDao{
    public LunBoDaoImpl() {
        super(LunBo.class);
    }


    @Override
    public Map<String, Object> getLbList(Integer page, Integer rows, String status) {
        int pageSize = rows;
        int pageNo = (page-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(LunBo.class);
        criteria.add(Restrictions.ne("lbStatus","2"));
        if(status!=null && status.equals("1")){
            criteria.add(Restrictions.eq("lbStatus","1"));
        }
        if(status!=null && status.equals("0")){
            criteria.add(Restrictions.eq("lbStatus","0"));
        }
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.asc("lbSort"));
        Page page1 = new Page(page.longValue(),rows.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("LbList",criteria.list());
        return map;
    }

    @Override
    public Integer updateLbStatus(Integer id, String status) {
        String sql = "update `lzt_lunbo` lb set lb.lb_status=:status where lb.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id).setParameter("status",status);
        return query.executeUpdate();
    }

    @Override
    public List<LunBo> getLunboList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(LunBo.class);
        criteria.add(Restrictions.eq("lbStatus","0"));
        return criteria.list();
    }

    @Override
    public LunBo getLunBoByAid(Integer aid) {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(LunBo.class);
        criteria.add(Restrictions.ne("lbStatus","2"));
        criteria.add(Restrictions.eq("lbAid",aid));
        if(criteria.list().size()<1){
            return null;
        }
        return (LunBo)criteria.list().get(0);
    }
}
