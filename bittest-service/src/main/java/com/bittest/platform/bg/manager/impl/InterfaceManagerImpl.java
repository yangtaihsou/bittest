package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.InterfaceMapper;
import com.bittest.platform.bg.domain.po.InterfaceInfo;
import com.bittest.platform.bg.manager.InterfaceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 2018-08-23.
 */
@Service("interfaceManager")
public class InterfaceManagerImpl implements InterfaceManager {

    @Autowired
    private InterfaceMapper interfaceMapper;

    @Override
    public int save(InterfaceInfo interfaceInfo) {
        return interfaceMapper.save(interfaceInfo);
    }

    @Override
    public int delete(Object id) {
        return interfaceMapper.delete(id);
    }

    @Override
    public int update(InterfaceInfo interfaceInfo) {
        return interfaceMapper.update(interfaceInfo);
    }

    @Override
    public List<InterfaceInfo> queryInterfaceByCase(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryInterfaceByCase(interfaceInfo);
    }

    @Override
    public InterfaceInfo queryObject(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryObject(interfaceInfo);
    }

    @Override
    public List<InterfaceInfo> queryList(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryList(interfaceInfo);
    }

    @Override
    public int queryTotal(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryTotal(interfaceInfo);
    }

    @Override
    public List<InterfaceInfo> queryAllInterfaceList(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryAllInterfaceList(interfaceInfo);
    }

    @Override
    public int queryAllInterfaceListCount(InterfaceInfo interfaceInfo) {
        return interfaceMapper.queryAllInterfaceListCount(interfaceInfo);
    }

    @Override
    public int queryInterfaceByDataBaseCount(String value) {
        return interfaceMapper.queryInterfaceByDataBaseCount(value);
    }

}
