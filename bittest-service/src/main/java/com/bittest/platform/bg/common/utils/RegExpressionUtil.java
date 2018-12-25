package com.bittest.platform.bg.common.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 2018-08-24.
 */
public class RegExpressionUtil {

    private static final Logger log = LoggerFactory.getLogger(RegExpressionUtil.class);

    /**
     * 正则表达式提取
     *
     * @param reg   提取规则（正则表达式）
     * @param value 提取文本
     * @param index 提取索引
     * @return
     */
    public static String regData(String reg, String value, int index) {
        String result = "";
        int test = 1;
        // 创建 Pattern 对象
        try {
            //reg = StringEscapeUtils.escapeJava(reg);
            Pattern r = Pattern.compile(reg);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(value);
            while (m.find()) {
                if (test == index) {
                    log.error("正则表达式提取到内容为：{}", m.group(1));
                    result = m.group(1);
                } else {
                    log.error("没有到达指定提取位置:{}，继续执行", test);
                }
                test++;
            }
        } catch (Exception e) {
            log.error("正则表达式提起数据信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

}
