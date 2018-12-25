package com.bittest.platform.pg.service.impl;

import com.bittest.platform.bg.export.resource.RequestResource;
import com.bittest.platform.bg.export.vo.RequestReqVo;

/**
 * 异步调用 任务计划执行
 * 2018-08-28.
 */
public class CaseRunThread extends Thread {

    private RequestResource requestResource;
    private RequestReqVo reqVo;

    public CaseRunThread(RequestReqVo reqVo, RequestResource requestResource) {
        this.reqVo = reqVo;
        this.requestResource = requestResource;
    }

    public void run() {
        requestResource.runCase(reqVo);
    }

}
