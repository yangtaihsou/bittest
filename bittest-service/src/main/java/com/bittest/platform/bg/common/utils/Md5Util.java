package com.bittest.platform.bg.common.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * Created by wuwuhuan on 2016/4/2.
 * MD5工具
 */
public class Md5Util {

    private static final Logger log = LoggerFactory.getLogger(Md5Util.class);

    public static String digestByMd5(String data) {
        try {
            return DigestUtils.md5Hex(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            log.error("Md5Util exception: data=" + data);

            throw new RuntimeException("Md5Util exception: data=" + data);
        }
    }
}
