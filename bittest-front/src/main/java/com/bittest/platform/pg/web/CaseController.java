package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.CaseResult;
import com.bittest.platform.bg.export.resource.CaseResource;
import com.bittest.platform.bg.export.resource.RequestResource;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.pg.service.impl.CaseRunThread;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 2018-08-20.
 */
@Controller
@RequestMapping("/case")
public class CaseController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(CaseController.class);

    @Autowired
    private SystemResource systemResource;

    @Autowired
    private CaseResource caseResource;


    @Autowired
    private RequestResource requestResource;

    /**
     * 跳转到用例列表界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toQueryCaseList")
    public String queryCaseList(Model model) {
        log.info("跳转到用例管理列表页");
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        return "bit/case/caseList";
    }

    /**
     * 用例列表界面异步调用 查询 用例分页信息
     *
     * @param model
     * @param aoData
     * @param caseInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCaseList")
    public DataTablesQueryResult queryCaseListTable(Model model, String aoData, CaseInfoReqVo caseInfoReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<CaseInfoReqVo> reqVo = new PaginationQuery<CaseInfoReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        caseInfoReqVo.setPin(getPin());
        reqVo.setQuery(caseInfoReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<CaseInfoResVo> result = caseResource.queryCaseInfoPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("查询用例分页信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 跳转到用例添加界面
     *
     * @return
     */
    @RequestMapping("/toCaseAdd")
    public String toCaseAdd(Model model, String taskId) {
        log.info("跳转到用例添加页");
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        model.addAttribute("taskId", taskId);
        model.addAttribute("systems", systemResource.querySystemList(reqVo).getList());
        return "bit/case/caseAdd";
    }

    /**
     * 跳转到用例编辑面
     *
     * @return
     */
    @RequestMapping("/toCaseEdit")
    public String toCaseAdd(Model model, CaseInfoReqVo caseInfoReqVo) {
        log.info("跳转到用例编辑页");
        ListResult<InterfaceResVo> interfaceList;
        //查询用例信息
        GenericResult<CaseInfoResVo> result = caseResource.queryCaseInfo(caseInfoReqVo);
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        model.addAttribute("cases", result.getValue());
        //获取系统信息
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        return "bit/case/caseEdit";
    }

    /**
     * 用例信息保存
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/caseEdit")
    public BasicResult caseEdit(Model model, CaseInfoReqVo caseInfoReqVo) {
        log.info("修改用例信息:{}", JSON.toJSONString(caseInfoReqVo));
        //查询用例信息
        BasicResult result = caseResource.updateCaseInfo(caseInfoReqVo);
        log.info("修改用例结果为：{}", JSON.toJSONString(result));
        return result;
    }


    /**
     * 用例添加
     *
     * @param caseInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/caseAdd")
    public GenericResult<CaseInfoResVo> caseAdd(CaseInfoReqVo caseInfoReqVo) {
        log.info("用例添加：{}", JSON.toJSONString(caseInfoReqVo));
        if (null != caseInfoReqVo) {
            caseInfoReqVo.setPin(getPin());
            caseInfoReqVo.setCaseId(String.valueOf(System.currentTimeMillis()));
        }
        GenericResult<CaseInfoResVo> result = caseResource.saveCaseInfo(caseInfoReqVo);
        log.info("用例添加结果：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 跳转到已关联用例列表页面
     *
     * @param caseInfoReqVo
     * @return
     */
    @RequestMapping("toFetchCaseList")
    public String toFetchCaseList(Model model, CaseInfoReqVo caseInfoReqVo) {
        model.addAttribute("taskId", caseInfoReqVo.getTaskId());
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        return "bit/case/fetchCaseList";
    }

    /**
     * 异步查询已关联用例列表信息
     *
     * @param model
     * @param aoData
     * @param caseInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryFetchCaseList")
    public DataTablesQueryResult queryFetchCaseList(Model model, String aoData, CaseInfoReqVo caseInfoReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<CaseInfoReqVo> reqVo = new PaginationQuery<CaseInfoReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        caseInfoReqVo.setPin(getPin());
        reqVo.setQuery(caseInfoReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<CaseInfoResVo> result = caseResource.queryCaseInfoPageByTask(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询已关联用例列表信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }


    /**
     * 跳转到未关联用例列表页面
     *
     * @param caseInfoReqVo
     * @return
     */
    @RequestMapping("toNoFetchCaseList")
    public String toNoFetchCaseList(Model model, CaseInfoReqVo caseInfoReqVo) {
        model.addAttribute("taskId", caseInfoReqVo.getTaskId());
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        return "bit/case/noFetchCaseList";
    }

    /**
     * 异步查询未关联用例列表信息
     *
     * @param model
     * @param aoData
     * @param caseInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryNoFetchCaseList")
    public DataTablesQueryResult queryNoFetchCaseList(Model model, String aoData, CaseInfoReqVo caseInfoReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<CaseInfoReqVo> reqVo = new PaginationQuery<CaseInfoReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        caseInfoReqVo.setPin(getPin());
        reqVo.setQuery(caseInfoReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<CaseInfoResVo> result = caseResource.queryCaseInfoPageNoFetch(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询已关联用例列表信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 移除关联用例
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("removeFetchCase")
    public BasicResult removeFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        log.info("移除关联用例：{}", JSON.toJSONString(taskCaseRelastionReqVo));
        BasicResult result = caseResource.deleteFetchCase(taskCaseRelastionReqVo);
        log.info("移除关联用例结果：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 添加关联用例
     *
     * @param taskCaseRelastionReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("addFetchCase")
    public BasicResult addFetchCase(TaskCaseRelastionReqVo taskCaseRelastionReqVo) {
        log.info("添加关联用例：{}", JSON.toJSONString(taskCaseRelastionReqVo));
        BasicResult result = caseResource.AddFetchCase(taskCaseRelastionReqVo);
        log.info("添加关联用例结果：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 执行用例信息
     *
     * @param model
     * @param reqVo
     * @return
     */
    @RequestMapping("runCaseList")
    public String runCaseList(Model model, RequestReqVo reqVo) {
        log.info("执行用例请求：{}", JSON.toJSONString(reqVo));
       // GenericResult<CaseInfoResVo> result = requestResource.interfaceReqByCase(reqVo);
        //return "bit/case/caseResult";
        String taskResultId = "case"+ String.valueOf(System.currentTimeMillis());
        String taskId = taskResultId;
        reqVo.setTaskResultId(taskResultId);
        reqVo.setTaskId(taskId);
        CaseRunThread testRunThread = new CaseRunThread(reqVo, requestResource);
        Thread thread = new Thread(testRunThread);
        try {
            thread.start();
        } catch (Exception e) {
            log.info("异步执行case启动失败:"+e.getMessage());
        }
        model.addAttribute("resultId", taskResultId);
        model.addAttribute("caseId", reqVo.getCaseId());
        model.addAttribute("polling", "true");//此标识，告知前端需要轮询异步执行的结果
        return "/bit/result/interfaceResultList";
    }

    /**
     * 删除用例信息
     *
     * @param caseInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCase")
    public BasicResult deleteCase(CaseInfoReqVo caseInfoReqVo) {
        log.info("删除用例信息:{}", JSON.toJSONString(caseInfoReqVo));
        //查询用例信息
        BasicResult result = caseResource.deleteCaseInfo(caseInfoReqVo);
        log.info("删除用例结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 用例详情
     *
     * @return
     */
    @RequestMapping("/caseDetail")
    public String caseDetail(Model model, CaseInfoReqVo caseInfoReqVo) {
        log.info("跳转到用例详情");
        ListResult<InterfaceResVo> interfaceList;
        //查询用例信息
        GenericResult<CaseInfoResVo> result = caseResource.queryCaseInfo(caseInfoReqVo);
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        model.addAttribute("cases", result.getValue());
        //获取系统信息
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        return "bit/case/caseDetail";
    }

    /**
     * 根据用例名称查询用例列表
     *
     * @param reqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCaseByName")
    public ListResult<CaseInfoResVo> queryCaseByName(CaseInfoReqVo reqVo) {
        ListResult<CaseInfoResVo> result = null;
        try {
            reqVo.setPin(getPin());
            log.info("根据用例名称查询用例列表req：{}", JSON.toJSONString(reqVo));
            result = caseResource.queryCaseByName(reqVo);
            log.info("根据用例名称查询用例列表res：{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("调用根据用例查询用例名称接口异常：{}", e.toString());
        } finally {
            return result;
        }
    }

}
