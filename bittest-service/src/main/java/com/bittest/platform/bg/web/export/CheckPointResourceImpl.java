package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.CheckPointResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.CheckPointReqVo;
import com.bittest.platform.bg.service.CheckPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-23.
 */
@Component("checkPointResource")
public class CheckPointResourceImpl implements CheckPointResource {

    private static final Logger log = LoggerFactory.getLogger(CheckPointResourceImpl.class);

    @Autowired
    private CheckPointService checkPointService;

    @Override
    public BasicResult deleteCheckPoint(CheckPointReqVo checkPointReqVo) {
        log.info("接收到检查点删除请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(checkPointReqVo));
        BasicResult result = checkPointService.deleteCheckPoint(checkPointReqVo);
        log.info("执行删除检查点请求结束：res{}", JSON.toJSONString(result));
        return result;
    }
}
