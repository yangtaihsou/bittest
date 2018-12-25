package com.bittest.platform.bg.schedule.service;

import com.bittest.platform.bg.common.utils.DateUtil;
import com.bittest.platform.bg.dao.TimerTaskConfigMapper;
import com.bittest.platform.bg.dao.TimerTaskMapper;
import com.bittest.platform.bg.domain.po.TimerTask;
import com.bittest.platform.bg.domain.po.TimerTaskConfig;
import com.bittest.platform.bg.schedule.constant.TaskEnum;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * 创建定时每月、每日或者每小时等类型执行的任务

 * Date: 15-5-7
 * Time: 下午2:51
 */

@Service("generateTimerTaskService")
public class GenerateTimerTaskServiceImpl implements TaskExcuteService {

    private static final Log log = LogFactory.getLog(GenerateTimerTaskServiceImpl.class);
    @Resource(name = "timerTaskMapper")
    protected TimerTaskMapper timerTaskMapper;
    @Resource(name = "timerTaskConfigMapper")
    private TimerTaskConfigMapper timerTaskConfigMapper;
    @Autowired
    private PlatformTransactionManager transactionManager;


    public void exe(){
        TimerTaskConfig timerTaskConfig = new TimerTaskConfig();
        timerTaskConfig.setStatus(1);//启动状态的
        timerTaskConfig.setBiztime(new Date());
        List<TimerTaskConfig> timerTaskConfigList = timerTaskConfigMapper.findByBizTime(timerTaskConfig);
        for(TimerTaskConfig timerTaskConfig1 : timerTaskConfigList){
            this.process(timerTaskConfig1);
        }
    }

    public boolean process(Object object)  {
        boolean result = true;
        // 事务控制
        TransactionStatus status = null;
        try {
            SimpleDateFormat yyyymmddhhmmssDateFormat = DateUtil.getDateFormat("yyyyMMddHHmmss");
            Assert.notNull(object);
            TimerTaskConfig timerTaskConfig = (TimerTaskConfig) object;
            String dataType = timerTaskConfig.getDataType();
            Calendar now = Calendar.getInstance();
            now.setTime(timerTaskConfig.getBiztime());
            TimerTask timerTask = new TimerTask();//定时任务
            if (dataType.equals("month")) {//每月执行
                now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
                now.set(Calendar.DAY_OF_MONTH, timerTaskConfig.getDay().intValue());
                now.set(Calendar.HOUR_OF_DAY, timerTaskConfig.getHour().intValue());
                now.set(Calendar.MINUTE, timerTaskConfig.getMinute().intValue());
                now.set(Calendar.SECOND, timerTaskConfig.getSecond().intValue());
            } else if (dataType.equals("day")) {//每日执行
                now.set(Calendar.DAY_OF_MONTH, now.get(Calendar.DAY_OF_MONTH) + 1);
                now.set(Calendar.HOUR_OF_DAY, timerTaskConfig.getHour().intValue());
                now.set(Calendar.MINUTE, timerTaskConfig.getMinute().intValue());
                now.set(Calendar.SECOND, timerTaskConfig.getSecond().intValue());
            } else if (dataType.equals("hour")) {//每小时执行
                now.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY) + 1);
                now.set(Calendar.MINUTE, timerTaskConfig.getMinute().intValue());
                now.set(Calendar.SECOND, timerTaskConfig.getSecond().intValue());
            } else if (dataType.equals("minute")) {//每分钟执行
                now.set(Calendar.MINUTE, now.get(Calendar.MINUTE) + 1);
                now.set(Calendar.SECOND, timerTaskConfig.getSecond().intValue());
            } else {
                //TODO
            }
            timerTask.setBizTime(timerTaskConfig.getBiztime());
            timerTask.setTaskStatus(TaskEnum.TaskStatus.init.status());
            StringBuffer timerTaskUUid = new StringBuffer();
            timerTaskUUid.append(timerTaskConfig.getTaskTimerKey()).append("_");//taskTimerKey
            timerTaskUUid.append(yyyymmddhhmmssDateFormat.format(now.getTime()));//yyyymmddhhmmss
            timerTask.setUuid(timerTaskUUid.toString());
            timerTask.setRetryCount(0);
            timerTask.setTaskData("");

            timerTaskConfig.setBiztime(now.getTime());
            // 开始事务
            status = this.initTansactionStatus(transactionManager,
                    TransactionDefinition.PROPAGATION_REQUIRED);
            timerTaskMapper.save(timerTask);//保存定时任务
            timerTaskConfigMapper.updateByPrimaryKeySelective(timerTaskConfig);//更新分发任务的下次执行时间
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("split task exception:" + ExceptionUtils.getStackTrace(e));
            result = false;
            transactionManager.rollback(status);
        } finally {
            return result;
        }
    }

    /**
     * 定义事物
     *
     * @param transactionManager
     * @param propagetion
     * @return
     */
    protected TransactionStatus initTansactionStatus(
            PlatformTransactionManager transactionManager, int propagetion) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();// 事务定义类
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        return transactionManager.getTransaction(def);
    }
}

