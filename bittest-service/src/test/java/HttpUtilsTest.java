import com.bittest.platform.bg.common.utils.HttpUtils;
import com.bittest.platform.bg.domain.po.HttpRes;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilsTest {
    @Test
    public void testSendByHttp(){
        String url = "https://shop-base-beta.bittest123.com/api/casLogin";
        String body = "password=Abcd1234!&username=sysadmin&validate=abcd";
        Map<String, String> heads = new HashMap<String, String>();
        heads.put("http.protocol.cookie-policy","compatibility");
        heads.put("cookie","cassessID=c2799f17436932b38bc175e5a3a307f5;locale=en;_xsrf=Tmd4SzROcHBlUmkwM213UkhBYU9HMDRhdENtamQ0QUg=|1541054346208358764|2d827bffa1dc1681b850e7d39d13da72ffce1f66;ticket=ST-1911e71f9e9af3ef55d3eb43aa71aa14b4ebbd981fb510611ef5164fc9ef1636");
        heads.put("referer","https://shop-repair-beta.bittest123.com.cn/user/afterSale/repairHeaderList?");
        String action = "GET";
        HttpRes httpRes = HttpUtils.sendByHttp(url,null,heads,action);
        System.out.println("--"+httpRes.getResBody());
    }

    @Test
    public void testInitHttpRequest() throws UnsupportedEncodingException {
        String url = "https://shop-staff-beta.bittest123.com/shop-admin/login?ref=toolbar&ewer=3";
        String body = "{\"message\":\"PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9Im5vIj8+PFJlcXVlc3Q+PEhlYWQ+PFR4Q29kZT4yODE4PC9UeENvZGU+PC9IZWFkPjxCb2R5PjxJbnN0aXR1dGlvbklEPjAwMzc2NjwvSW5zdGl0dXRpb25JRD48UGF5bWVudE5vPjIwMTgxMDMwMTU1ODMxMTg0NTI2Nzg2OTA5ODwvUGF5bWVudE5vPjxQYXltZW50V2F5PjEwPC9QYXltZW50V2F5PjxTdGF0dXM+MjA8L1N0YXR1cz48QW1vdW50PjEwMDwvQW1vdW50PjxDb3Vwb25BbW91bnQ+MDwvQ291cG9uQW1vdW50PjxDYXJkVHlwZT4xMDwvQ2FyZFR5cGU+PEJhbmtOb3RpZmljYXRpb25UaW1lP\n" +
                "jIwMTgxMDMwMTU1ODUwPC9CYW5rTm90aWZpY2F0aW9uVGltZT48UGF5ZXJJRC8+PE9wZXJhdG9ySUQvPjxTdG9yZUlELz48VGVybWluYWxJRC8+PFJlc3BvbnNlQ29kZT4wMDAwPC9SZXNwb25zZUNvZGU+\n" +
                "PFJlc3BvbnNlTWVzc2FnZT7miJDlip9bMDAwMDAwMF08L1Jlc3BvbnNlTWVzc2FnZT48L0JvZHk+PC9SZXF1ZXN0Pg==\",\n" +
                "\"signature\":\"19B26BEDE9817DC3430D0479480C4C7EDF03614F79B866FAD90C3743D0154EC4BD6B3E93536043BB9F06B8B68E03208F5F09848B0F93D53180CA1F83FC069A2188C568B6A74F782C5497D2A153E13D3D8392514D15FC0151C9B537350E614869B00E95384187EE90D3ECBECC72460A73399EF34950DE6ED59515BCC5D5CB7931\"}";
        Map<String, String> heads = new HashMap<String, String>();
        //heads.put("Content-Type","application/x-www-form-urlencoded");
        String action = "post";
        HttpUtils.initHttpRequest(url,body,action,heads);
    }
}
