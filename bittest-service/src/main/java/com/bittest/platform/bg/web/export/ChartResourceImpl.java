package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.ChartResource;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.ChartReqVo;
import com.bittest.platform.bg.export.vo.ChartResVo;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import com.bittest.platform.bg.export.vo.CountInfoResVo;
import com.bittest.platform.bg.service.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-27.
 */
@Component("chartResource")
public class ChartResourceImpl implements ChartResource {

    private static final Logger log = LoggerFactory.getLogger(ChartResourceImpl.class);

    @Autowired
    private ChartService chartService;

    @Override
    public GenericResult<ChartResVo> queryTaskChart(ChartReqVo reqVo) {
        log.info("首页报表TASK查询：req{}", JSON.toJSONString(reqVo));
        GenericResult<ChartResVo> result = chartService.queryTaskChart(reqVo);
        log.info("报表统计结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<ChartResVo> querySystemChart(ChartReqVo reqVo) {
        log.info("首页报表System查询：req{}", JSON.toJSONString(reqVo));
        GenericResult<ChartResVo> result = chartService.querySystemChart(reqVo);
        log.info("报表统计结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<CountInfoResVo> queryCountInfoByPage(PaginationQuery<CountInfoReqVo> query) {
        log.info("分页查询用户统计信息：req{}", JSON.toJSONString(query));
        PaginationResult<CountInfoResVo> result = chartService.queryCountInfoByPage(query);
        log.info("分页查询用户统计信息：res{}", JSON.toJSONString(result));
        return result;
    }
}
