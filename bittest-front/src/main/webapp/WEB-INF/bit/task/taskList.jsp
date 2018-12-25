<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>测试任务列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">任务名称</label>
                            <input type="text" id="taskName" name="taskName" class="form-control"  columWidthClass="col-sm-2" value=""  />
                            <div class="form-group">
                                <label class="from-control col-sm-1">所属系统</label>
                                <select id="sysId" name="sysId" class="form-control" columWidthClass="col-sm-5">
                                    <option value="">请选择</option>
                                    <c:forEach var="sys" items="${systems}" >
                                        <option value="${sys.systemId}">${sys.systemName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer footer-center">
                        <a href="javascript:void(0)" class="btn btn-success" onclick="query()">&nbsp;查&nbsp;&nbsp;询&nbsp;</a>
                    </div>
                    <!-- /.box-footer -->
                </div>
            </form>
        </div>
        <!-- /.box -->
        <div class="box">
            <div class="box-body">
                <table id="datatable" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>任务编号</th>
                        <th>任务名称</th>
                        <th>所属系统</th>
                        <th>修改时间</th>
                        <th>创建时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
            <!-- /.box-body -->
        </div>
        <!-- /.box -->
    </div>
    <!-- /.col -->
</div>
<!-- /.row -->
<footer>
    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = null;

        var tableUtil = new TableUtil("datatable");

        $(document).ready(function () {
            var aButtons = [{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>新增',
                "click":function(){
                    var tabid = "TASK_TASKADD";
                    var title = "新增任务计划";
                    var url = "<%=request.getContextPath()%>/task/toTaskAdd";
                    window.parent.addTabs(tabid , title , url , true);
                }
            }];

            var columns = [
                { "mData": "taskId" },
                { "mData": "taskName" },
                { "mData": "sysName" },
                { "mData": "updateTimeStr" },
                { "mData": "createTimeStr" },
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 5
                }
            ];
            datatable = me.initDataTable($('#datatable'), $("#queryform"), '/task/taskList', columns, columnDefs, aButtons,detail);
        });

        function getBtns(row){
            var  btns = '<a class="btn btn-xs btn-info" href="javascript:void(0);" onclick="run(\''+row.taskId+'\')"><i class="glyphicon glyphicon-check"></i>执 行</a>&nbsp;&nbsp;';
            btns +='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="taskEdit(\''+row.taskId+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
//            btns += '<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="toFetchCaseList(\''+row.taskId+'\')"><i class="fa fa-edit"></i>关联用例</a>&nbsp;&nbsp;';
            btns += '<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="queryResult(\''+row.taskId+'\')"><i class="glyphicon glyphicon-facetime-video"></i>查看任务结果</a>&nbsp;&nbsp;';
            btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="delTask(\''+row.taskId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
            return btns;
        }

        function toFetchCaseList(taskId) {
            var tabid = "TASK_FETCHCASELIST";
            var url = "<%=request.getContextPath()%>/case/toFetchCaseList?taskId="+taskId;
            var title = "已关联用例列表";
            window.parent.addTabs(tabid , title , url , true);
        }

        function taskEdit(taskId){
            var tabid = "TASK_TASKEDIT";
            var title = "任务计划管理";
            var url = "<%=request.getContextPath()%>/task/toTaskEdit?taskId="+taskId;
            window.parent.addTabs(tabid , title , url , true);
        }

        //执行任务计划
        function run(taskId){
            var queryform = $('#queryform').serialize();
            var url = "/task/taskRun?taskId="+taskId;
            me.postAjaxCallBack(queryform,url,runCallBack,"");
        }

        function runCallBack(data){
            if(data){
                alertBootbox("测试任务已执行，请稍后查看测试结果");
            }else{
                alertBootbox("任务计划执行异常，请稍后再试！");
            }
        }

        function delTask(taskId){
            bootbox.setLocale("zh_CN");
            bootbox.confirm("确认要删除该任务计划吗？", function (result) {
                if(result) {
                    var queryform = $('#queryform').serialize();
                    var url = "/task/deleteTask?taskId="+taskId;
                    me.postAjaxCallBack(queryform,url,delCallBack,"");
                }
            });
        }

        function delCallBack(data){
            if(data.code=="000000"){
                query();
            }else{
                alertBootbox("删除任务计划失败，请联系管理员！");
            }
        }

        function queryResult(taskId){
            var tabid = "TASK_RESULT";
            var url = "<%=request.getContextPath()%>/result/toQueryTaskResultList?taskId="+taskId;
            var title = "查看任务结果";
            window.parent.addTabs(tabid , title , url , true);
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/task/taskDetail?taskId="+data.taskId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "任务计划详情",
                className: "add-modal"
            });
        }

        function query(){
            datatable.fnDraw();
        }

    </script>
</footer>
</body>
</html>

