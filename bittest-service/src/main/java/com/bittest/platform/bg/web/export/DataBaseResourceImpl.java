package com.bittest.platform.bg.web.export;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.DataBaseResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;
import com.bittest.platform.bg.service.DataBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2018-08-22.
 */
@Component("dataBaseResource")
public class DataBaseResourceImpl implements DataBaseResource {

    private static final Logger log = LoggerFactory.getLogger(DataBaseResourceImpl.class);

    @Autowired
    private DataBaseService dataBaseService;

    @Override
    public GenericResult<DataBaseResVo> queryDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("接收到查询数据库详情请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        GenericResult<DataBaseResVo> result = dataBaseService.queryDataBase(dataBaseReqVo);
        log.info("执行查询数据库详情结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult saveDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("接收到保存数据库信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseService.saveDataBase(dataBaseReqVo);
        log.info("执行保存数据库信息结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult updateDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("接收到修改数据库信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseService.updateDataBase(dataBaseReqVo);
        log.info("执行修改数据库信息结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public PaginationResult<DataBaseResVo> queryDataBasePage(PaginationQuery<DataBaseReqVo> query) {
        log.info("接收到分页查询数据库信息请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(query));
        PaginationResult<DataBaseResVo> result = dataBaseService.queryDataBasePage(query);
        log.info("执行分页查询数据库信息结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public ListResult<DataBaseResVo> queryTotalDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("接收到查询个人所有数据库列表请求：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        ListResult<DataBaseResVo> result = dataBaseService.queryTotalDataBase(dataBaseReqVo);
        log.info("执行查询个人所有数据库列表请求结束：res{}", JSON.toJSONString(result));
        return result;
    }

    @Override
    public BasicResult connectDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("接收到测试数据库连接服务：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseService.connectDataBase(dataBaseReqVo);
        log.info("执行测试数据库连接服务结束：res{}", JSON.toJSONString(dataBaseReqVo));
        return result;
    }

    @Override
    public BasicResult deleteDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("删除数据库信息：com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseService.deleteDataBase(dataBaseReqVo);
        log.info("删除数据库信息结果为：res{}", JSON.toJSONString(result));
        return result;
    }
}
