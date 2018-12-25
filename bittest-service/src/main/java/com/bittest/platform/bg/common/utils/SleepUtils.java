package com.bittest.platform.bg.common.utils;

import com.bittest.platform.bg.domain.po.InterfaceInfo;
import com.bittest.platform.bg.export.vo.RequestInterfaceResVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * sleep 等待工具（可扩展支持参数化  暂不设定）
 */
public class SleepUtils {

    private static final Logger log = LoggerFactory.getLogger(SleepUtils.class);

    public static RequestInterfaceResVo sleep(InterfaceInfo interfaceInfo) {
        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        resVo.setReqBody(interfaceInfo.getBody());
        try {
            Thread.sleep(Integer.parseInt(interfaceInfo.getBody()));
            resVo.setResStatus(200);
        } catch (InterruptedException e) {
            resVo.setResBody(e.toString());
            resVo.setResStatus(500);
            log.error("sleep error", e.toString());
        } finally {
            return resVo;
        }
    }

}
