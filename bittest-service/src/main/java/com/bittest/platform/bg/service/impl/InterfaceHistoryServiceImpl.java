package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.InterfaceHistory;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import com.bittest.platform.bg.export.vo.InterfaceHistoryResVo;
import com.bittest.platform.bg.manager.InterfaceHistoryManager;
import com.bittest.platform.bg.service.InterfaceHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-02-05.
 */
@Service("interfaceHistoryService")
public class InterfaceHistoryServiceImpl implements InterfaceHistoryService {

    private static final Logger log = LoggerFactory.getLogger(InterfaceHistoryServiceImpl.class);

    @Autowired
    private InterfaceHistoryManager interfaceHistoryManager;


    @Override
    public BasicResult saveInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        BasicResult result = new BasicResult();
        try {
            InterfaceHistory count = interfaceHistoryManager.queryHistoryCount(interfaceHistoryReqVo.getPin());
            if (null != count && count.getCountNum() >= 50) {
                interfaceHistoryManager.delete(count.getMaxId());
            }
            InterfaceHistory reqVo = JSON.parseObject(JSON.toJSONString(interfaceHistoryReqVo), InterfaceHistory.class);
            if (null != interfaceHistoryReqVo.getHeadMap()) {
                reqVo.setHead(JSON.toJSONString(interfaceHistoryReqVo.getHeadMap()));
            }
            interfaceHistoryManager.save(reqVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("保存接口执行记录异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<InterfaceHistoryResVo> queryInterfaceHistoryList(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        ListResult<InterfaceHistoryResVo> result = new ListResult<InterfaceHistoryResVo>();
        try {
            InterfaceHistory reqVo = JSON.parseObject(JSON.toJSONString(interfaceHistoryReqVo), InterfaceHistory.class);
            List<InterfaceHistory> res = interfaceHistoryManager.queryList(reqVo);
            List<InterfaceHistoryResVo> resVoList = new ArrayList<InterfaceHistoryResVo>();
            if (null != res) {
                for (InterfaceHistory history : res) {
                    resVoList.add(JSON.parseObject(JSON.toJSONString(history), InterfaceHistoryResVo.class));
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询接口执行记录异常：{}", e.toString());
        } finally {
            return result;
        }

    }

    @Override
    public GenericResult<InterfaceHistoryResVo> queryInterfaceHistoryDetail(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        GenericResult<InterfaceHistoryResVo> result = new GenericResult<InterfaceHistoryResVo>();
        try {
            InterfaceHistory reqVo = JSON.parseObject(JSON.toJSONString(interfaceHistoryReqVo), InterfaceHistory.class);
            InterfaceHistory res = interfaceHistoryManager.queryObject(reqVo);
            InterfaceHistoryResVo resVo = JSON.parseObject(JSON.toJSONString(res), InterfaceHistoryResVo.class);
            LinkedHashMap<String, String> headMap = JSON.parseObject(res.getHead(), LinkedHashMap.class);
            resVo.setHeadMap(headMap);
            result.setValue(resVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("保存接口执行记录异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteInterfaceHistory(InterfaceHistoryReqVo interfaceHistoryReqVo) {
        BasicResult result = new BasicResult();
        try {
            InterfaceHistory reqVo = JSON.parseObject(JSON.toJSONString(interfaceHistoryReqVo), InterfaceHistory.class);
            interfaceHistoryManager.delete(reqVo.getId());
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除接口执行记录异常:{}", e.toString());
        } finally {
            return result;
        }
    }
}
