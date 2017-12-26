package com.lzt.dao;

import com.lzt.entity.User;

import java.util.List;
import java.util.Map;

public interface userDao extends AllDao<User>{
	
	public User getUserByUsername(String username);

	public Integer updatePassword(String password,Integer id);

	public Integer updateHeadImg(User user,Integer id);

	public Integer updateInfo(User user,Integer id);

	public Map<String,Object> getPageList(Integer start, Integer pageSize);

	public Integer updateStatus(Integer id,String type);

}
