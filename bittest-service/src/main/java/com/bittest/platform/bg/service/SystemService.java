package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.export.vo.SystemResVo;

/**
 * 2018-08-25.
 */
public interface SystemService {
    /**
     * 根据系统编号 查询系统详细信息
     *
     * @param systemReqVo
     * @return
     */
    GenericResult<SystemResVo> querySystem(SystemReqVo systemReqVo);

    /**
     * 保存系统信息
     *
     * @param systemReqVo
     * @return
     */
    BasicResult saveSystem(SystemReqVo systemReqVo);

    /**
     * 修改系统信息
     *
     * @param systemReqVo
     * @return
     */
    BasicResult updateSystem(SystemReqVo systemReqVo);


    /**
     * 分页查询系统信息
     *
     * @param query
     * @return
     */
    PaginationResult<SystemResVo> querySystemPage(PaginationQuery<SystemReqVo> query);


    /**
     * 查询用户所有系统信息
     *
     * @param systemReqVo
     * @return
     */
    ListResult<SystemResVo> querySystemList(SystemReqVo systemReqVo);

    /**
     * 删除系统信息
     *
     * @param systemReqVo
     * @return
     */
    BasicResult deleteSystem(SystemReqVo systemReqVo);
}
