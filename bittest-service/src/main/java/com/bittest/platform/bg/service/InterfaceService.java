package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInterfaceRelastionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;


/**
 * 2018-08-30.
 */
public interface InterfaceService {

    /**
     * 接口信息保存
     *
     * @param interfaceReqVo
     * @return
     */
    BasicResult saveInterface(InterfaceReqVo interfaceReqVo);

    /**
     * 接口信息编辑
     *
     * @param InterfaceReqVo
     * @return
     */
    BasicResult updateInterface(InterfaceReqVo InterfaceReqVo);

    /**
     * 分页查询接口信息信息
     *
     * @param query
     * @return
     */
    PaginationResult<InterfaceResVo> queryInterfacePage(PaginationQuery<InterfaceReqVo> query);

    /**
     * 查询接口详细信息
     *
     * @param interfaceReqVo
     * @return
     */
    GenericResult<InterfaceResVo> queryInterfaceDetail(InterfaceReqVo interfaceReqVo);

    /**
     * 根据caseId查询接口列表
     *
     * @param interfaceReqVo
     * @return
     */
    ListResult<InterfaceResVo> queryInterfaceByCase(InterfaceReqVo interfaceReqVo);

    /**
     * 启用 禁用接口
     *
     * @param caseInterfaceRelastionReqVo
     * @return
     */
    BasicResult updateCaseInterfaceRelation(CaseInterfaceRelastionReqVo caseInterfaceRelastionReqVo);

    /**
     * 分页查询用例接口列表
     *
     * @param query
     * @return
     */
    PaginationResult<InterfaceResVo> queryAllInterfaceList(PaginationQuery<InterfaceReqVo> query);

    /**
     * 添加已有接口信息（将已有接口 查询重新保存，并添加用例接口关联关系）
     *
     * @param interfaceReqVo
     * @return
     */
    BasicResult addConstainsInterface(InterfaceReqVo interfaceReqVo);


    /**
     * 删除接口信息（如果存在caseId 删除关联关系）
     *
     * @param interfaceReqVo
     * @return
     */
    BasicResult deleteInterfaceInfo(InterfaceReqVo interfaceReqVo);

    /**
     * 接口保存到用例（保存接口及添加用例关联）
     *
     * @param interfaceReqVo
     * @return
     */
    BasicResult interfaceSaveCase(InterfaceReqVo interfaceReqVo);

}
