package com.bittest.platform.bg.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    public static String formatDateToStringV1(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static String formatDateToStringV2(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String formatDateToStringV3(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(date);
    }

    public static String transferDate_yyyymmdd(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String currentDate_yyMMdd() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");

        return dateFormat.format(currentDate);
    }

    public static String currentDate_yMMdd() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd");

        String date = dateFormat.format(currentDate);
        return date.substring(1, date.length());
    }

    public static String currentDate_yyyyMMdd() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

        return dateFormat.format(currentDate);
    }

    public static String currentDate_yyyyMMddHHmmss() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        return dateFormat.format(currentDate);
    }

    public static String currentDate_HH() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH");

        return dateFormat.format(currentDate);
    }

    public static Date formatStringToDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
        }

        return date;
    }
    private static Map<String, ThreadLocal<SimpleDateFormat>> dataFormatMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

    public static SimpleDateFormat getDateFormat(final String pattern) {
        ThreadLocal<SimpleDateFormat> threadLocal = dataFormatMap.get(pattern);
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<SimpleDateFormat>() {
                @Override
                protected SimpleDateFormat initialValue() {
                    return new SimpleDateFormat(pattern);
                }
            };
            dataFormatMap.put(pattern, threadLocal);
        }
        return threadLocal.get();
    }
}
