package com.lzt.dao.impl;

import com.lzt.dao.WebDevelopDao;
import com.lzt.entity.Url;
import com.lzt.entity.WebDevelop;
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
 * @Create 2018/1/30
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("webDevelopDao")
public class WebDevelopDaoImpl extends AllDaoImpl<WebDevelop> implements WebDevelopDao{

    public WebDevelopDaoImpl() {
        super(WebDevelop.class);
    }

    @Override
    public Map<String, Object> getPageDevlop(Integer start, Integer pageSize) {
        int pageNo = (start-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(WebDevelop.class);
        criteria.add(Restrictions.eq("webStatus","0"));
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        Page page1 = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("DevList",criteria.list());
        return map;
    }

    @Override
    public Integer deleteDev(Integer id) {
        String sql = "update lzt_web_develop d set d.web_status='1',d.web_update_date=now() where d.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id);
        return query.executeUpdate();
    }

    @Override
    public Integer updateDev(WebDevelop webDevelop) {
        String sql = "update lzt_web_develop d set d.web_content =:webContent,d.web_title =:webTitle,d.web_update_date=now() where d.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("webContent",webDevelop.getWebContent()).setParameter("webTitle",webDevelop.getWebTitle())
                .setParameter("id",webDevelop.getId());
        return query.executeUpdate();
    }

    @Override
    public List<WebDevelop> getDevList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(WebDevelop.class);
        criteria.add(Restrictions.eq("webStatus","0"));
        criteria.addOrder(Order.desc("webCreateDate"));
        if(criteria.list().size()<1){
            return null;
        }
        return criteria.list();
    }
}













