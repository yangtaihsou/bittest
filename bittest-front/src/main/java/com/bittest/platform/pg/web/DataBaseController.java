package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.DataBaseResource;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;
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
 * 2018-08-23.
 */
@Controller
@RequestMapping("/database")
public class DataBaseController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(DataBaseController.class);

    @Autowired
    private DataBaseResource dataBaseResource;


    /**
     * 跳转到数据库添加页面
     *
     * @return
     */
    @RequestMapping("/toDataBaseAdd")
    public String toDataBaseAdd(Model model) {
        log.info("跳转到数据库添加页面");
        return "bit/database/dataBaseAdd";
    }

    /**
     * 跳转到数据库列表页面
     *
     * @return
     */
    @RequestMapping("/toDataBaseList")
    public String toDataBaseList() {
        log.info("跳转到数据库列表页面");
        return "bit/database/dataBaseList";
    }

    /**
     * 保存数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/dataBaseAdd")
    public BasicResult dataBaseAdd(DataBaseReqVo dataBaseReqVo) {
        log.info("调用数据库保存接口：req{}", JSON.toJSONString(dataBaseReqVo));
        dataBaseReqVo.setDatabaseId(String.valueOf(System.currentTimeMillis()));
        dataBaseReqVo.setPin(getPin());
        BasicResult result = dataBaseResource.saveDataBase(dataBaseReqVo);
        log.info("调用数据库保存接口返回结果：res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 分页查询数据库信息列表
     *
     * @param model
     * @param aoData
     * @param dataBaseReqVo
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryDataBaseList")
    public DataTablesQueryResult queryDataBaseList(Model model, String aoData, DataBaseReqVo dataBaseReqVo, HttpServletRequest request) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<DataBaseReqVo> reqVo = new PaginationQuery<DataBaseReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        dataBaseReqVo.setPin(getPin());
        reqVo.setQuery(dataBaseReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<DataBaseResVo> result = dataBaseResource.queryDataBasePage(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("查询用例分页信息：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 跳转到数据库编辑界面
     *
     * @param model
     * @param dataBaseReqVo
     * @return
     */
    @RequestMapping("/toDataBaseEdit")
    public String toDataBaseEdit(Model model, DataBaseReqVo dataBaseReqVo) {
        dataBaseReqVo.setPin(getPin());
        GenericResult<DataBaseResVo> resVo = dataBaseResource.queryDataBase(dataBaseReqVo);
        if ("000000".equals(resVo.getCode())) {
            model.addAttribute("database", resVo.getValue());
        } else {
            log.error("查询数据库信息异常");
        }
        return "bit/database/dataBaseEdit";
    }

    /**
     * 编辑数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/dataBaseEdit")
    public BasicResult dataBaseEdit(DataBaseReqVo dataBaseReqVo) {
        dataBaseReqVo.setPin(getPin());
        log.info("调用修改数据库信息接口：req{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseResource.updateDataBase(dataBaseReqVo);
        log.info("修改数据库信息返回结果：res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 测试数据库连接
     *
     * @param dataBaseReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/connectDataBase")
    public BasicResult connectDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("请求测试数据库连接接口:req{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseResource.connectDataBase(dataBaseReqVo);
        log.info("执行后返回结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 删除数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteDataBase")
    public BasicResult deleteDataBase(DataBaseReqVo dataBaseReqVo) {
        log.info("删除数据库信息:req{}", JSON.toJSONString(dataBaseReqVo));
        BasicResult result = dataBaseResource.deleteDataBase(dataBaseReqVo);
        log.info("删除数据库结果为：res{}", JSON.toJSONString(result));
        return result;
    }


    /**
     * 查询用户所有数据库列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryUserDataBase")
    public ListResult<DataBaseResVo> queryUserDataBase() {
        DataBaseReqVo dataBaseReqVo = new DataBaseReqVo();
        dataBaseReqVo.setPin(getPin());
        log.info("异步查询用户所有数据库连接:req{}", JSON.toJSONString(dataBaseReqVo));
        ListResult<DataBaseResVo> result = dataBaseResource.queryTotalDataBase(dataBaseReqVo);
        log.info("执行后返回结果为：res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 数据库详情
     *
     * @param model
     * @param dataBaseReqVo
     * @return
     */
    @RequestMapping("/dataBaseDetail")
    public String dataBaseDetail(Model model, DataBaseReqVo dataBaseReqVo) {
        dataBaseReqVo.setPin(getPin());
        GenericResult<DataBaseResVo> resVo = dataBaseResource.queryDataBase(dataBaseReqVo);
        if ("000000".equals(resVo.getCode())) {
            model.addAttribute("database", resVo.getValue());
        } else {
            log.error("查询数据库信息异常");
        }
        return "bit/database/dataBaseDetail";
    }
}
