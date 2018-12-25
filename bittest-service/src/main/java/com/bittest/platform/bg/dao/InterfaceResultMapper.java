package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.InterfaceResult;

/**
 * 接口执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface InterfaceResultMapper extends BaseMapper<InterfaceResult> {

    int deleteByTask(InterfaceResult result);

}
