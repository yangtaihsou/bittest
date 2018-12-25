package com.bittest.platform.bg.dao;

import java.util.List;

public interface BaseMapper<T> {

    int save(T t);

    int delete(Object id);

    int update(T t);

    T queryObject(T t);

    List<T> queryList(T t);

    int queryTotal(T t);
}