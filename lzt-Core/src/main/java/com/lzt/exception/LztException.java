package com.lzt.exception;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/6
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class LztException extends Exception{

    private String code;

    public LztException(String code,String message){
        super(message);
        this.code=code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
