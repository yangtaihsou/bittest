package com.bittest.platform.pg.domain;

/**
 * 用户记录上传的excel反馈信息
 *
 * @author tanxuhui
 */
public class ExcelUpMsg {
    // 标示记录的索引
    private Integer index;
    // 是否成功
    private Boolean parserStatus;
    // 反馈信息
    private String bindMsg;

    public ExcelUpMsg() {
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Boolean getParserStatus() {
        return parserStatus;
    }

    public void setParserStatus(Boolean parserStatus) {
        this.parserStatus = parserStatus;
    }

    public String getBindMsg() {
        return bindMsg;
    }

    public void setBindMsg(String bindMsg) {
        this.bindMsg = bindMsg;
    }

    @Override
    public String toString() {
        return "ExcelUpMsg{" + "index=" + index + ", parserStatus=" + parserStatus + ", bindMsg=" + bindMsg + '}';
    }
}
