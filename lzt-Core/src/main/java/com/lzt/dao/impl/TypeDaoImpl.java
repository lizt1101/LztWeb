package com.lzt.dao.impl;

import com.lzt.dao.TypeDao;
import com.lzt.entity.Type;
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
 * @Create 2017/11/27
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */

@Repository("typeDao")
public class TypeDaoImpl extends AllDaoImpl<Type> implements TypeDao {

    public TypeDaoImpl() {
        super(Type.class);
    }

    @Override
    public List<Type> getTypeList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Type.class);
        criteria.add(Restrictions.eq("isDelete","0"));
        criteria.addOrder(Order.asc("sort"));
        return criteria.list();
    }

    @Override
    public  Map<String,Object> getPageTypeList(Integer page, Integer rows) {
        int pageSize = rows;
        int pageNo = (page-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Type.class);
        criteria.add(Restrictions.eq("isDelete","0"));
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.desc("updateDate"));
        Page page1 = new Page(page.longValue(),rows.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("typeList",criteria.list());
        return map;
    }

    @Override
    public Integer saveOrUpdate(Type type) {
        Integer i = null;
        if(type.getId()!=null){ //更新
            String hql = "update Type t set t.typeName=:typeName,t.sort=:sort,updateDate=:updateDate where t.id=:id";
            Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
                    .setParameter("typeName",type.getTypeName()).setParameter("sort",type.getSort())
                    .setParameter("id",type.getId()).setParameter("updateDate",type.getUpdateDate());
            i = query.executeUpdate();
        }else{ //保存
          Type type1 = this.save(type);
          i = type1.getId();
        }
        return i;
    }

    @Override
    public Integer deletetype(Integer id) {
        Integer i = null;
        String hql = "update Type t set t.isDelete=1,updateDate=:updateDate where t.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
                .setParameter("updateDate",new Date()).setParameter("id",id);
        i = query.executeUpdate();
        return i;
    }
}
