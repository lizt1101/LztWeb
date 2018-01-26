package com.lzt.service.impl;

import com.lzt.dao.UserRoleDao;
import com.lzt.entity.UserRole;
import com.lzt.exception.LztException;
import com.lzt.util.DateUtil;
import com.lzt.util.MD5Util;
import com.lzt.vo.MessageVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzt.dao.userDao;
import com.lzt.entity.User;
import com.lzt.service.UserService;

import java.sql.Timestamp;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private userDao uDao;
    @Autowired
	private UserRoleDao userRoleDao;


	@Override
	public User getUserByUsername(String username) {
		
		return uDao.getUserByUsername(username);
	}

	@Override
	public void updatePassword(String newPassword) throws LztException {
		User user = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		Integer i = uDao.updatePassword(MD5Util.MD5(newPassword),user.getId());
		if(i == null || i<1){
			throw new LztException(MessageVo.ERROR,"修改失败密码!");
		}
		user.setPassword(MD5Util.MD5(newPassword));
		SecurityUtils.getSubject().getSession().setAttribute("CurrentUser", user);
	}

	@Override
	public void updateHeadImg(String headImg) throws LztException {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		User user = new User();
		user.setHeadImg(currentUser.getUsername()+"/"+headImg);
		Timestamp updateDate = DateUtil.getTimeStamp();
		Integer i = uDao.updateHeadImg(user,currentUser.getId());
		if(i == null || i<1){
			throw new LztException(MessageVo.ERROR,"更新图片失败!");
		}
		currentUser.setHeadImg(headImg);
		currentUser.setUpdateTime(updateDate);
		SecurityUtils.getSubject().getSession().setAttribute("CurrentUser", currentUser);
	}

	@Override
	public void updateInfo(User user) throws LztException {
		User currentUser = (User) SecurityUtils.getSubject().getSession().getAttribute("CurrentUser");
		Timestamp updateDate = DateUtil.getTimeStamp();
		user.setUpdateTime(updateDate);
		Integer i = uDao.updateInfo(user,currentUser.getId());
		if(i == null || i<1){
			throw new LztException(MessageVo.ERROR,"更新个人信息失败!");
		}
		currentUser.setDescription(user.getDescription());
		currentUser.setNickname(user.getNickname());
		currentUser.setUpdateTime(updateDate);
		SecurityUtils.getSubject().getSession().setAttribute("CurrentUser", currentUser);
	}

	@Override
	public Map<String, Object> getPageList(Integer start, Integer pageSize) {
		return uDao.getPageList(start,pageSize);
	}

	@Override
	public void insertUser(String userName, String password) throws LztException {
		User user = new User();
		user.setHeadImg("mr.jpg");
		Timestamp now = DateUtil.getTimeStamp();
		user.setCreateTime(now);
		user.setUpdateTime(now);
		user.setStatus(1);
		user.setUsername(userName);
		user.setPassword(MD5Util.MD5(password));
		user.setDescription("这个人很懒，什么都没有留下");
		user.setNickname(System.currentTimeMillis()+"lzt");
		User addUser = uDao.save(user);
		if(addUser==null){
			throw new LztException(MessageVo.PASSWORD_ERROR,"添加管理员失败!");
		}
		//添加用户角色
		UserRole userRole = new UserRole();
		userRole.setRoleId(2);
		userRole.setUserId(addUser.getId());
		UserRole userRole1 = userRoleDao.save(userRole);
		if(userRole1==null){
			throw new LztException(MessageVo.PASSWORD_ERROR,"添加角色失败!");
		}
	}

	@Override
	public void updateStatus(Integer id,String type) throws LztException {
		Integer i = uDao.updateStatus(id,type);
		if(i == null){
			throw new LztException(MessageVo.PASSWORD_ERROR,"用户管理失败!");
		}
	}

	@Override
	public void deleteUser(Integer id){
		uDao.remove(id);
	}

	@Override
	public User getuser(Integer id) {
		return uDao.get(id);
	}
}

















