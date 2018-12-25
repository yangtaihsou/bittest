package com.bittest.platform.pg.res;

import java.util.List;

public class ErpPageResult<T> {

    /**
     * 总数
     */
    private int totalCount;
    private List<T> values;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getValues() {
        return values;
    }

    public void setValues(List<T> values) {
        this.values = values;
    }


}
