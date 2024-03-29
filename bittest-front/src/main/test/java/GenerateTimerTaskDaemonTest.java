import com.bittest.platform.bg.schedule.service.TaskExcuteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class GenerateTimerTaskDaemonTest {

    private static final Log log = LogFactory.getLog(GenerateTimerTaskDaemonTest.class);
    @Autowired
    TaskExcuteService generateTimerTaskService;
    @Test
    public void testGenerateTimerTask(){
        generateTimerTaskService.exe();
        log.info("12344444444");
    }
}
