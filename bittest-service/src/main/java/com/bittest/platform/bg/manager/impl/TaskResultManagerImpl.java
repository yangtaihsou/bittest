package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.TaskResultMapper;
import com.bittest.platform.bg.domain.po.TaskResult;
import com.bittest.platform.bg.manager.TaskResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-27.
 */
@Service("taskResultManager")
public class TaskResultManagerImpl implements TaskResultManager {

    @Autowired
    private TaskResultMapper taskResultMapper;

    @Override
    public int save(TaskResult result) {
        return taskResultMapper.save(result);
    }

    @Override
    public int delete(TaskResult result) {
        return taskResultMapper.delete(result);
    }

    @Override
    public int deleteByTask(TaskResult result) {
        return taskResultMapper.deleteByTask(result);
    }

    @Override
    public int update(TaskResult result) {
        return taskResultMapper.update(result);
    }

    @Override
    public TaskResult queryObject(TaskResult result) {
        return taskResultMapper.queryObject(result);
    }

    @Override
    public List<TaskResult> queryList(TaskResult result) {
        return taskResultMapper.queryList(result);
    }

    @Override
    public int queryTotal(TaskResult result) {
        return taskResultMapper.queryTotal(result);
    }
}
