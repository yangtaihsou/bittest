package com.bittest.platform.bg.domain.vo;


import java.util.List;

/**
 * 解析jsf信息实体类
 * 2018-08-20.
 */
public class JsfMethods {

    //返回编码
    private int code;

    //返回信息
    private String message;

    //返回 方法名
    private String result;

    //存储方法实体类
    private List<Methods> methods;

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<Methods> getMethods() {
        return methods;
    }

    public void setMethods(List<Methods> methods) {
        this.methods = methods;
    }
}
