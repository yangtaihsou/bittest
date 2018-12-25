package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.ResultResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseResultResVo;
import com.bittest.platform.bg.export.vo.InterfaceResultResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;
import com.bittest.platform.bg.service.ResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-27.
 */
@Component("resultResource")
public class ResultResourceImpl implements ResultResource {

    @Autowired
    private static final Logger log = LoggerFactory.getLogger(ResultResourceImpl.class);

    @Autowired
    private ResultService resultService;

    @Override
    public PaginationResult<TaskResultResVo> queryTaskResultPage(PaginationQuery<ResultReqVo> query) {
        log.info("分页查询测试计划执行结果信息：req{}", JSON.toJSONString(query));
        PaginationResult<TaskResultResVo> result = resultService.queryTaskResultPage(query);
        log.info("分页查询结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<CaseResultResVo> queryCaseResultPage(PaginationQuery<ResultReqVo> query) {
        log.info("分页查询用例执行结果信息：req{}", JSON.toJSONString(query));
        PaginationResult<CaseResultResVo> result = resultService.queryCaseResultPage(query);
        log.info("分页查询结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<InterfaceResultResVo> queryInterfaceResultPage(PaginationQuery<ResultReqVo> query) {
        log.info("分页查询接口执行结果信息：req{}", JSON.toJSONString(query));
        PaginationResult<InterfaceResultResVo> result = resultService.queryInterfaceResultPage(query);
        log.info("分页查询结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteResultById(ResultReqVo resultReqVo) {
        log.info("根据执行结果编号删除结果信息：req{}", JSON.toJSONString(resultReqVo));
        BasicResult result = resultService.deleteResultById(resultReqVo);
        log.info("删除结果返回：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteResultByTask(ResultReqVo resultReqVo) {
        log.info("根据任务计划编号删除所有执行结果：req{}", JSON.toJSONString(resultReqVo));
        BasicResult result = resultService.deleteResultByTask(resultReqVo);
        log.info("删除结果返回：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<TaskResultResVo> queryTaskResultDetail(ResultReqVo resultReqVo) {
        log.info("查询任务计划执行结果详情：req{}", JSON.toJSONString(resultReqVo));
        GenericResult<TaskResultResVo> result = resultService.queryTaskResultDetail(resultReqVo);
        log.info("查询结果：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<CaseResultResVo> queryCaseResultDetail(ResultReqVo resultReqVo) {
        log.info("查询用例执行结果详情：req{}", JSON.toJSONString(resultReqVo));
        GenericResult<CaseResultResVo> result = resultService.queryCaseResultDetail(resultReqVo);
        log.info("查询结果：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<InterfaceResultResVo> queryInterfaceResultDetail(ResultReqVo resultReqVo) {
        log.info("查询接口执行结果详情：req{}", JSON.toJSONString(resultReqVo));
        GenericResult<InterfaceResultResVo> result = resultService.queryInterfaceResultDetail(resultReqVo);
        log.info("查询结果：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<CaseResultResVo> queryCaseResultList(ResultReqVo resultReqVo) {
        log.info("查询执行结果下所有用例执行结果：req{}", JSON.toJSONString(resultReqVo));
        ListResult<CaseResultResVo> result = resultService.queryCaseResultList(resultReqVo);
        log.info("查询结果：res{}", JSON.toJSONString(result));
        return result;
    }
}
