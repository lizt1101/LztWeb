package com.lzt.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.lzt.dao.BaseDao;

public class BaseDaoImpl<T,PK extends Serializable> extends HibernateDaoSupport implements BaseDao<T, PK> {

	protected Class<T> persistType;
	 
	 
	public BaseDaoImpl(Class<T> persistType) {
		this.persistType = persistType;
	}

	@Resource
	public void setSuperSessionFactory(SessionFactory factory) {
        super.setSessionFactory(factory);
    }
	
	@Override
	public T save(T entity) {
		this.getSessionFactory().getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public T merge(T entity) {
		 this.getHibernateTemplate().merge(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		 this.getHibernateTemplate().update(entity);
		return entity;
	}

	@Override
	public T get(PK id) {
		
		return this.getHibernateTemplate().get(this.persistType, id);
	}

	@Override
	public void remove(PK id) {
		this.getHibernateTemplate().delete(this.get(id));
		
	}

	@Override
	public void remove(T entity) {
		this.getHibernateTemplate().delete(entity);
		
	}

	@Override
	public void evict(T entity) {
		this.getHibernateTemplate().evict(entity);
		
	}

}
