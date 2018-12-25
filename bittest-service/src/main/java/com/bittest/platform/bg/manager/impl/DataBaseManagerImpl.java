package com.bittest.platform.bg.manager.impl;

import com.bittest.platform.bg.dao.DatabaseMapper;
import com.bittest.platform.bg.domain.po.DataBase;
import com.bittest.platform.bg.manager.DataBaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 2018-08-22.
 */
@Service("dataBaseManager")
public class DataBaseManagerImpl implements DataBaseManager {

    @Autowired
    private DatabaseMapper databaseMapper;

    @Override
    public int save(DataBase dataBase) {
        return databaseMapper.save(dataBase);
    }

    @Override
    public int update(DataBase dataBase) {
        return databaseMapper.update(dataBase);
    }

    @Override
    public DataBase queryObject(DataBase dataBase) {
        return databaseMapper.queryObject(dataBase);
    }

    @Override
    public List<DataBase> queryList(DataBase dataBase) {
        return databaseMapper.queryList(dataBase);
    }

    @Override
    public int queryTotal(DataBase dataBase) {
        return databaseMapper.queryTotal(dataBase);
    }

    @Override
    public List<DataBase> queryTotalList(DataBase dataBase) {
        return databaseMapper.queryTotalList(dataBase);
    }
}
