import com.alibaba.fastjson.JSON;
import com.bittest.platform.bg.common.utils.ParameterReplaceUtils;
import com.bittest.platform.bg.dao.NotifyInfoMapper;
import com.bittest.platform.bg.domain.po.NotifyInfo;
import com.bittest.platform.bg.export.resource.TaskResource;
import com.bittest.platform.bg.export.result.Pagination;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.service.TaskService;
import com.bittest.platform.pg.util.Constant;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 2018-08-27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-config.xml")
public class NotifyInfoTest {

    private static final Logger log = LoggerFactory.getLogger(NotifyInfoTest.class);

    @Autowired
    private NotifyInfoMapper notifyInfoMapper;


    /**
     * 分页查询单元测试
     */
    @Test
    public void taskPageQuery() {
        notifyInfoMapper.queryBySelective(null);
    }

    @Test
    public void testAdd() {
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setSysCode("directpay");
        notifyInfo.setFieldKey("test");
        notifyInfo.setFieldId("12345678");
        notifyInfo.setContext("{\"test\":\"test123456\",\"code\":0}");
        int result = notifyInfoMapper.save(notifyInfo);
        Assert.assertEquals(result,1);
    }

    @Test
    public void testQuery(){
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setSysCode("directpay");
        notifyInfo.setFieldKey("test");
        notifyInfo.setFieldId("1234567");
        NotifyInfo notifyInfoResult = notifyInfoMapper.queryByUUID(notifyInfo);
        Assert.assertNotNull(notifyInfoResult);
    }

    @Test
    public void testUpdate(){
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setSysCode("directpay");
        notifyInfo.setFieldKey("test");
        notifyInfo.setFieldId("1234567");
        notifyInfo.setContext("{\"test\":\"test1234567\",\"code\":0}");
        int result = notifyInfoMapper.updateByUUID(notifyInfo);
        Assert.assertEquals(result,1);
    }
    @Test
    public void testNotifyParse(){
        NotifyInfo notifyInfo = new NotifyInfo();
        notifyInfo.setSysCode("directpay");
        notifyInfo.setFieldKey("test");
        notifyInfo.setFieldId("1234567");
        NotifyInfo notifyInfoResult = notifyInfoMapper.queryByUUID(notifyInfo);
        String fieldIdValue = ParameterReplaceUtils.getParaValue(notifyInfoResult.getContext(),"test");
        Assert.assertEquals(fieldIdValue,"test1234567");
    }

    @Test
    public void testPageList(){

        NotifyInfo notifyInfo = null;
        int totalRecord = notifyInfoMapper.queryTotal(notifyInfo);
        Integer p = 1;
        Pagination pagination = new Pagination(totalRecord,Constant.pageSize, p);

        PaginationQuery<NotifyInfo> reqVo = new PaginationQuery<NotifyInfo>();
        reqVo.setQuery(notifyInfo);
        reqVo.setPagination(pagination);
        List<NotifyInfo>  notifyInfoList = notifyInfoMapper.queryBySelectiveForPagination(reqVo);
        Assert.assertEquals(notifyInfoList.size(),10);
    }

}