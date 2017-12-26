package com.lzt.dao.impl;

import com.lzt.dao.RoleDao;
import com.lzt.dao.TypeDao;
import com.lzt.entity.Role;
import org.springframework.stereotype.Repository;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Repository("roleDao")
public class RoleDaoImpl extends AllDaoImpl<Role> implements RoleDao {


    public RoleDaoImpl() {
        super(Role.class);
    }






}
