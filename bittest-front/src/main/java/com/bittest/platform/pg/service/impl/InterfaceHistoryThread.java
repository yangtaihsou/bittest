package com.bittest.platform.pg.service.impl;

import com.bittest.platform.bg.export.resource.InterfaceHistoryResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 2018-02-26.
 */
public class InterfaceHistoryThread extends Thread {

    private static final Logger log = LoggerFactory.getLogger(InterfaceHistoryThread.class);
    private InterfaceHistoryResource resource;
    private InterfaceHistoryReqVo reqVo;

    public InterfaceHistoryThread(InterfaceHistoryReqVo reqVo, InterfaceHistoryResource resource) {
        this.reqVo = reqVo;
        this.resource = resource;
    }

    public void run() {
        try {
            BasicResult resVo = resource.saveInterfaceHistory(reqVo);
            if (!resVo.getCode().equals("000000")) {
                log.error("保存接口执行记录失败：{}");
            }
        } catch (Exception e) {
            log.error("调用保存接口执行记录接口异常：{}", e.toString());
        }

    }
}
