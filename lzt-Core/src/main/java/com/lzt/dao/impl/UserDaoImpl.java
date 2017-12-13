package com.lzt.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.lzt.dao.userDao;
import com.lzt.entity.User;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends AllDaoImpl<User> implements userDao{

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User getUserByUsername(String username) {
		String hql = "from User uc where uc.username=:username";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		List<User> userList = query.list();
		if(userList.size()>0){
			return (User) query.list().get(0);
		}
		return null;
	}

}







