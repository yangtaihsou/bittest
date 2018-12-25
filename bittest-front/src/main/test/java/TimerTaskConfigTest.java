import com.bittest.platform.bg.dao.TimerTaskConfigMapper;
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
public class TimerTaskConfigTest {

    private static final Logger log = LoggerFactory.getLogger(TimerTaskConfigTest.class);

    @Autowired
    private TimerTaskConfigMapper timerTaskConfigMapper;


    @Test
    public void queryList(){
        timerTaskConfigMapper.queryBySelective(null);
        log.info("-----------");
    }

    @Test
    public void queryTotal(){
        int result = timerTaskConfigMapper.queryTotal(null);
        Assert.assertEquals(result,2);
        log.info("-----------");
    }

    @Test
    public  void testAdd(){
        TimerTaskConfig timerTaskConfig = new TimerTaskConfig();
        timerTaskConfig.setStatus(0);
        timerTaskConfig.setBiztime(new Date());
        timerTaskConfig.setDataType("month");
        timerTaskConfig.setDay(3);
        timerTaskConfig.setHour(12);
        timerTaskConfig.setMinute(2);
        timerTaskConfig.setSecond(11);
        timerTaskConfig.setTaskId("test");
        timerTaskConfig.setTaskTimerDesc("test");
        StringBuilder uuid = new StringBuilder(timerTaskConfig.getTaskId());
        uuid.append("#").append(timerTaskConfig.getYear()).append("#").
                append(timerTaskConfig.getMonth()).append("#").
                append(timerTaskConfig.getDay()).append("#").
                append(timerTaskConfig.getHour()).append("#").
                append(timerTaskConfig.getMinute()).append("#").
                append(timerTaskConfig.getSecond());
        timerTaskConfig.setTaskTimerKey(uuid.toString());
        timerTaskConfigMapper.save(timerTaskConfig);
    }
}