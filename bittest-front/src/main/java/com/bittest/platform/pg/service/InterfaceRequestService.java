package com.bittest.platform.pg.service;

import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceReqVo;
import com.bittest.platform.pg.vo.InterfaceRequestReqVo;

/**
 * 2018-08-31.
 */
public interface InterfaceRequestService {

    /**
     * 转换前端接口请求数据
     *
     * @param req
     * @return
     */
    InterfaceReqVo getInterfaceReqVo(InterfaceRequestReqVo req);

    /**
     * 转换前端接口请求为收藏
     *
     * @param req
     * @return
     */
    InterfaceCollectionReqVo getCollectionReqVo(InterfaceRequestReqVo req);


}
