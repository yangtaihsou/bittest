package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.InterfaceCollectionResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceCollectionResVo;
import com.bittest.platform.bg.service.InterfaceCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-02-05.
 */
@Component("interfaceCollectionResource")
public class InterfaceCollectionResourceImpl implements InterfaceCollectionResource {

    private static final Logger log = LoggerFactory.getLogger(InterfaceCollectionResourceImpl.class);

    @Autowired
    private InterfaceCollectionService interfaceCollectionService;

    @Override
    public GenericResult<InterfaceCollectionResVo> saveInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        log.info("收藏接口req：{}", JSON.toJSONString(interfaceCollectionReqVo));
        GenericResult<InterfaceCollectionResVo> result = interfaceCollectionService.saveInterfaceCollection(interfaceCollectionReqVo);
        log.info("收藏接口res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<InterfaceCollectionResVo> queryInterfaceCollectionList(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        log.info("根据查询收藏接口列表req：{}", JSON.toJSONString(interfaceCollectionReqVo));
        ListResult<InterfaceCollectionResVo> result = interfaceCollectionService.queryInterfaceCollectionList(interfaceCollectionReqVo);
        log.info("根据查询收藏接口列表res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public GenericResult<InterfaceCollectionResVo> queryInterfaceCollectionDetail(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        log.info("查询收藏接口详情req：{}", JSON.toJSONString(interfaceCollectionReqVo));
        GenericResult<InterfaceCollectionResVo> result = interfaceCollectionService.queryInterfaceCollectionDetail(interfaceCollectionReqVo);
        log.info("查询收藏接口详情res：{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult deleteInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        log.info("删除收藏接口req：{}", JSON.toJSONString(interfaceCollectionReqVo));
        BasicResult result = interfaceCollectionService.deleteInterfaceCollection(interfaceCollectionReqVo);
        log.info("删除收藏接口res：{}", JSON.toJSONString(result));
        return result;
    }
}
