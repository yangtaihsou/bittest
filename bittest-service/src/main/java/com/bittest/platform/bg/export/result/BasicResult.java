package com.bittest.platform.bg.export.result;

import java.io.Serializable;

/**
 * 基础返回结果类<br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午2:52<br>
 */
public class BasicResult extends Result implements Serializable {

    private static final long serialVersionUID = 1425589652792359471L;

    public BasicResult(ResultInfoEnum info) {
        super(info);
    }

    public BasicResult(String code, String message) {
        super(code, message);
    }

    public BasicResult() {
    }
}
