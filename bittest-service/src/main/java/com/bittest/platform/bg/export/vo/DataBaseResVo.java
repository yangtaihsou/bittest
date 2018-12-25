package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.DataBase;

/**
 * 2018-08-22.
 */
public class DataBaseResVo extends DataBase {

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
