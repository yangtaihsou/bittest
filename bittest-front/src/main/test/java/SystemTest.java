import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.SystemResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.SystemReqVo;
import com.bittest.platform.bg.service.SystemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-08-25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class SystemTest {

    private Logger log = LoggerFactory.getLogger(SystemTest.class);

    @Autowired
    private SystemService systemService;

    @Autowired
    private SystemResource systemResource;

    @Test
    public void save() {
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setSystemId("323232323");
        reqVo.setSystemName("111");
        reqVo.setRemark("111");
        reqVo.setPin("lisi");
        log.info("返回结果为，res：{}", JSON.toJSONString(systemService.saveSystem(reqVo)));
    }

    @Test
    public void update() {
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setSystemId("323232323");
        reqVo.setSystemName("222");
        reqVo.setRemark("122211");
        reqVo.setPin("lisi");
        log.info("返回结果为，res：{}", JSON.toJSONString(systemService.updateSystem(reqVo)));
    }

    @Test
    public void queryPage() {
        PaginationQuery<SystemReqVo> query = new PaginationQuery<SystemReqVo>();
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        query.setPagination(new Pagination(10, 1));
        log.info("返回结果为，res：{}", JSON.toJSONString(systemService.querySystemPage(query)));
    }

    @Test
    public void querySystems() {
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setSystemId("323232323");
        log.info("返回结果为，res：{}", JSON.toJSONString(systemService.querySystem(reqVo)));
    }


    @Test
    public void deleteSystem() {
        SystemReqVo reqVo = new SystemReqVo();
        reqVo.setSystemId("08170816162701000009");
        systemResource.deleteSystem(reqVo);
    }
}
