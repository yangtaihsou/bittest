package com.bittest.platform.bg.schedule.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

//@Service
public class TimerTaskExeDaemon {

    private final static Logger log = LoggerFactory.getLogger(TimerTaskExeDaemon.class);
    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (Exception e) {
                        log.error("执行定时任务报错", e);
                        try {
                            Thread.sleep(40 * 1000);//40秒休眠
                        } catch (InterruptedException e1) {
                            log.error("执行定时任务失败", e1);
                        }
                    } finally {

                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
