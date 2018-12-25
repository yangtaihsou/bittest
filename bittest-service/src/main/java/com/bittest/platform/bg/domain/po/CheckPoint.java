package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 检查点表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public class CheckPoint implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //检查点编号
    private String checkId;
    //接口编号
    private String interfaceId;
    //用例编号
    private String caseId;
    //检查方法（1、包含 2、不包含）
    private Integer checkMethod;
    //检查点类型（1、普通检查点 2、数据库检查点）
    private Integer checkType;
    //检查值
    private String checkValue;
    //查询列(仅限数据库检查点)
    private String columns;
    //查询条件（仅限数据库检查点）
    private String wheres;
    //表名仅限(数据库检查点)
    private String databaseId;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;
    //pin
    private String pin;
    //数据库表名
    private String tableName;
    //检查点结果
    private boolean result;

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
     * 获取：检查方法（1、普通检查点 2、数据库检查点）
     */
    public Integer getCheckMethod() {
        return checkMethod;
    }

    /**
     * 设置：检查方法（1、普通检查点 2、数据库检查点）
     */
    public void setCheckMethod(Integer checkMethod) {
        this.checkMethod = checkMethod;
    }

    /**
     * 获取：检查点类型（1、等于、包含、不等于）
     */
    public Integer getCheckType() {
        return checkType;
    }

    /**
     * 设置：检查点类型（1、等于、包含、不等于）
     */
    public void setCheckType(Integer checkType) {
        this.checkType = checkType;
    }

    /**
     * 获取：检查值
     */
    public String getCheckValue() {
        return checkValue;
    }

    /**
     * 设置：检查值
     */
    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getColumns() {
        return columns;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getWheres() {
        return wheres;
    }

    public void setWheres(String where) {
        this.wheres = where;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
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

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
