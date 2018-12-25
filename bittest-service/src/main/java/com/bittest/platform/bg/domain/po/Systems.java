package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;


/**
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
public class Systems extends BasePo implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //系统编号
    private String systemId;
    //系统名称
    private String systemName;
    //域名
    private String domainName;
    //域名的变量名称（用于http接口域名引用）
    private String domainParam;
    //ip
    private String ip;
    //pin
    private String pin;
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
     * 获取：系统编号
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * 设置：系统编号
     */
    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    /**
     * 获取：系统名称
     */
    public String getSystemName() {
        return systemName;
    }

    /**
     * 设置：系统名称
     */
    public void setSystemName(String systemName) {
        this.systemName = systemName;
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
     * 获取：pin
     */

    public String getPin() {
        return pin;
    }

    /**
     * 设置：pin
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDomainParam() {
        return domainParam;
    }

    public void setDomainParam(String domainParam) {
        this.domainParam = domainParam;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
