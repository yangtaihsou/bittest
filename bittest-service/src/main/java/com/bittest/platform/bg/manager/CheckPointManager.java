package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.CheckPoint;

import java.util.List;

/**
 * 2018-08-22.
 */
public interface CheckPointManager {

    int save(CheckPoint checkPoint);

    int delete(CheckPoint checkPoint);

    int update(CheckPoint checkPoint);

    List<CheckPoint> queryList(CheckPoint checkPoint);

}
