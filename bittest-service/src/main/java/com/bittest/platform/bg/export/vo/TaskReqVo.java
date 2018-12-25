package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.Task;

import java.util.Map;

/**
 * 2018-08-24.
 */
public class TaskReqVo extends Task {

    private Map<String, String> taskParamMap;

    public Map<String, String> getTaskParamMap() {
        return taskParamMap;
    }

    public void setTaskParamMap(Map<String, String> taskParamMap) {
        this.taskParamMap = taskParamMap;
    }
}
