package com.lzt.dao.impl;

import com.lzt.dao.RoleDao;
import com.lzt.dao.WebDao;
import com.lzt.entity.Role;
import com.lzt.entity.WebContent;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;


/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("webDao")
public class WebDaoImpl extends AllDaoImpl<WebContent> implements WebDao {


    public WebDaoImpl() {
        super(WebContent.class);
    }


    @Override
    public Integer updateContent(WebContent webContent) {
        String sql = "update lzt_common c set c.web_content=:content where c.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("content",webContent.getWebContent()).setParameter("id",webContent.getId());
        return query.executeUpdate();
    }
}
