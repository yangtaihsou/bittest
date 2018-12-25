package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CaseInfo;

import java.util.Map;

/**
 * 2018-08-26.
 */
public class CaseInfoReqVo extends CaseInfo {

    //接口参数化map
    private Map<String, String> caseParamMap;
    //接口执行顺序
    private Map<String, String> orders;

    public Map<String, String> getCaseParamMap() {
        return caseParamMap;
    }

    public void setCaseParamMap(Map<String, String> caseParamMap) {
        this.caseParamMap = caseParamMap;
    }

    public Map<String, String> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, String> orders) {
        this.orders = orders;
    }
}
