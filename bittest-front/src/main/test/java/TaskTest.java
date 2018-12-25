import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.export.resource.TaskResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.service.TaskService;
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
public class TaskTest {

    private static final Logger log = LoggerFactory.getLogger(TaskTest.class);

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskResource taskResource;


    /**
     * 分页查询单元测试
     */
    @Test
    public void taskPageQuery() {
        PaginationQuery<TaskReqVo> query = new PaginationQuery<TaskReqVo>();
        query.setPagination(new Pagination(10, 1));
        TaskReqVo reqVo = new TaskReqVo();
        reqVo.setPin("32323");
        query.setQuery(reqVo);
        log.info("查询结果为:{}", JSON.toJSONString(taskService.queryTaskList(query)));
    }

    @Test
    public void taskQuery() {
        TaskReqVo taskReqVo = new TaskReqVo();
        taskReqVo.setTaskId("323");
        log.info("查询结果为:{}", JSON.toJSONString(taskService.queryTask(taskReqVo)));
    }

    @Test
    public void deleteTask() {
        TaskReqVo taskReqVo = new TaskReqVo();
        taskReqVo.setTaskId("06171005165911000142");
        taskResource.deleteTask(taskReqVo);
    }

    @Test
    public void test() {

    }

}
