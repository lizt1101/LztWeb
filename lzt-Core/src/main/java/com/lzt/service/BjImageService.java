package com.lzt.service;

import com.lzt.entity.BjImage;
import com.lzt.exception.LztException;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/16
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface BjImageService {

    public Map<String,Object> getBjList(Integer page, Integer rows,String status);

    public Integer getMaxId();

    public void saveBjImage(BjImage bjImage);

    public BjImage getBjImage(Integer id);

    public void udateBjImage(BjImage bjImage) throws LztException;

    public void updateBjStatus(String id,String status) throws LztException;

    public List<BjImage> getQBjList();

}
