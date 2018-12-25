package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 数据关联提取表
 * 2018-08-23.
 */
public class DataFetch implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private BigInteger id;
    //提取器编号
    private String dataFetchId;
    //用例编号
    private String caseId;
    //接口编号
    private String interfaceId;
    //参数名称
    private String paramName;
    //提取规则
    private String regular;
    //提取索引
    private int regularIndex;
    //pin
    private String pin;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    private String result;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
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

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDataFetchId() {
        return dataFetchId;
    }

    public void setDataFetchId(String dataFetchId) {
        this.dataFetchId = dataFetchId;
    }

    public int getRegularIndex() {
        return regularIndex;
    }

    public void setRegularIndex(int regularIndex) {
        this.regularIndex = regularIndex;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
