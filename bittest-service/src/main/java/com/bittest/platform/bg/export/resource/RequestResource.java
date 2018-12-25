package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.domain.po.CaseResult;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.bg.export.vo.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-27.
 * 接收jsf请求接口
 */
@Path("interface")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface RequestResource {
    /**
     * 接口请求
     *
     * @param interfaceReqVo
     * @return
     */
    @POST
    @Path("/interfaceReq")
    public GenericResult<RequestInterfaceResVo> interfaceReq(InterfaceReqVo interfaceReqVo);

    /**
     * 用例执行
     *
     * @param requestReqVo
     * @return
     */
    @POST
    @Path("/caseRequest")
    public GenericResult<CaseInfoResVo> interfaceReqByCase(RequestReqVo requestReqVo);


    /**
     * 用例维度执行接口请求
     *
     * @param requestReqVo
     * @return
     */
    @POST
    @Path("/runCase")
    public GenericResult<CaseResult> runCase(RequestReqVo requestReqVo);

    /**
     * 请求执行
     *
     * @param requestReqVo
     * @return
     */
    @POST
    @Path("/taskRequest")
    public BasicResult interfaceReqByTask(RequestReqVo requestReqVo);


    /**
     * http方式 解析获取jsf相关信息
     *
     * @param jsfQueryReqVo
     * @return
     */
    @POST
    @Path("/jsfQueryInfo")
    public GenericResult<JsfQueryResVo> jsfQueryInfo(JsfQueryReqVo jsfQueryReqVo);


    /**
     * 网关方式获取jsf 别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    @POST
    @Path("/queryAlias")
    public GenericResult<JsfInfoResVo> queryAlias(JsfInfoReqVo jsfInfoReqVo);


    /**
     * 网关方式获取jsf 别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    @POST
    @Path("/queryIps")
    public GenericResult<JsfInfoResVo> queryIps(JsfInfoReqVo jsfInfoReqVo);


    /**
     * 网关方式获取jsf 方法（入参、方法名、返回参数）相关信息
     *
     * @param jsfInfoReqVo
     * @return
     */
    @POST
    @Path("/queryMethods")
    public GenericResult<JsfInfoResVo> queryMethods(JsfInfoReqVo jsfInfoReqVo);


}
