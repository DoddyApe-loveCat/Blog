package com.liwei.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Liwei on 2016/8/23.
 */
public class DateTest {

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // Date learnJavaStartDate = sdf.parse("2012-09-23 00:00:00");
            Date learnJavaStartDate = sdf.parse("1987-07-06 00:00:00");
            Long learnJavaStart = learnJavaStartDate.getTime();
            Long passed = System.currentTimeMillis() - learnJavaStart;
            Long passedDayNum = passed / 24 / 60 / 60 / 1000;
            System.out.println(passedDayNum);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
