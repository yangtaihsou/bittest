package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.enums.StatusEnum;
import com.bittest.platform.bg.common.utils.GsonUtils;
import com.bittest.platform.bg.domain.po.CaseInterfaceRelastion;
import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.domain.po.InterfaceInfo;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInterfaceRelastionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;
import com.bittest.platform.bg.manager.CaseInterfaceRelastionManager;
import com.bittest.platform.bg.manager.InterfaceManager;
import com.bittest.platform.bg.service.InterfaceService;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-08-30.
 */
@Service("interfaceService")
public class InterfaceServiceImpl implements InterfaceService {

    private static final Logger log = LoggerFactory.getLogger(InterfaceServiceImpl.class);

    @Autowired
    private InterfaceManager interfaceManager;


    @Autowired
    private CaseInterfaceRelastionManager caseInterfaceRelastionManager;

    /**
     * 保存接口信息
     *
     * @param interfaceReqVo
     * @return
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {AppRuntimeException.class})
    @Override
    public BasicResult saveInterface(InterfaceReqVo interfaceReqVo) {
        BasicResult result = new BasicResult();
        try {
            interfaceReqVo.setInterfaceId(String.valueOf(System.currentTimeMillis()));

            InterfaceInfo reqVo = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            reqVo.setHead(JSON.toJSONString(interfaceReqVo.getHeadMap()));
            reqVo.setCheckPoint(JSON.toJSONString(interfaceReqVo.getCheckPointList()));
            reqVo.setDataCheckPoint(JSON.toJSONString(interfaceReqVo.getDataCheckList()));
            reqVo.setDataFetch(JSON.toJSONString(interfaceReqVo.getDataFetchList()));
            //保存接口信息
            interfaceManager.save(reqVo);
            //如果用例编号不为空 增加用例接口关联关系信息
            if (StringUtils.isNotEmpty(interfaceReqVo.getCaseId())) {
                CaseInterfaceRelastion caseInterfaceRelastion = new CaseInterfaceRelastion();
                caseInterfaceRelastion.setCaseId(interfaceReqVo.getCaseId());
                caseInterfaceRelastion.setInterfaceId(interfaceReqVo.getInterfaceId());
                int order = caseInterfaceRelastionManager.queryRelationOrder(caseInterfaceRelastion);
                caseInterfaceRelastion.setInterfaceOrder(order + 1);
                caseInterfaceRelastionManager.save(caseInterfaceRelastion);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("保存接口信息异常:{}", e.toString());
            throw new AppRuntimeException("保存接口信息异常");
        } finally {
            return result;
        }
    }

    /**
     * 修改接口信息
     *
     * @param interfaceReqVo
     * @return
     */
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {AppRuntimeException.class})
    @Override
    public BasicResult updateInterface(InterfaceReqVo interfaceReqVo) {
        BasicResult result = new BasicResult();
        try {
            InterfaceInfo reqVo = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            reqVo.setHead(JSON.toJSONString(interfaceReqVo.getHeadMap()));
            reqVo.setCheckPoint(JSON.toJSONString(interfaceReqVo.getCheckPointList()));
            reqVo.setDataCheckPoint(JSON.toJSONString(interfaceReqVo.getDataCheckList()));
            reqVo.setDataFetch(JSON.toJSONString(interfaceReqVo.getDataFetchList()));
            //修改接口信息
            interfaceManager.update(reqVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("修改接口信息异常:{}", e.toString());
            throw new AppRuntimeException("修改接口信息异常");
        } finally {
            return result;
        }
    }

    /**
     * 分页查询接口信息列表
     *
     * @param query
     * @return
     */
    @Override
    public PaginationResult<InterfaceResVo> queryInterfacePage(PaginationQuery<InterfaceReqVo> query) {
        PaginationResult<InterfaceResVo> result = new PaginationResult<InterfaceResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(query.getQuery()), InterfaceInfo.class);
            req.setPageSize(pagination.getPageSize());
            req.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = interfaceManager.queryTotal(req);
            List<InterfaceResVo> resVoList = new ArrayList<InterfaceResVo>();
            if (totalRecord > 0) {
                List<InterfaceInfo> resList = interfaceManager.queryList(req);
                for (InterfaceInfo interfaceInfo : resList) {
                    resVoList.add(JSON.parseObject(JSON.toJSONString(interfaceInfo), InterfaceResVo.class));
                }
            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resVoList);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询任务列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 查询接口详细信息
     *
     * @param interfaceReqVo
     * @return
     */
    @Override
    public GenericResult<InterfaceResVo> queryInterfaceDetail(InterfaceReqVo interfaceReqVo) {
        GenericResult<InterfaceResVo> result = new GenericResult<InterfaceResVo>();
        InterfaceResVo resVo;
        try {
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            InterfaceInfo res = interfaceManager.queryObject(req);
            if (null != res) {
                resVo = JSON.parseObject(JSON.toJSONString(res), InterfaceResVo.class);
                resVo.setHeadMap(JSON.parseObject(res.getHead(), LinkedHashMap.class));
                List<CheckPoint> checkPointList = (List<CheckPoint>)   GsonUtils.fromJson(res.getCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
                        }.getType());
                resVo.setCheckPointList(checkPointList);
                List<CheckPoint> dataCheckList = (List<CheckPoint>) GsonUtils.fromJson(res.getDataCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
                }.getType());//TODO 无法识别value里带参数的，如${userName}
                resVo.setDataCheckList(dataCheckList);
                List<DataFetch> dataFetchList = (List<DataFetch>)  GsonUtils.fromJson(res.getDataCheckPoint(), new TypeToken<ArrayList<DataFetch>>() {
                }.getType());

                resVo.setDataFetchList(dataFetchList);
                result.setValue(resVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.error("查询接口信息不存在");
            }
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询接口详情异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<InterfaceResVo> queryInterfaceByCase(InterfaceReqVo interfaceReqVo) {
        ListResult<InterfaceResVo> result = new ListResult<InterfaceResVo>();
        List<InterfaceResVo> resVoList = new ArrayList<InterfaceResVo>();
        try {
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            List<InterfaceInfo> resList = interfaceManager.queryInterfaceByCase(req);
            if (null != resList) {
                for (InterfaceInfo res : resList) {
                    InterfaceResVo resVo = JSON.parseObject(JSON.toJSONString(res), InterfaceResVo.class);
                    resVo.setTypeStr(StatusEnum.ReqType_Status.getDesc(resVo.getType()));
                    resVoList.add(resVo);
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询接口详情异常:{}", e.toString());
        } finally {
            return result;
        }

    }

    @Override
    public BasicResult updateCaseInterfaceRelation(CaseInterfaceRelastionReqVo caseInterfaceRelastionReqVo) {
        BasicResult result = new BasicResult();
        try {
            CaseInterfaceRelastion reqVo = JSON.parseObject(JSON.toJSONString(caseInterfaceRelastionReqVo), CaseInterfaceRelastion.class);
            caseInterfaceRelastionManager.update(reqVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("修改接口状态异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<InterfaceResVo> queryAllInterfaceList(PaginationQuery<InterfaceReqVo> query) {
        PaginationResult<InterfaceResVo> result = new PaginationResult<InterfaceResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(query.getQuery()), InterfaceInfo.class);
            req.setPageSize(pagination.getPageSize());
            req.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = interfaceManager.queryAllInterfaceListCount(req);
            List<InterfaceResVo> resVoList = new ArrayList<InterfaceResVo>();
            if (totalRecord > 0) {
                List<InterfaceInfo> resList = interfaceManager.queryAllInterfaceList(req);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (InterfaceInfo interfaceInfo : resList) {
                    InterfaceResVo resVo = JSON.parseObject(JSON.toJSONString(interfaceInfo), InterfaceResVo.class);
                    resVo.setTypeStr(StatusEnum.ReqType_Status.getDesc(interfaceInfo.getType()));
                    resVo.setCreateTimeStr(sdf.format(interfaceInfo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(interfaceInfo.getUpdateTime()));
                    resVoList.add(resVo);

                }
            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resVoList);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询任务列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult addConstainsInterface(InterfaceReqVo interfaceReqVo) {
        BasicResult result = new BasicResult();
        CaseInterfaceRelastion caseInterfaceRelastion = new CaseInterfaceRelastion();
        try {
            caseInterfaceRelastion.setInterfaceId(interfaceReqVo.getInterfaceId());
            //查询接口关联用例条数
            int count = caseInterfaceRelastionManager.queryInterfaceCount(caseInterfaceRelastion);
            //如果已关联用例 添加新接口 未关联增加关联关系
            if (count > 0) {
                InterfaceInfo req = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
                InterfaceInfo interfaceInfo = interfaceManager.queryObject(req);
                interfaceInfo.setInterfaceId(String.valueOf(System.currentTimeMillis()));
                interfaceReqVo.setInterfaceId(interfaceInfo.getInterfaceId());
                interfaceManager.save(interfaceInfo);
            }
            if (StringUtils.isNotEmpty(interfaceReqVo.getCaseId())) {
                caseInterfaceRelastion.setCaseId(interfaceReqVo.getCaseId());
                caseInterfaceRelastion.setInterfaceId(interfaceReqVo.getInterfaceId());
                int order = caseInterfaceRelastionManager.queryRelationOrder(caseInterfaceRelastion);
                caseInterfaceRelastion.setInterfaceOrder(order + 1);
                caseInterfaceRelastionManager.save(caseInterfaceRelastion);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("添加已有接口信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteInterfaceInfo(InterfaceReqVo interfaceReqVo) {
        BasicResult result = new BasicResult();
        try {
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            interfaceManager.delete(req);
            if (StringUtils.isNotEmpty(interfaceReqVo.getCaseId())) {
                log.info("caseId存在，删除用例与接口关联关系：{}", interfaceReqVo.getCaseId());
                CaseInterfaceRelastion caseInterfaceRelastion = new CaseInterfaceRelastion();
                caseInterfaceRelastion.setInterfaceId(interfaceReqVo.getInterfaceId());
                caseInterfaceRelastion.setCaseId(interfaceReqVo.getCaseId());
                caseInterfaceRelastionManager.delete(caseInterfaceRelastion);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("删除接口信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {AppRuntimeException.class})
    public BasicResult interfaceSaveCase(InterfaceReqVo interfaceReqVo) {
        BasicResult result = new BasicResult();
        CaseInterfaceRelastion caseInterfaceRelastion = new CaseInterfaceRelastion();
        try {
            caseInterfaceRelastion.setInterfaceId(interfaceReqVo.getInterfaceId());
            InterfaceInfo req = JSON.parseObject(JSON.toJSONString(interfaceReqVo), InterfaceInfo.class);
            req.setHead(JSON.toJSONString(interfaceReqVo.getHeadMap()));
            req.setInterfaceId(String.valueOf(System.currentTimeMillis()));
            interfaceManager.save(req);
            if (StringUtils.isNotEmpty(interfaceReqVo.getCaseId())) {
                caseInterfaceRelastion.setCaseId(interfaceReqVo.getCaseId());
                caseInterfaceRelastion.setInterfaceId(req.getInterfaceId());
                int order = caseInterfaceRelastionManager.queryRelationOrder(caseInterfaceRelastion);
                caseInterfaceRelastion.setInterfaceOrder(order + 1);
                caseInterfaceRelastionManager.save(caseInterfaceRelastion);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("接口保存到用例异常:{}", e.toString());
            throw new AppRuntimeException("接口保存到用例异常");
        } finally {
            return result;
        }
    }
}
