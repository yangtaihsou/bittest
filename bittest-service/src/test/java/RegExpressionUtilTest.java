
import com.bittest.platform.bg.common.utils.RegExpressionUtil;
import org.junit.Test;

public class RegExpressionUtilTest {
    @Test
    public void testRegData(){
        //String reg="\"password\":\"(.+?)\"";//.*"password":"([^"]+)".*
        String reg="\"high\":(.+?),";//数字
        String contextStr="{\"username\":\"zs\",\"password\":\"123123pp\",\"phone\":\"13612345678\",\"high\":163,\"sub\":{\"username\":\"zs22\",\"password\":\"123123\",\"phone\":\"13612345678\"},\"width\":163}";
        int index = 1;
        String value = RegExpressionUtil.regData(reg,contextStr,index);
        System.out.println(value);
    }


}
