package com.bittest.platform.bg.dao;


import com.bittest.platform.bg.domain.po.NotifyInfo;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.Query;

import java.util.List;

public interface NotifyInfoMapper extends BaseMapper<NotifyInfo>{




     public List<NotifyInfo> queryBySelective(Query<NotifyInfo> notifyInfo);

     public long queryCountBySelective(NotifyInfo notifyInfo);
     
     public NotifyInfo queryByPrimaryKey(Long id);

   



   public List<NotifyInfo> queryBySelectiveForPagination(PaginationQuery<NotifyInfo> notifyInfo);



   /**根据唯一索引更新**/
   public int updateByUUID(NotifyInfo notifyInfo);

    public NotifyInfo queryByUUID(NotifyInfo notifyInfo);


}