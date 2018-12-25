package com.bittest.platform.bg.domain.vo;

import java.util.List;

/**
 * 2018-03-27.
 */
public class JsfIps {

    //返回编码
    private int code;

    //返回信息
    private String message;

    //ip地址实体类
    private List<Ips> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Ips> getResult() {
        return result;
    }

    public void setResult(List<Ips> result) {
        this.result = result;
    }
}
