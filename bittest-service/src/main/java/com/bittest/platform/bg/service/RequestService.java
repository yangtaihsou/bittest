package com.bittest.platform.bg.service;

import com.bittest.platform.bg.domain.po.CaseResult;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.bg.export.vo.*;

import java.util.Map;

/**
 * 2018-08-27.
 */
public interface RequestService {
    /**
     * 处理jsf接口请求
     *
     * @param interfaceReqVo
     * @return 请求结果
     */
    GenericResult<RequestInterfaceResVo> interfaceReq(InterfaceReqVo interfaceReqVo, Map<String, String> caseParam, Map<String, String> taskParam);

    /**
     * 处理http接口请求
     *
     * @param requestReqVo
     * @return 请求结果
     */
    BasicResult interfaceReqByTask(RequestReqVo requestReqVo);

    /**
     * 根据resource信息解析jsf接口信息
     *
     * @param jsfQueryReqVo
     * @return
     */
    GenericResult<JsfQueryResVo> jsfQueryInfo(JsfQueryReqVo jsfQueryReqVo);

    /**
     * 用例维度执行接口请求
     *
     * @param requestReqVo
     * @return
     */
    GenericResult<CaseInfoResVo> interfaceReqByCase(RequestReqVo requestReqVo);


    /**
     * 用例维度执行接口请求
     *
     * @param requestReqVo
     * @return
     */
    public GenericResult<CaseResult> runCase(RequestReqVo requestReqVo);
    /**
     * 网关方式获取jsf 别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    public GenericResult<JsfInfoResVo> queryAlias(JsfInfoReqVo jsfInfoReqVo);

    /**
     * 网关方式获取jsf ip:port
     *
     * @param jsfInfoReqVo
     * @return
     */
    public GenericResult<JsfInfoResVo> queryIps(JsfInfoReqVo jsfInfoReqVo);

    /**
     * 网关方式获取jsf 方法（入参、方法名、返回参数）相关信息
     *
     * @param jsfInfoReqVo
     * @return
     */
    public GenericResult<JsfInfoResVo> queryMethods(JsfInfoReqVo jsfInfoReqVo);
}
