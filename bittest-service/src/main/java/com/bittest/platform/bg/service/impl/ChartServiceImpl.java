package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.domain.po.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.ChartReqVo;
import com.bittest.platform.bg.export.vo.ChartResVo;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import com.bittest.platform.bg.export.vo.CountInfoResVo;
import com.bittest.platform.bg.manager.*;
import com.bittest.platform.bg.manager.*;
import com.bittest.platform.bg.service.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018-08-27.
 */
@Service("chartService")
public class ChartServiceImpl implements ChartService {

    private Logger log = LoggerFactory.getLogger(ChartServiceImpl.class);

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private CaseInfoManager caseInfoManager;

    @Autowired
    private TaskResultManager taskResultManager;

    @Autowired
    private CountInfoManager countInfoManager;

    @Autowired
    private InterfaceManager interfaceManager;

    @Override
    public GenericResult<ChartResVo> queryTaskChart(ChartReqVo reqVo) {
        GenericResult<ChartResVo> result = new GenericResult<ChartResVo>();
        ChartResVo resVo = new ChartResVo();
        List<String> taskName = new ArrayList<String>();
        List<Double> rate = new ArrayList<Double>();
        try {
            //获取任务计划总数
            Task task = new Task();
            int taskCount = taskManager.queryTotal(task);
            resVo.setTaskCount(taskCount);
            //获取用例总数
            int caseCount = caseInfoManager.queryCaseTotal();
            resVo.setCaseCount(caseCount);
            //获取用户总数
            CountInfo countInfo = new CountInfo();
            int userCount = countInfoManager.queryCounInfotCount(countInfo);
            resVo.setUserCount(userCount);
            //获取接口总数
            InterfaceInfo interfaceInfo = new InterfaceInfo();
            int interfaceCount = interfaceManager.queryTotal(interfaceInfo);
            resVo.setInterfaceCount(interfaceCount);

            TaskResult taskResult = new TaskResult();
            taskResult.setPin(reqVo.getPin());
            taskResult.setPageSize(10);
            taskResult.setStartNo(0);
            //获取最近执行前十条任务结果
            List<TaskResult> taskResultList = taskResultManager.queryList(taskResult);
            if (null != taskResultList && taskResultList.size() > 0) {
                for (TaskResult tr : taskResultList) {
                    taskName.add(tr.getTaskName());
                    rate.add(Double.parseDouble(tr.getSuccessRate()));
                }
            }
            resVo.setTaskNameTopTen(taskName);
            resVo.setTaskResultRateTopTen(rate);
            result.setValue(resVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("统计首页报表信息异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public GenericResult<ChartResVo> querySystemChart(ChartReqVo reqVo) {
        GenericResult<ChartResVo> result = new GenericResult<ChartResVo>();
        ChartResVo resVo = new ChartResVo();
        List<String> systemName = new ArrayList<String>();
        List<Integer> caseCounts = new ArrayList<Integer>();
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setPin(reqVo.getPin());
            List<CaseInfo> resList = caseInfoManager.queryCaseChart(caseInfo);
            if (null != resList && resList.size() > 0) {
                for (CaseInfo c : resList) {
                    systemName.add(c.getSystemName());
                    caseCounts.add(c.getCaseCount());
                }
            }
            resVo.setSystemName(systemName);
            resVo.setCaseCounts(caseCounts);
            result.setValue(resVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("统计各系统用例数量异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }
    }

    @Override
    public PaginationResult<CountInfoResVo> queryCountInfoByPage(PaginationQuery<CountInfoReqVo> query) {
        PaginationResult<CountInfoResVo> result = new PaginationResult<CountInfoResVo>();
        Pagination pagination = query.getPagination();
        if (null == pagination) {
            log.info("未传分页，查默认分页，首页10条");
            pagination = new Pagination(10, 1);
        }
        int totalRecord = 0;
        List<CountInfoResVo> res = null;
        try {
            CountInfo countInfo = JSON.parseObject(JSON.toJSONString(query.getQuery()), CountInfo.class);
            countInfo.setPageSize(pagination.getPageSize());
            countInfo.setStartNo((pagination.getpageNo() - 1) * pagination.getPageSize());
            totalRecord = countInfoManager.queryCounInfotCount(countInfo);
            res = new ArrayList<CountInfoResVo>();
            if (totalRecord > 0) {
                List<CountInfo> countList = countInfoManager.queryCounInfotListByPage(countInfo);
                for (CountInfo c : countList) {
                    CountInfoResVo resVo = JSON.parseObject(JSON.toJSONString(c), CountInfoResVo.class);
                    res.add(resVo);
                }

            } else {
                log.info("查询任务列表为空");
            }
            result.setList(res);
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询用户统计信息异常:{}", e.toString());
        } finally {
            return result;
        }
    }
}
