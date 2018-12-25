package com.bittest.platform.pg.service.impl;

import com.bittest.platform.bg.export.resource.InterfaceHistoryResource;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import com.bittest.platform.pg.service.HistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-02-28.
 */
@Service("historyService")
public class HistoryServiceImpl implements HistoryService {

    private static final Logger log = LoggerFactory.getLogger(HistoryServiceImpl.class);

    @Autowired
    private InterfaceHistoryResource interfaceHistoryResource;

    @Override
    public void saveHistory(InterfaceHistoryReqVo reqVo) {
        boolean result = true;
        InterfaceHistoryThread historyThread = new InterfaceHistoryThread(reqVo, interfaceHistoryResource);
        Thread thread = new Thread(historyThread);
        try {
            thread.start();
        } catch (Exception e) {
            log.error("异步保存接口执行来记录异常:{}", e.toString());
        }
    }

}
