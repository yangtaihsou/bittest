package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.CaseInfo;

import java.util.List;

/**
 * 用例表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface CaseInfoMapper extends BaseMapper<CaseInfo> {

    List<CaseInfo> queryCaseListByTask(CaseInfo caseInfo);

    int queryCaseTotalByTask(CaseInfo caseInfo);

    List<CaseInfo> queryCaseInfoPageNoFetch(CaseInfo caseInfo);

    int queryCaseInfoPageNoFetchTotal(CaseInfo caseInfo);

    int queryCaseBySystemTotal(CaseInfo caseInfo);

    List<CaseInfo> queryCaseChart(CaseInfo caseInfo);

    List<CaseInfo> queryCaseByName(CaseInfo caseInfo);

    int queryCaseTotal();

}
