import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.ResultResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.CaseResultResVo;
import com.bittest.platform.bg.export.vo.InterfaceResultResVo;
import com.bittest.platform.bg.export.vo.ResultReqVo;
import com.bittest.platform.bg.export.vo.TaskResultResVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 2018-08-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class ResultTest {

    private static final Logger log = LoggerFactory.getLogger(ResultTest.class);

    @Autowired
    private ResultResource resultResource;

    @Test
    public void taskPageTest() {
        PaginationQuery<ResultReqVo> query = new PaginationQuery<ResultReqVo>();
        query.setPagination(new Pagination(10, 1));
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setCaseId("1111");
        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        PaginationResult<TaskResultResVo> result = resultResource.queryTaskResultPage(query);
        log.info("执行结果为：{}", JSON.toJSONString(result));
    }

    @Test
    public void casePageTest() {
        PaginationQuery<ResultReqVo> query = new PaginationQuery<ResultReqVo>();
        query.setPagination(new Pagination(10, 1));
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("1111");
        reqVo.setTaskId("1111");
        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        PaginationResult<CaseResultResVo> result = resultResource.queryCaseResultPage(query);
        log.info("执行结果为：{}", JSON.toJSONString(result));
    }

    @Test
    public void interfacePageTest() {
        PaginationQuery<ResultReqVo> query = new PaginationQuery<ResultReqVo>();
        query.setPagination(new Pagination(10, 1));
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("1111");
        reqVo.setCaseId("1111");
        reqVo.setPin("lisi");
        query.setQuery(reqVo);
        PaginationResult<InterfaceResultResVo> result = resultResource.queryInterfaceResultPage(query);
        log.info("执行结果为：{}", JSON.toJSONString(result));
    }

    @Test
    public void deleteById() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("1111");
        reqVo.setPin("zhangjunming");
        resultResource.deleteResultById(reqVo);
    }

    @Test
    public void deleteByTask() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setTaskId("1111");
        reqVo.setPin("zhangjunming");
        resultResource.deleteResultByTask(reqVo);
    }

    @Test
    public void queryTaskResultDetail() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("06170922134415000095");
        resultResource.queryTaskResultDetail(reqVo);
    }

    @Test
    public void queryCaseResultDetail() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("06170922134415000095");
        reqVo.setCaseId("06170921095824000086");
        resultResource.queryCaseResultDetail(reqVo);
    }

    @Test
    public void queryInterfaceResultDetail() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("06170922134415000095");
        reqVo.setCaseId("06170921095824000086");
        reqVo.setInterfaceId("06170921101210000088");
        resultResource.queryInterfaceResultDetail(reqVo);
    }

    @Test
    public void queryCaseResultList() {
        ResultReqVo reqVo = new ResultReqVo();
        reqVo.setResultId("06170926183210000103");
        reqVo.setPin("lisi");
        resultResource.queryCaseResultList(reqVo);
    }

}
