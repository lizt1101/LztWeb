package com.lzt.service;

import com.lzt.entity.LeaveMessage;
import com.lzt.exception.LztException;

import java.util.Map;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2018/1/6
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public interface LeaveMessageService {

    public void save(LeaveMessage leaveMessage) throws LztException;

    public Map<String,Object> getLeaveMessageList(Integer start,Integer pageSize,String type);


    public Map<String,Object> getLeaveMessageShowList(Integer start,Integer pageSize);

    public void deleteMessage(String ids) throws LztException;

    public void updateMessage(String id,String isShow) throws LztException;


}
