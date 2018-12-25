package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.TaskCaseRelastion;

/**
 * 2018-08-22.
 */
public interface TaskCaseRelastionManager {

    /**
     * 保存关联用例
     *
     * @param taskCaseRelastion
     * @return
     */
    int save(TaskCaseRelastion taskCaseRelastion);

    /**
     * 删除关联用例
     *
     * @param taskCaseRelastion
     * @return
     */
    int delete(TaskCaseRelastion taskCaseRelastion);

    /**
     * 编辑关联用例启用禁用状态
     *
     * @param taskCaseRelastion
     * @return
     */
    int update(TaskCaseRelastion taskCaseRelastion);

    /**
     * 根据任务计划编号 taskId 删除所有用例关联关系
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
