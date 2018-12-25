package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public class Task extends BasePo implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //任务编号
    private String taskId;
    //任务名称
    private String taskName;
    //任务参数化
    private String taskParam;
    //系统编号
    private String sysId;
    //系统名称
    private String sysName;
    //pin
    private String pin;
    //任务状态（1、启用 2、禁用）
    private Integer status;
    //备注
    private String remark;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

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

    /**
     * 获取：任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置：任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取：任务参数化
     */
    public String getTaskParam() {
        return taskParam;
    }

    /**
     * 设置：任务参数化
     */
    public void setTaskParam(String taskParam) {
        this.taskParam = taskParam;
    }

    /**
     * 获取：
     */
    public String getPin() {
        return pin;
    }

    /**
     * 设置：
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * 获取：任务状态（1、启用 2、禁用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：任务状态（1、启用 2、禁用）
     */
    public void setStatus(Integer status) {
        this.status = status;
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

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }
}
