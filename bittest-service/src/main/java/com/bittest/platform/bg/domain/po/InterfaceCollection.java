package com.bittest.platform.bg.domain.po;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;


/**
 * 收藏接口表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public class InterfaceCollection extends BasePo implements Serializable {
    private static final long serialVersionUID = 1L;

    //id
    private Long id;
    //uuid
    private String interfaceId;
    //pin（接口所属人）
    private String pin;
    //接口名称
    private String name;
    //请求地址
    private String url;
    //报文请求头
    private String head;
    //jsf服务ip地址端口号
    private String ip;
    //jsf接口分组名称
    private String alias;
    //请求内容
    private String body;
    //请求方法（针对jsf接口调用的方法名称）
    private String method;
    //接口类型(1jsf  2post  3get)
    private Integer type;
    //修改时间
    private Date updateTime;
    //创建时间
    private Date createTime;

    private String caseId;

    private LinkedHashMap<String, String> headMap;

    private String caseName;

    private String token;

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
     * 获取：uuid
     */
    public String getInterfaceId() {
        return interfaceId;
    }

    /**
     * 设置：uuid
     */
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * 获取：pin（接口所属人）
     */
    public String getPin() {
        return pin;
    }

    /**
     * 设置：pin（接口所属人）
     */
    public void setPin(String pin) {
        this.pin = pin;
    }

    /**
     * 获取：接口名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：接口名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：请求地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置：请求地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：报文请求头
     */
    public String getHead() {
        return head;
    }

    /**
     * 设置：报文请求头
     */
    public void setHead(String head) {
        this.head = head;
    }

    /**
     * 获取：jsf服务ip地址端口号
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置：jsf服务ip地址端口号
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取：jsf接口分组名称
     */
    public String getAlias() {
        return alias;
    }

    /**
     * 设置：jsf接口分组名称
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * 获取：请求内容
     */
    public String getBody() {
        return body;
    }

    /**
     * 设置：请求内容
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 获取：请求方法（针对jsf接口调用的方法名称）
     */
    public String getMethod() {
        return method;
    }

    /**
     * 设置：请求方法（针对jsf接口调用的方法名称）
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 设置：接口类型(1jsf  2post  3get)
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * 获取：接口类型(1jsf  2post  3get)
     */
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public LinkedHashMap<String, String> getHeadMap() {
        return headMap;
    }

    public void setHeadMap(LinkedHashMap<String, String> headMap) {
        this.headMap = headMap;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
