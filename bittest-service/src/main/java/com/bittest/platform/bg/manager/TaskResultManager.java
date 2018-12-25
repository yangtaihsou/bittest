package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.TaskResult;

import java.util.List;

/**
 * 执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface TaskResultManager {
    int save(TaskResult result);

    int delete(TaskResult result);

    int deleteByTask(TaskResult result);

    int update(TaskResult result);

    TaskResult queryObject(TaskResult result);

    List<TaskResult> queryList(TaskResult result);

    int queryTotal(TaskResult result);
}
