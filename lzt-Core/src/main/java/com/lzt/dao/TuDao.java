package com.lzt.dao;

import com.lzt.entity.Tu;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface TuDao extends AllDao<Tu>{

    public void updateStatus(String type);

    public Map<String,Object> getQtList(Integer page, Integer rows, String status,String type);

    public Integer updateQtStatus(String id,String status);

    public List<Tu> getQtList();

}
