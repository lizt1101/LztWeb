package com.lzt.service;

import com.lzt.entity.ArtCount;
import com.lzt.exception.LztException;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface ArtCountService {

    public Integer updateArtCount(ArtCount artCount) throws LztException;

    public Integer getArtReadCount(Integer artId);

}
