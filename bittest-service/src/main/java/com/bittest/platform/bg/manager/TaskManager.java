package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.Task;

import java.util.List;

/**
 * 2018-08-24.
 */
public interface TaskManager {

    public int save(Task task);

    public int delete(Object id);

    public int update(Task task);

    public Task queryObject(Task task);

    public List<Task> queryList(Task task);

    public int queryTotal(Task task);

}
