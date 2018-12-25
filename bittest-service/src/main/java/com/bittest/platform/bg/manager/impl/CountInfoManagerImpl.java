package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.CountInfoMapper;
import com.bittest.platform.bg.domain.po.CountInfo;
import com.bittest.platform.bg.manager.CountInfoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-03-07.
 */
@Service("countInfoManager")
public class CountInfoManagerImpl implements CountInfoManager {

    @Autowired
    private CountInfoMapper countInfoMapper;

    @Override
    public List<CountInfo> queryCounInfotListByPage(CountInfo countInfo) {
        return countInfoMapper.queryCounInfotListByPage(countInfo);
    }

    @Override
    public int queryCounInfotCount(CountInfo countInfo) {
        return countInfoMapper.queryCounInfotCount(countInfo);
    }
}
