package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.InterfaceInfo;

import java.util.List;

/**
 * 2018-08-23.
 */
public interface InterfaceMapper extends BaseMapper<InterfaceInfo> {

    List<InterfaceInfo> queryInterfaceByCase(InterfaceInfo interfaceInfo);

    List<InterfaceInfo> queryAllInterfaceList(InterfaceInfo interfaceInfo);

    int queryAllInterfaceListCount(InterfaceInfo interfaceInfo);

    int queryInterfaceByDataBaseCount(String value);

}
