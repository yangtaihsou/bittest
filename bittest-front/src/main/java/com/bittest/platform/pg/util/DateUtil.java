package com.bittest.platform.pg.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {

    final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static String FORMATE_DAY = "yyyy-MM-dd";
    public static String FORMATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static Date getDateByInteralBefore(int day) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -day);

        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_DAY);
        Date date1 = date;
        try {
            String dateStr = sdf.format(date);
            date1 = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("时间转换错误：{}", e);
        }
        return date1;

    }

    public static Date getDateByInteralAfter(int day) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, day);
        Date date = cal.getTime();
        return date;
    }

    public static Date getIntegerDate(Date date) {

        Date inteDate = date;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMATE_DAY);
        try {
            String dateStr = sdf.format(date);
            inteDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("时间转换错误：{}", e);
        }
        return inteDate;
    }

    public static Date getLastTimeOfDay(Date date) {

        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.set(Calendar.HOUR_OF_DAY, 23);
        cl.set(Calendar.MINUTE, 59);
        cl.set(Calendar.SECOND, 59);
        date = cl.getTime();
        return date;
    }

    public static Date convertStringToStartDate(String time, String format) {

        Date date = null;

        if (!StringUtils.isNotBlank(time)) {
            return date;
        }
        try {
            if (!StringUtils.isNotBlank(format)) {
                format = DateUtil.FORMATE_DAY;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(time);

        } catch (Exception e) {
            logger.error("时间格式转换失败 time:{},format:{}", time, format);
            logger.error("时间格式转换失败异常", e);
        }
        return date;
    }

    public static Date convertStringToEndDate(String time, String format) {

        Date date = null;

        if (!StringUtils.isNotBlank(time)) {
            return date;
        }
        try {
            if (!StringUtils.isNotBlank(format)) {
                format = DateUtil.FORMATE_DAY;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(time);
            Calendar cl = Calendar.getInstance();
            cl.setTime(date);
            cl.set(Calendar.HOUR, 23);
            cl.set(Calendar.MINUTE, 59);
            cl.set(Calendar.SECOND, 59);
            date = cl.getTime();
        } catch (Exception e) {
            logger.error("时间格式转换失败 time:{},format:{}", time, format);
            logger.error("时间格式转换失败异常", e);
        }
        return date;
    }

    public static String convertDateToString(Date date, String format) {

        if (date == null) {
            return "";
        }
        if (!StringUtils.isNotBlank(format)) {
            format = DateUtil.FORMATE_DAY;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String time = sdf.format(date);
            return time;

        } catch (Exception e) {
            logger.error("时间格式转换失败 date:{},format:{}", date, format);
            logger.error("时间格式转换失败异常", e);
        }
        return "";
    }

}
