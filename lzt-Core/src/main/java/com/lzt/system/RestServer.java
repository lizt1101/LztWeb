package com.lzt.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/11/28
 * @Version 1.0
 * @Copyright:
 */
public class RestServer {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public RestServer(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
    }

    /**
     * 获取入参
     * @return
     */
    public String getContext(){
        String data = "";
        String line = null;
        try {
            request.setCharacterEncoding("UTF-8");
            if("GET".equals(request.getMethod())){
                String str = request.getQueryString();
                data = str.replaceAll("%22", "\"");
            }else if("POST".equals(request.getMethod())){
                BufferedReader reader = request.getReader();
                while (true) {
                    line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    data += line;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("get message failed");
        }
        return data;
    }


    /**
     * 发送回调消息
     * @param result
     */
    public void send(String result) {
        try {
            response.setHeader("Content-Type", "text/json;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(result);
        } catch (IOException ioe) {
            throw new RuntimeException("send message failed");
        }

    }

}
