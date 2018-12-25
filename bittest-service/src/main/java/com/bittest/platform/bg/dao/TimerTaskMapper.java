package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.TimerTask;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.Query;

import java.util.List;
import java.util.Map;

public interface TimerTaskMapper extends BaseMapper<TimerTask>{

    public List<TimerTask> findAll();

    public long findCount();

    public List<TimerTask> queryBySelective(Query<TimerTask> task);

    public long queryCountBySelective(Query<TimerTask> task);

    public TimerTask queryByPrimaryKey(Long id);

    public int deleteByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(TimerTask task);


    public List<TimerTask> findTaskByMapParm(Map map);

    public List<TimerTask> queryBySelectiveForPagination(PaginationQuery<TimerTask> task);

    public long queryCountBySelectiveForPagination(PaginationQuery<TimerTask> task);

    public int deleteByUniqueIndexuuid(TimerTask task);


    public int finishTimerTask(TimerTask task);


    public int lockTimerTask(TimerTask task);

    public List<TimerTask> selectLockedTimerTask(TimerTask task);

    public List<TimerTask> findByTimerTaskType(TimerTask task);


    public List<TimerTask> findTimerTaskByMapParm(Map map);


    public List<TimerTask> findFromDate(TimerTask task);

    public int unLockTimerTask(TimerTask object);

    public int resetTimerTaskById(TimerTask task);


    public int resetTimerTaskByQuery(PaginationQuery<TimerTask> task);

    public int updateResultStatus(TimerTask task);



    public int finishTask(TimerTask task);

    public int lockTask(TimerTask task);
    /**
     * 更新异常信息
     *
     * @param task
     * @return
     * @throws Exception
     */
    public int updateErrorMsg(TimerTask task);

    /**
     * 删除已经完成的任务
     *
     * @return
     */
    public int deleteFinishedTimerTask(Long start, Long end);

    /**
     * 批量锁定任务
     *
     * @param start end
     * @return
     * @throws Exception
     */
    public Integer batchLock(Long start, Long end);
}