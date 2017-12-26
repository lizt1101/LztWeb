package com.lzt.service;

import java.util.Set;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface PermissionService {

    public Set<String> getPers(Set<Integer> ids);
}
