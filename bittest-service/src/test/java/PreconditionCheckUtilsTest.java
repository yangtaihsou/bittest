

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.context.CaseParaApplicationContext;
import com.bittest.platform.bg.common.utils.PreconditionCheckUtils;
import com.bittest.platform.bg.domain.po.CheckPoint;
import org.junit.Test;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PreconditionCheckUtilsTest {
    @Test
    public void testCheck(){
        //[{"checkMethod":2,"checkType":1,"checkValue":"2","checkVar":"f","result":false}]
        List<CheckPoint> preconditionCheckList = new ArrayList<CheckPoint>();
        CheckPoint checkPoint = new CheckPoint();
        checkPoint.setCheckMethod(1);
        checkPoint.setCheckValue("1");
        checkPoint.setCheckVar("a");
        preconditionCheckList.add(checkPoint);

        CheckPoint checkPoint1 = new CheckPoint();
        checkPoint1.setCheckMethod(2);
        checkPoint1.setCheckValue("2");
        checkPoint1.setCheckVar("b");
        preconditionCheckList.add(checkPoint1);

        CheckPoint checkPoint2 = new CheckPoint();
        checkPoint2.setCheckMethod(3);
        checkPoint2.setCheckValue("3");
        checkPoint2.setCheckVar("c");
        preconditionCheckList.add(checkPoint2);

        CheckPoint checkPoint3 = new CheckPoint();
        checkPoint3.setCheckMethod(4);
        checkPoint3.setCheckValue("4");
        checkPoint3.setCheckVar("d");
        preconditionCheckList.add(checkPoint3);

        CheckPoint checkPoint4 = new CheckPoint();
        checkPoint4.setCheckMethod(5);
        checkPoint4.setCheckValue("5");
        checkPoint4.setCheckVar("e");
        preconditionCheckList.add(checkPoint4);


        Map<String, String> contextParam = new HashMap<String, String>();
        contextParam.put("a","1");
        contextParam.put("b","11");
        contextParam.put("c","3");
        CaseParaApplicationContext context =  new CaseParaApplicationContext();
        context.put(System.currentTimeMillis()+"", JSON.toJSONString(contextParam));
        CaseParaApplicationContext.setContext(context);
        Map<String, String> caseParam = new HashMap<String, String>();
        caseParam.put("d","5");
        Map<String, String> taskParam = new HashMap<String, String>();
        taskParam.put("e","3");
        //(1、包含 2、不包含 3、等于 4、大于 5、小于)
        Boolean result = PreconditionCheckUtils.check(preconditionCheckList,caseParam,taskParam);
        Assert.isTrue(result,"前置条件不通过");

    }
}
