package com.bittest.platform.pg.domain;

public class AssetTypeStatisticsRecordeExcelVo extends ExcelUpMsg {

    @ExcelMsg(index = 0, require = true, header = "资产类型", width = 20)
    private String typeStr;//字类型
    @ExcelMsg(index = 1, require = true, header = "本金规模（万元）", width = 20)
    private String amountStr;//本金规模
    @ExcelMsg(index = 2, require = true, header = "占比", width = 20)
    private String percentStr;//占比
    @ExcelMsg(index = 3, require = true, header = "平均利率", width = 20)
    private String agvProfitStr;//平均利率

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getAmountStr() {
        return amountStr;
    }

    public void setAmountStr(String amountStr) {
        this.amountStr = amountStr;
    }

    public String getPercentStr() {
        return percentStr;
    }

    public void setPercentStr(String percentStr) {
        this.percentStr = percentStr;
    }

    public String getAgvProfitStr() {
        return agvProfitStr;
    }

    public void setAgvProfitStr(String agvProfitStr) {
        this.agvProfitStr = agvProfitStr;
    }


}
