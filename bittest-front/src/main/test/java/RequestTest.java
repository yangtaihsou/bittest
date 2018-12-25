import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.RequestResource;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.JsfInfoReqVo;
import com.bittest.platform.bg.export.vo.JsfQueryReqVo;
import com.bittest.platform.bg.export.vo.RequestReqVo;
import com.bittest.platform.bg.service.RequestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-08-20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class RequestTest {

    private static final Logger log = LoggerFactory.getLogger(RequestTest.class);

    @Autowired
    private RequestService requestService;

    @Autowired
    private RequestResource requestResource;



    @Test
    public void queryAlias() {
        JsfInfoReqVo reqVo = new JsfInfoReqVo();
        reqVo.setJsf_url("TaskResource");
        requestResource.queryAlias(reqVo);
    }

    @Test
    public void queryIps() {
        JsfInfoReqVo reqVo = new JsfInfoReqVo();
        reqVo.setJsf_url("TaskResource");
        reqVo.setJsf_alias("interfacePlantfrom_bg_test");
        requestResource.queryIps(reqVo);
    }

    @Test
    public void queryMethods() {
        JsfInfoReqVo reqVo = new JsfInfoReqVo();
        reqVo.setJsf_url("TaskResource");
        reqVo.setJsf_alias("interfacePlantfrom_bg_test");
        requestResource.queryMethods(reqVo);
    }



    @Test
    public void reqTaskTest() {
        RequestReqVo requestReqVo = new RequestReqVo();
        requestReqVo.setTaskId("06170916140310000037");
        requestService.interfaceReqByTask(requestReqVo);
    }

    @Test
    public void requestByCase() {
        RequestReqVo requestReqVo = new RequestReqVo();
        requestReqVo.setCaseId("06170916140338000038");
        requestResource.interfaceReqByCase(requestReqVo);
    }


}
