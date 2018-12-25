package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.TaskCaseRelastion;

/**
 * 2018-08-22.
 */
public class TaskCaseRelastionReqVo extends TaskCaseRelastion {

    //用例批量关联
    private String[] caseIds;

    public String[] getCaseIds() {
        return caseIds;
    }

    public void setCaseIds(String[] caseIds) {
        this.caseIds = caseIds;
    }
}
