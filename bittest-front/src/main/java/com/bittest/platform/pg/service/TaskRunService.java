package com.bittest.platform.pg.service;

import com.bittest.platform.bg.export.vo.RequestReqVo;

/**
 * 2018-08-28.
 */
public interface TaskRunService {

    /**
     * 异步执行测试计划
     *
     * @param reqVo
     * @return
     */
    public boolean taskRun(RequestReqVo reqVo);

}
