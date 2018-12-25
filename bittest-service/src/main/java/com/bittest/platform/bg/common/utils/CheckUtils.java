package com.bittest.platform.bg.common.utils;

import com.bittest.platform.bg.common.enums.StatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2018-08-22.
 */
public class CheckUtils {

    private static final Logger log = LoggerFactory.getLogger(CheckUtils.class);

    /**
     * 检查点验证
     *
     * @param checkValue 检查值
     * @param info       检查内容
     * @param checkType  检查点类型(1、包含 2、不包含 3、等于)
     * @return
     */
    public static boolean checkData(String checkValue, String info, int checkType) {
        boolean result = false;
        try {
            if (checkType == StatusEnum.CheckMethod_Status.CHECK_CONSTAINS.status()) {
                if (info.indexOf(checkValue) != -1) {
                    result = true;
                }
            } else if (checkType == StatusEnum.CheckMethod_Status.CHECK_EQUAL.status()) {
                if (info.equals(checkValue)) {
                    result = true;
                }
            } else if (checkType == StatusEnum.CheckMethod_Status.CHECK_NOT_CONSTAINS.status()) {
                if (info.indexOf(checkValue) == -1) {
                    result = true;
                }
            } else {
                log.error("不存在该检查类型");
            }
        } catch (Exception e) {
            log.error("检查异常,", e.toString());
            result = false;
        } finally {
            return result;
        }
    }
}
