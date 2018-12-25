package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.export.vo.TaskResVo;

/**
 * 2018-08-24.
 */
public interface TaskService {

    /**
     * 测试任务保存
     *
     * @param taskReqVo
     * @return
     */
    GenericResult<TaskResVo> save(TaskReqVo taskReqVo);

    /**
     * 删除测试任务
     *
     * @param taskReqVo
     * @return
     */
    BasicResult delete(TaskReqVo taskReqVo);

    /**
     * 分页查询测试任务
     *
     * @param query
     * @return
     */
    PaginationResult<TaskResVo> queryTaskList(PaginationQuery<TaskReqVo> query);


    /**
     * 查询测试任务详细信息
     *
     * @param taskReqVo
     * @return
     */
    GenericResult<TaskResVo> queryTask(TaskReqVo taskReqVo);

    /**
     * 测试任务修改
     *
     * @param taskReqVo
     * @return
     */
    BasicResult updateTask(TaskReqVo taskReqVo);

}
