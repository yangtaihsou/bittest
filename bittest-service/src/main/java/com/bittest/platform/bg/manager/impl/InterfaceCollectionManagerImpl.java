package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.InterfaceCollectionMapper;
import com.bittest.platform.bg.domain.po.InterfaceCollection;
import com.bittest.platform.bg.manager.InterfaceCollectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-02-05.
 */
@Service("interfaceCollectionManager")
public class InterfaceCollectionManagerImpl implements InterfaceCollectionManager {

    @Autowired
    private InterfaceCollectionMapper interfaceCollectionMapper;

    @Override
    public int save(InterfaceCollection interfaceCollection) {
        return interfaceCollectionMapper.save(interfaceCollection);
    }

    @Override
    public int update(InterfaceCollection interfaceCollection) {
        return interfaceCollectionMapper.update(interfaceCollection);
    }

    @Override
    public int delete(Object id) {
        return interfaceCollectionMapper.delete(id);
    }

    @Override
    public InterfaceCollection queryObject(InterfaceCollection interfaceCollection) {
        return interfaceCollectionMapper.queryObject(interfaceCollection);
    }

    @Override
    public List<InterfaceCollection> queryList(InterfaceCollection interfaceCollection) {
        return interfaceCollectionMapper.queryList(interfaceCollection);
    }

}
