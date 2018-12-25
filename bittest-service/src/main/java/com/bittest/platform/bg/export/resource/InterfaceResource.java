package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInterfaceRelastionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-29.
 */
@Path("interface")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface InterfaceResource {

    /**
     * 接口信息保存
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("/saveInterface")
    public BasicResult saveInterface(InterfaceReqVo interfaceReqVo);

    /**
     * 接口信息编辑
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("/updateInterface")
    public BasicResult updateInterface(InterfaceReqVo interfaceReqVo);

    /**
     * 分页查询接口信息信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryInterfacePage")
    public PaginationResult<InterfaceResVo> queryInterfacePage(PaginationQuery<InterfaceReqVo> query);

    /**
     * 查询接口详细信息
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceDetail")
    public GenericResult<InterfaceResVo> queryInterfaceDetail(InterfaceReqVo interfaceReqVo);

    /**
     * 根据用例编号 查询接口列表
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceByCase")
    ListResult<InterfaceResVo> queryInterfaceByCase(InterfaceReqVo interfaceReqVo);

    /**
     * 分页查询用例接口列表
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryAllInterfaceList")
    PaginationResult<InterfaceResVo> queryAllInterfaceList(PaginationQuery<InterfaceReqVo> query);

    /**
     * 启用 禁用接口
     *
     * @param caseInterfaceRelastionReqVo
     * @return
     */
    @POST
    @Path("updateCaseInterfaceRelation")
    BasicResult updateCaseInterfaceRelation(CaseInterfaceRelastionReqVo caseInterfaceRelastionReqVo);


    /**
     * 添加已有接口信息（将已有接口 查询重新保存，并添加用例接口关联关系）
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("addConstainsInterface")
    BasicResult addConstainsInterface(InterfaceReqVo interfaceReqVo);


    /**
     * 删除接口信息（如果存在caseId 删除关联关系）
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("deleteInterfaceInfo")
    BasicResult deleteInterfaceInfo(InterfaceReqVo interfaceReqVo);

    /**
     * 接口保存到用例
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("interfaceSaveCase")
    BasicResult interfaceSaveCase(InterfaceReqVo interfaceReqVo);
}
