package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.ListResult;
import com.bittest.platform.bg.export.vo.InterfaceCollectionReqVo;
import com.bittest.platform.bg.export.vo.InterfaceCollectionResVo;

/**
 * package com.bittest.platform.bg.service;
 * 2018-08-23.
 */
public interface InterfaceCollectionService {

    public GenericResult<InterfaceCollectionResVo> saveInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo);

    public ListResult<InterfaceCollectionResVo> queryInterfaceCollectionList(InterfaceCollectionReqVo interfaceCollectionReqVo);


    public GenericResult<InterfaceCollectionResVo> queryInterfaceCollectionDetail(InterfaceCollectionReqVo interfaceCollectionReqVo);

    public BasicResult deleteInterfaceCollection(InterfaceCollectionReqVo interfaceCollectionReqVo);

}
