package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.TaskCaseRelastionMapper;
import com.bittest.platform.bg.domain.po.TaskCaseRelastion;
import com.bittest.platform.bg.manager.TaskCaseRelastionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-08-22.
 */
@Service("taskCaseRelastionManager")
public class TaskCaseRelastionManagerImpl implements TaskCaseRelastionManager {

    @Autowired
    private TaskCaseRelastionMapper taskCaseRelastionMapper;

    @Override
    public int save(TaskCaseRelastion taskCaseRelastion) {
        return taskCaseRelastionMapper.save(taskCaseRelastion);
    }

    @Override
    public int delete(TaskCaseRelastion taskCaseRelastion) {
        return taskCaseRelastionMapper.delete(taskCaseRelastion);
    }

    @Override
    public int update(TaskCaseRelastion taskCaseRelastion) {
        return taskCaseRelastionMapper.update(taskCaseRelastion);
    }

    @Override
    public int deleteByTask(TaskCaseRelastion taskCaseRelastion) {
        return taskCaseRelastionMapper.deleteByTask(taskCaseRelastion);
    }

    @Override
    public int queryCaseUseTotal(TaskCaseRelastion taskCaseRelastion) {
        return taskCaseRelastionMapper.queryCaseUseTotal(taskCaseRelastion);
    }
}
