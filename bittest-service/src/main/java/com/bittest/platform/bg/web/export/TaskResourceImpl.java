package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.TaskResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.export.vo.TaskResVo;
import com.bittest.platform.bg.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-24.
 */
@Component("taskResource")
public class TaskResourceImpl implements TaskResource {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    @Autowired
    private TaskService taskService;

    @Override
    public GenericResult<TaskResVo> saveTask(TaskReqVo taskReqVo) {
        log.info("测试任务-执行保存开始:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(taskReqVo));
        GenericResult<TaskResVo> result = taskService.save(taskReqVo);
        log.info("测试任务-执行保存结束,结果为:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteTask(TaskReqVo taskReqVo) {
        log.info("测试任务-执行删除开始:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(taskReqVo));
        BasicResult result = taskService.delete(taskReqVo);
        log.info("测试任务-执行删除结束,结果为:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<TaskResVo> queryTaskList(PaginationQuery<TaskReqVo> query) {
        log.info("测试任务-分页查询开始:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(query));
        PaginationResult<TaskResVo> result = taskService.queryTaskList(query);
        log.info("测试任务-分页查询结束:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<TaskResVo> queryTask(TaskReqVo taskReqVo) {
        log.info("测试任务-查询详细信息开始:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(taskReqVo));
        GenericResult<TaskResVo> result = taskService.queryTask(taskReqVo);
        log.info("测试任务-查询详细信息结束:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateTask(TaskReqVo taskReqVo) {
        log.info("测试任务-修改信息开始:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(taskReqVo));
        BasicResult result = taskService.updateTask(taskReqVo);
        log.info("测试任务-修改信息结束:com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(result));
        return result;
    }
}
