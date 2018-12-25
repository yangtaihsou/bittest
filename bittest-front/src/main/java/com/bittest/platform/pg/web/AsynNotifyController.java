package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.enums.StatusEnum;
import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;
import com.bittest.platform.bg.dao.NotifyInfoMapper;
import com.bittest.platform.bg.domain.po.NotifyInfo;
import com.bittest.platform.bg.domain.po.TaskResult;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.RequestInterfaceResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/asyn")
public class AsynNotifyController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(InterfaceController.class);

    @Autowired
    NotifyInfoMapper notifyInfoMapper;

    /**
     * 接收异步通知，keyField表示要解析的关键字段属性。一般表示单号、uuid等唯一。
     * @param sysCode
     * @param keyField
     * @param context
     * @return
     */
    @RequestMapping("/sub/{sysCode}/{keyField}")
    @ResponseBody
    public void subSave(@PathVariable String sysCode, @PathVariable String keyField,@RequestBody String context) {
        log.info("系统订阅异步通知，解析字段{},内容{}", sysCode+"@@@"+keyField,context);
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setSysCode(sysCode);
        notifyInfo.setFieldKey(keyField);
        String fieldIdValue = ParameterReplaceUtils.getParaValue(context,keyField);
        notifyInfo.setFieldId(fieldIdValue);
        notifyInfo.setContext(context);
        try {
            int result = notifyInfoMapper.save(notifyInfo);
            log.info("系统订阅异步通知,保存结果{}", result);
        }catch (Exception e){
            log.info("系统订阅异步通知异常信息:",e.getMessage());
            if(e instanceof DuplicateKeyException){
                int result = notifyInfoMapper.updateByUUID(notifyInfo);
                return;
            }
            throw new RuntimeException(e);
        }
        return;
    }

    @RequestMapping("/query/{sysCode}/{keyField}/{keyValue}")
    @ResponseBody
    public GenericResult notifyQuery(@PathVariable String sysCode,@PathVariable String keyField,@PathVariable String keyValue) {
        GenericResult result = new GenericResult();
        try {
            NotifyInfo notifyInfo = new NotifyInfo();
            notifyInfo.setSysCode(sysCode);
            notifyInfo.setFieldKey(keyField);
            notifyInfo.setFieldId(keyValue);
            NotifyInfo notifyInfoResult = notifyInfoMapper.queryByUUID(notifyInfo);
            if(notifyInfoResult!=null){
                result.setInfo(ResultInfoEnum.SUCCESS);
                result.setValue(notifyInfoResult);
            }else{
                result.setInfo(ResultInfoEnum.ERROR);
                result.setValue("查询数据为空");
            }
            return result;
        }catch (Exception e){
            result.setInfo(ResultInfoEnum.ERROR);
            result.setValue(e.getMessage());
            return result;
        }
    }


    @RequestMapping("toQueryNotifyList")
    public String toQueryNotifyList(Model model, NotifyInfo resultReqVo) {
        log.info("查看任务结果信息");
       /* model.addAttribute("taskId", resultReqVo.getTaskId());*/
        return "/bit/notify/notifyList";
    }


    @ResponseBody
    @RequestMapping("queryNotifyList")
    public DataTablesQueryResult queryNotifyList(Model model, String aoData, NotifyInfo notifyInfo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<NotifyInfo> reqVo = new PaginationQuery<NotifyInfo>();

        PaginationResult<NotifyInfo> result = new PaginationResult<NotifyInfo>();
        try {
            int totalRecord = notifyInfoMapper.queryTotal(notifyInfo);
            Pagination pagination = new Pagination(totalRecord,Constant.pageSize, p);
            reqVo.setQuery(notifyInfo);
            reqVo.setPagination(pagination);
            if (null == pagination) {
                log.info("未传分页，查默认分页，首页10条");
                pagination = new Pagination(10, 1);
            }
            if (totalRecord > 0) {
                List<NotifyInfo> taskResultList = notifyInfoMapper.queryBySelectiveForPagination(reqVo);
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
