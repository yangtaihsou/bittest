package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import com.bittest.platform.bg.export.vo.InterfaceHistoryResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-29.
 */
@Path("interfaceHistory")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface InterfaceHistoryResource {

    /**
     * 保存接口执行记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    @POST
    @Path("/saveInterfaceHistory")
    public BasicResult saveInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo);

    /**
     * 查询个人执行接口记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceHistoryList")
    public ListResult<InterfaceHistoryResVo> queryInterfaceHistoryList(InterfaceHistoryReqVo interfaceHistoryReqVo);

    /**
     * 查询记录详情
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceHistoryDetail")
    public GenericResult<InterfaceHistoryResVo> queryInterfaceHistoryDetail(InterfaceHistoryReqVo interfaceHistoryReqVo);


    /**
     * 删除接口信息执行记录
     *
     * @param interfaceHistoryReqVo
     * @return
     */
    @POST
    @Path("deleteInterfaceHistory")
    BasicResult deleteInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo);
}
