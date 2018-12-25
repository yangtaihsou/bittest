import com.bittest.platform.bg.export.resource.InterfaceCollectionResource;
import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
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
public class InterfaceCollectionTest {
    private static final Logger log = LoggerFactory.getLogger(DataBaseTest.class);

    @Autowired
    private InterfaceCollectionResource interfaceCollectionResource;

    @Test
    public void save() {
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        reqVo.setInterfaceId("10180206150043000217");
        reqVo.setPin("lisi");
        reqVo.setUrl("http://baiduc.com11111");
        reqVo.setHead("{11111111}");
        reqVo.setMethod("test11111");
        reqVo.setName("百度11111");
        reqVo.setType(2);
        interfaceCollectionResource.saveInterfaceCollection(reqVo);
    }

    @Test
    public void delete() {
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        reqVo.setInterfaceId("10180206145809000216");
        interfaceCollectionResource.deleteInterfaceCollection(reqVo);
    }

    @Test
    public void queryList() {
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        reqVo.setPin("test");
        interfaceCollectionResource.queryInterfaceCollectionList(reqVo);
    }

    @Test
    public void queryObject() {
        InterfaceCollectionReqVo reqVo = new InterfaceCollectionReqVo();
        reqVo.setInterfaceId("10180206150043000217");
        interfaceCollectionResource.queryInterfaceCollectionDetail(reqVo);
    }
}
