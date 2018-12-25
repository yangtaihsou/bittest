

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;
import com.bittest.platform.bg.domain.po.TaskResult;
import org.junit.Test;

import java.text.DecimalFormat;

public class CommonTest {

    @Test
    public void testJson(){
        String jsonStr = "{\"createTime\":1537338285000,\"id\":161,\"pin\":\"admin\",\"remark\":\"\",\"status\":1,\"sysId\":\"1536742645008\",\"taskId\":\"1537338285086\",\"taskName\":\"开发环境任务\",\"taskParam\":\"null\",\"updateTime\":1537338304000}";
        TaskResult taskResult = JSON.parseObject(jsonStr, TaskResult.class);
        System.out.print("----------------"+taskResult);
    }

    @Test
    public void testDouble() throws InterruptedException {

        long start =  System.currentTimeMillis();
        Thread.sleep(2121);
        long end =  System.currentTimeMillis();
        System.out.println(Double.valueOf(end-start)/1000);
        float time = Float.valueOf((end-start)/1000);
        System.out.println(time);

    }
    @Test
    public void testDecimalFormat(){
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String  successRate = decimalFormat.format((float) 1 / 3 * 100);
        System.out.println(successRate);
        System.out.println(Double.valueOf(successRate));
    }

    @Test
    public void testDoubleCompare(){

        System.out.println(new Double(1.001)/1);
        System.out.println(
                new Double(1.000).compareTo(new Double(1)) < 0);
    }
}
