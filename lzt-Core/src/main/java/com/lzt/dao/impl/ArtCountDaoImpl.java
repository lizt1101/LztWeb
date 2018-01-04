package com.lzt.dao.impl;

import com.lzt.dao.ArtCountDao;
import com.lzt.entity.ArtCount;
import com.lzt.entity.Article;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("artCountDao")
public class ArtCountDaoImpl extends AllDaoImpl<ArtCount> implements ArtCountDao {

    public ArtCountDaoImpl() {
        super(ArtCount.class);
    }

    @Override
    public Integer updateArtCount(ArtCount artCount) {
        String sql = "update lzt_art_count a set";
        if(artCount.getZan() != null){
            sql += " a.zan=:zan";
        }
        if(artCount.getLook() != null){
            sql += " a.look=:look";
        }
        if(artCount.getPing() != null){
            sql += " a.ping=:ping";
        }
        sql += " where a.artId=:artId";
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        if(artCount.getZan() != null){
           query.setParameter("zan",artCount.getZan());
        }
        if(artCount.getLook() != null){
            query.setParameter("look",artCount.getLook());
        }
        if(artCount.getPing() != null){
            query.setParameter("ping",artCount.getPing());
        }
        query.setParameter("artId",artCount.getArtId());
        return query.executeUpdate();
    }


    @Override
    public ArtCount getArtCount(Integer artId) {
        Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(ArtCount.class);
        criteria.add(Restrictions.eq("artId",artId));
        if(criteria.list().size()<1){
           return null;
        }
        return (ArtCount)criteria.list().get(0);
    }
}
