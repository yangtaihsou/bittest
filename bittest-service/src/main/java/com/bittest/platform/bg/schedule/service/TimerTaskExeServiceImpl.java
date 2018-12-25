package com.bittest.platform.bg.schedule.service;

import com.bittest.platform.bg.dao.TimerTaskMapper;
import com.bittest.platform.bg.domain.po.TimerTask;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.RequestReqVo;
import com.bittest.platform.bg.schedule.constant.TaskEnum;
import com.bittest.platform.bg.service.RequestService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 创建定时每月、每日或者每小时等类型执行的任务
 * <p>
 * Date: 15-5-7
 * Time: 下午2:51
 */


public class TimerTaskExeServiceImpl {

    private static final Log log = LogFactory.getLog(TimerTaskExeServiceImpl.class);
    @Resource(name = "timerTaskMapper")
    protected TimerTaskMapper timerTaskMapper;

    @Autowired
    private RequestService requestService;

    public void exe() {
        Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("taskStatus", TaskEnum.TaskStatus.init.status());
        parameterMap.put("bizTime", new Date());
        List<TimerTask> timerTaskList = timerTaskMapper.findTaskByMapParm(parameterMap);
        for (TimerTask timerTask : timerTaskList) {
            this.process(timerTask);
        }
    }

    public boolean process(TimerTask timerTask) {
        String resultMsg = "";
        try {
            int lockResult = timerTaskMapper.lockTask(timerTask);//①
            //锁定失败进行下一个
            if (lockResult == 0) {//②
                resultMsg = timerTask.getTaskId() + "该任务已经被其他机器锁定";//适用于多线程并发或者集群并发时
                log.info(resultMsg); //主要是为了统计锁冲突数量
                return true;
            }
            RequestReqVo requestReqVo = new RequestReqVo();
            requestReqVo.setTaskId(timerTask.getTaskId());
            BasicResult result = requestService.interfaceReqByTask(requestReqVo);//任务执行//③
            if (result != null && result.getCode().equals(ResultInfoEnum.SUCCESS.getErrorCode())) {//如果执行成功，则更改任务状态为执行完成
                int finishResult = timerTaskMapper.finishTask(timerTask);//④
                if (finishResult == 0) {
                    resultMsg = timerTask.getTaskId() + "执行任务成功，但事后更改状态为完成失败";
                }
            } else {//一般不会执行到这一步，会直接抛到异常
                resultMsg = timerTask.getTaskId() + "执行任务失败:" + result.getMessage();
            }
            log.info(resultMsg);
        } catch (Exception e) {
            resultMsg = timerTask.getTaskId() + "执行任务出现异常:" + e.getMessage();
            log.info(resultMsg);
        }
        return true;
    }

}

