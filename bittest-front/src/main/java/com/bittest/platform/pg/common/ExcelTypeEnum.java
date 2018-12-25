package com.bittest.platform.pg.common;

public enum ExcelTypeEnum {

    xls("xls", "03版本EXCEL格式"),
    xlsx("xlsx", "07及其后版本EXCEL格式");

    private String code;
    private String desc;

    private ExcelTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
