package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.TaskResult;

import java.util.LinkedHashMap;

/**
 * 2018-08-27.
 */
public class TaskResultResVo extends TaskResult {

    private String createTimeStr;

    private String updateTimeStr;

    private String isFinishStr;

    private LinkedHashMap<String, String> taskParamMap;

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getIsFinishStr() {
        return isFinishStr;
    }

    public void setIsFinishStr(String isFinishStr) {
        this.isFinishStr = isFinishStr;
    }

    public LinkedHashMap<String, String> getTaskParamMap() {
        return taskParamMap;
    }

    public void setTaskParamMap(LinkedHashMap<String, String> taskParamMap) {
        this.taskParamMap = taskParamMap;
    }
}
