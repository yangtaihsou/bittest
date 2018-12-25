import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;
import org.junit.Test;

public class ParameterReplaceUtilsTest {
    @Test
    public void testGetParaValueByReg(){

        String contextStr="{\"username\":\"zs\",\"password\":\"123123pp\",\"phone\":\"13612345678\",\"high\":163,\"sub\":{\"username\":\"zs22\",\"password\":\"123123\",\"phone\":\"13612345678\"},\"width\":163119}";
        String value = ParameterReplaceUtils.getJsonParaValueByReg(contextStr,"width");
        System.out.println(value);
    }
}
