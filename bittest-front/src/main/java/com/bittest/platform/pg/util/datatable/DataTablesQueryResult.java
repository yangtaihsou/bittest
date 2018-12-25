package com.bittest.platform.pg.util.datatable;

import java.io.Serializable;
import java.util.List;

/**
 * 页面表格数据格式封装
 *
 *
 *
 */
public class DataTablesQueryResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer iTotalRecords;
    private Integer sEcho;
    private Integer iTotalDisplayRecords;
    @SuppressWarnings("rawtypes")
    private List aaData;

    @SuppressWarnings("rawtypes")
    public DataTablesQueryResult(Integer iTotalRecords, Integer sEcho, Integer iTotalDisplayRecords, List aaData) {
        this.iTotalRecords = iTotalRecords;
        this.sEcho = sEcho;
        this.iTotalDisplayRecords = iTotalDisplayRecords;
        this.aaData = aaData;
    }

    @SuppressWarnings("rawtypes")
    public DataTablesQueryResult(List data, Integer total, Integer sEcho) {
        this.iTotalRecords = total;
        this.sEcho = sEcho;
        this.iTotalDisplayRecords = total;
        this.aaData = data;
    }

    public Integer getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Integer iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Integer getsEcho() {
        return sEcho;
    }

    public void setsEcho(Integer sEcho) {
        this.sEcho = sEcho;
    }

    public Integer getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Integer iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    @SuppressWarnings("rawtypes")
    public List getAaData() {
        return aaData;
    }

    @SuppressWarnings("rawtypes")
    public void setAaData(List aaData) {
        this.aaData = aaData;
    }
}
