package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.DataFetchReqVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-23.
 */
@Path("/dataFetch")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface DataFetchResource {

    @POST
    @Path("deleteDataFetch")
    public BasicResult deleteDataFetch(DataFetchReqVo dataFetchReqVo);
}
