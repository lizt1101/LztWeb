package com.lzt.dao;

import com.lzt.Bo.RoleBo;
import com.lzt.entity.UserRole;

import java.util.List;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface UserRoleDao extends AllDao<UserRole> {

    public List<RoleBo> getRoles(Integer id);



}
