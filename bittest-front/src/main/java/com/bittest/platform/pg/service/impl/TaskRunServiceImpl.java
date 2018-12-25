package com.bittest.platform.pg.service.impl;

import com.bittest.platform.bg.export.resource.RequestResource;
import com.bittest.platform.bg.export.vo.RequestReqVo;
import com.bittest.platform.pg.service.TaskRunService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-08-28.
 */
@Service("taskRunService")
public class TaskRunServiceImpl implements TaskRunService {

    private static final Logger log = LoggerFactory.getLogger(TaskRunServiceImpl.class);

    @Autowired
    private RequestResource requestResource;


    @Override
    public boolean taskRun(RequestReqVo reqVo) {
        boolean result = true;
        TestRunThread testRunThread = new TestRunThread(reqVo, requestResource);
        Thread thread = new Thread(testRunThread);
        try {
            thread.start();
        } catch (Exception e) {
            result = false;
        } finally {
            return result;
        }
    }
}
