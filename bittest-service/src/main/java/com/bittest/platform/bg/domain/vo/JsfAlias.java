package com.bittest.platform.bg.domain.vo;


import java.util.List;

/**
 * 解析jsf alias
 * 2018-08-20.
 */
public class JsfAlias {

    //返回编码
    private int code;

    //返回信息
    private String message;

    //返回 别名、服务地址实体类
    private List<String> result;

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

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

}
