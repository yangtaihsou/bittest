package com.bittest.platform.bg.export.vo;

import java.util.List;

/**
 * 2018-08-27.
 */
public class ChartResVo {

    private int userCount;

    private int taskCount;

    private int caseCount;

    private int interfaceCount;

    private List<String> taskNameTopTen;

    private List<Double> taskResultRateTopTen;

    private List<String> systemName;

    private List<Integer> caseCounts;

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    public int getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(int caseCount) {
        this.caseCount = caseCount;
    }

    public List<String> getTaskNameTopTen() {
        return taskNameTopTen;
    }

    public void setTaskNameTopTen(List<String> taskNameTopTen) {
        this.taskNameTopTen = taskNameTopTen;
    }

    public List<Double> getTaskResultRateTopTen() {
        return taskResultRateTopTen;
    }

    public void setTaskResultRateTopTen(List<Double> taskResultRateTopTen) {
        this.taskResultRateTopTen = taskResultRateTopTen;
    }

    public List<String> getSystemName() {
        return systemName;
    }

    public void setSystemName(List<String> systemName) {
        this.systemName = systemName;
    }

    public List<Integer> getCaseCounts() {
        return caseCounts;
    }

    public void setCaseCounts(List<Integer> caseCounts) {
        this.caseCounts = caseCounts;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }

    public int getInterfaceCount() {
        return interfaceCount;
    }

    public void setInterfaceCount(int interfaceCount) {
        this.interfaceCount = interfaceCount;
    }
}
