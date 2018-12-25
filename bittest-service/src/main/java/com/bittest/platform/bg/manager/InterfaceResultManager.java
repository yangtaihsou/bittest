package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.InterfaceResult;

import java.util.List;

/**
 * 接口执行结果表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-31 15:52:54
 */
public interface InterfaceResultManager {

    int save(InterfaceResult result);

    int delete(InterfaceResult result);

    int deleteByTask(InterfaceResult result);

    InterfaceResult queryObject(InterfaceResult result);

    List<InterfaceResult> queryList(InterfaceResult result);

    int queryTotal(InterfaceResult result);

}
