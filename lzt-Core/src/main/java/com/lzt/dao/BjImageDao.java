package com.lzt.dao;

import com.lzt.entity.BjImage;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface BjImageDao extends AllDao<BjImage>{

    public Map<String,Object> getBjList(Integer page, Integer rows,String status);

    public Integer getMaxBjId();

    public void saveBj(BjImage bjImage);

    public Integer updateBjImage(BjImage bjImage);

    public Integer updateBjStatus(Integer id,Integer status);

    public List<BjImage> getQBjList();

}
