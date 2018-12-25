import com.bittest.platform.bg.export.resource.ChartResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.CountInfoReqVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * 2018-08-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ChartTest {

    @Autowired
    private ChartResource chartResource;

//    @Test
//    public  void chartTest(){
//        ChartReqVo reqVo = new ChartReqVo();
//        reqVo.setPin("lisi");
//        chartResource.queryChart(reqVo);
//    }

    @Test
    public void count() {
        PaginationQuery<CountInfoReqVo> query = new PaginationQuery<CountInfoReqVo>();
        query.setPagination(new Pagination(10, 1));
        CountInfoReqVo reqVo = new CountInfoReqVo();
//        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        chartResource.queryCountInfoByPage(query);
    }


}
