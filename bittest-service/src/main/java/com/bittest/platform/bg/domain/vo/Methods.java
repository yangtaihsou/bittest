package com.bittest.platform.bg.domain.vo;

import java.util.List;

/**
 * 2018-03-21.
 */
public class Methods {

    //接口入参
    private List<String> inputParam;

    //方法名称
    private String methodName;

    //返回参数
    private String outputParam;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam;
    }

    public List<String> getInputParam() {
        return inputParam;
    }

    public void setInputParam(List<String> inputParam) {
        this.inputParam = inputParam;
    }
}
