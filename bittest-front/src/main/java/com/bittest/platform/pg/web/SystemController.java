package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.export.vo.SystemResVo;
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
 * 2018-08-25.
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController {

    private Logger log = LoggerFactory.getLogger(SystemController.class);

    @Autowired
    private SystemResource systemResource;


    /**
     * 跳转到系统列表界面
     *
     * @return
     */
    @RequestMapping("/toSystemList")
    public String toSystemList() {
        return "/bit/system/systemList";
    }

    /**
     * 系统列表界面异步调用 查询 系统分页信息
     *
     * @param model
     * @param aoData
     * @param systemReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/querySystemList")
    public DataTablesQueryResult queryCaseListTable(Model model, String aoData, SystemReqVo systemReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<SystemReqVo> reqVo = new PaginationQuery<SystemReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        systemReqVo.setPin(getPin());
        reqVo.setQuery(systemReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<SystemResVo> result = systemResource.querySystemPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("查询用例分页信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 跳转到系统列表界面
     *
     * @return
     */
    @RequestMapping("/toSystemAdd")
    public String toSystemAdd() {
        return "/bit/system/systemAdd";
    }

    /**
     * 跳转到系统列表界面
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/systemAdd")
    public BasicResult systemAdd(SystemReqVo systemReqVo) {
        try {
            systemReqVo.setPin(getPin());
            systemReqVo.setSystemId(String.valueOf(System.currentTimeMillis()));
        } catch (Exception e) {
            log.error("保存系统时 生成jsf uuid异常 或 获取pin信息异常,{}", e.toString());
        }
        return systemResource.saveSystem(systemReqVo);
    }

    /**
     * 跳转到系统编辑界面
     *
     * @return
     */
    @RequestMapping("/toSystemEdit")
    public String toSystemEdit(SystemReqVo systemReqVo, Model model) {
        try {
            GenericResult<SystemResVo> resVo = systemResource.querySystem(systemReqVo);
            if ("000000".equals(resVo.getCode())) {
                model.addAttribute("systemVo", resVo.getValue());
            } else {
                log.error("调用后台系统信息接口查询失败！,resVo:{}", JSON.toJSONString(resVo));
            }
        } catch (Exception e) {
            log.error("查询信息异常！,com.bittest.platform.bg.domain.vo:{}", e.toString());
        }
        return "/bit/system/systemEdit";
    }

    /**
     * 跳转到系统编辑界面
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/systemEdit")
    public BasicResult systemEdit(SystemReqVo systemReqVo) {
        BasicResult result = null;
        try {
            result = systemResource.updateSystem(systemReqVo);
            if (!"000000".equals(result.getCode())) {
                log.error("调用后台系统修改系统信息失败！,resVo:{}", JSON.toJSONString(result));
            }
        } catch (Exception e) {
            log.error("查询信息异常！,com.bittest.platform.bg.domain.vo:{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 删除系统信息
     *
     * @param systemReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteSystem")
    public BasicResult deleteSystem(SystemReqVo systemReqVo) {
        log.info("删除系统信息：{}", JSON.toJSONString(systemReqVo));
        BasicResult result = systemResource.deleteSystem(systemReqVo);
        log.info("删除系统信息返回结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 系统详情
     *
     * @return
     */
    @RequestMapping("/systemDetail")
    public String systemDetail(SystemReqVo systemReqVo, Model model) {
        try {
            GenericResult<SystemResVo> resVo = systemResource.querySystem(systemReqVo);
            if ("000000".equals(resVo.getCode())) {
                model.addAttribute("systemVo", resVo.getValue());
            } else {
                log.error("查询系统详情失败！,resVo:{}", JSON.toJSONString(resVo));
            }
        } catch (Exception e) {
            log.error("查询系统详情异常！,com.bittest.platform.bg.domain.vo:{}", e.toString());
        } finally {
            return "/bit/system/systemDetail";
        }
    }

}
