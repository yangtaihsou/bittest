package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.InterfaceInfo;

import java.util.List;

/**
 * 2018-08-23.
 */
public interface InterfaceManager {

    int save(InterfaceInfo interfaceInfo);

    int delete(Object id);

    int update(InterfaceInfo interfaceInfo);

    List<InterfaceInfo> queryInterfaceByCase(InterfaceInfo interfaceInfo);

    InterfaceInfo queryObject(InterfaceInfo interfaceInfo);

    List<InterfaceInfo> queryList(InterfaceInfo interfaceInfo);

    int queryTotal(InterfaceInfo interfaceInfo);

    List<InterfaceInfo> queryAllInterfaceList(InterfaceInfo interfaceInfo);

    int queryAllInterfaceListCount(InterfaceInfo interfaceInfo);

    int queryInterfaceByDataBaseCount(String value);
}
