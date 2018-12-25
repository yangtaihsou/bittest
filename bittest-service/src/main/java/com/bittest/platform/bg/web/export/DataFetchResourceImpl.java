package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.DataFetchResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.DataFetchReqVo;
import com.bittest.platform.bg.service.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-23.
 */
@Component("dataFetchResource")
public class DataFetchResourceImpl implements DataFetchResource {

    private static final Logger log = LoggerFactory.getLogger(DataFetchResourceImpl.class);

    @Autowired
    private DataFetchService dataFetchService;

    @Override
    public BasicResult deleteDataFetch(DataFetchReqVo dataFetchReqVo) {
        log.info("接收到删除提取器请求，com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataFetchReqVo));
        BasicResult result = dataFetchService.deleteDataFetch(dataFetchReqVo);
        log.info("执行删除提起器请求结束,res{}", JSON.toJSONString(result));
        return result;
    }
}
