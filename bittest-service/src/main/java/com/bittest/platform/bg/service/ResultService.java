package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseResultResVo;
import com.bittest.platform.bg.export.vo.InterfaceResultResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;

/**
 * 2018-08-27.
 */
public interface ResultService {

    /**
     * 任务计划结果分页查询
     *
     * @param query
     * @return
     */
    PaginationResult<TaskResultResVo> queryTaskResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据执行结果编码 分页查询用例执行结果列表
     *
     * @param query
     * @return
     */
    PaginationResult<CaseResultResVo> queryCaseResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据结果编码及用例编码 分页查询结果执行结果列表
     *
     * @param query
     * @return
     */
    PaginationResult<InterfaceResultResVo> queryInterfaceResultPage(PaginationQuery<ResultReqVo> query);

    /**
     * 根据执行结果编码 删除执行结果信息
     *
     * @param resultReqVo
     * @return
     */
    BasicResult deleteResultById(ResultReqVo resultReqVo);

    /**
     * 根据任务计划信息，清空该任务计划所有执行结果
     *
     * @param resultReqVo
     * @return
     */
    BasicResult deleteResultByTask(ResultReqVo resultReqVo);

    /**
     * 根据结果编号 查询任务计划结果详情
     *
     * @param resultReqVo
     * @return
     */
    GenericResult<TaskResultResVo> queryTaskResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据结果编号及用例编号 查询用例结果详情
     *
     * @param resultReqVo
     * @return
     */
    GenericResult<CaseResultResVo> queryCaseResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据任务计划、用例及接口编号 查询接口结果详情
     *
     * @param resultReqVo
     * @return
     */
    GenericResult<InterfaceResultResVo> queryInterfaceResultDetail(ResultReqVo resultReqVo);

    /**
     * 根据结果编号 查询出所有用例执行结果
     *
     * @param resultReqVo
     * @return
     */
    ListResult<CaseResultResVo> queryCaseResultList(ResultReqVo resultReqVo);

}
