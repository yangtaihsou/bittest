package com.bittest.platform.bg.export.vo;

/**
 * 2018-08-23.
 */
public class RequestReqVo {

    private String taskId;//任务编号

    private String caseId;//用例编号

    private String taskResultId;//任务结果id

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getTaskResultId() {
        return taskResultId;
    }

    public void setTaskResultId(String taskResultId) {
        this.taskResultId = taskResultId;
    }
}
