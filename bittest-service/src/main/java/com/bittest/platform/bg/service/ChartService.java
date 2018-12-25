package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.ChartReqVo;
import com.bittest.platform.bg.export.vo.ChartResVo;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import com.bittest.platform.bg.export.vo.CountInfoResVo;

/**
 * 2018-08-27.
 */
public interface ChartService {

    /**
     * 首页报表(近十次任务计划执行结果)
     *
     * @param reqVo
     * @return
     */
    GenericResult<ChartResVo> queryTaskChart(ChartReqVo reqVo);

    /**
     * 首页报表查询（系统维度，统计用力数量）
     *
     * @param reqVo
     * @return
     */
    GenericResult<ChartResVo> querySystemChart(ChartReqVo reqVo);

    /**
     * 分页查询 用户统计信息
     *
     * @param query
     * @return
     */
    PaginationResult<CountInfoResVo> queryCountInfoByPage(PaginationQuery<CountInfoReqVo> query);

}
