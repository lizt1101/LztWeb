package com.lzt.dao.impl;

import com.lzt.entity.Type;
import com.lzt.util.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.lzt.dao.userDao;
import com.lzt.entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userDao")
public class UserDaoImpl extends AllDaoImpl<User> implements userDao{

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public User getUserByUsername(String username) {
		String hql = "from User uc where uc.username=:username and uc.status=1";
		Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		List<User> userList = query.list();
		if(userList.size()>0){
			return (User) query.list().get(0);
		}
		return null;
	}

	@Override
	public Integer updatePassword(String password,Integer id) {
		String hql = "update User u set u.password=? where id=?";
		Object[] param = {password,id};
		Long l = updateadmin(hql,param);
		return l.intValue();
	}

	@Override
	public Integer updateHeadImg(User user,Integer id) {
		String hql = "update User u set u.headImg=?,u.updateTime=? where id=?";
		Object[] param = {user.getHeadImg(),user.getUpdateTime(),id};
		Long l = updateadmin(hql,param);
		return l.intValue();
	}

	@Override
	public Integer updateInfo(User user, Integer id) {
		String hql = "update User u set u.nickname=?,u.description=?,u.updateTime=? where id=?";
		Object[] param = {user.getNickname(),user.getDescription(),user.getUpdateTime(),id};
		Long l = updateadmin(hql,param);
		return l.intValue();
	}

	@Override
	public Map<String,Object> getPageList(Integer start, Integer pageSize) {
		int pageNo = (start-1)*pageSize;
		Criteria criteria = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createCriteria(User.class);
		/*criteria.add(Restrictions.eq("status",1));*/
  		Integer allCount = criteria.list().size();
		criteria.setFirstResult(pageNo);
		criteria.setMaxResults(pageSize);
		criteria.addOrder(Order.desc("updateTime"));
		Page page1 = new Page(start.longValue(),pageSize.longValue(),allCount.longValue());
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("page",page1);
		map.put("userList",criteria.list());
		return map;
	}

	@Override
	public Integer updateStatus(Integer id,String type) {
		String hql = "update User u set";
		if(type.equals("1")){
			hql += " u.status=0";	//禁止登陆
		}
		if(type.equals("2")){
			hql += " u.status=1"; //开启登陆
		}
		hql +=" where id =?";
		Object[] param = {id};
		Long l = updateadmin(hql,param);
		return l.intValue();
	}
}







