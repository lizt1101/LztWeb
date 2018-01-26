package com.lzt.service;

import com.lzt.entity.Tu;
import com.lzt.exception.LztException;

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
public interface TuService {

    public void saveTu(Tu tu);

    public Map<String,Object> getQtList(Integer page, Integer rows, String status,String type);

    public void updateQtStatus(String id,String status,String type) throws LztException;

    public List<Tu> getQtList();
}
