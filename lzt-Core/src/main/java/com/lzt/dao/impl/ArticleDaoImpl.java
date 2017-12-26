package com.lzt.dao.impl;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lzt.entity.User;
import com.lzt.util.DateUtil;
import com.lzt.vo.ArtVo;
import org.apache.shiro.SecurityUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.lzt.dao.ArticleDao;
import com.lzt.entity.Article;
import com.lzt.util.Page;

import javax.persistence.criteria.CriteriaBuilder;

@Repository("articleDao")
public class ArticleDaoImpl extends AllDaoImpl<Article> implements ArticleDao {

	public ArticleDaoImpl() {
		super(Article.class);
	}

	@Override
	public Article saveArt(Article art) {
		Article article = new Article();
		if(art.getAid()!=null){
			String sql = " update lzt_article a set a.content=:content,content_text=:contentText," +
					"sign=:sign,title=:title,typeId=:typeId,updateTime=:updateTime,a.updateBy=:updateBy where id=:id";
			Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
					.setParameter("content",art.getContent()).setParameter("contentText",art.getContentText())
					.setParameter("sign",art.getSign()).setParameter("title",art.getTitle()).setParameter("updateBy",art.getUpdateBy())
					.setParameter("typeId",art.getTypeId()).setParameter("updateTime",art.getUpdateTime()).setParameter("id",art.getAid());
			query.executeUpdate();
			article = get(art.getAid());
		}else{
			article = this.save(art);
		}
		return article;
	}

	@Override
	public Map<String,Object> getPageArticle(Integer start, Integer pageSize) {
		String hql = "from Article art order by art.createTime desc";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		Integer allCount = query.list().size();
		int limit = (start-1)*pageSize;
		query.setFirstResult(limit);
		query.setMaxResults(pageSize);
		Page page = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
		Map<String,Object> artMap = new HashMap<String,Object>();
		artMap.put("page", page);
		artMap.put("artList", query.list());
		return artMap;
	}

	@Override
	public Map<String, Object> getPageArticleList(Integer start, Integer pageSize) {
		String sql = "SELECT a.id id,a.`title` title,a.`content_text` content,a.`createTime` createTime,a.`updateTime` updateTime,b.`type_name` typeName,d.user_name updateBy,c.`user_name` userName \n" +
				"FROM `lzt_article` a " +
				"LEFT JOIN `lzt_type` b ON a.`typeId`=b.`id` " +
				"LEFT JOIN `lzt_user` c ON a.`userId`=c.`id` " +
				"LEFT JOIN `lzt_user` d ON d.`id`=a.updateBy " +
				"where a.status=0 ORDER BY a.`updateTime` desc";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.addScalar("id", IntegerType.INSTANCE).addScalar("title", StringType.INSTANCE).addScalar("content",StringType.INSTANCE)
				.addScalar("createTime", TimestampType.INSTANCE).addScalar("updateTime",TimestampType.INSTANCE)
				.addScalar("typeName",StringType.INSTANCE).addScalar("userName",StringType.INSTANCE).addScalar("updateBy",StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ArtVo.class));
		Integer allCount = query.list().size();
		int limit = (start-1)*pageSize;
		query.setFirstResult(limit);
		query.setMaxResults(pageSize);
		Page page = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
		Map<String,Object> artMap = new HashMap<String,Object>();
		artMap.put("page", page);
		artMap.put("artList", query.list());
		return artMap;
	}

	public Article getdetail(Integer id){
		Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(Article.class);
		criteria.add(Restrictions.eq("id",id));
		Article article = (Article)criteria.list().get(0);
		return article;
	}

	@Override
	public Integer deleteById(List<Integer> idList) {
		User user =  (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		String hql = "update Article art set art.status=1,art.updateTime=:updateTime,art.updateBy=:updateBy where art.id in (:idList)";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql)
				.setParameterList("idList",idList).setParameter("updateTime", DateUtil.getTimeStamp())
				.setParameter("updateBy", user.getId());
		return query.executeUpdate();
	}

	@Override
	public Integer getCountByTypeId(Integer typeId) {
		String sql = "select COUNT(*) countA from lzt_article a where a.typeId=:typeId group by a.typeId ";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql)
				.setParameter("typeId",typeId);
		BigInteger totalCount = (BigInteger)query.uniqueResult();
		if(totalCount==null){
			return null;
		}
		return totalCount.intValue();
	}
}
