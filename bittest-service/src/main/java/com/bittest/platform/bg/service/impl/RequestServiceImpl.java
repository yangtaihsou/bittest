package com.bittest.platform.bg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.context.CaseParaApplicationContext;
import com.bittest.platform.bg.common.enums.StatusEnum;
import com.bittest.platform.bg.common.utils.*;
import com.bittest.platform.bg.domain.po.*;
import com.bittest.platform.bg.domain.vo.Ips;
import com.bittest.platform.bg.domain.vo.JsfAlias;
import com.bittest.platform.bg.domain.vo.JsfInfo;
import com.bittest.platform.bg.domain.vo.JsfIps;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.bg.manager.*;
import com.bittest.platform.bg.service.RequestService;
import com.google.gson.reflect.TypeToken;
import io.netty.util.internal.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 2018-08-27.
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceImpl.class);

    private static final int pageSize = 10;


    @Autowired
    private DataBaseManager dataBaseManager;

    @Autowired
    private CaseInfoManager caseInfoManager;

    @Autowired
    private InterfaceManager interfaceManager;

    @Autowired
    private TaskManager taskManager;

    @Autowired
    private TaskResultManager taskResultManager;

    @Autowired
    private CaseResultManager caseResultManager;

    @Autowired
    private InterfaceResultManager interfaceResultManager;

    @Autowired
    private SystemsManager systemsManager;

    /**
     * 访问jsf接口
     *
     * @param interfaceInfo
     * @return
     */
    public RequestInterfaceResVo jsfRequest(InterfaceInfo interfaceInfo) {
        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        resVo.setReqBody(interfaceInfo.getBody());
        try {
            HttpRes res = null;//TODO JsfUtils.jsfRequest(interfaceInfo);
            resVo.setResBody(res.getResBody());
            resVo.setResStatus(res.getStatus());
        } catch (Exception e) {
            resVo.setErrorMessage(e.toString());
            log.error("jsf调用异常：{}", e.toString());
        } finally {
            return resVo;
        }
    }

    /**
     * 普通接口单次调用处理
     *
     * @param interfaceReqVo
     * @return 接口执行结果
     */
    @Override
    public GenericResult<RequestInterfaceResVo> interfaceReq(InterfaceReqVo interfaceReqVo, Map<String, String> caseParam, Map<String, String> taskParam) {
        GenericResult<RequestInterfaceResVo> result = new GenericResult<RequestInterfaceResVo>();
        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        if (null == interfaceReqVo) {
            result.setInfo(ResultInfoEnum.NULL_OBJECT_ERROR);
            return result;
        }
        try {
            //替换请求参数化信息
            String requestParamStr = ParameterReplaceUtils.replaceParam(JSON.toJSONString(interfaceReqVo), caseParam, taskParam);
            interfaceReqVo = JSON.parseObject(requestParamStr, InterfaceReqVo.class);
            //接口调用
            resVo = this.request(interfaceReqVo);
            buildInterfaceResult(resVo,interfaceReqVo,caseParam,taskParam);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            resVo.setResult(false);
        } finally {
            result.setValue(resVo);
            return result;
        }
    }

    private void buildInterfaceResult( RequestInterfaceResVo resVo,InterfaceReqVo interfaceReqVo , Map<String, String> caseParam, Map<String, String> taskParam){
        boolean interfaceResult = true;
        //如果接口执行返回结果为200 成功 进行检查点及数据提取
        if (resVo.getResStatus() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
            if (null != interfaceReqVo.getDataFetchList() && interfaceReqVo.getDataFetchList().size() > 0) {
                dataFech(resVo.getResBody(), interfaceReqVo.getDataFetchList(), caseParam);//response提取器进行处理
                String requestParamStr = ParameterReplaceUtils.replaceParam(JSON.toJSONString(interfaceReqVo), caseParam, taskParam);
                interfaceReqVo = JSON.parseObject(requestParamStr, InterfaceReqVo.class);//对结果进行填充
            }
            setResDataToContext(interfaceReqVo, resVo);
            boolean checkResult = checkPoint(resVo.getResBody(), interfaceReqVo.getCheckPointList());
            boolean dataCheckReslt = dataCheckPoint(resVo.getResBody(), interfaceReqVo.getDataCheckList());

            if (!checkResult || !dataCheckReslt) {
                interfaceResult = false;
            }
        } else {
            interfaceResult = false;
        }
        resVo.setCheckPointList(interfaceReqVo.getCheckPointList());
        resVo.setDataFetchList(interfaceReqVo.getDataFetchList());
        resVo.setDataCheckList(interfaceReqVo.getDataCheckList());
        resVo.setReqType(interfaceReqVo.getType());
        resVo.setUrl(interfaceReqVo.getUrl());
        resVo.setHeadMap(interfaceReqVo.getHeadMap());
        resVo.setResult(interfaceResult);
    }

    @Override
    public BasicResult interfaceReqByTask(RequestReqVo requestReqVo) {
        BasicResult result = new BasicResult();
        try {
            exeTaskAndSave(requestReqVo.getTaskId());
            result.setInfo(ResultInfoEnum.SUCCESS);
            return result;
        } catch (Exception e) {
            log.info(requestReqVo.getTaskId() + "执行测试计划并保存时异常:{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
            return result;
        }
    }

    /**
     * 执行测试任务并保存任务执行结果
     *
     * @param taskId
     */
    private void exeTaskAndSave(String taskId) {
        CaseParaApplicationContext.setContext(null);
        int casePassCount = 0;//用例执行成功数
        //用例总数
        int caseTotal = 0;
        String successRate = "0.00";
        TaskResult taskResult = new TaskResult();

        Map<String,Integer> taskExeResultMap = new HashMap<String, Integer>();
        taskExeResultMap.put("caseTotal",caseTotal);
        taskExeResultMap.put("casePassCount",casePassCount);
        try {
            String taskResultId = String.valueOf(System.currentTimeMillis());
            taskResult.setResultId(taskResultId);
            Task task = initTaskResult(taskId, taskResultId);//首先将对任务结果表进行初始化
            taskExeResultMap = exeTask(task, taskResultId);//执行任务
            taskResult.setIsfinish(StatusEnum.Finish_Status.RUN_FINISH.status());
        } catch (Exception e) {
            log.info(taskId + "执行测试计划异常:{}", e.toString());
            taskResult.setIsfinish(StatusEnum.Finish_Status.RUN_EXCEPTION.status());
        } finally {
            casePassCount = taskExeResultMap.get("casePassCount");
            caseTotal = taskExeResultMap.get("caseTotal");
            if (caseTotal == 0) {
                taskResult.setIsfinish(StatusEnum.Finish_Status.RUN_FINISH.status());
            }else {
                DecimalFormat decimalFormat = new DecimalFormat(".00");
                successRate = decimalFormat.format((float) casePassCount / caseTotal * 100);
            }
            taskResult.setSuccessRate(successRate);
            //修改任务执行状态及成功率
            taskResultManager.update(taskResult);
        }
    }

    /**
     * 初始化测试任务信息到测试任务结果表
     *
     * @param taskId
     * @param taskResultId
     * @return
     */
    private Task initTaskResult(String taskId, String taskResultId) {
        Task task = new Task();
        task.setTaskId(taskId);
        //查询计划信息
        Task taskRes = taskManager.queryObject(task);
        TaskResult taskResult = JSON.parseObject(JSON.toJSONString(taskRes), TaskResult.class);
        taskResult.setResultName(taskResult.getTaskName().concat(DateUtil.currentDate_yyyyMMddHHmmss()));
        taskResult.setResultId(taskResultId);
        taskResult.setIsfinish(StatusEnum.Finish_Status.RUN_ING.status());
        //初始化测试任务信息到测试任务结果表
        taskResultManager.save(taskResult);
        return taskRes;
    }

    /**
     * 执行测试任务
     *
     * @param task
     * @param taskResultId
     * @return
     */
    private Map<String,Integer> exeTask(Task task, String taskResultId) {
        Map<String,Integer> resultMap = new HashMap<String, Integer>();
        int caseTotal = 0;
        //用例执行成功数
        int casePassCount = 0;
        String taskParam = ParameterReplaceUtils.replaceParam(task.getTaskParam(), null, null);
        //任务参数化信息
        Map<String, String> taskParamMap = JSON.parseObject(taskParam, HashMap.class);
        CaseInfo caseInfo = new CaseInfo();
        caseInfo.setTaskId(task.getTaskId());
        caseInfo.setStatus(1); //用例状态可用
        caseInfo.setPageSize(20);//默认任务下的可用用例最多20个
        caseInfo.setStartNo(0);
        //分页查询用例信息
        List<CaseInfo> caseInfoList = caseInfoManager.queryCaseListByTask(caseInfo);
        if(caseInfoList!=null) {
            caseTotal = caseInfoList.size();
            for (CaseInfo caseInfo1 : caseInfoList) {
                Boolean caseExeResult = exeCaseAndSave(caseInfo1, taskParamMap, taskResultId, task.getTaskId());
                if (caseExeResult) {
                    casePassCount++;
                }
            }
        }
        resultMap.put("caseTotal",caseTotal);
        resultMap.put("casePassCount",casePassCount);
        return resultMap;
    }

    /**
     * 执行case下的所有接口，并保存case执行结果。里面涵盖了接口执行结果的保存
     *
     * @param caseInfo
     * @param taskParamMap
     * @param taskResultId
     * @param taskId
     * @return
     */
    private Boolean exeCaseAndSave(CaseInfo caseInfo, Map<String, String> taskParamMap, String taskResultId, String taskId) {
        Boolean caseExeResult = Boolean.TRUE;
        try {

            InterfaceInfo interfaceInfo = new InterfaceInfo();
            interfaceInfo.setStatus(1);
            interfaceInfo.setCaseId(caseInfo.getCaseId());
            //根据用例编号 查询所属所有接口信息
            List<InterfaceInfo> interfaceInfoList = interfaceManager.queryInterfaceByCase(interfaceInfo);

            //case全局参数信里的变量
            String caseParam = ParameterReplaceUtils.replaceParam(caseInfo.getCaseParam(), null, taskParamMap);
            Map<String, String> caseParamMap = JSON.parseObject(caseParam, HashMap.class);
            for (InterfaceInfo interfaceInfoResult : interfaceInfoList) {
                Boolean interExeResult = exeInterfaceAndSave(interfaceInfoResult, taskParamMap, caseParamMap, taskResultId, taskId, caseInfo.getCaseId());
                if (!interExeResult) {
                    caseExeResult = Boolean.FALSE;//统计case通过的数量，需要case下所有接口都通过。有一个接口没有通过，就算失败。
                }
            }
            CaseResult caseResult = JSON.parseObject(JSON.toJSONString(caseInfo), CaseResult.class);
            Systems systems = new Systems();
            systems.setSystemId(caseInfo.getSysId());
            caseResult.setSystemName(systemsManager.queryObject(systems).getSystemName());
            caseResult.setResultId(taskResultId);
            caseResult.setTaskId(taskId);
            if (caseExeResult) {
                caseResult.setResult(StatusEnum.Result_Status.RESULT_PASS.status());
            } else {
                caseResult.setResult(StatusEnum.Result_Status.RESULT_FAIL.status());
            }
            //保存用例执行结果
            caseResultManager.save(caseResult);
        } catch (Exception e) {
            log.info("请求case执行并保存请求结果过程中，报错:" + e.getMessage());
            caseExeResult = Boolean.FALSE;
        }
        return caseExeResult;
    }

    private Boolean exeInterfaceAndSave(InterfaceInfo interfaceInfo, Map<String, String> taskParamMap, Map<String, String> caseParamMap, String taskResultId, String taskId, String caseId) {
        Boolean exeResult = Boolean.TRUE;
        try {
            InterfaceReqVo reqVo = getInterfaceReqVo(interfaceInfo);
            GenericResult<RequestInterfaceResVo> res = this.interfaceReq(reqVo, caseParamMap, taskParamMap);
            if (res == null || res.getValue() == null || !res.getValue().getResult()) {
                exeResult = Boolean.FALSE;
            }
            InterfaceResult interfaceResult = JSON.parseObject(JSON.toJSONString(interfaceInfo), InterfaceResult.class);
            interfaceResult.setDataFetch(JSON.toJSONString(res.getValue().getDataFetchList()));
            interfaceResult.setDataCheckPoint(JSON.toJSONString(res.getValue().getDataCheckList()));
            interfaceResult.setCheckPoint(JSON.toJSONString(res.getValue().getCheckPointList()));
            interfaceResult.setResBody(res.getValue().getResBody());
            interfaceResult.setResultCode(res.getValue().getResStatus());
            interfaceResult.setBody(res.getValue().getReqBody());
            interfaceResult.setResultId(taskResultId);
            interfaceResult.setTaskId(taskId);
            interfaceResult.setCaseId(caseId);
            interfaceResult.setUrl(res.getValue().getUrl());
            interfaceResult.setHead(JSON.toJSONString(res.getValue().getHeadMap()));
            if (exeResult) {
                interfaceResult.setResult(StatusEnum.Result_Status.RESULT_PASS.status());
            } else {
                interfaceResult.setResult(StatusEnum.Result_Status.RESULT_FAIL.status());
            }
            //保存接口执行结果
            interfaceResultManager.save(interfaceResult);//保存失败，也算执行失败
        } catch (Exception e) {
            log.info("请求接口执行并保存请求结果过程中，报错:" + e.getMessage());
            exeResult = Boolean.FALSE;
        }
        return exeResult;
    }

    /**
     * 网关方式获取jsf 别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryAlias(JsfInfoReqVo jsfInfoReqVo) {
        GenericResult<JsfInfoResVo> result = new GenericResult<JsfInfoResVo>();
        JsfInfoResVo resVo = new JsfInfoResVo();
        String url = PropertiesUtil.jSfAddress.get("jsf.load.alias.url");
        StringBuffer body = new StringBuffer();
        body.append("{\"appId\": \"" + PropertiesUtil.jSfAddress.get("jsf.appId") + "\",");
        body.append("\"erp\": \"" + PropertiesUtil.jSfAddress.get("jsf.erp") + "\",");
        body.append("\"token\": \"" + PropertiesUtil.jSfAddress.get("jsf.token") + "\",");
        body.append("\"ifaceName\": \"" + jsfInfoReqVo.getJsf_url() + "\"}");
        try {
            //解析jsf提供别名、ip、port
            HttpRes aliasIpsRes = HttpUtils.sendByPost(url, body.toString(), null);
            JsfAlias alias = JSON.parseObject(aliasIpsRes.getResBody(), JsfAlias.class);
            if (aliasIpsRes.getStatus() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
                if (alias != null && alias.getCode() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
                    if (alias.getResult().size() > 0) {
                        resVo.setAlias(alias.getResult());
                        result.setValue(resVo);
                        result.setInfo(ResultInfoEnum.SUCCESS);
                    } else {
                        result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_EMPTY);
                    }
                } else {
                    log.error("获取jsf alias异常：{}", JSON.toJSONString(alias));
                    result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_EMPTY);
                }
            } else {
                result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
            }
        } catch (Exception e) {
            log.error("获取jsf alias异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
        } finally {
            return result;
        }
    }


    /**
     * 网关方式获取jsf ip:port
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryIps(JsfInfoReqVo jsfInfoReqVo) {
        GenericResult<JsfInfoResVo> result = new GenericResult<JsfInfoResVo>();
        JsfInfoResVo resVo = new JsfInfoResVo();
        List<String> ips = new ArrayList<String>();
        String url = PropertiesUtil.jSfAddress.get("jsf.load.ips.url");
        String body = "[{\"appId\":\"" + PropertiesUtil.jSfAddress.get("jsf.appId") + "\",\"token\":\"" + PropertiesUtil.jSfAddress.get("jsf.token") + "\",\"ifaceName\":\"" + jsfInfoReqVo.getJsf_url() + "\",\"alias\":\"" + jsfInfoReqVo.getJsf_alias() + "\",\"providerStatus\":1}]";
        try {
            //解析jsf提供别名、ip、port
            HttpRes ipsRes = HttpUtils.sendByPost(url, body, null);
            if (ipsRes.getStatus() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
                JsfIps jsfIps = JSON.parseObject(ipsRes.getResBody(), JsfIps.class);
                if (jsfIps.getCode() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
                    for (Ips ip : jsfIps.getResult()) {
                        ips.add(ip.getIp() + ":" + ip.getPort());
                    }
                    resVo.setIps(ips);
                    result.setValue(resVo);
                    result.setInfo(ResultInfoEnum.SUCCESS);
                } else {
                    log.error("JSFOpenApi获取接口ip返回结果异常：{}", JSON.toJSONString(ipsRes));
                    result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
                }
            } else {
                log.error("调用JSFOpenApi获取接口ip异常：{}", JSON.toJSONString(ipsRes));
                result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
            }
        } catch (Exception e) {
            log.error("获取jsf ips异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
        } finally {
            return result;
        }
    }

    /**
     * 网关方式获取jsf 方法（入参、方法名、返回参数）相关信息
     *
     * @param jsfInfoReqVo
     * @return
     */
    @Override
    public GenericResult<JsfInfoResVo> queryMethods(JsfInfoReqVo jsfInfoReqVo) {
        GenericResult<JsfInfoResVo> result = new GenericResult<JsfInfoResVo>();
        JsfInfoResVo resVo = new JsfInfoResVo();
        try {
            List<String> methods = null;//TODO JsfUtils.getMethod(jsfInfoReqVo);
            resVo.setMethods(methods);
            result.setValue(resVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            log.error("获取jsf Methods异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.JSF_RESOLVE_METHOD_ERROR);
            return result;
        }
        return result;
    }


    /**
     * HTTP GET接口请求
     *
     * @param interfaceInfo
     * @return
     */
    public RequestInterfaceResVo httpGetRequest(InterfaceInfo interfaceInfo) {
        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        HttpRes res = HttpUtils.sendByGet(interfaceInfo.getUrl(), interfaceInfo.getHeadMap());
        resVo.setReqBody(interfaceInfo.getUrl());
        resVo.setResStatus(res.getStatus());
        resVo.setResBody(res.getResBody());
        return resVo;
    }

    /**
     * HTTP POST请求
     *
     * @param interfaceInfo
     * @return
     */
    public RequestInterfaceResVo httpPostRequest(InterfaceInfo interfaceInfo) {

        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        HttpRes res = HttpUtils.sendByPost(interfaceInfo.getUrl(), interfaceInfo.getBody(), interfaceInfo.getHeadMap());
        resVo.setReqBody(interfaceInfo.getBody());
        resVo.setResStatus(res.getStatus());
        resVo.setResBody(res.getResBody());
        return resVo;
    }


    /**
     * http获取jsf别名 ip 方法方式 目前已弃用（更改为jsf网关JSFOpenAPI方式获取）
     *
     * @param jsfQueryReqVo
     * @return
     */
    @Override
    public GenericResult<JsfQueryResVo> jsfQueryInfo(JsfQueryReqVo jsfQueryReqVo) {
        GenericResult<JsfQueryResVo> result = new GenericResult<JsfQueryResVo>();
        JsfQueryResVo resVo = new JsfQueryResVo();
        //解析jsf提供别名
        //获取别名信息
        HttpRes aliasRes = HttpUtils.sendByGet(PropertiesUtil.jSfAddress.get("jsf.alias") + jsfQueryReqVo.getJsf_url(), null);
        //获取方法信息
        HttpRes methodsRes = HttpUtils.sendByGet(PropertiesUtil.jSfAddress.get("jsf.methods") + jsfQueryReqVo.getJsf_url(), null);
        //获取ip服务信息
        HttpRes ipsRes = HttpUtils.sendByGet(PropertiesUtil.jSfAddress.get("jsf.addrs") + jsfQueryReqVo.getJsf_url(), null);

        //封装返回jsf别名信息
        if (aliasRes.getStatus() == StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status()) {
            JsfInfo alias = JSON.parseObject(aliasRes.getResBody(), JsfInfo.class);
            if (null != alias && StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status() == alias.getCode()) {
                resVo.setAlias(alias.getData().getAliases());
                if (null != alias.getData().getAliases() && alias.getData().getAliases().size() > 0) {
                    //封装返回jsf方法信息
                    if (StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status() == methodsRes.getStatus()) {
                        JsfInfo methods = JSON.parseObject(methodsRes.getResBody(), JsfInfo.class);
                        if (null != methods && StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status() == methods.getCode()) {
                            //截取jsf方法名 key为使用方法名  value为展示方法名（带入参）
                            Map<String, String> methodMap = new HashMap<String, String>();
                            for (String m : methods.getData().getMethods()) {
                                //截取" "与"("之间的方法名
                                int start = m.indexOf(" ") + 1;
                                String mKey = m.substring(start, m.indexOf("("));
                                String mValue = m.substring(start);
                                methodMap.put(mKey, mValue);
                            }
                            resVo.setMethods(methodMap);
                        } else {
                            result.setInfo(ResultInfoEnum.JSF_RESOLVE_METHOD_ERROR);
                            log.error("获取jsf方法名信息异常：{}", methods.getMessage());
                        }
                    }

                    //封装返回jsf ip服务信息
                    if (StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status() == ipsRes.getStatus()) {
                        JsfInfo ips = JSON.parseObject(ipsRes.getResBody(), JsfInfo.class);
                        if (null != ipsRes && StatusEnum.ReqType_Status.HTTP_REQ_SUCCESS.status() == ips.getCode()) {
                            List<String> ipsList = new ArrayList<String>();
                            for (String ipKey : ips.getData().getAddrs().keySet()) {
                                for (String ipValue : ips.getData().getAddrs().get(ipKey)) {
                                    ipsList.add(ipKey.concat(":").concat(ipValue));
                                }
                            }
                            resVo.setIps(ipsList);
                            result.setInfo(ResultInfoEnum.SUCCESS);
                        } else {
                            result.setInfo(ResultInfoEnum.JSF_RESOLVE_IPS_ERROR);
                            log.error("获取jsf ip地址信息异常：{}", ips.getMessage());
                        }
                    }
                } else {
                    result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_EMPTY);
                    log.error("无别名信息：{}", alias.getMessage());
                }
            } else {
                result.setInfo(ResultInfoEnum.JSF_RESOLVE_ALIAS_ERROR);
                log.error("获取jsf别名信息异常：{}", alias.getMessage());
            }
        }
        result.setValue(resVo);
        return result;
    }

    @Override
    public  GenericResult<CaseResult> runCase(RequestReqVo requestReqVo){
        CaseParaApplicationContext.setContext(null);
        GenericResult<CaseResult> result = new GenericResult<CaseResult>();
        String taskResultId = requestReqVo.getTaskResultId();
        String taskId = taskResultId;
        CaseResult caseResult = new CaseResult();
        caseResult.setTaskId(taskId);
        caseResult.setResultId(taskResultId);
        result.setValue(caseResult);
        try {
            CaseInfo caseInfo = new CaseInfo();
            caseInfo.setCaseId(requestReqVo.getCaseId());
            //查询用例信息
            CaseInfo res = caseInfoManager.queryObject(caseInfo);
            this.exeCaseAndSave(res,null,taskResultId,taskId); //如果交互好，可以异步执行这个代码。前端再循环获取结果数据展示。
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("用例执行异常：{}", e.toString());
        } finally {
            return result;
        }
    }
    @Override
    public GenericResult<CaseInfoResVo> interfaceReqByCase(RequestReqVo requestReqVo) {
        CaseParaApplicationContext.setContext(null);
        GenericResult<CaseInfoResVo> result = new GenericResult<CaseInfoResVo>();
        List<InterfaceResultResVo> interfaceResultList = new ArrayList<InterfaceResultResVo>();
        Map<String, String> caseParamMap;
        CaseInfo caseInfo = new CaseInfo();
        try {
            caseInfo.setCaseId(requestReqVo.getCaseId());
            //查询用例信息
            CaseInfo res = caseInfoManager.queryObject(caseInfo);
            CaseInfoResVo caseInfoResVo = null;
            caseInfoResVo = JSON.parseObject(JSON.toJSONString(res), CaseInfoResVo.class);
            //参数化信息变量替换
            String caseParam = ParameterReplaceUtils.replaceParam(res.getCaseParam(), null, null);
            caseParamMap = JSON.parseObject(caseParam, LinkedHashMap.class);
            caseInfoResVo.setCaseParamMap(JSON.parseObject(res.getCaseParam(), LinkedHashMap.class));
            InterfaceInfo interfaceInfo = new InterfaceInfo();
            interfaceInfo.setStatus(1);
            interfaceInfo.setCaseId(requestReqVo.getCaseId());
            //根据用例编号 查询所属所有接口信息
            List<InterfaceInfo> interfaceInfoList = interfaceManager.queryInterfaceByCase(interfaceInfo);
            int cResult = StatusEnum.Result_Status.RESULT_PASS.status();
            for (InterfaceInfo it : interfaceInfoList) {
                InterfaceReqVo reqVo = getInterfaceReqVo(it);
                GenericResult<RequestInterfaceResVo> resResult = this.interfaceReq(reqVo, caseParamMap, null);
                InterfaceResultResVo interfaceResultResVo = JSON.parseObject(JSON.toJSONString(it), InterfaceResultResVo.class);
                interfaceResultResVo.setResBody(resResult.getValue().getResBody());
                interfaceResultResVo.setResultCode(resResult.getValue().getResStatus());
                interfaceResultResVo.setBody(resResult.getValue().getReqBody());
                interfaceResultResVo.setTypeStr(StatusEnum.ReqType_Status.getDesc(it.getType()));
                interfaceResultResVo.setResult(resResult.getValue().getResult() ? StatusEnum.Result_Status.RESULT_PASS.status() : StatusEnum.Result_Status.RESULT_FAIL.status());
                interfaceResultResVo.setDataCheckList(resResult.getValue().getDataCheckList());
                interfaceResultResVo.setCheckPointList(resResult.getValue().getCheckPointList());
                interfaceResultResVo.setDataFetchList(resResult.getValue().getDataFetchList());
                interfaceResultResVo.setUrl(resResult.getValue().getUrl());
                // interfaceResultResVo.setHeadMap(reqVo.getHeadMap());
                interfaceResultResVo.setHeadMap(resResult.getValue().getHeadMap());
                interfaceResultList.add(interfaceResultResVo);
                if (!resResult.getValue().getResult()) {
                    cResult = StatusEnum.Result_Status.RESULT_FAIL.status();
                }
            }
            caseInfoResVo.setResult(cResult);
            caseInfoResVo.setInterfaceResultList(interfaceResultList);
            result.setValue(caseInfoResVo);
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("用例执行异常：{}", e.toString());
        } finally {

            return result;
        }
    }


    /**
     * 将接口对象信息 转换为 请求接口请求对象
     *
     * @param it
     * @return
     */
    private InterfaceReqVo getInterfaceReqVo(InterfaceInfo it) {
        InterfaceReqVo interfaceReqVo = JSON.parseObject(JSON.toJSONString(it), InterfaceReqVo.class);
        List<CheckPoint> checkPointList = (List<CheckPoint>) GsonUtils.fromJson(it.getCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
        }.getType());
        interfaceReqVo.setCheckPointList(checkPointList);
        List<CheckPoint> dataCheckList = (List<CheckPoint>) GsonUtils.fromJson(it.getDataCheckPoint(), new TypeToken<ArrayList<CheckPoint>>() {
        }.getType());
        interfaceReqVo.setDataCheckList(dataCheckList);
        List<DataFetch> dataFetchList = (List<DataFetch>) GsonUtils.fromJson(it.getDataFetch(), new TypeToken<ArrayList<DataFetch>>() {
        }.getType());
        interfaceReqVo.setDataFetchList(dataFetchList);
        LinkedHashMap<String, String> headMap = JSON.parseObject(it.getHead(), LinkedHashMap.class);
        interfaceReqVo.setHeadMap(headMap);
        return interfaceReqVo;
    }

    /**
     * 根据接口类型 ，调用不同接口请求
     *
     * @param interfaceReqVo
     * @return
     */
    private RequestInterfaceResVo request(InterfaceReqVo interfaceReqVo) {
        RequestInterfaceResVo resVo = null;
        if (StatusEnum.ReqType_Status.JSF.status() == interfaceReqVo.getType()) {
            resVo = this.jsfRequest(interfaceReqVo);
        } else if (StatusEnum.ReqType_Status.GET.status() == interfaceReqVo.getType()) {
            resVo = this.httpGetRequest(interfaceReqVo);
        } else if (StatusEnum.ReqType_Status.POST.status() == interfaceReqVo.getType()) {
            resVo = this.httpPostRequest(interfaceReqVo);
        } else if (StatusEnum.ReqType_Status.SLEEP.status() == interfaceReqVo.getType()) {
            resVo = SleepUtils.sleep(interfaceReqVo);
        } else if (StatusEnum.ReqType_Status.DATABASE.status() == interfaceReqVo.getType()) {
            resVo = this.dbOp(interfaceReqVo);
        }
        return resVo;
    }

    //将接口返回的response字符串放入threadlocal
    private void setResDataToContext(InterfaceReqVo interfaceReqVo, RequestInterfaceResVo resVo) {
        if (resVo != null) {
            CaseParaApplicationContext context = CaseParaApplicationContext.getContext();
            if (context == null)
                context = new CaseParaApplicationContext();
            context.put(interfaceReqVo.getInterfaceId(), resVo.getResBody());
            CaseParaApplicationContext.setContext(context);
        }
    }

    /**
     * 执行检查点
     *
     * @param resBody
     * @param checkPointList
     * @return
     */
    private boolean checkPoint(String resBody, List<CheckPoint> checkPointList) {
        boolean interfaceResult = true;
        if (null != checkPointList && checkPointList.size() > 0) {
            for (CheckPoint cp : checkPointList) {
                boolean ckResult = CheckUtils.checkData(cp.getCheckValue(), resBody, cp.getCheckMethod());
                //如果检查点验证失败，接口执行结果为失败
                if (!ckResult) {
                    interfaceResult = false;
                }
                cp.setResult(ckResult);
            }
        }
        return interfaceResult;
    }

    /**
     * 执行数据库检查点
     *
     * @param resBody
     * @param checkPointList
     * @return
     */
    private boolean dataCheckPoint(String resBody, List<CheckPoint> checkPointList) {
        boolean interfaceResult = true;
        if (null != checkPointList && checkPointList.size() > 0) {
            for (CheckPoint cp : checkPointList) {
                DataBase dataBase = new DataBase();
                dataBase.setDatabaseId(cp.getDatabaseId());
                try {
                    DataBase db = dataBaseManager.queryObject(dataBase);
                    String rs = DbHelper.getResult(db.getUrl(), db.getUsername(), db.getPassword(), "select " + cp.getWheres());
                    cp.setColumns(rs);
                    cp.setTableName(db.getName());
                    boolean ckResult = CheckUtils.checkData(cp.getCheckValue(), rs, cp.getCheckMethod());
                    cp.setResult(ckResult);
                } catch (Exception e) {
                    cp.setColumns(e.toString());
                    cp.setResult(false);
                } finally {
                    if (!cp.getResult()) {
                        interfaceResult = false;
                    }
                }
            }
        }
        return interfaceResult;
    }

    /**
     * 数据提取
     *
     * @param resBody
     * @param dataFetchList
     * @param caseParam
     */
    private void dataFech(String resBody, List<DataFetch> dataFetchList, Map<String, String> caseParam) {
        try {
            for (DataFetch dataFetch : dataFetchList) {
                String regValue = RegExpressionUtil.regData(dataFetch.getRegular(), resBody, dataFetch.getRegularIndex());
                dataFetch.setResult(regValue);
                if (null != caseParam) {
                    caseParam.put(dataFetch.getParamName(), regValue);
                }
            }
        } catch (Exception e) {
            log.info("数据提取异常：{}", e.toString());
        }
    }

    /**
     * 数据库执行操作
     *
     * @return
     */
    private RequestInterfaceResVo dbOp(InterfaceReqVo interfaceReqVo) {
        RequestInterfaceResVo resVo = new RequestInterfaceResVo();
        try {
            DataBase db = new DataBase();
            db.setPin(interfaceReqVo.getPin());
            db.setDatabaseId(interfaceReqVo.getUrl());
            DataBase dbRes = dataBaseManager.queryObject(db);
            int count = DbHelper.updateStmt(dbRes.getUrl(), dbRes.getUsername(), dbRes.getPassword(), interfaceReqVo.getBody());
            resVo.setResStatus(200);
            resVo.setResBody("受影响行数：" + count);
        } catch (Exception e) {
            resVo.setResStatus(500);
            resVo.setResBody(e.toString());
        } finally {
            return resVo;
        }
    }

}
