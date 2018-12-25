package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import com.bittest.platform.bg.export.vo.InterfaceHistoryResVo;

/**
 * 2018-08-23.
 */
public interface InterfaceHistoryService {

    /**
     * 保存接口执行记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    public BasicResult saveInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo);

    /**
     * 查询个人执行接口记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    public ListResult<InterfaceHistoryResVo> queryInterfaceHistoryList(InterfaceHistoryReqVo interfaceHistoryReqVo);

    /**
     * 查询记录详情
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    public GenericResult<InterfaceHistoryResVo> queryInterfaceHistoryDetail(InterfaceHistoryReqVo interfaceHistoryReqVo);

    /**
     * 删除接口信息执行记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    public BasicResult deleteInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo);

}
