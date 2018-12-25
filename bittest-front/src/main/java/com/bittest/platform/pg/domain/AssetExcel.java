package com.bittest.platform.pg.domain;


public class AssetExcel extends ExcelUpMsg {


    @ExcelMsg(index = 0, require = true, header = "序号", width = 20)
    private int order;

    @ExcelMsg(index = 1, require = true, header = "所属部门", width = 20)
    private String department;

    @ExcelMsg(index = 2, require = true, header = "资产名称", width = 20)
    private String assetName;

    @ExcelMsg(index = 3, require = true, header = "行业", width = 20)
    private String industryCategory;

    @ExcelMsg(index = 4, require = true, header = "内外部", width = 20)
    private String assetTypeGroup;

    @ExcelMsg(index = 5, require = true, header = "风控编号", width = 20)
    private String productCode;

    @ExcelMsg(index = 6, require = true, header = "融资方名称", width = 20)
    private String fName;


    @ExcelMsg(index = 7, require = true, header = "增信机构", width = 20)
    private String orgCode;

    @ExcelMsg(index = 8, require = true, header = "原始资产起息日", width = 20)
    private String oriBeginDate;

    @ExcelMsg(index = 9, require = true, header = "原始资产到期日", width = 20)
    private String endDate;

    @ExcelMsg(index = 10, require = true, header = "一年天数", width = 20)
    private String dayOfYear;

    @ExcelMsg(index = 11, require = true, header = "计息方式", width = 20)
    private String profitType;

    @ExcelMsg(index = 12, require = true, header = "原始资产金额", width = 20)
    private String oriAssetAmount;

    @ExcelMsg(index = 13, require = true, header = "原始资产收益率", width = 20)
    private String assetProfit;

    @ExcelMsg(index = 14, require = true, header = "转让日", width = 20)
    private String expireDate;

    @ExcelMsg(index = 15, require = true, header = "到期日", width = 20)
    private String endDate1;

    @ExcelMsg(index = 16, require = true, header = "转让对应原始资产本金", width = 20)
    private String assetAmount;

    @ExcelMsg(index = 17, require = true, header = "转让价格", width = 20)
    private String costAmount;

    @ExcelMsg(index = 18, require = true, header = "真实回报率", width = 20)
    private String repoProfit;

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public String getAssetTypeGroup() {
        return assetTypeGroup;
    }

    public void setAssetTypeGroup(String assetTypeGroup) {
        this.assetTypeGroup = assetTypeGroup;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOriBeginDate() {
        return oriBeginDate;
    }

    public void setOriBeginDate(String oriBeginDate) {
        this.oriBeginDate = oriBeginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDayOfYear() {
        return dayOfYear;
    }

    public void setDayOfYear(String dayOfYear) {
        this.dayOfYear = dayOfYear;
    }

    public String getProfitType() {
        return profitType;
    }

    public void setProfitType(String profitType) {
        this.profitType = profitType;
    }

    public String getOriAssetAmount() {
        return oriAssetAmount;
    }

    public void setOriAssetAmount(String oriAssetAmount) {
        this.oriAssetAmount = oriAssetAmount;
    }

    public String getAssetProfit() {
        return assetProfit;
    }

    public void setAssetProfit(String assetProfit) {
        this.assetProfit = assetProfit;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getEndDate1() {
        return endDate1;
    }

    public void setEndDate1(String endDate1) {
        this.endDate1 = endDate1;
    }

    public String getAssetAmount() {
        return assetAmount;
    }

    public void setAssetAmount(String assetAmount) {
        this.assetAmount = assetAmount;
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }

    public String getRepoProfit() {
        return repoProfit;
    }

    public void setRepoProfit(String repoProfit) {
        this.repoProfit = repoProfit;
    }

    public String getIndustryCategory() {
        return industryCategory;
    }

    public void setIndustryCategory(String industryCategory) {
        this.industryCategory = industryCategory;
    }


}
