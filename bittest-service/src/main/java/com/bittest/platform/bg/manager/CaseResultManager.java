package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.CaseResult;

import java.util.List;

/**
 * 用例执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface CaseResultManager {
    int save(CaseResult result);

    int delete(CaseResult result);

    int deleteByTask(CaseResult result);

    CaseResult queryObject(CaseResult result);

    List<CaseResult> queryList(CaseResult result);

    int queryTotal(CaseResult result);
}
