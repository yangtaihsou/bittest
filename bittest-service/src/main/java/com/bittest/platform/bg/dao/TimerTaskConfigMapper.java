package com.bittest.platform.bg.dao;



import com.bittest.platform.bg.domain.po.TimerTaskConfig;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.Query;

import java.util.List;

public interface TimerTaskConfigMapper extends BaseMapper<TimerTaskConfig>{

    public List<TimerTaskConfig> findAll();

    public long findCount();

    public List<TimerTaskConfig> queryBySelective(Query<TimerTaskConfig> timerTaskConfig);

    public long queryCountBySelective(Query<TimerTaskConfig> timerTaskConfig);

    public TimerTaskConfig queryByPrimaryKey(Long id);

    public int deleteByPrimaryKey(Long id);

    public int updateByPrimaryKeySelective(TimerTaskConfig timerTaskConfig);


    public int update(TimerTaskConfig timerTaskConfig);


    public List<TimerTaskConfig> queryBySelectiveForPagination(PaginationQuery<TimerTaskConfig> timerTaskConfig);

    public long queryCountBySelectiveForPagination(PaginationQuery<TimerTaskConfig> timerTaskConfig);

    public int deleteByUniqueIndextaskTimerType(TimerTaskConfig timerTaskConfig);

    public int deleteByUniqueIndexsubTaskType(TimerTaskConfig timerTaskConfig);

    public int deleteByUniqueIndexsubTaskKey(TimerTaskConfig timerTaskConfig);

    public int deleteByUniqueIndextaskTimerKey(TimerTaskConfig timerTaskConfig);


    public List<TimerTaskConfig> findByBizTime(TimerTaskConfig timerTaskConfig);
}