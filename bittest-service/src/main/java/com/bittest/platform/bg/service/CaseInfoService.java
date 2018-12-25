package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInfoReqVo;
import com.bittest.platform.bg.export.vo.CaseInfoResVo;
import com.bittest.platform.bg.export.vo.TaskCaseRelastionReqVo;


/**
 * 2018-08-26.
 */
public interface CaseInfoService {
    /**
     * 根据case编号 查询case详细信息
     *
     * @param caseInfoReqVo
     * @return
     */
    GenericResult<CaseInfoResVo> queryCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 保存用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    GenericResult<CaseInfoResVo> saveCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 修改用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    BasicResult updateCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 删除用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    BasicResult deleteCaseInfo(CaseInfoReqVo caseInfoReqVo);

    /**
     * 分页查询用例信息
     *
     * @param query
     * @return
     */
    PaginationResult<CaseInfoResVo> queryCaseInfoPage(PaginationQuery<CaseInfoReqVo> query);


    /**
     * 根据任务编号 分页查询用例信息
     *
     * @param query
     * @return
     */
    PaginationResult<CaseInfoResVo> queryCaseInfoPageByTask(PaginationQuery<CaseInfoReqVo> query);

    /**
     * 查询用户未关联任务的用例
     *
     * @param query
     * @return
     */
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageNoFetch(PaginationQuery<CaseInfoReqVo> query);

    /**
     * 添加关联用例
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    public BasicResult AddFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 启用禁用关联信息
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    public BasicResult updateFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 删除关联信息
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    public BasicResult deleteFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo);

    /**
     * 根据用例名称查询用例
     *
     * @param caseInfoReqVo
     * @return
     */
    public ListResult<CaseInfoResVo> queryCaseByName(CaseInfoReqVo caseInfoReqVo);
}
