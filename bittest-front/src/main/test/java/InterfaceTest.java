import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.domain.po.InterfaceInfo;
import com.bittest.platform.bg.export.resource.InterfaceResource;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.CaseInterfaceRelastionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.bg.export.vo.InterfaceResVo;
import com.bittest.platform.bg.manager.InterfaceManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-08-23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class InterfaceTest {

    private static final Logger log = LoggerFactory.getLogger(InterfaceTest.class);

    @Autowired
    private InterfaceManager interfaceManager;

    @Autowired
    private InterfaceResource interfaceResource;

//    @Test
//    public  void insert(){
//        InterfaceInfo interfaceInfo = new InterfaceInfo();
//        interfaceInfo.setInterfaceId("212121212");
//        interfaceInfo.setName("323");
//        interfaceInfo.setUrl("3232332");
//        interfaceInfo.setBody("3232");
//        interfaceInfo.setPin("lisi");
//        interfaceInfo.setType(1);
//        interfaceInfo.setStatus(1);
//        interfaceManager.save(interfaceInfo);
//    }

    @Test
    public void update() {
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setInterfaceId("22222");
        interfaceInfo.setName("");
        interfaceInfo.setUrl("22222");
        interfaceInfo.setBody("22222");
        interfaceInfo.setPin("zhangjunming4");
        interfaceInfo.setInterfaceId("32323");
        interfaceManager.update(interfaceInfo);
    }

    @Test
    public void queryInterfaceDetail() {
        InterfaceReqVo interfaceReqVo = new InterfaceReqVo();
        interfaceReqVo.setInterfaceId("");
        GenericResult<InterfaceResVo> result = interfaceResource.queryInterfaceDetail(interfaceReqVo);
        log.error("查询结果为：{}", JSON.toJSONString(result));
    }

    @Test
    public void queryAllInterfaceList() {
        PaginationQuery<InterfaceReqVo> query = new PaginationQuery<InterfaceReqVo>();
        query.setPagination(new Pagination(10, 1));
        InterfaceReqVo reqVo = new InterfaceReqVo();
        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        interfaceResource.queryAllInterfaceList(query);
    }

    @Test
    public void addConstainsInterface() {
        InterfaceReqVo reqVo = new InterfaceReqVo();
        reqVo.setInterfaceId("06170920213959000072");
        reqVo.setCaseId("06170916140338000038");
        interfaceResource.addConstainsInterface(reqVo);
    }
//
//    @Test
//    public void deleteInterface(){
//        InterfaceReqVo reqVo = new InterfaceReqVo();
//        reqVo.setInterfaceId("06170925203348000099");
////        reqVo.setCaseId("06170916140338000038");
//        interfaceResource.deleteInterfaceInfo(reqVo);
//    }

    @Test
    public void enableStatus() {
        CaseInterfaceRelastionReqVo reqVo = new CaseInterfaceRelastionReqVo();
        reqVo.setStatus(1);
        reqVo.setCaseId("1");
        reqVo.setInterfaceId("1");
        interfaceResource.updateCaseInterfaceRelation(reqVo);
    }

}
