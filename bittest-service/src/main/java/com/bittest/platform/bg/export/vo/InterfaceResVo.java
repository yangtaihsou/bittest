package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.domain.po.InterfaceInfo;

import java.util.List;

/**
 * 2018-08-27.
 * 接口请求返回VO
 */
public class InterfaceResVo extends InterfaceInfo {

    private List<DataFetch> dataFetchList;

    private List<CheckPoint> checkPointList;

    private List<CheckPoint> dataCheckList;
    private String typeStr;
    private String createTimeStr;
    private String updateTimeStr;

    public List<DataFetch> getDataFetchList() {
        return dataFetchList;
    }

    public void setDataFetchList(List<DataFetch> dataFetchList) {
        this.dataFetchList = dataFetchList;
    }

    public List<CheckPoint> getCheckPointList() {
        return checkPointList;
    }

    public void setCheckPointList(List<CheckPoint> checkPointList) {
        this.checkPointList = checkPointList;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public List<CheckPoint> getDataCheckList() {
        return dataCheckList;
    }

    public void setDataCheckList(List<CheckPoint> dataCheckList) {
        this.dataCheckList = dataCheckList;
    }

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
