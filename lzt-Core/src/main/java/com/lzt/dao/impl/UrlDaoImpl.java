package com.lzt.dao.impl;

import com.lzt.dao.UrlDao;
import com.lzt.entity.Type;
import com.lzt.entity.Url;
import com.lzt.util.Page;
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
 * @Create 2018/1/25
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("urlDao")
public class UrlDaoImpl extends AllDaoImpl<Url> implements UrlDao{


    public UrlDaoImpl() {
        super(Url.class);
    }


    @Override
    public Map<String, Object> getPageUrlList(Integer start, Integer pageSize) {
        int pageNo = (start-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Url.class);
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        Page page1 = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("urlList",criteria.list());
        return map;
    }

    @Override
    public Integer deleteUrlById(Integer id) {
        String sql = "delete from lzt_url where id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id);
        return query.executeUpdate();
    }

    @Override
    public List<Url> getUrlList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Url.class);
        if(criteria.list().size()<1){
            return null;
        }
        return criteria.list();
    }
}
