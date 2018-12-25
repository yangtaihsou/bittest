package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.CaseInfo;

import java.util.List;

/**
 * 2018-08-26.
 */
public interface CaseInfoManager {
    /**
     * 新增用例
     *
     * @param caseInfo
     * @return
     */
    int save(CaseInfo caseInfo);

    /**
     * 根据用例编号 删除用例信息
     *
     * @return
     */
    int delete(String caseId);

    /**
     * 查询用例信息
     *
     * @param caseInfo
     * @return
     */
    CaseInfo queryObject(CaseInfo caseInfo);

    /**
     * 根据caseId修改案例信息
     *
     * @param caseInfo
     * @return
     */
    int update(CaseInfo caseInfo);

    /**
     * 根据任务编号 分页查询用例列表
     *
     * @param caseInfo
     * @return
     */
    List<CaseInfo> queryCaseListByTask(CaseInfo caseInfo);

    /**
     * 根据任务编号 分页查询用例列表数量
     *
     * @param caseInfo
     * @return
     */
    int queryCaseTotalByTask(CaseInfo caseInfo);

    /**
     * 分页查询用例列表
     *
     * @param caseInfo
     * @return
     */
    List<CaseInfo> queryList(CaseInfo caseInfo);

    /**
     * 分页查询用例列表条数
     *
     * @param caseInfo
     * @return
     */
    int queryTotal(CaseInfo caseInfo);

    /**
     * 查询任务未关联用例列表
     *
     * @param caseInfo
     * @return
     */
    List<CaseInfo> queryCaseInfoPageNoFetch(CaseInfo caseInfo);

    /**
     * 查询任务未关联用例条数
     *
     * @param caseInfo
     * @return
     */
    int queryCaseInfoPageNoFetchTotal(CaseInfo caseInfo);

    /**
     * 根据系统编号 查询用例数量
     *
     * @param caseInfo
     * @return
     */
    int queryCaseBySystemTotal(CaseInfo caseInfo);

    /**
     * 根据系统维度，统计用例数量
     *
     * @param caseInfo
     * @return
     */
    List<CaseInfo> queryCaseChart(CaseInfo caseInfo);

    /**
     * 根据用例名称查询用例列表
     *
     * @param caseInfo
     * @return
     */
    List<CaseInfo> queryCaseByName(CaseInfo caseInfo);

    /**
     * 查询用例总数
     *
     * @return
     */
    int queryCaseTotal();

}
