package com.bittest.platform.bg.service;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;

/**
 * 2018-08-22.
 */
public interface DataBaseService {

    /**
     * 根据数据库编号 查询数据库详细信息
     *
     * @param dataBaseReqVo
     * @return
     */
    GenericResult<DataBaseResVo> queryDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 保存数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    BasicResult saveDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 修改数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    BasicResult updateDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 分页查询数据库信息
     *
     * @param query
     * @return
     */
    PaginationResult<DataBaseResVo> queryDataBasePage(PaginationQuery<DataBaseReqVo> query);


    /**
     * 查询所有数据库信息（用于添加数据库检查点）
     *
     * @param dataBaseReqVo
     * @return
     */
    ListResult<DataBaseResVo> queryTotalDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 测试数据库连接
     *
     * @param dataBaseReqVo
     * @return
     */
    BasicResult connectDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 删除数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    BasicResult deleteDataBase(DataBaseReqVo dataBaseReqVo);

}
