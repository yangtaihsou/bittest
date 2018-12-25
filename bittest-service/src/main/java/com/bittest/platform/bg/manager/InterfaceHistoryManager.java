package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.InterfaceHistory;

import java.util.List;

/**
 * 2018-08-23.
 */
public interface InterfaceHistoryManager {

    int save(InterfaceHistory interfaceHistory);

    int delete(Object id);

    InterfaceHistory queryObject(InterfaceHistory interfaceHistory);

    List<InterfaceHistory> queryList(InterfaceHistory interfaceHistory);

    InterfaceHistory queryHistoryCount(String pin);

}
