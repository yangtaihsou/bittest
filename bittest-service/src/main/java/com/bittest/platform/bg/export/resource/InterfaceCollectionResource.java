package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceCollectionResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-29.
 */
@Path("interfaceCollection")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface InterfaceCollectionResource {

    /**
     * 收藏接口
     *
     * @param interfaceCollectionReqVo
     * @return
     */
    @POST
    @Path("/saveInterfaceCollection")
    public GenericResult<InterfaceCollectionResVo> saveInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo);

    /**
     * 查询收藏接口列表
     *
     * @param interfaceCollectionReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceCollectionList")
    public ListResult<InterfaceCollectionResVo> queryInterfaceCollectionList(InterfaceCollectionReqVo interfaceCollectionReqVo);

    /**
     * 查询收藏接口详情
     *
     * @param interfaceCollectionReqVo
     * @return
     */
    @POST
    @Path("queryInterfaceCollectionDetail")
    public GenericResult<InterfaceCollectionResVo> queryInterfaceCollectionDetail(InterfaceCollectionReqVo interfaceCollectionReqVo);


    /**
     * 删除收藏接口
     *
     * @param interfaceCollectionReqVo
     * @return
     */
    @POST
    @Path("deleteInterfaceCollection")
    BasicResult deleteInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo);
}
