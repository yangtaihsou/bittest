package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 用例接口关联关系表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public class CaseInterfaceRelastion implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //用例编号
    private String caseId;
    //接口编号
    private String interfaceId;
    //执行顺序（存在多接口串联执行的顺序）
    private Integer interfaceOrder;
    //接口状态（1、启用 2、禁用）
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
     * 获取：接口编号
     */
    public String getInterfaceId() {
        return interfaceId;
    }

    /**
     * 设置：接口编号
     */
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * 获取：执行顺序（存在多接口串联执行的顺序）
     */
    public Integer getInterfaceOrder() {
        return interfaceOrder;
    }

    /**
     * 设置：执行顺序（存在多接口串联执行的顺序）
     */
    public void setInterfaceOrder(Integer interfaceOrder) {
        this.interfaceOrder = interfaceOrder;
    }

    /**
     * 获取：
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
