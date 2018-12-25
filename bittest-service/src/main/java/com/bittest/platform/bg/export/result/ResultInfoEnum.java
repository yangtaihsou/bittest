package com.bittest.platform.bg.export.result;

public enum ResultInfoEnum {


    /**
     * 公共返回
     */
    SUCCESS("000000", "交易成功"),
    JSF_TIMEOUT_ERROR("100001", "JSF服务调用超时"),
    JSF_ERROR("100002", "访问JSF服务异常，请检查JSF服务是否正常"),
    REQUEST_PARAMS_ERROR("000001", "请求参数异常"),
    ERROR("000002", "执行错误"),
    UNKNOWN_ERROR("000003", "未知异常"),
    NULL_OBJECT_ERROR("000004", "接口访问信息为空"),
    PARAM_INVALID("000005", "参数真实性验证错误"),
    NO_TYPE("000006", "亲，本系统不支持该接口类型请求"),


    /**
     * 解析jsf服务错误编码
     */
    JSF_RESOLVE_ERROR("200000", "解析jsf服务时，访问jsf服务异常"),
    JSF_RESOLVE_TIMEOUT("200001", "解析jsf服务时，访问jsf服务连接超时"),
    JSF_RESOLVE_METHOD_ERROR("200002", "获取jsf方法信息异常"),
    JSF_RESOLVE_METHOD_EMPTY("200003", "获取jsf方法信息为空"),
    JSF_RESOLVE_ALIAS_ERROR("200004", "解析jsf服务时，访问jsf别名信息异常"),
    JSF_RESOLVE_IPS_ERROR("200005", "解析jsf服务时，访问jsf ip服务信息异常"),
    JSF_RESOLVE_ALIAS_EMPTY("200006", "未查询到相关别名信息，请确认要解析的jsf接口服务是否正常启动！");

    private String errorCode;
    private String errorMsg;

    ResultInfoEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg(String extMsg) {
        return errorMsg + " " + extMsg;
    }
}