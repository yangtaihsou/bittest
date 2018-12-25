package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.InterfaceHistory;

/**
 * 2018-08-23.
 */
public interface InterfaceHistoryMapper extends BaseMapper<InterfaceHistory> {
    InterfaceHistory queryHistoryCount(String pin);
}
