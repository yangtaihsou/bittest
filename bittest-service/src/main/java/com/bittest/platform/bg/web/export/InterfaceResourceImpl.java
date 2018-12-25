package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.InterfaceResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInterfaceRelastionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;
import com.bittest.platform.bg.service.InterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-30.
 */
@Component("interfaceResource")
public class InterfaceResourceImpl implements InterfaceResource {

    private static final Logger log = LoggerFactory.getLogger(InterfaceResourceImpl.class);

    @Autowired
    private InterfaceService interfaceService;

    @Override
    public BasicResult saveInterface(InterfaceReqVo interfaceReqVo) {
        log.info("接收到保存接口信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceService.saveInterface(interfaceReqVo);
        log.info("保存接口信息执行结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateInterface(InterfaceReqVo interfaceReqVo) {
        log.info("接收到修改接口信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceService.updateInterface(interfaceReqVo);
        log.info("修改接口信息执行结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<InterfaceResVo> queryInterfacePage(PaginationQuery<InterfaceReqVo> query) {
        log.info("接收到分页查询接口列表请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(query));
        PaginationResult<InterfaceResVo> result = interfaceService.queryInterfacePage(query);
        log.info("分页查询接口列表执行结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<InterfaceResVo> queryInterfaceDetail(InterfaceReqVo interfaceReqVo) {
        log.info("接收到查询接口详情请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        GenericResult<InterfaceResVo> result = interfaceService.queryInterfaceDetail(interfaceReqVo);
        log.info("查询接口详情执行结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<InterfaceResVo> queryInterfaceByCase(InterfaceReqVo interfaceReqVo) {
        log.info("接收到根据case信息查询接口列表请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        ListResult<InterfaceResVo> result = interfaceService.queryInterfaceByCase(interfaceReqVo);
        log.info("根据case信息查询接口列表结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<InterfaceResVo> queryAllInterfaceList(PaginationQuery<InterfaceReqVo> query) {
        log.info("分页查询用户接口信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(query));
        PaginationResult<InterfaceResVo> result = interfaceService.queryAllInterfaceList(query);
        log.info("分页查询用户接口信息请求结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateCaseInterfaceRelation(CaseInterfaceRelastionReqVo caseInterfaceRelastionReqVo) {
        log.info("修改接口状态请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(caseInterfaceRelastionReqVo));
        BasicResult result = interfaceService.updateCaseInterfaceRelation(caseInterfaceRelastionReqVo);
        log.info("修改接口状态请求返回结果：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult addConstainsInterface(InterfaceReqVo interfaceReqVo) {
        log.info("添加已有接口请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceService.addConstainsInterface(interfaceReqVo);
        log.info("添加已有接口请求结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteInterfaceInfo(InterfaceReqVo interfaceReqVo) {
        log.info("删除接口信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceService.deleteInterfaceInfo(interfaceReqVo);
        log.info("删除接口信息请求执行结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult interfaceSaveCase(InterfaceReqVo interfaceReqVo) {
        log.info("接口保存到用例req：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceService.interfaceSaveCase(interfaceReqVo);
        log.info("接口保存到用例res{}", JSON.toJSONString(result));
        return result;
    }
}
