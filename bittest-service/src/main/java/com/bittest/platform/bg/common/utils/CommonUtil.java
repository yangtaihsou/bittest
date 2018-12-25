package com.bittest.platform.bg.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;


public class CommonUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /*将一个object类型转换为一个BigDecimal*/
    public static BigDecimal nvlDecimal(Object object, BigDecimal defaultValue) {
        if (object != null) {
            try {
                if (object instanceof Double) {
                    return BigDecimal.valueOf(((Double) object).doubleValue());
                }
                if (object instanceof String) {
                    if (StringUtils.isEmpty(object.toString())) {
                        return defaultValue;
                    }
                    return new BigDecimal((String) object);
                }
                return (BigDecimal) object;
            } catch (Exception e) {
                log.error("", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

    /*小数点后格式化几位*/
    public static BigDecimal decimalFormatDouble(BigDecimal data, int scale) {
        return data.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    /*判断字符串是否为空*/
    public static String nvl(String s, String defaultValue) {
        if (!StringUtils.isEmpty(s)) {
            return s;
        }
        return defaultValue;
    }

    public static String strNvl(Object object, String defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        return object.toString();
    }

    public static Integer nvlInt(Object object, Integer defaultValue) {
        if (object != null) {
            try {
                return Integer.parseInt(object.toString());
            } catch (NumberFormatException e) {
                log.error("", e);
                return defaultValue;
            }
        }
        return defaultValue;
    }

}
