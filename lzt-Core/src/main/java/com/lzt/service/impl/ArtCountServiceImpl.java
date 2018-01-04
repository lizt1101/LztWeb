package com.lzt.service.impl;

import com.lzt.dao.ArtCountDao;
import com.lzt.entity.ArtCount;
import com.lzt.exception.LztException;
import com.lzt.service.ArtCountService;
import com.lzt.service.RoleService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/22
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
@Service("artCountService")
public class ArtCountServiceImpl implements ArtCountService {

    @Autowired
    private ArtCountDao artCountDao;

    @Override
    public Integer updateArtCount(ArtCount artCount) throws LztException {
        ArtCount artCount1 = artCountDao.getArtCount(artCount.getArtId());
        if(artCount.getLook() != null && artCount1 != null){
            artCount.setLook(artCount.getLook()+artCount1.getLook());
        }
        if(artCount1 == null){
            artCount.setPing(0);
            artCount.setZan(0);
            artCountDao.save(artCount);
            return 1;
        }else{
            Integer i = artCountDao.updateArtCount(artCount);
            if(i==null || i<1){
                throw new LztException(MessageVo.PASSWORD_ERROR,"更新失败!");
            }
        }
        return artCount1.getLook()+1;
    }

    @Override
    public Integer getArtReadCount(Integer artId) {
        ArtCount artCount1 = artCountDao.getArtCount(artId);
        if(artCount1==null){
            return 0;
        }else{
            return artCount1.getLook();
        }
    }
}
