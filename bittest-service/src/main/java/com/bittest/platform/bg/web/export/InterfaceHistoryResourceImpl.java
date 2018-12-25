package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.InterfaceHistoryResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import com.bittest.platform.bg.export.vo.InterfaceHistoryResVo;
import com.bittest.platform.bg.service.InterfaceHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-02-05.
 */
@Component("interfaceHistoryResource")
public class InterfaceHistoryResourceImpl implements InterfaceHistoryResource {

    private static final Logger log = LoggerFactory.getLogger(InterfaceHistoryResourceImpl.class);

    @Autowired
    private InterfaceHistoryService interfaceHistoryService;

    @Override
    public BasicResult saveInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        log.info("保存接口执行记录req：{}", JSON.toJSONString(interfaceHistoryReqVo));
        BasicResult result = interfaceHistoryService.saveInterfaceHistory(interfaceHistoryReqVo);
        log.info("保存接口执行记录结果res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<InterfaceHistoryResVo> queryInterfaceHistoryList(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        log.info("查询执行记录列表req：{}", JSON.toJSONString(interfaceHistoryReqVo));
        ListResult<InterfaceHistoryResVo> result = interfaceHistoryService.queryInterfaceHistoryList(interfaceHistoryReqVo);
        log.info("查询执行记录列表res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<InterfaceHistoryResVo> queryInterfaceHistoryDetail(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        log.info("查询执行记录详情req：{}", JSON.toJSONString(interfaceHistoryReqVo));
        GenericResult<InterfaceHistoryResVo> result = interfaceHistoryService.queryInterfaceHistoryDetail(interfaceHistoryReqVo);
        log.info("查询执行记录详情res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        log.info("删除执行记录详情req：{}", JSON.toJSONString(interfaceHistoryReqVo));
        BasicResult result = interfaceHistoryService.deleteInterfaceHistory(interfaceHistoryReqVo);
        log.info("删除执行记录详情res：{}", JSON.toJSONString(result));
        return result;
    }
}
