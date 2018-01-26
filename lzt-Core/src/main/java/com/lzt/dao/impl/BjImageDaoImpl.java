package com.lzt.dao.impl;

import com.lzt.dao.BjImageDao;
import com.lzt.entity.BjImage;
import com.lzt.entity.Type;
import com.lzt.util.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("bjImageDao")
public class BjImageDaoImpl extends AllDaoImpl<BjImage> implements BjImageDao {

    public BjImageDaoImpl() {
        super(BjImage.class);
    }

    @Override
    public Map<String,Object> getBjList(Integer page, Integer rows,String status) {
        int pageSize = rows;
        int pageNo = (page-1)*pageSize;
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BjImage.class);
        if(status!=null && status.equals("1")){
            criteria.add(Restrictions.eq("isDelete","1"));
        }
        if(status!=null && status.equals("0")){
            criteria.add(Restrictions.eq("isDelete","0"));
        }
        Integer allCount = criteria.list().size();
        criteria.setFirstResult(pageNo);
        criteria.setMaxResults(pageSize);
        criteria.addOrder(Order.desc("updateDate"));
        Page page1 = new Page(page.longValue(),rows.longValue(),allCount.longValue());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("page",page1);
        map.put("BjList",criteria.list());
        return map;
    }

    @Override
    public Integer getMaxBjId() {
        String sql = "SELECT MAX(a.id) maxId FROM `lzt_bj_image` a";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        Integer totalCount = (Integer) query.uniqueResult();
        if(totalCount==null){
            return 0;
        }
        return totalCount.intValue();
    }

    @Override
    public void saveBj(BjImage bjImage) {
        String sql = "INSERT INTO `lzt_bj_image`\n" +
                "VALUES\n" +
                "(:id,:bjname,:url,:sort,:createDate,:updateDate,:isDelete,:isPu)";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",bjImage.getId()).setParameter("bjname",bjImage.getBjName())
                .setParameter("url",bjImage.getBjUrl()).setParameter("sort",bjImage.getSort())
                .setParameter("createDate",bjImage.getCreateDate()).setParameter("updateDate",bjImage.getUpdateDate())
                .setParameter("isDelete",bjImage.getIsDelete()).setParameter("isPu",bjImage.getIsPu());
        query.executeUpdate();
    }

    @Override
    public Integer updateBjImage(BjImage bjImage) {
        String sql = "update `lzt_bj_image` bj set bj.bj_name=:bjname,bj.update_date=:updateDate,bj.is_pu=:isPu,bj.sort=:sort where bj.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",bjImage.getId()).setParameter("bjname",bjImage.getBjName())
               .setParameter("sort",bjImage.getSort()).setParameter("updateDate",bjImage.getUpdateDate())
               .setParameter("isPu",bjImage.getIsPu());
        return query.executeUpdate();
    }

    @Override
    public Integer updateBjStatus(Integer id, Integer status) {
        String sql = "update `lzt_bj_image` bj set bj.is_delete=:status,bj.update_date=:updateDate where bj.id=:id";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        query.setParameter("id",id).setParameter("status",status)
                .setParameter("updateDate",new Date());
        return query.executeUpdate();
    }

    @Override
    public List<BjImage> getQBjList() {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(BjImage.class);
        criteria.add(Restrictions.eq("isDelete","0"));
        criteria.addOrder(Order.asc("sort"));
        return criteria.list();
    }
}
