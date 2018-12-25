package com.bittest.platform.bg.export.result;

import java.io.Serializable;

/**
 * 通用返回结果类<br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午2:54<br>
 */
public class GenericResult<T> extends Result implements Serializable {

    private static final long serialVersionUID = -1342094832750370935L;

    private T value;

    public GenericResult() {
    }

    public GenericResult(ResultInfoEnum info) {
        super(info);
    }

    public GenericResult(String code, String message) {
        super(code, message);
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
