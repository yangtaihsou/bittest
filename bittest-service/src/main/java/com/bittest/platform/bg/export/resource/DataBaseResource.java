package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.result.*;
import com.bittest.platform.bg.export.vo.DataBaseReqVo;
import com.bittest.platform.bg.export.vo.DataBaseResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 2018-08-22.
 */
@Path("/database")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface DataBaseResource {

    /**
     * 根据数据库编号 查询数据库详细信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("/queryDataBase")
    public GenericResult<DataBaseResVo> queryDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 保存数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("/saveDataBase")
    public BasicResult saveDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 修改数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("/updateDataBase")
    public BasicResult updateDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 分页查询数据库信息
     *
     * @param query
     * @return
     */
    @POST
    @Path("queryDataBasePage")
    public PaginationResult<DataBaseResVo> queryDataBasePage(PaginationQuery<DataBaseReqVo> query);


    /**
     * 查询所有数据库信息（用于添加数据库检查点）
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("queryTotalDataBase")
    public ListResult<DataBaseResVo> queryTotalDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 测试数据库连接
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("connectDataBase")
    public BasicResult connectDataBase(DataBaseReqVo dataBaseReqVo);

    /**
     * 删除数据库信息
     *
     * @param dataBaseReqVo
     * @return
     */
    @POST
    @Path("/deleteDataBase")
    public BasicResult deleteDataBase(DataBaseReqVo dataBaseReqVo);
}
