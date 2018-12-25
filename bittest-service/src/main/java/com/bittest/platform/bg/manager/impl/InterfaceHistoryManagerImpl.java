package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.InterfaceHistoryMapper;
import com.bittest.platform.bg.domain.po.InterfaceHistory;
import com.bittest.platform.bg.manager.InterfaceHistoryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-02-05.
 */
@Service("interfaceHistoryManager")
public class InterfaceHistoryManagerImpl implements InterfaceHistoryManager {

    @Autowired
    private InterfaceHistoryMapper interfaceHistoryMapper;

    @Override
    public int save(InterfaceHistory interfaceHistory) {
        return interfaceHistoryMapper.save(interfaceHistory);
    }

    @Override
    public int delete(Object id) {
        return interfaceHistoryMapper.delete(id);
    }

    @Override
    public InterfaceHistory queryObject(InterfaceHistory interfaceHistory) {
        return interfaceHistoryMapper.queryObject(interfaceHistory);
    }

    @Override
    public List<InterfaceHistory> queryList(InterfaceHistory interfaceHistory) {
        return interfaceHistoryMapper.queryList(interfaceHistory);
    }

    @Override
    public InterfaceHistory queryHistoryCount(String pin) {
        return interfaceHistoryMapper.queryHistoryCount(pin);
    }
}
