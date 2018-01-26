package com.lzt.service.impl;

import com.lzt.dao.LunBoDao;
import com.lzt.entity.LunBo;
import com.lzt.exception.LztException;
import com.lzt.service.LunBoService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service("lunBoService")
public class LunBoServiceImpl implements LunBoService{

    @Autowired
    private LunBoDao lunBoDao;

    @Override
    public void saveLunbotu(LunBo lunBo) {
        lunBo.setLbStatus("0");
        lunBoDao.save(lunBo);
    }

    @Override
    public Map<String, Object> getLbList(Integer page, Integer rows, String status) {
        return lunBoDao.getLbList(page,rows,status);
    }

    @Override
    public void updateLbStatus(String id, String status) throws LztException {
        Integer i = lunBoDao.updateLbStatus(Integer.parseInt(id),status);
        if(i==null || i<1){
            throw new LztException(MessageVo.ERROR,"更新失败!");
        }
    }

    @Override
    public List<LunBo> getLbList() {
        return lunBoDao.getLunboList();
    }

    @Override
    public void getLbByAid(Integer aid) throws LztException {
        LunBo lunBo = lunBoDao.getLunBoByAid(aid);
        if(lunBo != null){
            throw new LztException(MessageVo.ERROR,"该编号"+aid+"的文章已经有轮播图了!");
        }
    }
}
