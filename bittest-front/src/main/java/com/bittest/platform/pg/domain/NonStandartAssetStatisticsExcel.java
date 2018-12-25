package com.bittest.platform.pg.domain;

public class NonStandartAssetStatisticsExcel extends ExcelUpMsg {

    @ExcelMsg(index = 0, require = true, header = "月份", width = 20)
    private String monthStr;//月份

    @ExcelMsg(index = 1, require = true, header = "内部资产购买本金（万元）", width = 20)
    private String internalBuyAmountStr;

    @ExcelMsg(index = 2, require = true, header = "外部资产购买本金（万元）", width = 20)
    private String externalBuyAmountStr;

    @ExcelMsg(index = 3, require = true, header = "内部资产到期本金（万元）", width = 20)
    private String internalExpireAmountStr;

    @ExcelMsg(index = 4, require = true, header = "外部资产到期本金（万元）", width = 20)
    private String externalExpireAmountStr;

    @ExcelMsg(index = 5, require = true, header = "内部资产本金余额（万元）", width = 20)
    private String internalStockAmountStr;

    @ExcelMsg(index = 6, require = true, header = "外部资产本金余额（万元）", width = 20)
    private String externalStockAmountStr;

    public String getMonthStr() {
        return monthStr;
    }

    public void setMonthStr(String monthStr) {
        this.monthStr = monthStr;
    }

    public String getInternalBuyAmountStr() {
        return internalBuyAmountStr;
    }

    public void setInternalBuyAmountStr(String internalBuyAmountStr) {
        this.internalBuyAmountStr = internalBuyAmountStr;
    }

    public String getExternalBuyAmountStr() {
        return externalBuyAmountStr;
    }

    public void setExternalBuyAmountStr(String externalBuyAmountStr) {
        this.externalBuyAmountStr = externalBuyAmountStr;
    }

    public String getInternalExpireAmountStr() {
        return internalExpireAmountStr;
    }

    public void setInternalExpireAmountStr(String internalExpireAmountStr) {
        this.internalExpireAmountStr = internalExpireAmountStr;
    }

    public String getExternalExpireAmountStr() {
        return externalExpireAmountStr;
    }

    public void setExternalExpireAmountStr(String externalExpireAmountStr) {
        this.externalExpireAmountStr = externalExpireAmountStr;
    }

    public String getInternalStockAmountStr() {
        return internalStockAmountStr;
    }

    public void setInternalStockAmountStr(String internalStockAmountStr) {
        this.internalStockAmountStr = internalStockAmountStr;
    }

    public String getExternalStockAmountStr() {
        return externalStockAmountStr;
    }

    public void setExternalStockAmountStr(String externalStockAmountStr) {
        this.externalStockAmountStr = externalStockAmountStr;
    }


}
