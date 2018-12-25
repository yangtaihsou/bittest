package com.bittest.platform.bg.domain.vo;


/**
 * 解析jsf信息实体类
 * 2018-08-20.
 */
public class JsfInfo {

    //返回编码
    private int code;

    //返回信息
    private String message;

    //返回 别名、方法名、服务地址实体类
    private Data data;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
