package com.bittest.platform.bg.export.vo;

import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-08-31.
 */
public class RequestInterfaceResVo {

    private String reqBody;

    private String resBody;

    private String errorMessage;

    private int resStatus;

    private boolean result;

    private List<DataFetch> dataFetchList;

    private List<CheckPoint> checkPointList;

    private List<CheckPoint> dataCheckList;

    private int reqType;

    private String url;

    private LinkedHashMap<String, String> headMap;

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getResBody() {
        return resBody;
    }

    public void setResBody(String resBody) {
        this.resBody = resBody;
    }

    public int getResStatus() {
        return resStatus;
    }

    public void setResStatus(int resStatus) {
        this.resStatus = resStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
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

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public LinkedHashMap<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(LinkedHashMap<String, String> headMap) {
        this.headMap = headMap;
    }
}
