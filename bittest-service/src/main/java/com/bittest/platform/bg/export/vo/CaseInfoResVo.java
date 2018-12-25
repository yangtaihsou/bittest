package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CaseInfo;

import java.util.List;
import java.util.Map;

/**
 * 2018-08-26.
 */
public class CaseInfoResVo extends CaseInfo {

    private String createTimeStr;

    private String updateTimeStr;

    private Map<String, String> caseParamMap;

    private List<InterfaceResultResVo> interfaceResultList;

    private int result;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public Map<String, String> getCaseParamMap() {
        return caseParamMap;
    }

    public void setCaseParamMap(Map<String, String> caseParamMap) {
        this.caseParamMap = caseParamMap;
    }

    public List<InterfaceResultResVo> getInterfaceResultList() {
        return interfaceResultList;
    }

    public void setInterfaceResultList(List<InterfaceResultResVo> interfaceResultList) {
        this.interfaceResultList = interfaceResultList;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
