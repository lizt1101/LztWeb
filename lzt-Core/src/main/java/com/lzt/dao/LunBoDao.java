package com.lzt.dao;

import com.lzt.entity.LunBo;

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
public interface LunBoDao extends AllDao<LunBo>{

    public Map<String,Object> getLbList(Integer page, Integer rows, String status);

    public Integer updateLbStatus(Integer Id,String status);

    public List<LunBo> getLunboList();

    public LunBo getLunBoByAid(Integer aid);


}
