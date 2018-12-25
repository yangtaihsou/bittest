import com.bittest.platform.bg.export.resource.InterfaceHistoryResource;
import com.bittest.platform.bg.export.vo.InterfaceHistoryReqVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-02-06.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class InterfaceHistoryTest {
    private static final Logger log = LoggerFactory.getLogger(DataBaseTest.class);

    @Autowired
    private InterfaceHistoryResource interfaceHistoryResource;

    @Test
    public void save() {
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        reqVo.setInterfaceId("323232");
        reqVo.setPin("test");
        reqVo.setUrl("http://baiduc.com");
        reqVo.setHead("{111}");
        reqVo.setMethod("test");
        reqVo.setType(1);
        interfaceHistoryResource.saveInterfaceHistory(reqVo);
    }

    @Test
    public void delete() {
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        reqVo.setId(Long.parseLong("1"));
        interfaceHistoryResource.deleteInterfaceHistory(reqVo);
    }

    @Test
    public void queryList() {
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        reqVo.setPin("test");
        reqVo.setUrl("com.test");
        interfaceHistoryResource.queryInterfaceHistoryList(reqVo);
    }

    @Test
    public void queryObject() {
        InterfaceHistoryReqVo reqVo = new InterfaceHistoryReqVo();
        reqVo.setId(Long.parseLong("2"));
        interfaceHistoryResource.queryInterfaceHistoryDetail(reqVo);
    }
}
