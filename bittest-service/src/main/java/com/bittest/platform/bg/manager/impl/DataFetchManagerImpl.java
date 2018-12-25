package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.DataFetchMapper;
import com.bittest.platform.bg.domain.po.DataFetch;
import com.bittest.platform.bg.manager.DataFetchManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-23.
 */
@Service("dataFetchManager")
public class DataFetchManagerImpl implements DataFetchManager {

    @Autowired
    private DataFetchMapper dataFetchMapper;

    @Override
    public int save(DataFetch dataFetch) {
        return dataFetchMapper.save(dataFetch);
    }

    @Override
    public int delete(DataFetch dataFetch) {
        return dataFetchMapper.delete(dataFetch);
    }

    @Override
    public int update(DataFetch dataFetch) {
        return dataFetchMapper.update(dataFetch);
    }

    @Override
    public List<DataFetch> queryList(DataFetch dataFetch) {
        return dataFetchMapper.queryList(dataFetch);
    }
}
