package com.bittest.platform.pg.vo;


/**
 *  
 */
public class AuthVo {

    private String uuid;
    private String inputUser;
    private String name;
    private String phone;
    private Long authtype;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getInputUser() {
        return this.inputUser;
    }

    public void setInputUser(String inputUser) {
        this.inputUser = inputUser;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getAuthtype() {
        return this.authtype;
    }

    public void setAuthtype(Long authtype) {
        this.authtype = authtype;
    }

}
