package com.lzt.dao;

import com.lzt.entity.User;

public interface userDao extends AllDao<User>{
	
	public User getUserByUsername(String username);

}
