package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.dao.TimerTaskConfigMapper;
import com.bittest.platform.bg.domain.po.NotifyInfo;
import com.bittest.platform.bg.domain.po.TimerTaskConfig;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/timerTaskConfig")
public class TimerTaskConfigController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(TimerTaskConfigController.class);


    @Resource(name = "timerTaskConfigMapper")
    private TimerTaskConfigMapper timerTaskConfigMapper;


    @RequestMapping("toList")
    public String toList(Model model, NotifyInfo resultReqVo) {
        log.info("查看任务结果信息");
        return "bit/timerTaskConfig/timerTaskConfigList";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public DataTablesQueryResult list(Model model, String aoData, TimerTaskConfig timerTaskConfig ) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<TimerTaskConfig> reqVo = new PaginationQuery<TimerTaskConfig>();
        PaginationResult<TimerTaskConfig> result = new PaginationResult<TimerTaskConfig>();
        try {
            int totalRecord = timerTaskConfigMapper.queryTotal(timerTaskConfig);
            Pagination pagination = new Pagination(totalRecord, Constant.pageSize, p);
            reqVo.setQuery(timerTaskConfig);
            reqVo.setPagination(pagination);
            if (null == pagination) {
                log.info("未传分页，查默认分页，首页10条");
                pagination = new Pagination(10, 1);
            }
            if (totalRecord > 0) {
                List<TimerTaskConfig> taskResultList = timerTaskConfigMapper.queryBySelectiveForPagination(reqVo);
                result.setList(taskResultList);
            } else {
                log.info("查询任务执行结果列表为空");
            }
            result.setPagination(new Pagination(totalRecord, pagination.getPageSize(), pagination.getpageNo()));
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            result.setInfo(ResultInfoEnum.ERROR);
            log.error("分页查询任务执行结果列表失败:{}", e.toString());
        }
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询任务执行结果列表：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }



    @RequestMapping("/toAdd")
    public String toSave() {
        return "/bit/timerTaskConfig/timerTaskConfigAdd";
    }

    /**
     * 新增
     *
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public BasicResult save(TimerTaskConfig timerTaskConfig, HttpServletRequest request) {
        BasicResult basicResult = new BasicResult();
        basicResult.setInfo(ResultInfoEnum.SUCCESS);
        try {
            StringBuilder uuid = new StringBuilder(timerTaskConfig.getTaskId());
            uuid.append("#").append(timerTaskConfig.getYear()).append("#").
                    append(timerTaskConfig.getMonth()).append("#").
                    append(timerTaskConfig.getDay()).append("#").
                    append(timerTaskConfig.getHour()).append("#").
                    append(timerTaskConfig.getMinute()).append("#").
                    append(timerTaskConfig.getSecond());
            timerTaskConfig.setTaskTimerKey(uuid.toString());
            timerTaskConfig.setStatus(0);//初始化是停止的
            int result = timerTaskConfigMapper.save(timerTaskConfig);
            if (result == 0) {
                basicResult.setInfo(ResultInfoEnum.ERROR);
            }
        } catch (Exception e) {
            logger.error(request.getRequestURI(), e);
            basicResult.setInfo(ResultInfoEnum.ERROR);
        }
        return basicResult;
    }




    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit(Model model, Long id, HttpServletRequest request) {
        TimerTaskConfig timerTaskConfig = timerTaskConfigMapper.queryByPrimaryKey(id);
        model.addAttribute("timerTaskConfig", timerTaskConfig);
        return "/bit/timerTaskConfig/timerTaskConfigEdit";
    }


    /**
     * 更新一个
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public BasicResult edit(TimerTaskConfig timerTaskConfig, HttpServletRequest request) {
        BasicResult basicResult = new BasicResult();
        try {
            StringBuilder uuid = new StringBuilder(timerTaskConfig.getTaskId());
            uuid.append("#").append(timerTaskConfig.getYear()).append("#").
                    append(timerTaskConfig.getMonth()).append("#").
                    append(timerTaskConfig.getDay()).append("#").
                    append(timerTaskConfig.getHour()).append("#").
                    append(timerTaskConfig.getMinute()).append("#").
                    append(timerTaskConfig.getSecond());
            timerTaskConfig.setTaskTimerKey(uuid.toString());
            int resultCode = timerTaskConfigMapper.updateByPrimaryKeySelective(timerTaskConfig);
            if (resultCode == 0) {
                basicResult.setInfo(ResultInfoEnum.ERROR);
                return basicResult;
            }
            basicResult.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            logger.error(request.getRequestURI(), e);
            basicResult.setInfo(ResultInfoEnum.ERROR);
        }
        return basicResult;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "updateStatus", method = RequestMethod.POST)
    @ResponseBody
    public BasicResult updateStatus(Long id, Integer status, HttpServletRequest request) {
        BasicResult basicResult = new BasicResult();
        try {
            TimerTaskConfig timerTaskConfig = new TimerTaskConfig();
            timerTaskConfig.setId(id);
            timerTaskConfig.setStatus(status);
            int resultCode = timerTaskConfigMapper.updateByPrimaryKeySelective(timerTaskConfig);
            if (resultCode == 0) {
                basicResult.setInfo(ResultInfoEnum.ERROR);
                return basicResult;
            }
            basicResult.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            logger.error(request.getRequestURI(), e);
            basicResult.setInfo(ResultInfoEnum.ERROR);

        }
        return basicResult;
    }



    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    @ResponseBody
    public BasicResult delete(Long id, HttpServletRequest request) {
        BasicResult result = new BasicResult();
        try {
            int resultCode = timerTaskConfigMapper.deleteByPrimaryKey(id);
            if (resultCode == 0) {
                result.setInfo(ResultInfoEnum.ERROR);
                return result;
            }
            result.setInfo(ResultInfoEnum.SUCCESS);
        } catch (Exception e) {
            logger.error(request.getRequestURI(), e);
            result.setInfo(ResultInfoEnum.ERROR);
        }
        return result;
    }


}