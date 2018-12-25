package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.CheckPointReqVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-23.
 */
@Path("/checkPoint")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface CheckPointResource {

    /**
     * 删除检查点
     *
     * @param checkPointReqVo
     * @return
     */
    @POST
    @Path("deleteCheckPoint")
    public BasicResult deleteCheckPoint(CheckPointReqVo checkPointReqVo);

}
