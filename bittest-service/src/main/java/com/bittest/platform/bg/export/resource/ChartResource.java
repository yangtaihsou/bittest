package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.ChartReqVo;
import com.bittest.platform.bg.export.vo.ChartResVo;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import com.bittest.platform.bg.export.vo.CountInfoResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-27.
 */
@Path("/chart")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface ChartResource {

    /**
     * 首页报表(近十次任务计划执行结果)
     *
     * @param reqVo
     * @return
     */
    @POST
    @Path("/queryTaskChart")
    public GenericResult<ChartResVo> queryTaskChart(ChartReqVo reqVo);

    /**
     * 首页报表查询（系统维度，统计用力数量）
     *
     * @param reqVo
     * @return
     */
    @POST
    @Path("/querySystemChart")
    public GenericResult<ChartResVo> querySystemChart(ChartReqVo reqVo);

    /**
     * 分页查询用例统计信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryCountInfoByPage")
    public PaginationResult<CountInfoResVo> queryCountInfoByPage(PaginationQuery<CountInfoReqVo> query);


}
