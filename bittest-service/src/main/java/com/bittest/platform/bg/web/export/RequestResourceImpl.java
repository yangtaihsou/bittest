package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.CaseResult;
import com.bittest.platform.bg.export.resource.RequestResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.bg.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-27.
 */
@Component("requestResource")
public class RequestResourceImpl implements RequestResource {

    private static final Logger log = LoggerFactory.getLogger(RequestResourceImpl.class);

    @Autowired
    private RequestService requestService;

    /**
     * 接口维度调用
     *
     * @param interfaceReqVo
     * @return
     */
    @Override
    public GenericResult<RequestInterfaceResVo> interfaceReq(InterfaceReqVo interfaceReqVo) {
        log.info("接收到接口发送请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(interfaceReqVo));
        GenericResult<RequestInterfaceResVo> result = requestService.interfaceReq(interfaceReqVo, null, null);
        log.info("接口发送请求结束,返回信息vo:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用例维度执行接口请求
     *
     * @param requestReqVo
     * @return
     */
    @Override
    public GenericResult<CaseInfoResVo> interfaceReqByCase(RequestReqVo requestReqVo) {
        log.info("接收到用例执行请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(requestReqVo));
        GenericResult<CaseInfoResVo> result = requestService.interfaceReqByCase(requestReqVo);
        log.info("用例执行返回结果，res:{}", JSON.toJSONString(result));
        return result;
    }

    @Override
   public GenericResult<CaseResult> runCase(RequestReqVo requestReqVo){
        log.info("接收到用例执行请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(requestReqVo));
        GenericResult<CaseResult> result = requestService.runCase(requestReqVo);
        log.info("用例执行返回结果，res:{}", JSON.toJSONString(result));
        return result;

    }

    /**
     * 任务维度执行接口请求
     *
     * @param requestReqVo
     * @return
     */
    @Override
    public BasicResult interfaceReqByTask(RequestReqVo requestReqVo) {
        log.info("接收到任务执行请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(requestReqVo));
        BasicResult result = requestService.interfaceReqByTask(requestReqVo);
        log.info("任务执行结束,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 解析jsf接口信息
     *
     * @param jsfQueryReqVo
     * @return
     */
    @Override
    public GenericResult<JsfQueryResVo> jsfQueryInfo(JsfQueryReqVo jsfQueryReqVo) {
        log.info("接收到解析jsf接口请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(jsfQueryReqVo));
        GenericResult<JsfQueryResVo> result = requestService.jsfQueryInfo(jsfQueryReqVo);
        log.info("解析jsf接口请求结束，res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 网关方式获取jsf 别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryAlias(JsfInfoReqVo jsfInfoReqVo) {
        log.info("接收到解析jsf alias接口请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(jsfInfoReqVo));
        GenericResult<JsfInfoResVo> result = requestService.queryAlias(jsfInfoReqVo);
        log.info("解析jsf alias接口请求请求结束，res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 网关方式获取jsf ip:port
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryIps(JsfInfoReqVo jsfInfoReqVo) {
        log.info("接收到解析jsf ips接口请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(jsfInfoReqVo));
        GenericResult<JsfInfoResVo> result = requestService.queryIps(jsfInfoReqVo);
        log.info("解析jsf ips接口请求请求结束，res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 网关方式获取jsf 方法（入参、方法名、返回参数）相关信息
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryMethods(JsfInfoReqVo jsfInfoReqVo) {
        log.info("接收到解析jsf methods接口请求,com.bittest.platform.bg.domain.vo:{}", JSON.toJSONString(jsfInfoReqVo));
        GenericResult<JsfInfoResVo> result = requestService.queryMethods(jsfInfoReqVo);
        log.info("解析jsf methods方法接口请求请求结束，res{}", JSON.toJSONString(result));
        return result;
    }
}
