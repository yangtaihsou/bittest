package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.InterfaceCollection;

import java.util.List;

/**
 * 2018-08-23.
 */
public interface InterfaceCollectionManager {

    int save(InterfaceCollection interfaceCollection);

    int update(InterfaceCollection interfaceCollection);

    int delete(Object id);

    InterfaceCollection queryObject(InterfaceCollection interfaceCollection);

    List<InterfaceCollection> queryList(InterfaceCollection interfaceCollection);

}
