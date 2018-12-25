package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.TaskMapper;
import com.bittest.platform.bg.domain.po.Task;
import com.bittest.platform.bg.manager.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-24.
 */
@Service("taskManager")
public class TaskManagerImpl implements TaskManager {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public int save(Task task) {
        return taskMapper.save(task);
    }

    @Override
    public int delete(Object id) {
        return taskMapper.delete(id);
    }

    @Override
    public int update(Task task) {
        return taskMapper.update(task);
    }

    @Override
    public Task queryObject(Task task) {
        return taskMapper.queryObject(task);
    }

    @Override
    public List<Task> queryList(Task task) {
        return taskMapper.queryList(task);
    }

    @Override
    public int queryTotal(Task task) {
        return taskMapper.queryTotal(task);
    }
}
