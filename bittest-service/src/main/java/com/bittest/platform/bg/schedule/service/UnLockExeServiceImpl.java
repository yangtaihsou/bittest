package com.bittest.platform.bg.schedule.service;

import com.bittest.platform.bg.dao.TimerTaskMapper;
import com.bittest.platform.bg.domain.po.TimerTask;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 任务调度--定期重置被锁定的任务
 *
 * @author yangkuan
 */
@Service("unLockExeService")
public class UnLockExeServiceImpl implements TaskExcuteService {

    private static final Log log = LogFactory.getLog(UnLockExeServiceImpl.class);

    @Resource(name = "timerTaskMapper")
    protected TimerTaskMapper timerTaskMapper;
    @Override
    public void exe() {
        try {
            Date updateTime = new Date();
            updateTime = new Date(updateTime.getTime() - 600 * 1000);//拿10分钟前锁定的数据
            TimerTask task = new TimerTask();
            task.setLastUpdate(updateTime);
            task.setRetryCount(4);//拿重复执行不到4次的(不包含四次)
            List<TimerTask> lockTaskList = timerTaskMapper.selectLockedTask(task);
            if (lockTaskList != null && lockTaskList.size() > 0) {
                for (TimerTask exeTimerTask:lockTaskList) {
                    timerTaskMapper.unLockTask(exeTimerTask);
                }
            }
        } catch (Exception e) {
            log.error("子任务调度--定期重置被锁定的子任务异常:" + e.getMessage());
        }
    }

    @Override
    public boolean process(Object object) {
        return false;
    }


}
