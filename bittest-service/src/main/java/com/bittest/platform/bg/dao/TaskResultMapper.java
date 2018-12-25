package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.TaskResult;

/**
 * 执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface TaskResultMapper extends BaseMapper<TaskResult> {

    int deleteByTask(TaskResult result);

}
