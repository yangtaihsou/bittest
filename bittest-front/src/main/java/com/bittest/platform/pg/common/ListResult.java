package com.bittest.platform.pg.common;


import java.io.Serializable;
import java.util.List;

public class ListResult<T> extends AbstractResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<T> values;

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }

    public boolean verifyValues() {
        if (this.values == null || this.values.size() == 0) {
            return false;
        }
        return true;
    }
}
