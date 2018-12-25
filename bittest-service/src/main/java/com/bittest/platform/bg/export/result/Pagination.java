package com.bittest.platform.bg.export.result;

import java.io.Serializable;

/**
 * <br>
 * User: laitao<br>
 * Date: 15-3-18<br>
 * Time: 下午3:45<br>
 */
public class Pagination implements Serializable {

    private static final long serialVersionUID = 743475068647030246L;

    private int itemCount;//总记录条数
    private int pageSize;//每页记录数
    private int pageNo;//当前页码
    private int pageCount;//总页数
    private int startNo;//开始的位置

    public Pagination(int pageSize, int currentIndex) {
        this.pageSize = pageSize;
        this.pageNo = currentIndex;
    }

    public Pagination(int itemCount, int pageSize, int currentIndex) {
        this.itemCount = itemCount;
        this.pageSize = pageSize;
        this.pageNo = currentIndex;
        this.pageCount = (itemCount + pageSize - 1) / pageSize;
        this.startNo = (currentIndex - 1) * pageSize;
    }

    public int getitemCount() {
        return itemCount;
    }

    public void setitemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getpageNo() {
        return pageNo;
    }

    public void setpageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getpageCount() {
        return pageCount;
    }

    public void setpageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    public int getStartNo() {
        return startNo;
    }

    public void setStartNo(int startNo) {
        this.startNo = startNo;
    }
}
