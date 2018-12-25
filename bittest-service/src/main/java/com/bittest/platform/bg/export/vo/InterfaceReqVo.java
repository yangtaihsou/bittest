package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.domain.po.InterfaceInfo;

import java.util.List;
import java.util.Map;

/**
 * 2018-08-27.
 * jsf请求接收VO
 */
public class InterfaceReqVo extends InterfaceInfo {

    //提取器
    private List<DataFetch> dataFetchList;
    //普通检查点
    private List<CheckPoint> checkPointList;
    //接口排序
    private Map<String, String> orders;
    //数据库检查点
    private List<CheckPoint> dataCheckList;

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

    public Map<String, String> getOrders() {
        return orders;
    }

    public void setOrders(Map<String, String> orders) {
        this.orders = orders;
    }

    public List<CheckPoint> getDataCheckList() {
        return dataCheckList;
    }

    public void setDataCheckList(List<CheckPoint> dataCheckList) {
        this.dataCheckList = dataCheckList;
    }
}
