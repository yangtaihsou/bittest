package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CaseResult;

import java.util.LinkedHashMap;

/**
 * 2018-08-27.
 */
public class CaseResultResVo extends CaseResult {

    private String createTimeStr;

    private LinkedHashMap<String, String> caseParamMap;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public LinkedHashMap<String, String> getCaseParamMap() {
        return caseParamMap;
    }

    public void setCaseParamMap(LinkedHashMap<String, String> caseParamMap) {
        this.caseParamMap = caseParamMap;
    }
}
