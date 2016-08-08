package com.liwei.controller.admin;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * json-lib 日期处理类
 * Created by Liwei on 2016/8/7.
 */
public class DateJsonValueProcessor implements JsonValueProcessor{

    private String format;

    public DateJsonValueProcessor(String format){
        this.format = format;
    }

    /**
     * 处理数组部分不实现
     * @param o
     * @param jsonConfig
     * @return
     */
    @Override
    public Object processArrayValue(Object o, JsonConfig jsonConfig) {
        return null;
    }


    @Override
    public Object processObjectValue(String s, Object o, JsonConfig jsonConfig) {
        if(o == null){
            return "";
        }
        if(o instanceof Timestamp){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format((Timestamp)o);
        }
        if(o instanceof Date){
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format((Date)o);
        }
        return o.toString();
    }
}
