package com.lzt.service.impl;

import com.lzt.dao.BjImageDao;
import com.lzt.entity.BjImage;
import com.lzt.exception.LztException;
import com.lzt.service.BjImageService;
import com.lzt.vo.MessageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
@Service("bjImageService")
public class BjImageServiceImpl implements BjImageService{

    @Autowired
    private BjImageDao bjImageDao;

    @Override
    public Map<String, Object> getBjList(Integer page, Integer rows,String status) {
        return bjImageDao.getBjList(page,rows,status);
    }

    @Override
    public Integer getMaxId() {
        return bjImageDao.getMaxBjId();
    }

    @Override
    public void saveBjImage(BjImage bjImage) {
        if(bjImage.getIsPu()==null){
            bjImage.setIsPu("1");
        }else{
            bjImage.setIsPu("0");
        }
        bjImage.setCreateDate(new Date());
        bjImage.setUpdateDate(new Date());
        bjImage.setIsDelete("0");
        bjImageDao.saveBj(bjImage);
    }

    @Override
    public BjImage getBjImage(Integer id) {
        return bjImageDao.get(id);
    }


    @Override
    public void udateBjImage(BjImage bjImage) throws LztException {
        if(bjImage.getIsPu()==null){
            bjImage.setIsPu("1");
        }else{
            bjImage.setIsPu("0");
        }
        bjImage.setUpdateDate(new Date());
        Integer i = bjImageDao.updateBjImage(bjImage);
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"更新背景失败!");
        }
    }

    @Override
    public void updateBjStatus(String id, String status) throws LztException {
        Integer i = bjImageDao.updateBjStatus(Integer.parseInt(id),Integer.parseInt(status));
        if(i<1 || i==null){
            throw new LztException(MessageVo.PASSWORD_ERROR,"更新背景状态失败!");
        }
    }

    @Override
    public List<BjImage> getQBjList() {
        return bjImageDao.getQBjList();
    }
}















