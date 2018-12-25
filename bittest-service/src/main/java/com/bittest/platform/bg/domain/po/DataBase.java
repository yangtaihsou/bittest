package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * 数据库管理表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
public class DataBase extends BasePo implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //数据库编号
    private String databaseId;
    //地址
    private String url;
    //名称
    private String name;
    //用户名
    private String username;
    //密码
    private String password;
    //备注
    private String remark;
    //pin
    private String pin;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
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

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public void setDatabaseId(String databaseId) {
        this.databaseId = databaseId;
    }
}
