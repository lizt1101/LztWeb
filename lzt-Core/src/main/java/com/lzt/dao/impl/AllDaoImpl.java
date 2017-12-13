package com.lzt.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

import com.lzt.dao.AllDao;

public class AllDaoImpl<T> extends BaseDaoImpl<T, Integer> implements AllDao<T> {

	public AllDaoImpl(Class persistType) {
		super(persistType);
	}
	
	public Long updateadmin(final String hql,final Object... params){
		Long l = (Long) getHibernateTemplate().execute(new HibernateCallback<Object>(){

			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				int i=0;
				for (Object p : params) {
					query.setParameter(i++, p);
				}
				Integer rows = query.executeUpdate();
				return new Long(rows);
			}
		});
		return l;
	}

}
