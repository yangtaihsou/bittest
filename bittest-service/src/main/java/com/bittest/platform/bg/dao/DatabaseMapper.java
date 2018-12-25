package com.bittest.platform.bg.dao;

import com.bittest.platform.bg.domain.po.DataBase;

import java.util.List;

/**
 * 数据库管理表
 *
 * @author admin
 * @email admin@charlink.com.cn
 * @date 2018-08-25 11:16:35
 */
public interface DatabaseMapper extends BaseMapper<DataBase> {

    /**
     * 查询用户所有数据库列表信息
     *
     * @param dataBase
     * @return
     */
    List<DataBase> queryTotalList(DataBase dataBase);

}
