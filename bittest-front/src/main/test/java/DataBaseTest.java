import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.DataBaseResource;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.service.DataBaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-08-22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class DataBaseTest {

    private static final Logger log = LoggerFactory.getLogger(DataBaseTest.class);

    @Autowired
    private DataBaseService dataBaseService;

    @Autowired
    private DataBaseResource dataBaseResource;


    @Test
    public void save() {
        DataBaseReqVo reqVo = new DataBaseReqVo();
        reqVo.setPin("lisi");
        reqVo.setRemark("备注");
        reqVo.setDatabaseId("1111");
        reqVo.setName("接口自动化系统数据库");
        reqVo.setUrl("127.0.0.1:3306/bittestplatform");
        reqVo.setUsername("root");
        reqVo.setPassword("123456");
        log.info("返回结果为：{}", JSON.toJSONString(dataBaseService.saveDataBase(reqVo)));
    }

    @Test
    public void update() {
        DataBaseReqVo reqVo = new DataBaseReqVo();
        reqVo.setPin("lisi");
        reqVo.setRemark("备注11");
        reqVo.setDatabaseId("1111");
        reqVo.setName("接口自动化系统数据库1");
        reqVo.setUrl("127.0.0.1:3306/bittestplatform1");
        reqVo.setUsername("root1");
        reqVo.setPassword("1234561");
        log.info("返回结果为：{}", JSON.toJSONString(dataBaseService.updateDataBase(reqVo)));
    }

    @Test
    public void query() {
        DataBaseReqVo reqVo = new DataBaseReqVo();
        reqVo.setDatabaseId("1111");
        reqVo.setPin("lisi");
        log.info("返回结果为：{}", JSON.toJSONString(dataBaseService.queryDataBase(reqVo)));
    }

    @Test
    public void connectionTest() {
        DataBaseReqVo reqVo = new DataBaseReqVo();
        reqVo.setUrl("172.24.7.186:3306/323");
        reqVo.setUsername("root");
        reqVo.setPassword("1234561");
        log.info("数据库连接测试：{}", JSON.toJSONString(dataBaseService.connectDataBase(reqVo)));
    }

    @Test
    public void deleteDataBase() {
        DataBaseReqVo reqVo = new DataBaseReqVo();
        reqVo.setDatabaseId("1111");
        dataBaseResource.deleteDataBase(reqVo);
    }
}
