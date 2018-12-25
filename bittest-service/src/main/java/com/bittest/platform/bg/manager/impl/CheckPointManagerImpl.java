package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.CheckPointMapper;
import com.bittest.platform.bg.domain.po.CheckPoint;
import com.bittest.platform.bg.manager.CheckPointManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-22.
 */
@Service("checkPointManager")
public class CheckPointManagerImpl implements CheckPointManager {

    @Autowired
    private CheckPointMapper checkPointMapper;

    @Override
    public int save(CheckPoint checkPoint) {
        return checkPointMapper.save(checkPoint);
    }

    @Override
    public int delete(CheckPoint checkPoint) {
        return checkPointMapper.delete(checkPoint);
    }

    @Override
    public int update(CheckPoint checkPoint) {
        return checkPointMapper.update(checkPoint);
    }

    @Override
    public List<CheckPoint> queryList(CheckPoint checkPoint) {
        return checkPointMapper.queryList(checkPoint);
    }
}
