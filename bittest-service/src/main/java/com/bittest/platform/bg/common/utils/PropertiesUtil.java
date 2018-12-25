package com.bittest.platform.bg.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class PropertiesUtil {

    private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);

    public static ConcurrentMap<String, String> jSfAddress = new ConcurrentHashMap<String, String>();

    static {
        try {
            init();
        } catch (Exception e) {
            log.error("PropertiesUtil init exception", e);
        }
    }

    public static void init() throws Exception {
        log.info("加载配置文件信息开始");

        initMap("props/system-config", jSfAddress);

        log.info("加载配置文件信息完成");
    }

    public static void initMap(String configFile, ConcurrentMap<String, String> configMap) {
        ResourceBundle configRb = ResourceBundle.getBundle(configFile);
        Enumeration<String> elements = configRb.getKeys();
        while (elements.hasMoreElements()) {
            String key = elements.nextElement();
            configMap.put(key, configRb.getString(key));
        }
    }
}
