package com.bittest.platform.bg.domain.po;

/**
 * 分页信息
 * 2018-08-24.
 */
public class BasePo {

    //页大小
    private Integer pageSize;

    //当前页数
    private Integer currentPage;

    //开始条数
    private Integer startNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getStartNo() {
        return startNo;
    }

    public void setStartNo(Integer startNo) {
        this.startNo = startNo;
    }
}
