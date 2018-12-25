package com.bittest.platform.bg.export.resource;


import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInfoReqVo;
import com.bittest.platform.bg.export.vo.CaseInfoResVo;
import com.bittest.platform.bg.export.vo.TaskCaseRelastionReqVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/case")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface CaseResource {
    /**
     * 根据case编号 查询case详细信息
     *
     * @param caseInfoReqVo
     * @return
     */
    @POST
    @Path("/queryCaseInfo")
    public GenericResult<CaseInfoResVo> queryCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 保存用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    @POST
    @Path("/saveCaseInfo")
    public GenericResult<CaseInfoResVo> saveCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 修改用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    @POST
    @Path("/updateCaseInfo")
    public BasicResult updateCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 删除用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    @POST
    @Path("/deleteCaseInfo")
    public BasicResult deleteCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 分页查询用例信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryCaseInfoPage")
    public PaginationResult<CaseInfoResVo> queryCaseInfoPage(PaginationQuery<CaseInfoReqVo> query);


    /**
     * 根据任务编号 分页查询用例信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryCaseInfoPageByTask")
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageByTask(PaginationQuery<CaseInfoReqVo> query);

    /**
     * 查询用户未关联任务的用例
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryCaseInfoPageNoFetch")
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageNoFetch(PaginationQuery<CaseInfoReqVo> query);


    /**
     * 添加关联用例
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    @POST
    @Path("AddFetchCase")
    public BasicResult AddFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 启用禁用关联信息
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    @POST
    @Path("updateFetchCase")
    public BasicResult updateFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 删除关联信息
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    @POST
    @Path("deleteFetchCase")
    public BasicResult deleteFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 根据用例名称查询用例
     *
     * @param caseInfoReqVo
     * @return
     */
    @POST
    @Path("queryCaseByName")
    public ListResult<CaseInfoResVo> queryCaseByName(CaseInfoReqVo caseInfoReqVo);


}
