package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 用例表
 *
 * @date 2018-08-31 15:52:54
 */
public class CaseInfo extends BasePo implements Serializable {

    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //用例ID
    private String caseId;
    //任务编号
    private String taskId;
    //用例名称
    private String caseName;
    //用例参数化
    private String caseParam;
    //所属系统编号
    private String sysId;
    //所属系统名称
    private String systemName;
    //备注
    private String remark;
    //case状态 （1启用 2禁用）
    private Integer status;
    //case所属者pin
    private String pin;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;
    //报表统计用例数量
    private int caseCount;

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
     * 获取：备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：case状态 （1启用 2禁用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：case状态 （1启用 2禁用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：case所属者pin
     */
    public String getPin() {
        return pin;
    }

    /**
     * 设置：case所属者pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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

    /**
     * 获取：任务编号
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置：任务编号
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }
}
