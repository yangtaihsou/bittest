package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.Systems;

/**
 * 2018-08-25.
 */
public class SystemResVo extends Systems {

    private String createTimeStr;

    private String updateTimeStr;

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
}
