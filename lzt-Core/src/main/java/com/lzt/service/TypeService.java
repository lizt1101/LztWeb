package com.lzt.service;

import com.lzt.entity.Type;
import com.lzt.exception.LztException;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/8
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface TypeService {

    public List<Type> getTypeList();

    public Map<String,Object> getPageTypeList(Integer page, Integer rows);

    public void saveOrUpdate(Type type) throws LztException;

    public void deleteType(Integer id) throws LztException;

}
