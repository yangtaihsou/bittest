package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.domain.po.InterfaceResult;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-08-27.
 */
public class InterfaceResultResVo extends InterfaceResult {

    private String createTimeStr;

    private String typeStr;

    private List<DataFetch> dataFetchList;

    private List<CheckPoint> checkPointList;

    private List<CheckPoint> dataCheckList;

    private LinkedHashMap<String, String> headMap;

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

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

    public List<CheckPoint> getDataCheckList() {
        return dataCheckList;
    }

    public void setDataCheckList(List<CheckPoint> dataCheckList) {
        this.dataCheckList = dataCheckList;
    }

    public LinkedHashMap<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(LinkedHashMap<String, String> headMap) {
        this.headMap = headMap;
    }
}
