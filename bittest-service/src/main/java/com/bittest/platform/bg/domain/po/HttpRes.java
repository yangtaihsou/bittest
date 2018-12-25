package com.bittest.platform.bg.domain.po;

/**
 * http请求返回VO
 * 2018-08-23.
 */
public class HttpRes {

    private int status;//返回状态

    private String resBody;//返回内容

    public String getResBody() {
        return resBody;
    }

    public void setResBody(String resBody) {
        this.resBody = resBody;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}