package com.bittest.platform.pg.web;


import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.dao.TimerTaskMapper;
import com.bittest.platform.bg.domain.po.NotifyInfo;
import com.bittest.platform.bg.domain.po.TimerTask;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.result.ResultInfoEnum;
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
import java.util.List;

@Controller
@RequestMapping("/timerTask/")
public class TimerTaskController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(TimerTaskController.class);

    @Resource(name = "timerTaskMapper")
    protected TimerTaskMapper timerTaskMapper;

    @RequestMapping("toList")
    public String toList(Model model, NotifyInfo resultReqVo) {
        log.info("查看任务结果信息");
        return "/bit/timerTask/timerTaskList";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public DataTablesQueryResult list(Model model, String aoData, TimerTask timerTask ) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<TimerTask> reqVo = new PaginationQuery<TimerTask>();
        PaginationResult<TimerTask> result = new PaginationResult<TimerTask>();
        try {
            int totalRecord = timerTaskMapper.queryTotal(timerTask);
            Pagination pagination = new Pagination(totalRecord, Constant.pageSize, p);
            reqVo.setQuery(timerTask);
            reqVo.setPagination(pagination);
            if (null == pagination) {
                log.info("未传分页，查默认分页，首页10条");
                pagination = new Pagination(10, 1);
            }
            if (totalRecord > 0) {
                List<TimerTask> taskResultList = timerTaskMapper.queryBySelectiveForPagination(reqVo);
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


}
