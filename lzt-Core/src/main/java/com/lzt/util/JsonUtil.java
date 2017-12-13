package com.lzt.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * @Title
 * @Description
 * @Author:lizitao
 * @Create 2017/12/5
 * @Version 1.0
 * @Copyright:2016 www.jointem.com
 */
public class JsonUtil {


    public static String jsonToString(Object data){
        String str = JSON.toJSONString(data,new ValueFilter(){
            @Override
            public Object process(Object object, String s, Object v) {
                if(v instanceof Integer || v instanceof Long){
                    return v+"";
                }
                if(v instanceof Timestamp){
                    DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    v = sdf.format(v);
                }
                return v;
            }
        }, SerializerFeature.DisableCircularReferenceDetect);

        return str;
    }
}
