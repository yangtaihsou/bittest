package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.vo.CheckPointReqVo;

/**
 * 2018-08-23.
 */
public interface CheckPointService {

    BasicResult deleteCheckPoint(CheckPointReqVo checkPointReqVo);

}
