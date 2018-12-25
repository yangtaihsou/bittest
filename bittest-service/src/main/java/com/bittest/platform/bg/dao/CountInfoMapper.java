package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.CaseInfo;
import com.bittest.platform.bg.domain.po.CountInfo;

import java.util.List;

/**
 * 统计相关方法
 */
public interface CountInfoMapper extends BaseMapper<CaseInfo> {


    List<CountInfo> queryCounInfotListByPage(CountInfo countInfo);

    int queryCounInfotCount(CountInfo countInfo);


}
