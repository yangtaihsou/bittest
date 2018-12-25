package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.context.CaseParaApplicationContext;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.export.resource.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.*;
import com.bittest.platform.pg.common.StatusEnum;
import com.bittest.platform.pg.service.HistoryService;
import com.bittest.platform.pg.service.InterfaceRequestService;
import com.bittest.platform.pg.util.Constant;
import com.bittest.platform.pg.util.datatable.DataTableHelper;
import com.bittest.platform.pg.util.datatable.DataTableParameter;
import com.bittest.platform.pg.util.datatable.DataTablesQueryResult;
import com.bittest.platform.pg.vo.InterfaceRequestReqVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2018-08-29.
 */
@Controller
@RequestMapping("/interface")
public class InterfaceController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(InterfaceController.class);

    @Autowired
    private RequestResource requestResource;

    @Autowired
    private DataBaseResource dataBaseResource;

    @Autowired
    private InterfaceResource interfaceResource;

    @Autowired
    private InterfaceRequestService interfaceRequestService;

    @Autowired
    private InterfaceHistoryResource interfaceHistoryResource;

    @Autowired
    private InterfaceCollectionResource interfaceCollectionResource;

    @Autowired
    private HistoryService historyService;

    @RequestMapping("/toInterfaceAdd")
    public String toInterfaceAdd(Model model, InterfaceReqVo interfaceReqVo) {
        log.info("跳转到接口添加界面");
        model.addAttribute("interfaceReqVo", interfaceReqVo);
        return "/bit/interface/interfaceAdd";
    }

    /**
     * 接口信息保存
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping("/interfaceAdd")
    public BasicResult interfaceAdd(InterfaceRequestReqVo req) {
        req.setPin(getPin());
        InterfaceReqVo reqVo = interfaceRequestService.getInterfaceReqVo(req);
        log.info("接口添加:{}", JSON.toJSONString(reqVo));
        BasicResult result = interfaceResource.saveInterface(reqVo);
        log.info("接口添加返回信息：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 跳转到接口编辑界面
     *
     * @param model
     * @param interfaceReqVo
     * @return
     */
    @RequestMapping("/toInterfaceEdit")
    public String toInterfaceEdit(Model model, InterfaceReqVo interfaceReqVo) {
        log.info("跳转到接口编辑界面：{}", JSON.toJSONString(interfaceReqVo));
        GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(interfaceReqVo);
        log.info("返回查询接口信息为：{}", JSON.toJSONString(result));
        model.addAttribute("interfaces", result.getValue());
        DataBaseReqVo dataBaseReqVo = new DataBaseReqVo();
        dataBaseReqVo.setPin(getPin());
        ListResult<DataBaseResVo> dataBaseList = dataBaseResource.queryTotalDataBase(dataBaseReqVo);
        model.addAttribute("dataBase", dataBaseList.getList());
        log.info("加载数据库信息：{}", JSON.toJSONString(dataBaseList));
        return "/bit/interface/interfaceEdit";
    }

    /**
     * 接口编辑
     *
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping("/interfaceEdit")
    public BasicResult interfaceEdit(InterfaceRequestReqVo req) {
        InterfaceReqVo reqVo = interfaceRequestService.getInterfaceReqVo(req);
        log.info("接口编辑:{}", JSON.toJSONString(reqVo));
        BasicResult result = interfaceResource.updateInterface(reqVo);
        log.info("编辑返回：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 解析jsf接口信息
     *
     * @param jsfQueryReqVo
     * @return
     */
    @RequestMapping("/jsfResolve")
    @ResponseBody
    public GenericResult<JsfQueryResVo> jsfResolve(JsfQueryReqVo jsfQueryReqVo) {
        log.info("解析jsf接口信息,com.bittest.platform.bg.domain.vo{}", jsfQueryReqVo);
        GenericResult<JsfQueryResVo> result = requestResource.jsfQueryInfo(jsfQueryReqVo);
        log.info("解析jsf接口返回信息,res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 解析jsf接口信息 获取别名
     *
     * @param jsfInfoReqVo
     * @return
     */
    @RequestMapping("/jsfResolveAlias")
    @ResponseBody
    public GenericResult<JsfInfoResVo> jsfResolveAlias(JsfInfoReqVo jsfInfoReqVo) {
        GenericResult<JsfInfoResVo> result = null;
        try {
            log.info("解析jsf接口信息 获取别名,com.bittest.platform.bg.domain.vo{}", jsfInfoReqVo);
            result = requestResource.queryAlias(jsfInfoReqVo);
            log.info("解析jsf接口信息 获取别名返回信息,res{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("访问jsf接口解析接口别名信息异常：{}", e.toString());
        } finally {
            return result;
        }

    }

    /**
     * 解析jsf接口信息 获取ips
     *
     * @param jsfInfoReqVo
     * @return
     */
    @RequestMapping("/jsfResolveIps")
    @ResponseBody
    public GenericResult<JsfInfoResVo> jsfResolveIps(JsfInfoReqVo jsfInfoReqVo) {
        GenericResult<JsfInfoResVo> result = null;
        try {
            log.info("解析jsf接口信息 获取ip,com.bittest.platform.bg.domain.vo{}", jsfInfoReqVo);
            result = requestResource.queryIps(jsfInfoReqVo);
            log.info("解析jsf接口信息 获取ip返回信息,res{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("访问jsf接口解析接口ip信息异常：{}", e.toString());
        } finally {
            return result;
        }

    }

    /**
     * 解析jsf接口信息 获取methods
     *
     * @param jsfInfoReqVo
     * @return
     */
    @RequestMapping("/jsfResolveGetMethods")
    @ResponseBody
    public GenericResult<JsfInfoResVo> jsfResolveGetMethods(JsfInfoReqVo jsfInfoReqVo) {
        log.info("解析jsf接口信息 获取methods,com.bittest.platform.bg.domain.vo{}", jsfInfoReqVo);
        GenericResult<JsfInfoResVo> result = requestResource.queryMethods(jsfInfoReqVo);
        log.info("解析jsf接口信息 获取methods返回信息,res{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 接口请求执行
     *
     * @param req
     * @return
     */
    @RequestMapping("/jsfRequest")
    @ResponseBody
    public GenericResult<RequestInterfaceResVo> jsfRequest(InterfaceRequestReqVo req) {
        log.info("接收到前端入参,com.bittest.platform.bg.domain.vo{}", JSON.toJSONString(req));
        req.setPin(getPin());
        GenericResult<RequestInterfaceResVo> result = null;
        CaseParaApplicationContext.setContext(null);
        try {
            InterfaceReqVo reqVo = interfaceRequestService.getInterfaceReqVo(req);
            //异步保存接口执行记录
            InterfaceHistoryReqVo historyReqVo = JSON.parseObject(JSON.toJSONString(reqVo), InterfaceHistoryReqVo.class);
            historyService.saveHistory(historyReqVo);
            result = requestResource.interfaceReq(reqVo);
            //关联结果html转换
            if (null != result.getValue().getDataFetchList() && result.getValue().getDataFetchList().size() > 0) {
                for (DataFetch dataFetch : result.getValue().getDataFetchList()) {
                    String rs = dataFetch.getResult();//StringEscapeUtils.escapeHtml(dataFetch.getResult());
                    dataFetch.setResult(rs);
                }
            }
            log.info("执行接口返回信息,res{}", JSON.toJSONString(result));
        } catch (Exception e) {
            log.info("执行接口异常：{}", e.toString());
            result.setInfo(ResultInfoEnum.ERROR);
        } finally {
            return result;
        }

    }

    /**
     * 用例列表界面异步调用 查询 用例下的接口信息
     *
     * @param model
     * @param interfaceReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryInterfaceByCase")
    public ListResult<InterfaceResVo> queryInterfaceByCase(Model model, InterfaceReqVo interfaceReqVo) {
        ListResult<InterfaceResVo> resList = interfaceResource.queryInterfaceByCase(interfaceReqVo);
        log.info("查询用例分页信息：{}", JSON.toJSONString(resList));
        return resList;
    }

    /**
     * 删除接口信息（如果用例编号存在 则删除关联关系）
     *
     * @param interfaceReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("interfaceDel")
    public BasicResult interfaceDel(InterfaceReqVo interfaceReqVo) {
        log.info("删除接口信息:{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceResource.deleteInterfaceInfo(interfaceReqVo);
        log.info("删除接口结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 启用禁用接口信息（修改关联表状态）
     *
     * @param reqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("relastionUpdate")
    public BasicResult relastionUpdate(CaseInterfaceRelastionReqVo reqVo) {
        log.info("启用禁用关联状态:{}", JSON.toJSONString(reqVo));
        if (reqVo.getStatus() == StatusEnum.Relation_Status.ENABLE.status()) {
            reqVo.setStatus(StatusEnum.Relation_Status.DIS_ENABLE.status());
        } else if (reqVo.getStatus() == StatusEnum.Relation_Status.DIS_ENABLE.status()) {
            reqVo.setStatus(StatusEnum.Relation_Status.ENABLE.status());
        }
        BasicResult result = interfaceResource.updateCaseInterfaceRelation(reqVo);
        log.info("启用禁用结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 跳转到添加以后接口用例列表页
     *
     * @param model
     * @param caseId
     * @return
     */
    @RequestMapping("toAddExitInterfaceList")
    public String toAddExitInterfaceList(Model model, String caseId) {
        log.info("跳转到添加已有接口列表页 caseId为：{}", caseId);
        model.addAttribute("caseId", caseId);
        return "/bit/interface/addExitInterfaceList";
    }

    /**
     * 分页查询接口列表信息
     *
     * @param model
     * @param aoData
     * @param interfaceReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("addExitInterfaceList")
    public DataTablesQueryResult addExitInterfaceList(Model model, String aoData, InterfaceReqVo interfaceReqVo) {
        DataTableParameter dp = DataTableHelper.buildDataTableParameter(aoData);
        Integer p = dp.getPageNo() == null ? 1 : dp.getPageNo();
        PaginationQuery<InterfaceReqVo> reqVo = new PaginationQuery<InterfaceReqVo>();
        Pagination pagination = new Pagination(Constant.pageSize, p);
        //获取用户PIN信息
        interfaceReqVo.setPin(getPin());
        reqVo.setQuery(interfaceReqVo);
        reqVo.setPagination(pagination);
        PaginationResult<InterfaceResVo> result = interfaceResource.queryAllInterfaceList(reqVo);
        DataTablesQueryResult queryResult = new DataTablesQueryResult(result.getList(), result.getPagination().getitemCount(), dp.getsEcho() + 1);
        log.info("分页查询接口列表结果：{}", JSON.toJSONString(queryResult));
        return queryResult;
    }

    /**
     * 添加已有接口信息
     *
     * @param interfaceReqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("addExitInterface")
    public BasicResult addExitInterface(InterfaceReqVo interfaceReqVo) {
        log.info("添加已有接口信息：{}", JSON.toJSONString(interfaceReqVo));
        BasicResult result = interfaceResource.addConstainsInterface(interfaceReqVo);
        log.info("添加结果为：{}", JSON.toJSONString(result));
        return result;
    }

    /**
     * 跳转到添加以后接口用例列表页
     *
     * @param model
     * @return
     */
    @RequestMapping("toQueryInterfaceList")
    public String toQueryInterfaceList(Model model) {
        log.info("跳转到接口管理列表页");
        return "/bit/interface/queryInterfaceList";
    }

    /**
     * 接口详情
     *
     * @param model
     * @param interfaceReqVo
     * @return
     */
    @RequestMapping("/interfaceDetail")
    public String interfaceDetail(Model model, InterfaceReqVo interfaceReqVo) {
        log.info("接口详情：{}", JSON.toJSONString(interfaceReqVo));
        GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(interfaceReqVo);
        log.info("返回查询接口信息为：{}", JSON.toJSONString(result));
        model.addAttribute("interfaces", result.getValue());
        DataBaseReqVo dataBaseReqVo = new DataBaseReqVo();
        dataBaseReqVo.setPin(getPin());
        ListResult<DataBaseResVo> dataBaseList = dataBaseResource.queryTotalDataBase(dataBaseReqVo);
        model.addAttribute("dataBase", dataBaseList.getList());
        log.info("加载数据库信息：{}", JSON.toJSONString(dataBaseList));
        return "/bit/interface/interfaceDetail";
    }

    /**
     * 首页跳转到接口测试工具界面
     *
     * @return
     */
    @RequestMapping("/interfaceTest")
    public String toInterfaceTest() {
        log.debug("跳转到单接口测试界面");
        return "/bit/interface/interfaceTest";
    }

    /**
     * 加载用户接口执行记录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryHistory")
    public ListResult<InterfaceHistoryResVo> queryHistory(String search) {
        ListResult<InterfaceHistoryResVo> result = null;
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        try {
            reqVo.setUrl(search);
            reqVo.setPin(getPin());
            result = interfaceHistoryResource.queryInterfaceHistoryList(reqVo);
        } catch (Exception e) {
            log.info("加载执行记录异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 加载用户收藏接口
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCollection")
    public ListResult<InterfaceCollectionResVo> queryCollection(String search) {
        ListResult<InterfaceCollectionResVo> result = null;
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        try {
            reqVo.setName(search);
            reqVo.setPin(getPin());
            result = interfaceCollectionResource.queryInterfaceCollectionList(reqVo);
        } catch (Exception e) {
            log.info("加载收藏列表异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 查询收藏详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryCollectionDetail")
    public GenericResult<InterfaceCollectionResVo> queryCollectionDetail(String interfaceId) {
        GenericResult<InterfaceCollectionResVo> result = null;
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        try {
            reqVo.setInterfaceId(interfaceId);
            result = interfaceCollectionResource.queryInterfaceCollectionDetail(reqVo);
        } catch (Exception e) {
            log.info("查询收藏详情异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 查询历史记录详情
     */
    @ResponseBody
    @RequestMapping("/queryHistoryDetail")
    public GenericResult<InterfaceHistoryResVo> queryHistoryDetail(Long id) {
        GenericResult<InterfaceHistoryResVo> result = null;
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        try {
            reqVo.setId(id);
            result = interfaceHistoryResource.queryInterfaceHistoryDetail(reqVo);
        } catch (Exception e) {
            log.info("查询历史详情异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 删除历史记录详情
     */
    @ResponseBody
    @RequestMapping("/deleteHistory")
    public BasicResult deleteHistory(Long id) {
        BasicResult result = null;
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        try {
            reqVo.setId(id);
            result = interfaceHistoryResource.deleteInterfaceHistory(reqVo);
        } catch (Exception e) {
            log.info("删除历史记录异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 删除收藏接口
     */
    @ResponseBody
    @RequestMapping("/deleteCollection")
    public BasicResult deleteCollection(String interfaceId) {
        BasicResult result = null;
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        try {
            reqVo.setInterfaceId(interfaceId);
            result = interfaceCollectionResource.deleteInterfaceCollection(reqVo);
        } catch (Exception e) {
            log.info("删除收藏接口异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 保存收藏接口
     */
    @ResponseBody
    @RequestMapping("/saveCollection")
    public GenericResult<InterfaceCollectionResVo> saveCollection(InterfaceRequestReqVo req) {
        GenericResult<InterfaceCollectionResVo> result = null;
        try {
            InterfaceCollectionReqVo reqVo = interfaceRequestService.getCollectionReqVo(req);
            reqVo.setPin(getPin());
            result = interfaceCollectionResource.saveInterfaceCollection(reqVo);
        } catch (Exception e) {
            log.info("保存收藏接口异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 跳转到接口另存为界面
     *
     * @return
     */
    @RequestMapping("/toSaveAsInfo")
    private String toSaveAs() {
        return "/bit/interface/saveAsInfo";
    }

    /**
     * 另存为收藏接口
     */
    @ResponseBody
    @RequestMapping("/saveAsCollection")
    public GenericResult<InterfaceCollectionResVo> saveAsCollection(InterfaceRequestReqVo req, String otherName) {
        GenericResult<InterfaceCollectionResVo> result = null;
        try {
            InterfaceCollectionReqVo reqVo = interfaceRequestService.getCollectionReqVo(req);
            reqVo.setPin(getPin());
            reqVo.setInterfaceId("");
            reqVo.setName(otherName);
            result = interfaceCollectionResource.saveInterfaceCollection(reqVo);
        } catch (Exception e) {
            log.info("另存为收藏接口异常：{}", e.toString());
        } finally {
            return result;
        }
    }

    /**
     * 跳转到保存到用例页面
     *
     * @return
     */
    @RequestMapping("/toSaveCaseInfo")
    private String toSaveCaseInfo() {
        return "/bit/interface/interfaceSaveCaseInfo";
    }

    @RequestMapping("/interfaceSaveCase")
    @ResponseBody
    public BasicResult interfaceSaveCase(InterfaceRequestReqVo req) {
        log.info("接口保存到用例req{}", JSON.toJSONString(req));
        req.setPin(getPin());
        InterfaceReqVo reqVo = interfaceRequestService.getInterfaceReqVo(req);
        BasicResult result = interfaceResource.interfaceSaveCase(reqVo);
        log.info("接口保存到用例res{}", JSON.toJSONString(result));
        return result;
    }


}
