package com.lzt.service;

import com.lzt.entity.LunBo;
import com.lzt.exception.LztException;

import java.util.List;
import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/19
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface LunBoService {


    public void saveLunbotu(LunBo lunBo);

    public Map<String,Object> getLbList(Integer page, Integer rows, String status);

    public void updateLbStatus(String id,String status) throws LztException;

    public List<LunBo> getLbList();

    public void getLbByAid(Integer aid) throws LztException;
}
