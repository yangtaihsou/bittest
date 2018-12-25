package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.InterfaceCollection;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceCollectionResVo;
import com.bittest.platform.bg.manager.InterfaceCollectionManager;
import com.bittest.platform.bg.service.InterfaceCollectionService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-02-06.
 */
@Service("interfaceCollectionService")
public class InterfaceCollectionServiceImpl implements InterfaceCollectionService {

    private static final Logger log = LoggerFactory.getLogger(InterfaceCollectionServiceImpl.class);

    @Autowired
    private InterfaceCollectionManager interfaceCollectionManager;


    @Override
    public GenericResult<InterfaceCollectionResVo> saveInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        GenericResult<InterfaceCollectionResVo> result = new GenericResult<InterfaceCollectionResVo>();
        InterfaceCollectionResVo resVo = new InterfaceCollectionResVo();
        try {
            InterfaceCollection reqVo = JSON.parseObject(JSON.toJSONString(interfaceCollectionReqVo), InterfaceCollection.class);
            reqVo.setHead(JSON.toJSONString(interfaceCollectionReqVo.getHeadMap()));
            if (StringUtils.isEmpty(interfaceCollectionReqVo.getInterfaceId())) {
                reqVo.setInterfaceId(String.valueOf(System.currentTimeMillis()));
                interfaceCollectionManager.save(reqVo);
            } else {
                interfaceCollectionManager.update(reqVo);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
            resVo.setInterfaceId(reqVo.getInterfaceId());
            result.setValue(resVo);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("收藏接口异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<InterfaceCollectionResVo> queryInterfaceCollectionList(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        ListResult<InterfaceCollectionResVo> result = new ListResult<InterfaceCollectionResVo>();
        try {
            InterfaceCollection reqVo = JSON.parseObject(JSON.toJSONString(interfaceCollectionReqVo), InterfaceCollection.class);
            List<InterfaceCollection> res = interfaceCollectionManager.queryList(reqVo);
            List<InterfaceCollectionResVo> resVoList = new ArrayList<InterfaceCollectionResVo>();
            if (null != res) {
                for (InterfaceCollection Collection : res) {
                    resVoList.add(JSON.parseObject(JSON.toJSONString(Collection), InterfaceCollectionResVo.class));
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询收藏接口列表异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<InterfaceCollectionResVo> queryInterfaceCollectionDetail(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        GenericResult<InterfaceCollectionResVo> result = new GenericResult<InterfaceCollectionResVo>();
        try {
            InterfaceCollection reqVo = JSON.parseObject(JSON.toJSONString(interfaceCollectionReqVo), InterfaceCollection.class);
            InterfaceCollection res = interfaceCollectionManager.queryObject(reqVo);
            InterfaceCollectionResVo resVo = JSON.parseObject(JSON.toJSONString(res), InterfaceCollectionResVo.class);
            LinkedHashMap<String, String> headMap = JSON.parseObject(res.getHead(), LinkedHashMap.class);
            resVo.setHeadMap(headMap);
            result.setValue(resVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询收藏接口详情异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo) {
        BasicResult result = new BasicResult();
        try {
            InterfaceCollection reqVo = JSON.parseObject(JSON.toJSONString(interfaceCollectionReqVo), InterfaceCollection.class);
            interfaceCollectionManager.delete(reqVo.getInterfaceId());
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除收藏接口异常:{}", e.toString());
        } finally {
            return result;
        }
    }
}
