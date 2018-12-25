package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 任务用例关联关系表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public class TaskCaseRelastion implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //任务编号
    private String taskId;
    //用例编号
    private String caseId;
    //用例状态（1、启用 2、禁用）
    private Integer status;
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
     * 获取：用例编号
     */
    public String getCaseId() {
        return caseId;
    }

    /**
     * 设置：用例编号
     */
    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取：用例状态（1、启用 2、禁用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：用例状态（1、启用 2、禁用）
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
}
