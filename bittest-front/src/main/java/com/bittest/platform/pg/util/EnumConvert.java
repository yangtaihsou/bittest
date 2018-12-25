package com.bittest.platform.pg.util;


import com.bittest.platform.pg.domain.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EnumConvert {

    final static Logger logger = LoggerFactory.getLogger(EnumConvert.class);

    public static List<KeyValue> getEnumList(Class enumClass) {

        List<KeyValue> kvs = new ArrayList<KeyValue>();
        try {
            for (Object obj : enumClass.getEnumConstants()) {

                Method codeM = enumClass.getDeclaredMethod("getCode", null);
                Method msgM = enumClass.getDeclaredMethod("getMsg", null);
                Object codeObj = codeM.invoke(obj, null);
                String codeStr = "";
                if (codeObj instanceof Long) {
                    Long code = (Long) codeObj;
                    codeStr = code + "";
                } else if (codeObj instanceof Integer) {
                    Integer code = (Integer) codeObj;
                    codeStr = code + "";
                } else if (codeObj instanceof String) {
                    codeStr = (String) codeObj;
                }
                String msg = (String) msgM.invoke(obj, null);
                KeyValue kv = new KeyValue(codeStr, msg);
                kvs.add(kv);
            }
        } catch (Exception e) {
            EnumConvert.logger.error("获取枚举类value报错：", e);
        }
        return kvs;
    }

    public static String getMsg(Class enumClass, Long code) {

        List<KeyValue> kvs = new ArrayList<KeyValue>();
        String msg = "";
        try {
            for (Object obj : enumClass.getEnumConstants()) {

                Method codeM = enumClass.getDeclaredMethod("getCode", null);
                Method msgM = enumClass.getDeclaredMethod("getMsg", null);
                Object codeObj = codeM.invoke(obj, null);
                String codeStr = "";
                if (codeObj instanceof Long) {
                    code = (Long) codeObj;
                    codeStr = code + "";
                } else if (codeObj instanceof Integer) {
                    codeStr = codeObj + "";
                } else if (codeObj instanceof String) {
                    codeStr = (String) codeObj;
                }

                if (codeStr.equals(code + "")) {
                    msg = (String) msgM.invoke(obj, null);
                }
            }
        } catch (Exception e) {
            EnumConvert.logger.error("获取枚举类value报错：", e);
        }
        return msg;


    }


    public static String getMsg(Class enumClass, Object code) {

        List<KeyValue> kvs = new ArrayList<KeyValue>();
        String msg = "";
        try {
            for (Object obj : enumClass.getEnumConstants()) {

                Method codeM = enumClass.getDeclaredMethod("getCode", null);
                Method msgM = enumClass.getDeclaredMethod("getMsg", null);
                Object codeObj = codeM.invoke(obj, null);
                String codeStr = "";
                if (codeObj instanceof Long) {
                    code = (Long) codeObj;
                    codeStr = code + "";
                } else if (codeObj instanceof Integer) {
                    codeStr = codeObj + "";
                } else if (codeObj instanceof String) {
                    codeStr = (String) codeObj;
                }

                if (codeStr.equals(code + "")) {
                    msg = (String) msgM.invoke(obj, null);
                }
            }
        } catch (Exception e) {
            EnumConvert.logger.error("获取枚举类value报错：", e);
        }
        return msg;


    }

}
