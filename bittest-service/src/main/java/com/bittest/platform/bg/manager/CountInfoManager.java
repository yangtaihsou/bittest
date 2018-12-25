package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.CountInfo;

import java.util.List;

/**
 * 2018-03-07.
 */
public interface CountInfoManager {

    List<CountInfo> queryCounInfotListByPage(CountInfo countInfo);

    int queryCounInfotCount(CountInfo countInfo);

}
