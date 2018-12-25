package com.bittest.platform.pg.util;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {

    static final String[] pattern = new String[]{"yyyy-MM", "yyyyMM", "yyyy/MM",
            "yyyyMMdd", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMddHHmmss",
            "yyyy-MM-dd HH", "yyyy/MM/dd HH", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd HH:mm", "yyyy-MM-dd HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss"};

    @Override
    public Date convert(String source) {
        try {
            return DateUtils.parseDate(source, pattern);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
