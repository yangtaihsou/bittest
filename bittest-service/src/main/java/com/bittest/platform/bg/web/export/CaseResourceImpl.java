package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.CaseResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInfoReqVo;
import com.bittest.platform.bg.export.vo.CaseInfoResVo;
import com.bittest.platform.bg.export.vo.TaskCaseRelastionReqVo;
import com.bittest.platform.bg.service.CaseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-26.
 */
@Component("caseResource")
public class CaseResourceImpl implements CaseResource {

    private static final Logger log = LoggerFactory.getLogger(CaseResourceImpl.class);
    @Autowired
    private CaseInfoService caseInfoService;

    @Override
    public GenericResult<CaseInfoResVo> queryCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        log.info("查询用例详情开始,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(caseInfoReqVo));
        GenericResult<CaseInfoResVo> result = caseInfoService.queryCaseInfo(caseInfoReqVo);
        log.info("查询用例详情返回：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<CaseInfoResVo> saveCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        log.info("新增用例请求开始,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(caseInfoReqVo));
        GenericResult<CaseInfoResVo> result = caseInfoService.saveCaseInfo(caseInfoReqVo);
        log.info("新增用例请求结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        log.info("修改用例请求开始,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(caseInfoReqVo));
        BasicResult result = caseInfoService.updateCaseInfo(caseInfoReqVo);
        log.info("修改用例请求结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        log.info("删除用例请求开始,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(caseInfoReqVo));
        BasicResult result = caseInfoService.deleteCaseInfo(caseInfoReqVo);
        log.info("删除用例请求结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPage(PaginationQuery<CaseInfoReqVo> query) {
        log.info("分页查询用例列表请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(query));
        PaginationResult<CaseInfoResVo> result = caseInfoService.queryCaseInfoPage(query);
        log.info("分页查询用例列表结果,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageByTask(PaginationQuery<CaseInfoReqVo> query) {
        log.info("根据taskId分页查询用例列表请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(query));
        PaginationResult<CaseInfoResVo> result = caseInfoService.queryCaseInfoPageByTask(query);
        log.info("根据taskId分页查询用例列表结果,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageNoFetch(PaginationQuery<CaseInfoReqVo> query) {
        log.info("分页查询未关联任务的用例请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(query));
        PaginationResult<CaseInfoResVo> result = caseInfoService.queryCaseInfoPageNoFetch(query);
        log.info("执行分页查询未关联任务的用例结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult AddFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        log.info("接受到添加关联用例请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(taskCaseRelastionReqVo));
        BasicResult result = caseInfoService.AddFetchCase(taskCaseRelastionReqVo);
        log.info("添加关联用例请求结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        log.info("接收到编辑关联用例信息请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(taskCaseRelastionReqVo));
        BasicResult result = caseInfoService.updateFetchCase(taskCaseRelastionReqVo);
        log.info("编辑关联用例信息请求执行结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        log.info("接收到删除关联用例信息请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(taskCaseRelastionReqVo));
        BasicResult result = caseInfoService.deleteFetchCase(taskCaseRelastionReqVo);
        log.info("删除关联用例信息请求执行结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<CaseInfoResVo> queryCaseByName(CaseInfoReqVo caseInfoReqVo) {
        log.info("根据用例名称查询用例列表req,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(caseInfoReqVo));
        ListResult<CaseInfoResVo> result = caseInfoService.queryCaseByName(caseInfoReqVo);
        log.info("根据用例名称查询用例列表res,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }
}
