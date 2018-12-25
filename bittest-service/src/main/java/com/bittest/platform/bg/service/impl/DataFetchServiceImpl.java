package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.dao.DataFetchMapper;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.DataFetchReqVo;
import com.bittest.platform.bg.service.DataFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-08-23.
 */
@Service("dataFetchService")
public class DataFetchServiceImpl implements DataFetchService {

    private Logger log = LoggerFactory.getLogger(DataFetchServiceImpl.class);

    @Autowired
    private DataFetchMapper dataFetchMapper;

    /**
     * 删除提取器
     *
     * @param dataFetchReqVo
     * @return
     */
    @Override
    public BasicResult deleteDataFetch(DataFetchReqVo dataFetchReqVo) {
        BasicResult result = new BasicResult();
        try {
            DataFetch req = JSON.parseObject(JSON.toJSONString(dataFetchReqVo), DataFetch.class);
            dataFetchMapper.delete(req);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("删除提取器信息异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }
}
