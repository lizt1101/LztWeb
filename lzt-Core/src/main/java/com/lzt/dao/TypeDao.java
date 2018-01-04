package com.lzt.dao;

import com.lzt.entity.Type;

import java.util.List;
import java.util.Map;

public interface TypeDao extends AllDao<Type>{

	public List<Type> getTypeList();

	public Map<String,Object> getPageTypeList(Integer page, Integer rows);

	public Integer saveOrUpdate(Type type);

	public Integer deletetype(Integer id);

	public Type getType(Integer id);


}
