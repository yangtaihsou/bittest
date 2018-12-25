//import common.util.StringEscapeUtils;

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;
import com.bittest.platform.bg.common.utils.RegExpressionUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 2018-08-30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class RegTest {

    private static final Logger log = LoggerFactory.getLogger(RegTest.class);

    /**
     * 随机数参数化替换单元测试
     */
    @Test
    public void randomTest() {
        String reqParam = "一${UUID}二${__Random( 1 , 100 ,)}三${__Random(3,4,test)}四${__Random(5,6,ranVar)}五${__Random(1,100)}";
        Map<String, String> userParam = new HashMap<String, String>();
        userParam.put("test", "test");
        System.out.println(ParameterReplaceUtils.ramdomReplace(reqParam, userParam));
        System.out.println(JSON.toJSONString(userParam));
    }

    /**
     * UUID参数化替换单元测试
     */
    @Test
    public void uuidTest() {
        String reqParam = "一${ __uuid}二${__uuid,testt}三${_uuid,3,4)}";
        Map<String, String> userParam = new HashMap<String, String>();
        userParam.put("test", "test");
        System.out.println(ParameterReplaceUtils.uuidReplace(reqParam, userParam));
        System.out.println(JSON.toJSONString(userParam));
    }

    /**
     * time参数化替换单元测试
     */
    @Test
    public void timeTest() {
        String reqParam = "${__time(mmss,)}${__time(yyyy-MM-dd HH-mm-ss)}---${__time(yyyy-MM-dd HH-mm-ss,fsf)}";
//        String reqParam="${__time(mmss)}";
        Map<String, String> userParam = new HashMap<String, String>();
        System.out.println(ParameterReplaceUtils.timeReplace(reqParam, userParam));
        System.out.println("reqParam:" + reqParam);
        System.out.println(JSON.toJSONString(userParam));
    }

    /**
     * 用户参数化替换单元测试
     */
    @Test
    public void userTest() {
        String reqParam = "一${ te!st }二${user}三${admin}四${orderId}五${order}六";
        Map<String, String> caseParam = new HashMap<String, String>();
        caseParam.put("user", "zhangjunming4");
        caseParam.put("admin", "administrator");
        caseParam.put("te!st", "test");
        Map<String, String> taskParam = new HashMap<String, String>();
        taskParam.put("user", "lisi");
        System.out.println(ParameterReplaceUtils.userParamReplace(reqParam, caseParam, taskParam));
    }

    @Test
    public void regTest() {
        String reg = "policyNo\\\":\\\"(.+?)\\\",\\\"serialNo";
        //reg = StringEscapeUtils.escapeJava(reg);
        String body = "{\"version\":\"1.0.0\",\"functionFlag\":\"INSURE\",\"interfaceFlag\":\"JD\",\"companyCode\":\"120\",\"transId\":\"fe5262e287984b5e994c4b296e952d04\",\"sendTime\":\"2018-03-16 16:46:50\",\"responseCode\":0,\"responseMsg\":\"交易成功\",\"data\":\"{\\\"order\\\":{\\\"orderId\\\":\\\"72264313941\\\",\\\"policyInfoList\\\":[{\\\"insuredIdNo\\\":\\\"110101198506020310\\\",\\\"policyNo\\\":\\\"JD05418041600510854\\\",\\\"serialNo\\\":\\\"412415\\\"}],\\\"return\\\":true}}\"}";
        RegExpressionUtil.regData(reg, body, 1);
    }


}
