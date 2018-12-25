package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.CaseResult;

/**
 * 用例执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface CaseResultMapper extends BaseMapper<CaseResult> {

    int deleteByTask(CaseResult result);

}
