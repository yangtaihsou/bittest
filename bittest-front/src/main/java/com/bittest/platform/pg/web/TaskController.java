package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.resource.TaskResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.pg.service.TaskRunService;
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

/**
 * 2018-08-22.
 */
@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskResource taskResource;


    @Autowired
    private TaskRunService taskRunService;

    @Autowired
    private SystemResource systemResource;

    /**
     * 跳转到任务列表页
     *
     * @return
     */
    @RequestMapping("/toTaskList")
    public String toTaskList(Model model) {
        try {
            SystemReqVo reqVo = new SystemReqVo();
            reqVo.setPin(getPin());
            //获取系统信息
            ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
            model.addAttribute("systems", systemList.getList());
        } catch (Exception e) {
            log.error("跳转到任务列表页时，获取系统信息异常：{}", e.toString());
        } finally {
            return "/bit/task/taskList";
        }
    }

    /**
     * 异步加载任务列表信息
     *
     * @param model
     * @param aoData
     * @param taskReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/taskList")
    public DataTablesQueryResult taskList(Model model, String aoData, TaskReqVo taskReqVo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<TaskReqVo> reqVo = new PaginationQuery<TaskReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        taskReqVo.setPin(getPin());
        reqVo.setQuery(taskReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<TaskResVo> result = taskResource.queryTaskList(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        logger.info(JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 跳转到任务添加页面
     *
     * @return
     */
    @RequestMapping("toTaskAdd")
    public String toTaskAdd(Model model) {
        try {
            SystemReqVo reqVo = new SystemReqVo();
            reqVo.setPin(getPin());
            //获取系统信息
            ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
            model.addAttribute("systems", systemList.getList());
        } catch (Exception e) {
            log.error("跳转任务添加界面时，获取系统信息列表异常：{}", e.toString());
        } finally {
            return "/bit/task/taskAdd";
        }
    }

    /**
     * 任务添加
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("taskAdd")
    public GenericResult<TaskResVo> taskAdd(TaskReqVo taskReqVo) {
        taskReqVo.setTaskId(String.valueOf(System.currentTimeMillis()));
        taskReqVo.setPin(getPin());
        GenericResult<TaskResVo> result = taskResource.saveTask(taskReqVo);
        return result;
    }

    /**
     * 跳转到任务编辑页面
     *
     * @return
     */
    @RequestMapping("toTaskEdit")
    public String toTaskEdit(Model model, TaskReqVo taskReqVo) {
        try {
            GenericResult<TaskResVo> result = taskResource.queryTask(taskReqVo);
            SystemReqVo reqVo = new SystemReqVo();
            reqVo.setPin(getPin());
            ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
            model.addAttribute("systems", systemList.getList());
            model.addAttribute("task", result.getValue());
        } catch (Exception e) {
            log.error("跳转任务修改界面异常：{}", e.toString());
        } finally {
            return "/bit/task/taskEdit";
        }
    }

    /**
     * 任务编辑
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("taskEdit")
    public BasicResult taskEdit(TaskReqVo taskReqVo) {
        BasicResult result = taskResource.updateTask(taskReqVo);
        return result;
    }

    /**
     * 执行 任务计划
     *
     * @param reqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("taskRun")
    public boolean taskRun(RequestReqVo reqVo) {
        return taskRunService.taskRun(reqVo);
    }

    /**
     * 删除任务计划信息
     *
     * @param taskReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("deleteTask")
    public BasicResult deleteTask(TaskReqVo taskReqVo) {
        log.info("删除任务计划信息：{}", JSON.toJSONString(taskReqVo));
        BasicResult result = taskResource.deleteTask(taskReqVo);
        log.info("删除任务计划信息返回结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 任务计划详情
     *
     * @return
     */
    @RequestMapping("taskDetail")
    public String taskDetail(Model model, TaskReqVo taskReqVo) {
        GenericResult<TaskResVo> result = taskResource.queryTask(taskReqVo);
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin(getPin());
        ListResult<SystemResVo> systemList = systemResource.querySystemList(reqVo);
        model.addAttribute("systems", systemList.getList());
        model.addAttribute("task", result.getValue());
        return "/bit/task/taskDetail";
    }

}
