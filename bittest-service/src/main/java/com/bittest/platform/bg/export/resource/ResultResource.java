package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseResultResVo;
import com.bittest.platform.bg.export.vo.InterfaceResultResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-27.
 */
@Path("/result")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface ResultResource {

    /**
     * 任务计划结果分页查询
     *
     * @param query
     * @return
     */
    @POST
    @Path("/queryTaskResultPage")
    public PaginationResult<TaskResultResVo> queryTaskResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据执行结果编码 分页查询用例执行结果列表
     *
     * @param query
     * @return
     */
    @POST
    @Path("/queryCaseResultPage")
    public PaginationResult<CaseResultResVo> queryCaseResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据结果编码及用例编码 分页查询结果执行结果列表
     *
     * @param query
     * @return
     */
    @POST
    @Path("/queryInterfaceResultPage")
    public PaginationResult<InterfaceResultResVo> queryInterfaceResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据执行结果编码 删除执行结果信息
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/deleteResultById")
    public BasicResult deleteResultById(ResultReqVo resultReqVo);

    /**
     * 根据任务计划信息，清空该任务计划所有执行结果
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/deleteResultByTask")
    public BasicResult deleteResultByTask(ResultReqVo resultReqVo);


    /**
     * 根据结果编号 查询任务计划结果详情
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/queryTaskResultDetail")
    GenericResult<TaskResultResVo> queryTaskResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据结果编号及用例编号 查询用例结果详情
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/queryCaseResultDetail")
    GenericResult<CaseResultResVo> queryCaseResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据任务计划、用例及接口编号 查询接口结果详情
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/queryInterfaceResultDetail")
    GenericResult<InterfaceResultResVo> queryInterfaceResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据结果编号 查询出所有用例执行结果
     *
     * @param resultReqVo
     * @return
     */
    @POST
    @Path("/queryCaseResultList")
    ListResult<CaseResultResVo> queryCaseResultList(ResultReqVo resultReqVo);

}
