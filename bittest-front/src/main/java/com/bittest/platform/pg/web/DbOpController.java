package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.DataBaseResource;
import com.bittest.platform.bg.export.resource.InterfaceResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;
import com.bittest.platform.pg.common.StatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 2018-08-20.
 */
@Controller
@RequestMapping("/dbOp")
public class DbOpController extends BaseController {

    @Autowired
    private InterfaceResource interfaceResource;

    @Autowired
    private DataBaseResource dataBaseResource;


    /**
     * 跳转到dbOp添加界面
     *
     * @return
     */
    @RequestMapping("/toDbOpAdd")
    public String toSleepAdd(Model model, String caseId) {
        logger.info("跳转到toDbOpAdd添加页");
        try {
            if (StringUtils.isEmpty(caseId)) {
                logger.error("caseId为空");
            }
            //获取个人数据库列表
            DataBaseReqVo reqVo = new DataBaseReqVo();
            reqVo.setPin(getPin());
            ListResult<DataBaseResVo> result = dataBaseResource.queryTotalDataBase(reqVo);
            model.addAttribute("db", result.getList());
            model.addAttribute("caseId", caseId);
        } catch (Exception e) {
            logger.error("跳转dbOp新增页面异常：{}", e.toString());
        }
        return "bit/dbOperate/dbOpAdd";
    }

    /**
     * dbOp添加
     *
     * @param reqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/dbOpAdd")
    public BasicResult dbOpAdd(InterfaceReqVo reqVo) {
        BasicResult result = null;
        try {
            reqVo.setPin(getPin());
            if (null != reqVo) {
                reqVo.setPin(getPin());
                reqVo.setType(StatusEnum.ReqType_Status.DATABASE.status());
            }
            logger.info("dbOpAdd添加:{}", JSON.toJSONString(reqVo));
            result = interfaceResource.saveInterface(reqVo);
            logger.info("dbOpAdd添加结果：{}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("dbOpAdd保存异常：{}", e.toString());
        }
        return result;
    }

    /**
     * 跳转到sleep编辑面
     *
     * @return
     */
    @RequestMapping("/toDbOpEdit")
    public String toSleepEdit(Model model, InterfaceReqVo reqVo) {
        try {
            logger.info("跳转到sleep编辑页");
            //查询sleep信息
            //获取个人数据库列表
            DataBaseReqVo dbVo = new DataBaseReqVo();
            dbVo.setPin(getPin());
            ListResult<DataBaseResVo> dbResult = dataBaseResource.queryTotalDataBase(dbVo);
            model.addAttribute("db", dbResult.getList());
            GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(reqVo);
            model.addAttribute("dbOp", result.getValue());
        } catch (Exception e) {
            logger.error("进入dbOp编辑时异常：{}", e.toString());
        }
        return "bit/dbOperate/dbOpEdit";
    }

    /**
     * dbOp编辑
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/dbOpEdit")
    public BasicResult sleepEdit(Model model, InterfaceReqVo reqVo) {
        BasicResult result = null;
        try {
            reqVo.setPin(getPin());
            reqVo.setType(StatusEnum.ReqType_Status.DATABASE.status());
            logger.info("修改dbOp信息:{}", JSON.toJSONString(reqVo));
            //修改sleep信息
            result = interfaceResource.updateInterface(reqVo);
            logger.info("修改dbOp结果为：{}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("保存dbOp编辑时异常：{}", e.toString());
        }
        return result;
    }


    /**
     * dbOp详情
     *
     * @return
     */
    @RequestMapping("/dbOpDetail")
    public String caseDetail(Model model, InterfaceReqVo reqVo) {
        logger.info("跳转到dbOp详情");
        try {
            reqVo.setPin(getPin());
            //查询用例信息
            GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(reqVo);
            model.addAttribute("dbOp", result.getValue());
        } catch (Exception e) {
            logger.error("获取dbOp详情异常:{}", e.toString());
        }
        return "bit/dbOperate/dbOpDetail";
    }


}
