package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.InterfaceResultMapper;
import com.bittest.platform.bg.domain.po.InterfaceResult;
import com.bittest.platform.bg.manager.InterfaceResultManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-27.
 */
@Service("interfaceResultManager")
public class InterfaceResultManagerImpl implements InterfaceResultManager {

    @Autowired
    private InterfaceResultMapper interfaceResultMapper;

    @Override
    public int save(InterfaceResult result) {
        return interfaceResultMapper.save(result);
    }

    @Override
    public int delete(InterfaceResult result) {
        return interfaceResultMapper.delete(result);
    }

    @Override
    public int deleteByTask(InterfaceResult result) {
        return interfaceResultMapper.deleteByTask(result);
    }

    @Override
    public InterfaceResult queryObject(InterfaceResult result) {
        return interfaceResultMapper.queryObject(result);
    }

    @Override
    public List<InterfaceResult> queryList(InterfaceResult result) {
        return interfaceResultMapper.queryList(result);
    }

    @Override
    public int queryTotal(InterfaceResult result) {
        return interfaceResultMapper.queryTotal(result);
    }
}
