package com.lzt.service;

import com.lzt.entity.User;
import com.lzt.exception.LztException;

import java.util.Map;

public interface UserService {
	
	public User getUserByUsername(String username);

	public void updatePassword(String nowPassword) throws LztException;

	public void updateHeadImg(String headImg) throws LztException;

	public void updateInfo(User user) throws LztException;

	public Map<String,Object> getPageList(Integer start, Integer pageSize);


	public void insertUser(String userName,String password) throws LztException;

	public void updateStatus(Integer id,String type) throws LztException;

	public void deleteUser(Integer id);

	public User getuser(Integer id);

}
