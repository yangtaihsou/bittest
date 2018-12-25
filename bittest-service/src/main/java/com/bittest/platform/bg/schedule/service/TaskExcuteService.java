package com.bittest.platform.bg.schedule.service;


/**
 * 简单任务接口
 */
public interface TaskExcuteService {

    public void exe();
    /**
     * 处理
     *
     * @param object
     * @return
     * @throws Exception
     */
    public boolean process(Object object);

}