package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.ResultResource;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.pg.common.ExcelUtils;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * 2018-08-28.
 */
@Controller
@RequestMapping("/result")
public class ResultController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ResultController.class);

    @Autowired
    private ResultResource resultResource;

    @Autowired
    private SystemResource systemResource;

    /**
     * 跳转到任务结果列表页
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("toQueryTaskResultList")
    public String toQueryTaskResultList(Model model, ResultReqVo resultReqVo) {
        log.info("查看任务结果信息");
        model.addAttribute("taskId", resultReqVo.getTaskId());
        return "/bit/result/taskResultList";
    }

    /**
     * 查询任务执行结果列表
     *
     * @param model
     * @param aoData
     * @param resultReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("queryTaskResultList")
    public DataTablesQueryResult queryTaskResultList(Model model, String aoData, ResultReqVo resultReqVo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<ResultReqVo> reqVo = new PaginationQuery<ResultReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        resultReqVo.setPin(getPin());
        reqVo.setQuery(resultReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<TaskResultResVo> result = resultResource.queryTaskResultPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询任务执行结果列表：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 跳转到用例结果列表页
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("toQueryCaseResultList")
    public String toQueryCaseResultList(Model model, ResultReqVo resultReqVo) {
        log.info("查看用例结果信息");
        SystemReqVo systemReqVo = new SystemReqVo();
        systemReqVo.setPin(getPin());
        ListResult<SystemResVo> systems = systemResource.querySystemList(systemReqVo);
        log.info("获取用户系统列表:{}", JSON.toJSONString(systems));
        model.addAttribute("systems", systems.getList());
        model.addAttribute("resultId", resultReqVo.getResultId());
        model.addAttribute("caseId", resultReqVo.getCaseId());
        return "/bit/result/caseResultList";
    }

    /**
     * 查询任务执行结果列表
     *
     * @param model
     * @param aoData
     * @param resultReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("queryCaseResultList")
    public DataTablesQueryResult queryCaseResultList(Model model, String aoData, ResultReqVo resultReqVo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<ResultReqVo> reqVo = new PaginationQuery<ResultReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        resultReqVo.setPin(getPin());
        reqVo.setQuery(resultReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<CaseResultResVo> result = resultResource.queryCaseResultPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询任务执行结果列表：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }


    /**
     * 跳转到用例结果列表页
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("toQueryInterfaceResultList")
    public String toQueryInterfaceResultList(Model model, ResultReqVo resultReqVo) {
        log.info("查看接口结果信息");
        model.addAttribute("resultId", resultReqVo.getResultId());
        model.addAttribute("caseId", resultReqVo.getCaseId());
        return "/bit/result/interfaceResultList";
    }

    /**
     * 查询接口执行结果列表
     *
     * @param model
     * @param aoData
     * @param resultReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("queryInterfaceResultList")
    public DataTablesQueryResult queryInterfaceResultList(Model model, String aoData, ResultReqVo resultReqVo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<ResultReqVo> reqVo = new PaginationQuery<ResultReqVo>();
        Pagination pagination = new Pagination(20, p);//展示条数尽量多
        //获取用户PIN信息
        resultReqVo.setPin(getPin());
        reqVo.setQuery(resultReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<InterfaceResultResVo> result = resultResource.queryInterfaceResultPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询任务执行结果列表：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 根据resultId删除结果报告
     *
     * @param resultReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteResult")
    public BasicResult deleteResult(ResultReqVo resultReqVo) {
        resultReqVo.setPin(getPin());
        log.info("根据resultId删除结果报告：{}", JSON.toJSONString(resultReqVo));
        BasicResult result = resultResource.deleteResultById(resultReqVo);
        log.info("删除结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 根据taskId删除结果报告
     *
     * @param resultReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteResultByTask")
    public BasicResult deleteResultByTask(ResultReqVo resultReqVo) {
        resultReqVo.setPin(getPin());
        log.info("根据taskId删除结果报告：{}", JSON.toJSONString(resultReqVo));
        BasicResult result = resultResource.deleteResultByTask(resultReqVo);
        log.info("删除结果为：{}", JSON.toJSONString(result));
        return result;
    }

    @ResponseBody
    @RequestMapping("exportResult")
    public BasicResult exportResult(ResultReqVo resultReqVo, HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null;
        try {
            resultReqVo.setPin(getPin());
            GenericResult<TaskResultResVo> taskResult = resultResource.queryTaskResultDetail(resultReqVo);
            ListResult<CaseResultResVo> caseResultResList = resultResource.queryCaseResultList(resultReqVo);
            List<CaseResultResVo> caseResultList = caseResultResList.getList();
            String rootpath = request.getRealPath("/");
            String realPath = rootpath + "/model/result.xlsx";
            inputStream = new FileInputStream(realPath);
            XSSFWorkbook wb = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wb.getSheetAt(0);
            //填写计划结果信息
            XSSFRow taskRow = sheet.getRow(2);
            taskRow.getCell(1).setCellValue(taskResult.getValue().getResultName());
            //taskRow.getCell(2).setCellValue(DateUtils.dateToStr(taskResult.getValue().getCreateTime()));
            //taskRow.getCell(3).setCellValue(DateUtils.dateToStr(taskResult.getValue().getUpdateTime()));
            taskRow.getCell(4).setCellValue(taskResult.getValue().getIsfinish() == 1 ? "执行中" : taskResult.getValue().getIsfinish() == 2 ? "已完成" : taskResult.getValue().getIsfinish() == 3 ? "执行异常" : "");
            taskRow.getCell(5).setCellValue(taskResult.getValue().getSuccessRate() + "%");
            XSSFRow caseRow;
            int caseRowIndex = 4;
            if (null != caseResultList && caseResultList.size() > 0) {
                for (int i = 0; i < caseResultList.size(); i++) {
                    if (i == 0) {
                        caseRow = sheet.getRow(caseRowIndex);
                    } else {
                        XSSFRow toRow = sheet.createRow(caseRowIndex + i);
                        //合并单元格
                        sheet.addMergedRegion((new CellRangeAddress(caseRowIndex + i, caseRowIndex + i, 2, 3)));
                        //设置行高
                        toRow.setHeightInPoints(22);
                        ExcelUtils.copyRow(wb, sheet.getRow(caseRowIndex), toRow, false);
                        caseRow = sheet.getRow(caseRowIndex + i);
                    }
                    caseRow.getCell(1).setCellValue(caseResultList.get(i).getCaseId());
                    caseRow.getCell(2).setCellValue(caseResultList.get(i).getCaseName());
                    caseRow.getCell(4).setCellValue(caseResultList.get(i).getSystemName());
                    caseRow.getCell(5).setCellValue(caseResultList.get(i).getResult() == 1 ? "通过" : caseResultList.get(i).getResult() == 2 ? "失败" : "");
                }
            }
            //输出Excel文件
            OutputStream output = response.getOutputStream();
            response.reset();
            response.setHeader("Content-disposition", "attachment; filename=" + taskResult.getValue().getResultName() + ".xlsx");
            response.setContentType("application/msexcel");
            wb.write(output);
            output.close();

        } catch (Exception e) {
            logger.error("导出excel异常,{}", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 任务计划执行结果详情
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("taskResultDetail")
    public String taskResultDetail(Model model, ResultReqVo resultReqVo) {
        log.info("查询任务计划执行结果详情req：{}", JSON.toJSONString(resultReqVo));
        GenericResult<TaskResultResVo> result = resultResource.queryTaskResultDetail(resultReqVo);
        log.info("查询任务计划执行结果详情res:{}", JSON.toJSONString(result));
        model.addAttribute("result", result.getValue());
        return "/bit/result/taskResultDetail";
    }

    /**
     * 用例执行结果详情
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("caseResultDetail")
    public String caseResultDetail(Model model, ResultReqVo resultReqVo) {
        log.info("查询用例执行结果详情req：{}", JSON.toJSONString(resultReqVo));
        GenericResult<CaseResultResVo> result = resultResource.queryCaseResultDetail(resultReqVo);
        log.info("查询用例执行结果详情res:{}", JSON.toJSONString(result));
        model.addAttribute("result", result.getValue());
        return "/bit/result/caseResultDetail";
    }

    /**
     * 用例执行结果详情
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("caseResultDetailForAjax")
    @ResponseBody
    public CaseResultResVo  caseResultDetailForAjax(Model model, ResultReqVo resultReqVo) {
        log.info("查询用例执行结果详情req：{}", JSON.toJSONString(resultReqVo));
        GenericResult<CaseResultResVo> result = resultResource.queryCaseResultDetail(resultReqVo);
        log.info("查询用例执行结果详情res:{}", JSON.toJSONString(result));
        return result.getValue();
    }
    /**
     * 用例执行结果详情
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("interfaceResultDetail")
    public String interfaceResultDetail(Model model, ResultReqVo resultReqVo) {
        log.info("查询接口执行结果详情req：{}", JSON.toJSONString(resultReqVo));
        GenericResult<InterfaceResultResVo> result = resultResource.queryInterfaceResultDetail(resultReqVo);
        log.info("查询接口执行结果详情res:{}", JSON.toJSONString(result));
        model.addAttribute("result", result.getValue());
        return "/bit/result/interfaceResultDetail";
    }

    /**
     * 用例执行结果详情
     *
     * @param model
     * @param resultReqVo
     * @return
     */
    @RequestMapping("sleepResultDetail")
    public String sleepResultDetail(Model model, ResultReqVo resultReqVo) {
        log.info("查询接口执行结果详情req：{}", JSON.toJSONString(resultReqVo));
        GenericResult<InterfaceResultResVo> result = resultResource.queryInterfaceResultDetail(resultReqVo);
        log.info("查询接口执行结果详情res:{}", JSON.toJSONString(result));
        model.addAttribute("result", result.getValue());
        return "/bit/result/sleepResultDetail";
    }

}
