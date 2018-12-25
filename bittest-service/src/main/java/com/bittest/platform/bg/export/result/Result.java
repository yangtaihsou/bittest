package com.bittest.platform.bg.export.result;

import java.io.Serializable;

/**
 * 返回结果抽象类<br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午2:48<br>
 */
public abstract class Result implements Serializable {

    private String code;

    private String message;

    public Result() {
    }

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(ResultInfoEnum info) {
        if (info != null) {
            code = info.getErrorCode();
            message = info.getErrorMsg();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setInfo(ResultInfoEnum info) {
        if (info != null) {
            code = info.getErrorCode();
            message = info.getErrorMsg();
        }
    }

    public void setInfo(AppRuntimeException info) {
        if (info != null) {
            code = info.getErrorCode();
            if (code == null)
                setInfo(ResultInfoEnum.UNKNOWN_ERROR);
            message = info.getMessage();
        } else {
            setInfo(ResultInfoEnum.UNKNOWN_ERROR);
        }
    }
}
