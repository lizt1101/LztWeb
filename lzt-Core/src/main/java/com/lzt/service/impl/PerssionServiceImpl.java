package com.lzt.service.impl;

import com.lzt.Bo.PermissionBo;
import com.lzt.dao.PermissionDao;
import com.lzt.service.PermissionService;
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
@Service("permissionService")
public class PerssionServiceImpl implements PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    @Override
    public Set<String> getPers(Set<Integer> ids) {
        Set<String> pers = new HashSet<String>();
        List<PermissionBo> permissionBoList = permissionDao.getPers(ids);
        for(PermissionBo permissionBo:permissionBoList){
            pers.add(permissionBo.getPerName());
        }
        return pers;
    }
}
