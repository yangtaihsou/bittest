package com.bittest.platform.pg.vo;

import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.domain.po.DataFetch;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 2018-08-31.
 */
public class InterfaceRequestReqVo {

    private int interfaceType;

    private String interfaceId;

    private String caseId;

    //jsf接口信息
    private String jsf_interfaceName;

    private String jsf_url;

    private String jsf_alias;

    private String jsf_method;

    private String jsf_ip;

    private String jsf_token;

    private String jsf_body;

    //post接口信息
    private String post_interfaceName;

    private String post_url;

    private String post_body;

    private LinkedHashMap<String, String> post_head;

    //get接口信息
    private String get_interfaceName;

    private String get_url;

    private String pin;

    private LinkedHashMap<String, String> get_head;

    private List<DataFetch> dataFetchList;

    private List<CheckPoint> checkPointList;

    private List<CheckPoint> dataCheckList;

    public String getJsf_interfaceName() {
        return jsf_interfaceName;
    }

    public void setJsf_interfaceName(String jsf_interfaceName) {
        this.jsf_interfaceName = jsf_interfaceName;
    }

    public String getJsf_url() {
        return jsf_url;
    }

    public void setJsf_url(String jsf_url) {
        this.jsf_url = jsf_url;
    }

    public String getJsf_alias() {
        return jsf_alias;
    }

    public void setJsf_alias(String jsf_alias) {
        this.jsf_alias = jsf_alias;
    }

    public String getJsf_method() {
        return jsf_method;
    }

    public void setJsf_method(String jsf_method) {
        this.jsf_method = jsf_method;
    }

    public String getJsf_ip() {
        return jsf_ip;
    }

    public void setJsf_ip(String jsf_ip) {
        this.jsf_ip = jsf_ip;
    }

    public String getJsf_body() {
        return jsf_body;
    }

    public void setJsf_body(String jsf_body) {
        this.jsf_body = jsf_body;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
    }

    public String getGet_url() {
        return get_url;
    }

    public void setGet_url(String get_url) {
        this.get_url = get_url;
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

    public int getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(int interfaceType) {
        this.interfaceType = interfaceType;
    }

    public List<CheckPoint> getDataCheckList() {
        return dataCheckList;
    }

    public void setDataCheckList(List<CheckPoint> dataCheckList) {
        this.dataCheckList = dataCheckList;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getPost_interfaceName() {
        return post_interfaceName;
    }

    public void setPost_interfaceName(String post_interfaceName) {
        this.post_interfaceName = post_interfaceName;
    }

    public String getGet_interfaceName() {
        return get_interfaceName;
    }

    public void setGet_interfaceName(String get_interfaceName) {
        this.get_interfaceName = get_interfaceName;
    }

    public LinkedHashMap<String, String> getPost_head() {
        return post_head;
    }

    public void setPost_head(LinkedHashMap<String, String> post_head) {
        this.post_head = post_head;
    }

    public LinkedHashMap<String, String> getGet_head() {
        return get_head;
    }

    public void setGet_head(LinkedHashMap<String, String> get_head) {
        this.get_head = get_head;
    }

    public String getJsf_token() {
        return jsf_token;
    }

    public void setJsf_token(String jsf_token) {
        this.jsf_token = jsf_token;
    }
}
