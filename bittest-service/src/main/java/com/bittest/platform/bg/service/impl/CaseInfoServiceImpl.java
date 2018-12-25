package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.CaseInfo;
import com.bittest.platform.bg.domain.po.CaseInterfaceRelastion;
import com.bittest.platform.bg.domain.po.TaskCaseRelastion;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.CaseInfoReqVo;
import com.bittest.platform.bg.export.vo.CaseInfoResVo;
import com.bittest.platform.bg.export.vo.TaskCaseRelastionReqVo;
import com.bittest.platform.bg.manager.CaseInfoManager;
import com.bittest.platform.bg.manager.CaseInterfaceRelastionManager;
import com.bittest.platform.bg.manager.TaskCaseRelastionManager;
import com.bittest.platform.bg.service.CaseInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 2018-08-26.
 */
@Service("caseInfoService")
public class CaseInfoServiceImpl implements CaseInfoService {

    private static final Logger log = LoggerFactory.getLogger(CaseInfoService.class);

    @Autowired
    private CaseInfoManager caseInfoManager;

    @Autowired
    private TaskCaseRelastionManager taskCaseRelastionManager;

    @Autowired
    private CaseInterfaceRelastionManager caseInterfaceRelastionManager;


    @Override
    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {RuntimeException.class})
    public BasicResult updateCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        BasicResult result = new BasicResult();
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(caseInfoReqVo), CaseInfo.class);
            caseInfo.setCaseParam(JSON.toJSONString(caseInfoReqVo.getCaseParamMap()));
            caseInfoManager.update(caseInfo);
            if (null != caseInfoReqVo.getOrders() && caseInfoReqVo.getOrders().size() > 0) {
                for (String interfaceId : caseInfoReqVo.getOrders().keySet()) {
                    CaseInterfaceRelastion relastion = new CaseInterfaceRelastion();
                    relastion.setCaseId(caseInfoReqVo.getCaseId());
                    relastion.setInterfaceId(interfaceId);
                    relastion.setInterfaceOrder(Integer.parseInt(caseInfoReqVo.getOrders().get(interfaceId)));
                    caseInterfaceRelastionManager.update(relastion);
                }
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("修改案例信息失败:{}", e.toString());
            throw new RuntimeException("保存用例信息失败");
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<CaseInfoResVo> queryCaseInfo(CaseInfoReqVo caseInfoReqVo) {

        GenericResult<CaseInfoResVo> result = new GenericResult<CaseInfoResVo>();
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(caseInfoReqVo), CaseInfo.class);
            CaseInfo res = caseInfoManager.queryObject(caseInfo);
            CaseInfoResVo caseInfoResVo = null;
            if (null != res) {
                caseInfoResVo = JSON.parseObject(JSON.toJSONString(res), CaseInfoResVo.class);
                caseInfoResVo.setCaseParamMap(JSON.parseObject(res.getCaseParam(), HashMap.class));
                result.setInfo(ResultInfoEnum.SUCCESS);
                result.setValue(caseInfoResVo);
            } else {
                log.info("查询用例信息不存在:{}", JSON.toJSONString(caseInfoReqVo));
            }
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("查询用例信息失败:{}", e.toString());
        } finally {
            return result;
        }

    }


    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {AppRuntimeException.class})
    @Override
    public GenericResult<CaseInfoResVo> saveCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        GenericResult<CaseInfoResVo> result = new GenericResult<CaseInfoResVo>();
        CaseInfoResVo resVo = new CaseInfoResVo();
        resVo.setCaseId(caseInfoReqVo.getCaseId());
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(caseInfoReqVo), CaseInfo.class);
            caseInfo.setCaseParam(JSON.toJSONString(caseInfoReqVo.getCaseParamMap()));
            //如果任务Id不为空，新增任务及用例关联关系
            if (StringUtils.isNotEmpty(caseInfoReqVo.getTaskId())) {
                TaskCaseRelastion taskCaseRelastion = new TaskCaseRelastion();
                taskCaseRelastion.setTaskId(caseInfoReqVo.getTaskId());
                taskCaseRelastion.setCaseId(caseInfoReqVo.getCaseId());
                taskCaseRelastionManager.save(taskCaseRelastion);
            }
            result.setValue(resVo);
            caseInfoManager.save(caseInfo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (DuplicateKeyException e) {
            result.setInfo(ResultInfoEnum.SUCCESS);
            log.info("用例重复保存:{}", JSON.toJSONString(caseInfoReqVo));
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("保存案例执行失败:{}", e.toString());
            throw new AppRuntimeException("保存案例失败");
        } finally {
            return result;
        }
    }


    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {Exception.class})
    @Override
    public BasicResult deleteCaseInfo(CaseInfoReqVo caseInfoReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskCaseRelastion taskCaseRelastion = new TaskCaseRelastion();
            taskCaseRelastion.setCaseId(caseInfoReqVo.getCaseId());
            int caseUseTotal = taskCaseRelastionManager.queryCaseUseTotal(taskCaseRelastion);
            if (caseUseTotal > 0) {
                result.setCode("999999");
                String message = "当前用例已在" + caseUseTotal + "个任务计划中使用，不能删除！";
                result.setMessage(message);
                return result;
            } else {
                //删除用例及接口关联信息
                caseInfoManager.delete(caseInfoReqVo.getCaseId());
                CaseInterfaceRelastion caseInterfaceRelastion = new CaseInterfaceRelastion();
                caseInterfaceRelastion.setCaseId(caseInfoReqVo.getCaseId());
                caseInterfaceRelastionManager.deleteByCase(caseInterfaceRelastion);
            }
            log.info("删除案例执行成功");
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("删除案例执行失败:{}", e.toString());
            throw new Exception();
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPage(PaginationQuery<CaseInfoReqVo> query) {
        PaginationResult<CaseInfoResVo> result = new PaginationResult<CaseInfoResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        int totalRecord = 0;
        List<CaseInfoResVo> resTask = new ArrayList<CaseInfoResVo>();
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(query.getQuery()), CaseInfo.class);
            caseInfo.setPageSize(pagination.getPageSize());
            caseInfo.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            totalRecord = caseInfoManager.queryTotal(caseInfo);
            if (totalRecord > 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<CaseInfo> caseList = caseInfoManager.queryList(caseInfo);
                for (CaseInfo c : caseList) {
                    CaseInfoResVo resVo = JSON.parseObject(JSON.toJSONString(c), CaseInfoResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }

            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询用例列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageByTask(PaginationQuery<CaseInfoReqVo> query) {
        PaginationResult<CaseInfoResVo> result = new PaginationResult<CaseInfoResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        int totalRecord = 0;
        List<CaseInfoResVo> resTask = null;
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(query.getQuery()), CaseInfo.class);
            caseInfo.setPageSize(pagination.getPageSize());
            caseInfo.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            totalRecord = caseInfoManager.queryCaseTotalByTask(caseInfo);
            resTask = new ArrayList<CaseInfoResVo>();
            if (totalRecord > 0) {
                List<CaseInfo> caseList = caseInfoManager.queryCaseListByTask(caseInfo);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (CaseInfo c : caseList) {
                    CaseInfoResVo resVo = JSON.parseObject(JSON.toJSONString(c), CaseInfoResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }

            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("根据task编号分页查询用例列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<CaseInfoResVo> queryCaseInfoPageNoFetch(PaginationQuery<CaseInfoReqVo> query) {
        PaginationResult<CaseInfoResVo> result = new PaginationResult<CaseInfoResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        int totalRecord = 0;
        List<CaseInfoResVo> resTask = null;
        try {
            CaseInfo caseInfo = JSON.parseObject(JSON.toJSONString(query.getQuery()), CaseInfo.class);
            caseInfo.setPageSize(pagination.getPageSize());
            caseInfo.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            totalRecord = caseInfoManager.queryCaseInfoPageNoFetchTotal(caseInfo);
            resTask = new ArrayList<CaseInfoResVo>();
            if (totalRecord > 0) {
                List<CaseInfo> caseList = caseInfoManager.queryCaseInfoPageNoFetch(caseInfo);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (CaseInfo c : caseList) {
                    CaseInfoResVo resVo = JSON.parseObject(JSON.toJSONString(c), CaseInfoResVo.class);
                    resVo.setCreateTimeStr(sdf.format(resVo.getCreateTime()));
                    resVo.setUpdateTimeStr(sdf.format(resVo.getUpdateTime()));
                    resTask.add(resVo);
                }

            } else {
                log.info("查询任务列表为空");
            }
            result.setList(resTask);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("根据task编号分页查询用例列表失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, rollbackFor = {AppRuntimeException.class})
    @Override
    public BasicResult AddFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskCaseRelastion taskCaseRelastion = new TaskCaseRelastion();
            for (String caseId : taskCaseRelastionReqVo.getCaseIds()) {
                taskCaseRelastion.setCaseId(caseId);
                taskCaseRelastion.setTaskId(taskCaseRelastionReqVo.getTaskId());
                taskCaseRelastionManager.save(taskCaseRelastion);
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("保存用例关联失败:{}", e.toString());
            throw new AppRuntimeException("保存用例关联失败");
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult updateFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskCaseRelastion taskCaseRelastion = JSON.parseObject(JSON.toJSONString(taskCaseRelastionReqVo), TaskCaseRelastion.class);
            taskCaseRelastionManager.update(taskCaseRelastion);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("编辑用例关联失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public BasicResult deleteFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        BasicResult result = new BasicResult();
        try {
            TaskCaseRelastion taskCaseRelastion = JSON.parseObject(JSON.toJSONString(taskCaseRelastionReqVo), TaskCaseRelastion.class);
            taskCaseRelastionManager.delete(taskCaseRelastion);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.info("删除用例关联失败:{}", e.toString());
        } finally {
            return result;
        }
    }

    @Override
    public ListResult<CaseInfoResVo> queryCaseByName(CaseInfoReqVo caseInfoReqVo) {
        ListResult<CaseInfoResVo> result = new ListResult<CaseInfoResVo>();
        List<CaseInfoResVo> resVoList = new ArrayList<CaseInfoResVo>();
        try {
            CaseInfo req = JSON.parseObject(JSON.toJSONString(caseInfoReqVo), CaseInfo.class);
            List<CaseInfo> caseInfoList = caseInfoManager.queryCaseByName(req);
            if (null != caseInfoList && caseInfoList.size() > 0) {
                for (CaseInfo caseInfo : caseInfoList) {
                    CaseInfoResVo resVo = JSON.parseObject(JSON.toJSONString(caseInfo), CaseInfoResVo.class);
                    resVoList.add(resVo);
                }
            }
            result.setList(resVoList);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("根据名称查询用例列表异常：{}", e.toString());
        } finally {
            return result;
        }
    }
}
