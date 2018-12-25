package com.bittest.platform.pg.service;

import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;

/**
 * 2018-02-28.
 */
public interface HistoryService {

    /**
     * 异步保存接口执行记录
     *
     * @param reqVo
     */
    public void saveHistory(InterfaceHistoryReqVo reqVo);

}
