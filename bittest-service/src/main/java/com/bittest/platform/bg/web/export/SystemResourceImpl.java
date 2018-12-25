package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.export.vo.SystemResVo;
import com.bittest.platform.bg.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-25.
 */
@Component("systemResource")
public class SystemResourceImpl implements SystemResource {

    private static final Logger log = LoggerFactory.getLogger(SystemResourceImpl.class);

    @Autowired
    private SystemService systemService;

    @Override
    public GenericResult<SystemResVo> querySystem(SystemReqVo systemReqVo) {
        log.info("查询系统信息请求开始,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(systemReqVo));
        GenericResult<SystemResVo> result = systemService.querySystem(systemReqVo);
        log.info("查询系统信息请求结束,res: {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult saveSystem(SystemReqVo systemReqVo) {
        log.info("保存系统信息请求开始,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(systemReqVo));
        BasicResult result = systemService.saveSystem(systemReqVo);
        log.info("保存系统信息请求结束,res: {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateSystem(SystemReqVo systemReqVo) {
        log.info("修改系统信息请求开始,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(systemReqVo));
        BasicResult result = systemService.updateSystem(systemReqVo);
        log.info("修改系统信息请求结束,res: {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<SystemResVo> querySystemPage(PaginationQuery<SystemReqVo> query) {
        log.info("分页查询系统信息请求开始,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(query));
        PaginationResult<SystemResVo> result = systemService.querySystemPage(query);
        log.info("分页查询系统信息请求结束,res: {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<SystemResVo> querySystemList(SystemReqVo systemReqVo) {
        log.info("查询用户系统信息列表集合,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(systemReqVo));
        ListResult<SystemResVo> result = systemService.querySystemList(systemReqVo);
        log.info("查询用户系统信息列表结束,res: {}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteSystem(SystemReqVo systemReqVo) {
        log.info("删除系统信息请求开始,com.bittest.platform.bg.domain.vo: {}", JSON.toJSONString(systemReqVo));
        BasicResult result = systemService.deleteSystem(systemReqVo);
        log.info("删除系统信息请求结束,res: {}", JSON.toJSONString(result));
        return result;
    }
}
