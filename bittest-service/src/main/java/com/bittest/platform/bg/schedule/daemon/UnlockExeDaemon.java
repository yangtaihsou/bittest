package com.bittest.platform.bg.schedule.daemon;

import com.bittest.platform.bg.schedule.service.TaskExcuteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UnlockExeDaemon {

    @Autowired
    TaskExcuteService unLockExeService;
    private final static Logger log = LoggerFactory.getLogger(UnlockExeDaemon.class);
    @PostConstruct
    public void init() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        unLockExeService.exe();
                        Thread.sleep(10 * 1000);
                    } catch (Exception e) {
                        log.error("执行定时任务的解锁报错", e.getMessage());
                        try {
                            Thread.sleep(40 * 1000);//40秒休眠
                        } catch (InterruptedException e1) {
                            log.error("执行定时任务的解锁线程异常", e1);
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
