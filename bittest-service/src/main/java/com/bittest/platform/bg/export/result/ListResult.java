package com.bittest.platform.bg.export.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * List类型返回结果类<br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午2:56<br>
 */
public class ListResult<T> extends Result implements Serializable {
    private static final long serialVersionUID = -7450177725900417295L;

    private List<T> list = new ArrayList<T>();

    public ListResult() {
    }

    public ListResult(ResultInfoEnum info) {
        super(info);
    }

    public ListResult(String code, String message) {
        super(code, message);
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean add(T value) {
        return this.list.add(value);
    }
}
