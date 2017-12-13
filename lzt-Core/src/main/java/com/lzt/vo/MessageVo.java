package com.lzt.vo;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/11/27
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class MessageVo {

    public static final String SUCCESS="000000";
    public static final String ERROR="000002";

    private String code = "000000";
    private Object data;
    private String message = "success";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
