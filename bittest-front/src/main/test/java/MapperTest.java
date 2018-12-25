

import com.bittest.platform.bg.dao.CaseResultMapper;
import com.bittest.platform.bg.domain.po.CaseResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class MapperTest {

    private static final Logger log = LoggerFactory.getLogger(MapperTest.class);
    @Autowired
    private CaseResultMapper caseResultMapper;
    @Test
    public void testCaseResultSave(){
        CaseResult caseResult = new CaseResult();
        caseResult.setResultId(String.valueOf(System.currentTimeMillis()));
        caseResult.setTaskId(caseResult.getResultId());
        caseResult.setCaseId(caseResult.getResultId());
        caseResult.setCaseName("test");
        caseResult.setSystemName("test");
        caseResult.setCreateTime(new Date());
        caseResult.setPin("pin");
        int id = caseResultMapper.save(caseResult);
        log.info("------------"+id);
    }
}
