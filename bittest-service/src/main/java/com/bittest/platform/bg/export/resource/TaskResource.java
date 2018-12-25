package com.bittest.platform.bg.export.resource;

import com.bittest.platform.bg.export.result.BasicResult;
import com.bittest.platform.bg.export.result.GenericResult;
import com.bittest.platform.bg.export.result.PaginationQuery;
import com.bittest.platform.bg.export.result.PaginationResult;
import com.bittest.platform.bg.export.vo.TaskReqVo;
import com.bittest.platform.bg.export.vo.TaskResVo;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 测试任务对外接口
 * 2018-08-24.
 */
@Path("/task")
//请求数据类型
@Consumes({"application/json"})
//返回数据类型
@Produces({"application/json"})
public interface TaskResource {

    @POST
    @Path("saveTask")
    public GenericResult<TaskResVo> saveTask(TaskReqVo taskReqVo);

    @POST
    @Path("deleteTask")
    public BasicResult deleteTask(TaskReqVo taskReqVo);

    @POST
    @Path("queryTaskList")
    public PaginationResult<TaskResVo> queryTaskList(PaginationQuery<TaskReqVo> query);

    @POST
    @Path("queryTask")
    public GenericResult<TaskResVo> queryTask(TaskReqVo taskReqVo);

    @POST
    @Path("updateTask")
    public BasicResult updateTask(TaskReqVo taskReqVo);
}
