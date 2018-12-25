package com.bittest.platform.pg.util.datatable;

import com.bittest.platform.pg.util.Constant;

import java.util.List;


/**
 * 分页数据类型封装
 *
 *
 *
 */
public class DataTableParameter {

    private int sEcho; // 请求服务器端次数
    private int iDisplayStart;// 其实记录，第一条为0
    private int iDisplayLength;
    private int iColumns;//列表总列数
    private List<String> mDataProps; // 列的Name列表
    private List<Boolean> bSortables;// 列对应是否能排序
    private int iSortingCols;//用于排列的列数
    private List<Integer> iSortCols; // 排序列的编号
    private List<String> iSortColsName; // 排序列的名称
    private List<String> sSortDirs; // 排布列排序形式 Asc/Desc

    private Integer pageNo;
    private Integer pageSize;

    public DataTableParameter(int sEcho, int iDisplayStart, int iDisplayLength, int iColumns, List<String> mDataProps,
                              List<Boolean> bSortables, int iSortingCols, List<Integer> iSortCols, List<String> sSortDirs,
                              List<String> iSortColsName) {
        this.sEcho = sEcho;
        this.iDisplayStart = iDisplayStart;
        this.iDisplayLength = iDisplayLength;
        this.iColumns = iColumns;
        this.mDataProps = mDataProps;
        this.bSortables = bSortables;
        this.iSortingCols = iSortingCols;
        this.iSortCols = iSortCols;
        this.sSortDirs = sSortDirs;
        this.iSortColsName = iSortColsName;
    }

    public int getsEcho() {
        return sEcho;
    }

    public void setsEcho(int sEcho) {
        this.sEcho = sEcho;
    }

    public int getiDisplayStart() {
        return iDisplayStart;
    }

    public void setiDisplayStart(int iDisplayStart) {
        this.iDisplayStart = iDisplayStart;
    }

    public int getiDisplayLength() {
        return iDisplayLength;
    }

    public void setiDisplayLength(int iDisplayLength) {
        this.iDisplayLength = iDisplayLength;
    }

    public int getiColumns() {
        return iColumns;
    }

    public void setiColumns(int iColumns) {
        this.iColumns = iColumns;
    }

    public List<String> getmDataProps() {
        return mDataProps;
    }

    public void setmDataProps(List<String> mDataProps) {
        this.mDataProps = mDataProps;
    }

    public List<Boolean> getbSortables() {
        return bSortables;
    }

    public void setbSortables(List<Boolean> bSortables) {
        this.bSortables = bSortables;
    }

    public int getiSortingCols() {
        return iSortingCols;
    }

    public void setiSortingCols(int iSortingCols) {
        this.iSortingCols = iSortingCols;
    }

    public List<Integer> getiSortCols() {
        return iSortCols;
    }

    public void setiSortCols(List<Integer> iSortCols) {
        this.iSortCols = iSortCols;
    }

    public List<String> getiSortColsName() {
        return iSortColsName;
    }

    public void setiSortColsName(List<String> iSortColsName) {
        this.iSortColsName = iSortColsName;
    }

    public List<String> getsSortDirs() {
        return sSortDirs;
    }

    public void setsSortDirs(List<String> sSortDirs) {
        this.sSortDirs = sSortDirs;
    }

    public Integer getPageNo() {
        if (iDisplayStart < 1) {
            pageNo = 1;
        } else {
            pageNo = iDisplayStart / getPageSize() + 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if (iDisplayLength < 1) {
            pageSize = Constant.pageSize;
        } else {
            pageSize = iDisplayLength;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
