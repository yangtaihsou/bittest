package com.bittest.platform.bg.export.resource;


import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.export.vo.SystemResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/system")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface SystemResource {
    /**
     * 根据系统编号 查询系统详细信息
     *
     * @param systemReqVo
     * @return
     */
    @POST
    @Path("/querySystem")
    public GenericResult<SystemResVo> querySystem(SystemReqVo systemReqVo);

    /**
     * 保存系统信息
     *
     * @param systemReqVo
     * @return
     */
    @POST
    @Path("/saveSystem")
    public BasicResult saveSystem(SystemReqVo systemReqVo);

    /**
     * 修改系统信息
     *
     * @param systemReqVo
     * @return
     */
    @POST
    @Path("/updateSystem")
    public BasicResult updateSystem(SystemReqVo systemReqVo);


    /**
     * 分页查询系统信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("querySystemPage")
    public PaginationResult<SystemResVo> querySystemPage(PaginationQuery<SystemReqVo> query);

    /**
     * 查询用户所有系统信息
     *
     * @param systemReqVo
     * @return
     */
    @POST
    @Path("/querySystemList")
    public ListResult<SystemResVo> querySystemList(SystemReqVo systemReqVo);

    /**
     * 删除系统信息
     *
     * @param systemReqVo
     * @return
     */
    @POST
    @Path("/deleteSystem")
    public BasicResult deleteSystem(SystemReqVo systemReqVo);

}
