package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.dao.CheckPointMapper;
import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.CheckPointReqVo;
import com.bittest.platform.bg.service.CheckPointService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2018-08-23.
 */
@Service("checkPointService")
public class CheckPointServiceImpl implements CheckPointService {

    private static final Logger log = LoggerFactory.getLogger(CheckPointServiceImpl.class);

    @Autowired
    private CheckPointMapper checkPointMapper;

    @Override
    public BasicResult deleteCheckPoint(CheckPointReqVo checkPointReqVo) {
        BasicResult result = new BasicResult();
        try {
            CheckPoint req = JSON.parseObject(JSON.toJSONString(checkPointReqVo), CheckPoint.class);
            int res = checkPointMapper.delete(req);
            if (res == 0) {
                log.error("删除检查点信息不存在");
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除检查点信息异常：{}", e.toString());
        } finally {
            return result;
        }
    }
}
