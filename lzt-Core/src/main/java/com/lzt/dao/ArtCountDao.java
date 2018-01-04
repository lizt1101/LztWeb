package com.lzt.dao;

import com.lzt.entity.ArtCount;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface ArtCountDao extends AllDao<ArtCount> {

    public Integer updateArtCount(ArtCount artCount);

    public ArtCount getArtCount(Integer artId);

}
