/**
 * 2018-08-26.
 */

import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.CaseResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.CaseInfoReqVo;
import com.bittest.platform.bg.service.CaseInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")*/
public class CaseTest {

    private static final Logger log = LoggerFactory.getLogger(CaseTest.class);

    @Autowired
    private CaseInfoService caseInfoService;

    @Autowired
    private CaseResource caseResource;


    @Test
    public void caseSave() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setCaseId(String.valueOf(System.currentTimeMillis()));
        reqVo.setPin("lisi");
        reqVo.setCaseName("测试用例");
        reqVo.setRemark("备注");
        reqVo.setSysId("固收");
        reqVo.setCaseParam("{111}");
        reqVo.setStatus(1);
        log.info("保存结果为:{}", JSON.toJSONString(caseInfoService.saveCaseInfo(reqVo)));
    }

    @Test
    public void caseUpdte() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setCaseId("3232323");
        reqVo.setPin("lisi");
        reqVo.setCaseName("测试用例");
        reqVo.setRemark("备注");
        reqVo.setSysId("固收");
        reqVo.setCaseParam("{111}");
        reqVo.setStatus(1);
        log.info("修改结果为:{}", JSON.toJSONString(caseInfoService.updateCaseInfo(reqVo)));
    }

    @Test
    public void caseDelete() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setCaseId("3232323");
        log.info("删除结果为:{}", JSON.toJSONString(caseInfoService.deleteCaseInfo(reqVo)));
    }

    @Test
    public void caseQyery() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setCaseId("111");
        log.info("查询结果为:{}", JSON.toJSONString(caseInfoService.queryCaseInfo(reqVo)));
    }

    @Test
    public void fetchCase() {
        PaginationQuery<CaseInfoReqVo> query = new PaginationQuery<CaseInfoReqVo>();
        query.setPagination(new Pagination(10, 1));
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setPin("lisi");
        reqVo.setTaskId("323");
        query.setQuery(reqVo);
        log.info("查询结果为:{}", JSON.toJSONString(caseResource.queryCaseInfoPageByTask(query)));
    }

    @Test
    public void NoFetch() {
        PaginationQuery<CaseInfoReqVo> query = new PaginationQuery<CaseInfoReqVo>();
        query.setPagination(new Pagination(10, 1));
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setPin("lisi");
        reqVo.setTaskId("323");
        query.setQuery(reqVo);
        log.info("查询结果为:{}", JSON.toJSONString(caseResource.queryCaseInfoPageNoFetch(query)));
    }

    @Test
    public void deleteCase() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setCaseId("06171005170112000143");
        caseResource.deleteCaseInfo(reqVo);
    }


    @Test
    public void queryCaseByName() {
        CaseInfoReqVo reqVo = new CaseInfoReqVo();
        reqVo.setPin("lisi");
        caseResource.queryCaseByName(reqVo);
    }
    @Test
    public void testContain() {
        String body = "{\n" +
                "    \"msg\": \"success\",\n" +
                "    \"code\": 0,\n" +
                "    \"data\": {\n" +
                "        \"token\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3NCIsImlhdCI6MTUzNjcyMDIwMiwiZXhwIjoxNTM3MzI1MDAyfQ.9Rdh_H3tN6vWe1tWelKXHNkXAItzaBukhpnFnYiwxn28PkdiU3idYzTyjCPCxxNeTmjAwjy87YQF1BEIz6_TEQ\",\n" +
                "        \"expire\": 604800\n" +
                "    }\n" +
                "}";
        String checkString  = "\"msg\": \"success\"";
        System.out.println(body.indexOf(checkString));
    }
}
