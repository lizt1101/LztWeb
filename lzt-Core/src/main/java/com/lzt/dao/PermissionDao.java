package com.lzt.dao;

import com.lzt.Bo.PermissionBo;
import com.lzt.entity.Permission;
import com.lzt.entity.Type;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface PermissionDao extends AllDao<Permission>{

    public List<PermissionBo> getPers(Set<Integer> roleIds);


	


}
