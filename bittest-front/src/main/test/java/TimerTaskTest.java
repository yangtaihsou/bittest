import com.bittest.platform.bg.dao.TimerTaskConfigMapper;
import com.bittest.platform.bg.dao.TimerTaskMapper;
import com.bittest.platform.bg.domain.po.TimerTask;
import com.bittest.platform.bg.domain.po.TimerTaskConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * 2018-08-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class TimerTaskTest {

    private static final Logger log = LoggerFactory.getLogger(TimerTaskTest.class);

    @Autowired
    private TimerTaskMapper timerTaskMapper;


    @Test
    public void queryList(){
        timerTaskMapper.queryBySelective(null);
        log.info("-----------");
    }

    @Test
    public void queryTotal(){
        int result = timerTaskMapper.queryTotal(null);
        Assert.assertEquals(result,2);
        log.info("-----------");
    }

    @Test
    public  void testAdd(){
        TimerTask timerTask = new TimerTask();
        timerTask.setBizTime(new Date());
        timerTask.setTaskData("");
        timerTask.setTaskId("test");
        timerTask.setTaskStatus(0);
        timerTask.setUuid("test");
        timerTaskMapper.save(timerTask);
    }
}