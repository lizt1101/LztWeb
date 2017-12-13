package com.lzt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzt.dao.userDao;
import com.lzt.entity.User;
import com.lzt.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private userDao uDao;

	@Override
	public User getUserByUsername(String username) {
		
		return uDao.getUserByUsername(username);
	}

}
