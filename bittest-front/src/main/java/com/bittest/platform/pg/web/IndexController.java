package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.ChartResource;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.ChartReqVo;
import com.bittest.platform.bg.export.vo.ChartResVo;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import com.bittest.platform.bg.export.vo.CountInfoResVo;
import com.bittest.platform.pg.domain.ErrorResult;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.PropertiesUtils;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController extends BaseController {

    private String logoutUrl = PropertiesUtils.loginout.get("erp_logoutUrl");

    private String domain = PropertiesUtils.authen.get("webapp.domain.name");

    @Autowired
    private ChartResource chartResource;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index(HttpServletRequest request) {
        logger.info("访问首页");
        ModelAndView mv = new ModelAndView("index");
        String pin = getPin();
        String currentRoles = getRole();
        mv.addObject("pin", pin);
        mv.addObject("currentRoles", currentRoles);

        return mv;
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String welcome(Model model) {
        logger.info("统计页");
        ChartReqVo chartReqVo = new ChartReqVo();
        chartReqVo.setPin(getPin());
        GenericResult<ChartResVo> taskResult = chartResource.queryTaskChart(chartReqVo);
        GenericResult<ChartResVo> systemResult = chartResource.querySystemChart(chartReqVo);
        model.addAttribute("chart", taskResult.getValue());
        model.addAttribute("x", JSON.toJSONString(taskResult.getValue().getTaskNameTopTen()));
        model.addAttribute("systems", JSON.toJSONString(systemResult.getValue().getSystemName()));
        model.addAttribute("systemCaseCounts", JSON.toJSONString(systemResult.getValue().getCaseCounts()));
        return "/index/chart";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("redirect:" + logoutUrl + domain);
        //SSOHelper.logout(response,"cbpmgt.com");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/error/catchError")
    public ErrorResult errorMsg(HttpServletResponse response, String type) {
        ErrorResult er = new ErrorResult();
        if ("1".equals(type)) {
            er.setErrorCode("403");
            er.setErrorMsg("没有请求权限，请联系管理员");
        } else if ("2".equals(type)) {
            er.setErrorCode("500");
            er.setErrorMsg("服务器内部错误");
        } else if ("3".equals(type)) {
            er.setErrorCode("501");
            er.setErrorMsg("附件总大小不能超过10M");
        }
        return er;

    }

    /**
     * 跳转到用户统计页面
     *
     * @return
     */
    @RequestMapping("/toQueryCountList")
    public String toQueryCountList() {
        return "/index/countList";
    }

    /**
     * 异步查询用户统计列表信息
     *
     * @param model
     * @param aoData
     * @param countInfoReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCountList")
    public DataTablesQueryResult queryCountList(Model model, String aoData, CountInfoReqVo countInfoReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<CountInfoReqVo> reqVo = new PaginationQuery<CountInfoReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        reqVo.setQuery(countInfoReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<CountInfoResVo> result = chartResource.queryCountInfoByPage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        logger.info("异步查询用户统计列表信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

}
