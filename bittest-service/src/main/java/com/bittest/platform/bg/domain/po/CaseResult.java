package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 执行用例结果表
 *
 * @date 2018-08-31 15:52:54
 */
public class CaseResult extends BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //执行结果编号
    private String resultId;
    //任务编号
    private String taskId;
    //用例ID
    private String caseId;
    //用例名称
    private String caseName;
    //用例参数化
    private String caseParam;
    //所属系统编号
    private String sysId;
    //所属系统名称
    private String systemName;
    //备注
    private int result;
    //创建时间
    private Date createTime;
    //pin
    private String pin;
    //系统名称

    /**
     * 获取：id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：用例ID
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 设置：用例ID
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取：用例名称
     */
    public String getCaseName() {
        return caseName;
    }

    /**
     * 设置：用例名称
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * 获取：用例参数化
     */
    public String getCaseParam() {
        return caseParam;
    }

    /**
     * 设置：用例参数化
     */
    public void setCaseParam(String caseParam) {
        this.caseParam = caseParam;
    }

    /**
     * 获取：所属系统编号
     */
    public String getSysId() {
        return sysId;
    }

    /**
     * 设置：所属系统编号
     */
    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
