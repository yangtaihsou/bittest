package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.DataFetch;

import java.util.List;

/**
 * 2018-08-23.
 */
public interface DataFetchManager {

    int save(DataFetch dataFetch);

    int delete(DataFetch dataFetch);

    int update(DataFetch dataFetch);

    List<DataFetch> queryList(DataFetch dataFetch);
}
