package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.Task;

import java.util.Map;

/**
 * 2018-08-24.
 */
public class TaskResVo extends Task {


    private String createTimeStr;

    private String updateTimeStr;

    private Map<String, String> taskParamMap;

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

    public Map<String, String> getTaskParamMap() {
        return taskParamMap;
    }

    public void setTaskParamMap(Map<String, String> taskParamMap) {
        this.taskParamMap = taskParamMap;
    }
}
