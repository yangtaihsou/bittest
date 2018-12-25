/**
 * Copyright(c) 2004- www.360buy.com
 * io.bitjob.domain.TaskDto.java
 */
package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangkuan@jd.com
 * @date
 */
public class TimerTask implements Serializable {


    /**
     * 主键id
     */
    private Long id;


    private String taskId;



    /**
     * 任务数据
     */
    private String taskData;


    /**
     * 任务状态
     */
    private Integer taskStatus;


    /**
     * 运行次数
     */
    private Integer retryCount;


    /**
     * UUID
     */
    private String uuid;


    /**
     * 创建时间
     */
    private Date bizTime;


    /**
     * 任务结果,0表示任务初始，1表示任务成功，2表示任务失败
     */
    private Integer resultStatus;


    /**
     * 最后修改时间
     */
    private Date lastUpdate;


    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务执行时发生的错误信息
     */
    private String errorMsg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getTaskData() {
        return taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getRetryCount() {
        return retryCount;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getBizTime() {
        return bizTime;
    }

    public void setBizTime(Date bizTime) {
        this.bizTime = bizTime;
    }

    public Integer getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(Integer resultStatus) {
        this.resultStatus = resultStatus;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}