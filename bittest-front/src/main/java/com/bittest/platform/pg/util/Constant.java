package com.bittest.platform.pg.util;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

public class Constant {

    public final static int pageSize = 10;

    public final static int pageSize_Modal = 10;
    public static final String AUTH_INFOS_COOKIE_NAME = "_authinfos_";
    public static final String AUTH_PASS = "_pass_";
    public static final int AUTH_INFOS_MAX_AGE = 3 * 60 * 60;
    public static final String AUTH_CODE_COOKIE_NAME = "_authcode_";
    public static final int AUTH_CODE_INFOS_MAX_AGE = 4 * 60;
    public static final String TRADE_AESPWD = "tradeAesPwd";
    public static final String JD_UNICK = "unick";
    public static final String JD_GET_LOGININFO_URL = "getLoginInfoUrl";
    //TRADE_TYPE:10-购买,11-追加，20-取消购买,30-赎回,31-部分赎回，40-取消赎回
    //追加类型
    public static final String ACCOUNT_TRADE_TYPE_ADD = "10,11,40";
    //赎回类型
    public static final String ACCOUNT_TRADE_TYPE_REFUND = "30,31,20";
    //京东商家编码
    public static final String JD_SHOP = "jd_shop";
    //京东业务人员
    public static final String JD_BUSSINESS = "jd_bussiness";
    //京东商家编码
    public static final String ERR_MESSAGE = "网络异常";
    //京东商家编码
    public static final String NOT_ROLE_STAFF = "对不起，您没有访问权限";
    //京东商家编码
    public static final String NOT_COMPANY_STAFF = "对不起，您不是信托公司员工";
    public static final int BUFFER_LENGTH = 1024 * 2;
    public static final String SMS_TEMPLATE_CODE = "asset_erp_login_sms";
    public static final String SMS_AUTH_CODE = "msg_code";
    public static final String SMS_AUTH_SERVICE_NO = "sms_auth_service";
    public static final int DEFAULT_WEEK_INTERVAL = 7;//星期默认天数
    public static final int DEFAULT_MOTH_INTERVAL = 30;//月份默认天数
    public static final String SPLET_SYMBOL = ",";//月份默认天数
    public static final String ASSET_COMPANY_TYPE = "ASSET_COMPANY_TYPE";//资产公司类型
    public static final String ACCOUNT_AUDIT_STATUS = "ACCOUNT_AUDIT_STATUS";//客商审核状态
    public static final String FINANCE_CODE = "FINANCE_CODE";//账务主体]
    public static final String DEPARTMENT = "DEPARTMENT";//资产所属部门
    public static final BigDecimal DECIMAL_100 = new BigDecimal("100");
    public static final String SYS_NAME = "ams_erp";
    public static final String PERCENT = "%";

    public static void loadRequestParameterMapToModel(Model model, HttpServletRequest request) {
        model.addAttribute("parameterMap", request.getParameterMap());
    }

}
