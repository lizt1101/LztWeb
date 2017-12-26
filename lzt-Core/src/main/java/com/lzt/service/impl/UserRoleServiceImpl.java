package com.lzt.service.impl;

import com.lzt.Bo.RoleBo;
import com.lzt.Bo.RolesIdsBo;
import com.lzt.dao.UserRoleDao;
import com.lzt.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public RolesIdsBo getRoles(Integer id) {
        RolesIdsBo rolesIdsBo = new RolesIdsBo();
        List<RoleBo> roleBos = userRoleDao.getRoles(id);
        Set<String> names = new HashSet<String>();
        Set<Integer> ids = new HashSet<Integer>();
        for (RoleBo r:roleBos) {
            names.add(r.getRoleName());
            ids.add(r.getId());
        }
        rolesIdsBo.setRoleNames(names);
        rolesIdsBo.setRoleIds(ids);
        return rolesIdsBo;
    }
}
