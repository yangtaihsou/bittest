package com.bittest.platform.pg.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


public class PropertiesUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);

    public static ConcurrentMap<String, String> authen = new ConcurrentHashMap<String, String>();
    public static ConcurrentMap<String, String> loginout = new ConcurrentHashMap<String, String>();

    static {
        try {
            init();
        } catch (Exception e) {
            logger.error("PropertiesUtil init exception", e);
        }
    }

    public static void init() throws RuntimeException {
        logger.info("Properties init start");
	/*	initMap("authen", authen);
		initMap("props/loginout", loginout);*/

        logger.info("Properties init end");
    }

    public static void initMap(String configFile, ConcurrentMap<String, String> configMap) {
        try {
            ResourceBundle configRb = ResourceBundle.getBundle(configFile);
            Enumeration<String> elements = configRb.getKeys();
            while (elements.hasMoreElements()) {
                String key = elements.nextElement();
                configMap.put(key, new String(configRb.getString(key).getBytes()));
            }
        } catch (Exception e) {
            logger.error("读取系统配置文件[" + configFile + "]异常", e);
            throw new RuntimeException(e);
        }
    }


}
