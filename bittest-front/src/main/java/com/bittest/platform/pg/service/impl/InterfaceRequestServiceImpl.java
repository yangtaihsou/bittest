package com.bittest.platform.pg.service.impl;

import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.pg.common.StatusEnum;
import com.bittest.platform.pg.service.InterfaceRequestService;
import com.bittest.platform.pg.vo.InterfaceRequestReqVo;
import org.springframework.stereotype.Service;

/**
 * 2018-08-31.
 */
@Service("interfaceRequestService")
public class InterfaceRequestServiceImpl implements InterfaceRequestService {

    @Override
    public InterfaceReqVo getInterfaceReqVo(InterfaceRequestReqVo req) {
        InterfaceReqVo reqVo = new InterfaceReqVo();
        reqVo.setCaseId(req.getCaseId());
        reqVo.setInterfaceId(req.getInterfaceId());
        reqVo.setType(req.getInterfaceType());
        reqVo.setDataFetchList(req.getDataFetchList());
        reqVo.setCheckPointList(req.getCheckPointList());
        reqVo.setDataCheckList(req.getDataCheckList());
        reqVo.setPin(req.getPin());
        if (StatusEnum.ReqType_Status.JSF.status() == req.getInterfaceType()) {
            reqVo.setName(req.getJsf_interfaceName().trim());
            reqVo.setUrl(req.getJsf_url().trim());
            reqVo.setBody(req.getJsf_body().trim());
            reqVo.setIp(req.getJsf_ip().trim());
            reqVo.setAlias(req.getJsf_alias().trim());
            reqVo.setMethod(req.getJsf_method().trim());
            reqVo.setToken(req.getJsf_token().trim());
        } else if (StatusEnum.ReqType_Status.POST.status() == req.getInterfaceType()) {
            reqVo.setName(req.getPost_interfaceName().trim());
            reqVo.setUrl(req.getPost_url().trim());
            reqVo.setBody(req.getPost_body().trim());
            reqVo.setHeadMap(req.getPost_head());
        } else if (StatusEnum.ReqType_Status.GET.status() == req.getInterfaceType()) {
            reqVo.setName(req.getGet_interfaceName().trim());
            reqVo.setUrl(req.getGet_url().trim());
            reqVo.setHeadMap(req.getGet_head());
        }
        return reqVo;
    }

    @Override
    public InterfaceCollectionReqVo getCollectionReqVo(InterfaceRequestReqVo req) {
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        reqVo.setInterfaceId(req.getInterfaceId());
        reqVo.setType(req.getInterfaceType());
        reqVo.setPin(req.getPin());
        if (StatusEnum.ReqType_Status.JSF.status() == req.getInterfaceType()) {
            reqVo.setName(req.getJsf_interfaceName().trim());
            reqVo.setUrl(req.getJsf_url().trim());
            reqVo.setBody(req.getJsf_body().trim());
            reqVo.setIp(req.getJsf_ip().trim());
            reqVo.setAlias(req.getJsf_alias().trim());
            reqVo.setMethod(req.getJsf_method().trim());
            reqVo.setToken(req.getJsf_token().trim());
        } else if (StatusEnum.ReqType_Status.POST.status() == req.getInterfaceType()) {
            reqVo.setName(req.getPost_interfaceName().trim());
            reqVo.setUrl(req.getPost_url().trim());
            reqVo.setBody(req.getPost_body().trim());
            reqVo.setHeadMap(req.getPost_head());
        } else if (StatusEnum.ReqType_Status.GET.status() == req.getInterfaceType()) {
            reqVo.setName(req.getGet_interfaceName().trim());
            reqVo.setUrl(req.getGet_url().trim());
            reqVo.setHeadMap(req.getGet_head());
        }
        return reqVo;
    }
}
