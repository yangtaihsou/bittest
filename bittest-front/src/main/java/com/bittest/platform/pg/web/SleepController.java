package com.bittest.platform.pg.web;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.InterfaceResource;
import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
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
@RequestMapping("/sleep")
public class SleepController extends BaseController {


    @Autowired
    private InterfaceResource interfaceResource;


    /**
     * 跳转到sleep添加界面
     *
     * @return
     */
    @RequestMapping("/toSleepAdd")
    public String toSleepAdd(Model model, String caseId) {
        logger.info("跳转到sleep添加页");
        if (StringUtils.isEmpty(caseId)) {
            logger.error("caseId为空");
        }
        model.addAttribute("caseId", caseId);
        return "bit/sleep/sleepAdd";
    }

    /**
     * sleep添加
     *
     * @param reqVo
     * @return
     */
    @ResponseBody
    @RequestMapping("/sleepAdd")
    public BasicResult sleepAdd(InterfaceReqVo reqVo) {
        BasicResult result = null;
        try {
            reqVo.setPin(getPin());
            if (null != reqVo) {
                reqVo.setPin(getPin());
                reqVo.setInterfaceId(String.valueOf(System.currentTimeMillis()));
                reqVo.setType(StatusEnum.ReqType_Status.SLEEP.status());
            }
            logger.info("sleep添加:{}", JSON.toJSONString(reqVo));
            result = interfaceResource.saveInterface(reqVo);
            logger.info("sleep添加结果：{}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("sleep保存异常：{}", e.toString());
        }
        return result;
    }

    /**
     * 跳转到sleep编辑面
     *
     * @return
     */
    @RequestMapping("/toSleepEdit")
    public String toSleepEdit(Model model, InterfaceReqVo reqVo) {
        try {
            logger.info("跳转到sleep编辑页");
            //查询sleep信息
            reqVo.setPin(getPin());
            GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(reqVo);
            model.addAttribute("sleep", result.getValue());
        } catch (Exception e) {
            logger.error("进入sleep编辑时异常：{}", e.toString());
        }
        return "bit/sleep/sleepEdit";
    }

    /**
     * sleep编辑
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/sleepEdit")
    public BasicResult sleepEdit(Model model, InterfaceReqVo reqVo) {
        BasicResult result = null;
        try {
            reqVo.setPin(getPin());
            reqVo.setType(StatusEnum.ReqType_Status.SLEEP.status());
            logger.info("修改sleep信息:{}", JSON.toJSONString(reqVo));
            //修改sleep信息
            result = interfaceResource.updateInterface(reqVo);
            logger.info("修改sleep结果为：{}", JSON.toJSONString(result));
        } catch (Exception e) {
            logger.error("保存sleep编辑时异常：{}", e.toString());
        }
        return result;
    }


//    /**
//     * 删除用例信息
//     * @param caseInfoReqVo
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping("/deleteSleep")
//    public BasicResult deleteCase(CaseInfoReqVo caseInfoReqVo){
//        logger.info("删除sleep信息:{}",JSON.toJSONString(caseInfoReqVo));
//        //查询用例信息
//        BasicResult result = interface.deleteCaseInfo(caseInfoReqVo);
//        log.info("删除sleep结果为：{}",JSON.toJSONString(result));
//        return result;
//    }

    /**
     * sleep详情
     *
     * @return
     */
    @RequestMapping("/sleepDetail")
    public String caseDetail(Model model, InterfaceReqVo reqVo) {
        logger.info("跳转到sleep详情");
        try {
            reqVo.setPin(getPin());
            //查询用例信息
            GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(reqVo);
            model.addAttribute("sleep", result.getValue());
        } catch (Exception e) {
            logger.error("获取sleep详情异常:{}", e.toString());
        }
        return "bit/sleep/sleepDetail";
    }


}
