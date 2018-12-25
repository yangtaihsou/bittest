package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.Task;
import com.bittest.platform.bg.domain.po.TaskCaseRelastion;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.export.vo.TaskResVo;
import com.bittest.platform.bg.manager.TaskCaseRelastionManager;
import com.bittest.platform.bg.manager.TaskManager;
import com.bittest.platform.bg.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2018-08-24.
 */
@Service("taskService")
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);
    @Autowired
    private TaskManager taskManager;
    @Autowired
    private TaskCaseRelastionManager taskCaseRelastionManager;

    @Override
    public GenericResult<TaskResVo> save(TaskReqVo taskReqVo) {
        GenericResult<TaskResVo> result = new GenericResult<TaskResVo>();
        TaskResVo resVo = new TaskResVo();
        Task task = JSON.parseObject(JSON.toJSONString(taskReqVo), Task.class);
        task.setTaskParam(JSON.toJSONString(taskReqVo.getTaskParamMap()));
        try {
            resVo.setTaskId(taskReqVo.getTaskId());
            result.setValue(resVo);
            taskManager.save(task);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("测试任务保存异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    @Override
    public BasicResult delete(TaskReqVo taskReqVo) {
        BasicResult basicResult = new BasicResult();
        try {
            Task task = JSON.parseObject(JSON.toJSONString(taskReqVo), Task.class);
            TaskCaseRelastion relastion = new TaskCaseRelastion();
            relastion.setTaskId(taskReqVo.getTaskId());
            taskCaseRelastionManager.deleteByTask(relastion);
            taskManager.delete(task);
            basicResult.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            basicResult.setInfo(ResultInfoEnum.ERROR);
            log.error("删除测试任务异常：{}", e.toString());
            throw new Exception();
        } finally {
            return basicResult;
        }
    }

    /**
     * 分页查询测试任务
     *
     * @param query 查询参数
     * @return 测试任务分页查询结果
     */
    @Override
    public PaginationResult<TaskResVo> queryTaskList(PaginationQuery<TaskReqVo> query) {
        PaginationResult<TaskResVo> result = new PaginationResult<TaskResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            Task task = JSON.parseObject(JSON.toJSONString(query.getQuery()), Task.class);
            task.setPageSize(pagination.getPageSize());
            task.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = taskManager.queryTotal(task);
            List<TaskResVo> resTask = new ArrayList<TaskResVo>();
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<Task> taskList = taskManager.queryList(task);
                for (Task t : taskList) {
                    TaskResVo resVo = JSON.parseObject(JSON.toJSONString(t), TaskResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }
            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询任务列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<TaskResVo> queryTask(TaskReqVo taskReqVo) {
        GenericResult<TaskResVo> result = new GenericResult<TaskResVo>();
        try {
            Task task = JSON.parseObject(JSON.toJSONString(taskReqVo), Task.class);
            Task res = taskManager.queryObject(task);
            if (null != res) {
                TaskResVo taskResVo = JSON.parseObject(JSON.toJSONString(res), TaskResVo.class);
                taskResVo.setTaskParamMap(JSON.parseObject(res.getTaskParam(), HashMap.class));
                result.setValue(taskResVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.info("查询测试任务不存在:{}", JSON.toJSONString(taskReqVo));
                result.setInfo(ResultInfoEnum.ERROR);
            }
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询测试任务详细信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult updateTask(TaskReqVo taskReqVo) {
        BasicResult result = new BasicResult();
        try {
            Task task = JSON.parseObject(JSON.toJSONString(taskReqVo), Task.class);
            task.setTaskParam(JSON.toJSONString(taskReqVo.getTaskParamMap()));
            taskManager.update(task);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.info("修改任务信息异常,{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }
}
