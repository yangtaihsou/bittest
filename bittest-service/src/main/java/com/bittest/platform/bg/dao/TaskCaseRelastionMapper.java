package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.TaskCaseRelastion;

/**
 * 任务用例关联关系表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface TaskCaseRelastionMapper extends BaseMapper<TaskCaseRelastion> {

    /**
     * 根据任务信息 删除所有该任务与用例关联关系
     *
     * @param taskCaseRelastion
     * @return
     */
    int deleteByTask(TaskCaseRelastion taskCaseRelastion);

    /**
     * 根据caseId 查询用例在任务计划中使用数量
     *
     * @param taskCaseRelastion
     * @return
     */
    int queryCaseUseTotal(TaskCaseRelastion taskCaseRelastion);

}
