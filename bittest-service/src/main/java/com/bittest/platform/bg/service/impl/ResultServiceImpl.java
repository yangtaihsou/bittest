package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.enums.StatusEnum;
import com.bittest.platform.bg.common.utils.GsonUtils;
import com.bittest.platform.bg.domain.po.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.domain.po.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseResultResVo;
import com.bittest.platform.bg.export.vo.InterfaceResultResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;
import com.bittest.platform.bg.manager.CaseResultManager;
import com.bittest.platform.bg.manager.InterfaceResultManager;
import com.bittest.platform.bg.manager.TaskResultManager;
import com.bittest.platform.bg.service.ResultService;
import com.google.gson.reflect.TypeToken;
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
 * 2018-08-27.
 */
@Service("resultService")
public class ResultServiceImpl implements ResultService {

    private static final Logger log = LoggerFactory.getLogger(ResultServiceImpl.class);

    @Autowired
    private TaskResultManager taskResultManager;

    @Autowired
    private CaseResultManager caseResultManager;

    @Autowired
    private InterfaceResultManager interfaceResultManager;


    @Override
    public PaginationResult<TaskResultResVo> queryTaskResultPage(PaginationQuery<ResultReqVo> query) {
        PaginationResult<TaskResultResVo> result = new PaginationResult<TaskResultResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            TaskResult taskResult = JSON.parseObject(JSON.toJSONString(query.getQuery()), TaskResult.class);
            taskResult.setPageSize(pagination.getPageSize());
            taskResult.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = taskResultManager.queryTotal(taskResult);
            List<TaskResultResVo> resTask = new ArrayList<TaskResultResVo>();
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<TaskResult> taskResultList = taskResultManager.queryList(taskResult);
                for (TaskResult t : taskResultList) {
                    TaskResultResVo resVo = JSON.parseObject(JSON.toJSONString(t), TaskResultResVo.class);
                    resVo.setIsFinishStr(StatusEnum.Finish_Status.getDesc(resVo.getIsfinish()));
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }
            } else {
                log.info("查询任务执行结果列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询任务执行结果列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<CaseResultResVo> queryCaseResultPage(PaginationQuery<ResultReqVo> query) {
        PaginationResult<CaseResultResVo> result = new PaginationResult<CaseResultResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(query.getQuery()), CaseResult.class);
            caseResult.setPageSize(pagination.getPageSize());
            caseResult.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = caseResultManager.queryTotal(caseResult);
            List<CaseResultResVo> resCase = new ArrayList<CaseResultResVo>();
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<CaseResult> caseResultList = caseResultManager.queryList(caseResult);
                for (CaseResult c : caseResultList) {
                    CaseResultResVo resVo = JSON.parseObject(JSON.toJSONString(c), CaseResultResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resCase.add(resVo);
                }
            } else {
                log.info("查询用例执行结果列表为空");
            }
            result.setList(resCase);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询用例执行结果列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<InterfaceResultResVo> queryInterfaceResultPage(PaginationQuery<ResultReqVo> query) {
        PaginationResult<InterfaceResultResVo> result = new PaginationResult<InterfaceResultResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        try {
            InterfaceResult interfaceResult = JSON.parseObject(JSON.toJSONString(query.getQuery()), InterfaceResult.class);
            interfaceResult.setPageSize(pagination.getPageSize());
            interfaceResult.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            int totalRecord = interfaceResultManager.queryTotal(interfaceResult);
            List<InterfaceResultResVo> resInterface = new ArrayList<InterfaceResultResVo>();
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<InterfaceResult> interfaceResultList = interfaceResultManager.queryList(interfaceResult);
                for (InterfaceResult i : interfaceResultList) {
                    InterfaceResultResVo resVo = JSON.parseObject(JSON.toJSONString(i), InterfaceResultResVo.class);
                    resVo.setTypeStr(StatusEnum.ReqType_Status.getDesc(i.getType()));
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resInterface.add(resVo);
                }
            } else {
                log.info("查询接口执行结果列表为空");
            }
            result.setList(resInterface);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询接口执行结果列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    @Override
    public BasicResult deleteResultById(ResultReqVo resultReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskResult taskResult = JSON.parseObject(JSON.toJSONString(resultReqVo), TaskResult.class);
            taskResultManager.delete(taskResult);
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(resultReqVo), CaseResult.class);
            caseResultManager.delete(caseResult);
            InterfaceResult interfaceResult = JSON.parseObject(JSON.toJSONString(resultReqVo), InterfaceResult.class);
            interfaceResultManager.delete(interfaceResult);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("根据执行结果编号删除结果信息异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
            throw new Exception();
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteResultByTask(ResultReqVo resultReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskResult taskResult = JSON.parseObject(JSON.toJSONString(resultReqVo), TaskResult.class);
            taskResultManager.deleteByTask(taskResult);
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(resultReqVo), CaseResult.class);
            caseResultManager.deleteByTask(caseResult);
            InterfaceResult interfaceResult = JSON.parseObject(JSON.toJSONString(resultReqVo), InterfaceResult.class);
            interfaceResultManager.deleteByTask(interfaceResult);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("根据计划编号删除所有执行结果:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
            throw new Exception();
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<TaskResultResVo> queryTaskResultDetail(ResultReqVo resultReqVo) {
        GenericResult<TaskResultResVo> result = new GenericResult<TaskResultResVo>();
        try {
            TaskResult taskResult = JSON.parseObject(JSON.toJSONString(resultReqVo), TaskResult.class);
            TaskResult res = taskResultManager.queryObject(taskResult);
            if (null != res) {
                TaskResultResVo resVo = JSON.parseObject(JSON.toJSONString(res), TaskResultResVo.class);
                LinkedHashMap<String, String> taskParamMap = JSON.parseObject(res.getTaskParam(), LinkedHashMap.class);
                resVo.setTaskParamMap(taskParamMap);
                result.setValue(resVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.error("查询计划结果不存在");
            }
        } catch (Exception e) {
            log.error("查询的计划结果信息异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<CaseResultResVo> queryCaseResultDetail(ResultReqVo resultReqVo) {
        GenericResult<CaseResultResVo> result = new GenericResult<CaseResultResVo>();
        try {
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(resultReqVo), CaseResult.class);
            CaseResult res = caseResultManager.queryObject(caseResult);
            if (null != res) {
                CaseResultResVo resVo = JSON.parseObject(JSON.toJSONString(res), CaseResultResVo.class);
                LinkedHashMap<String, String> caseParam = JSON.parseObject(res.getCaseParam(), LinkedHashMap.class);
                resVo.setCaseParamMap(caseParam);
                result.setValue(resVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.error("查询用例执行结果不存在");
            }
        } catch (Exception e) {
            log.error("查询用例执行结果异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<InterfaceResultResVo> queryInterfaceResultDetail(ResultReqVo resultReqVo) {
        GenericResult<InterfaceResultResVo> result = new GenericResult<InterfaceResultResVo>();
        try {
            InterfaceResult interfaceResult = JSON.parseObject(JSON.toJSONString(resultReqVo), InterfaceResult.class);
            InterfaceResult res = interfaceResultManager.queryObject(interfaceResult);
            if (null != res) {
                InterfaceResultResVo resVo = JSON.parseObject(JSON.toJSONString(res), InterfaceResultResVo.class);
                List<CheckPoint> checkPointList = (List<CheckPoint>) GsonUtils.fromJson(res.getCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
                }.getType());
                resVo.setCheckPointList(checkPointList);
                List<CheckPoint> dataCheckList = (List<CheckPoint>) GsonUtils.fromJson(res.getDataCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
                }.getType());
                resVo.setDataCheckList(dataCheckList);
                List<DataFetch> dataFetchList = (List<DataFetch>) GsonUtils.fromJson(res.getDataFetch(), new TypeToken<ArrayList<DataFetch>>() {
                }.getType());
                resVo.setDataFetchList(dataFetchList);
                LinkedHashMap<String, String> headMap = JSON.parseObject(res.getHead(), LinkedHashMap.class);
                resVo.setHeadMap(headMap);
                result.setValue(resVo);
                result.setInfo(ResultInfoEnum.SUCCESS);
            } else {
                log.error("查询接口执行结果不存在");
            }
        } catch (Exception e) {
            log.error("查询接口执行结果异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<CaseResultResVo> queryCaseResultList(ResultReqVo resultReqVo) {
        ListResult<CaseResultResVo> result = new ListResult<CaseResultResVo>();
        List<CaseResultResVo> resVoList = new ArrayList<CaseResultResVo>();
        try {
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(resultReqVo), CaseResult.class);
            List<CaseResult> resList = caseResultManager.queryList(caseResult);
            if (null != resList && resList.size() > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (CaseResult c : resList) {
                    CaseResultResVo resVo = JSON.parseObject(JSON.toJSONString(c), CaseResultResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVoList.add(resVo);
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询用例结果集合异常：{}", e.toString());
        } finally {
            return result;
        }
    }
}
