package com.bittest.platform.bg.manager;

import com.bittest.platform.bg.domain.po.DataBase;

import java.util.List;

/**
 * 2018-08-22.
 */
public interface DataBaseManager {

    int save(DataBase dataBase);

    int update(DataBase dataBase);

    DataBase queryObject(DataBase dataBase);

    List<DataBase> queryList(DataBase dataBase);

    int queryTotal(DataBase dataBase);

    List<DataBase> queryTotalList(DataBase dataBase);
}
