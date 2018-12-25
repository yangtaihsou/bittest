<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>任务结果列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <form id="exportform" action="/valuationReport/export">
                <input type="hidden" id="resultId"  name="resultId" value=""/>
            </form>
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">执行状态</label>
                            <select id="isfinish" name="isfinish" class="form-control"  columWidthClass="col-sm-2">
                                <option value="0">请选择</option>
                                <option value="1">执行中</option>
                                <option value="2">已完成</option>
                            </select>
                            <input type="hidden" id="taskId" value="${taskId}"  />
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
                        <th>结果编号</th>
                        <th>结果名称</th>
                        <th>执行时间</th>
                        <th>完成时间</th>
                        <th>执行状态</th>
                        <th>成功率</th>
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
            var aButtons;
            var taskId = $("#taskId").val();
            if(taskId!=undefined&&taskId!=''){
                aButtons = [{
                    "sExtends":"common",
                    "sButtonClass": "btn btn-xs btn-danger btn-size",
                    "innerHTML":'<i class="fa fa-edit"></i>清空测试结果',
                    "click":function(){
                        bootbox.setLocale("zh_CN");
                        bootbox.confirm("确认要清空该任务计划的所有测试结果吗？", function (result) {
                            if(result) {
                                var queryform = $('#queryform').serialize();
                                var url = "/result/deleteResultByTask?taskId="+$("#taskId").val();
                                me.postAjaxCallBack(queryform,url,deleteCallBack,"");
                            }
                        });
                    }
                }];
            }else{
                aButtons = [];
            }

            var columns = [
                { "mData": "resultId" },
                { "mData": "resultName" },
                { "mData": "createTimeStr" },
                { "mData": "updateTimeStr" },
                { "mData": "isFinishStr" },
                { "mData": "successRate" },
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        var info;
                        var styles;
                        if(data<60){
                            styles = 'bg-red-active';
                        }else if(data<80){
                            styles = 'bg-yellow-active';
                        }else if(data<100){
                            styles = 'bg-olive-active';
                        }else{
                            styles = 'bg-green-active';
                        }
                        return info='<span class="badge '+styles+'">'+data+'%</span>';
                    },
                    "targets": 5
                },
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 6
                }
            ];
            var url = "/result/queryTaskResultList?taskId="+$("#taskId").val();
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url , columns, columnDefs, aButtons,detail);
        });

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="queryResult(\''+row.resultId+'\')"><i class="glyphicon glyphicon-facetime-video"></i>查看用例结果</a>&nbsp;&nbsp;';
            btns +='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="exportResult(\''+row.resultId+'\')"><i class="glyphicon glyphicon-share"></i>导出报告</a>&nbsp;&nbsp;';
            btns +='<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="deleteResult(\''+row.resultId+'\')"><i class="fa fa-edit"></i>删 除</a>&nbsp;&nbsp;';
            return btns;
        }

        function queryResult(resultId){
            var tabid = "CASE_RESULT";
            var url = "<%=request.getContextPath()%>/result/toQueryCaseResultList?resultId="+resultId;
            var title = "查看用例结果";
            window.parent.addTabs(tabid , title , url , true);
        }

        function exportResult(resultId){
            $("#resultId").val(resultId);
            $("#exportform").attr("action","/result/exportResult");
            $("#exportform").submit();
        }

        function deleteResult(resultId){
            bootbox.setLocale("zh_CN");
            bootbox.confirm("确认要删除测试结果吗？", function (result) {
                 if(result) {
                     var queryform = $('#queryform').serialize();
                     var url = "/result/deleteResult?resultId="+resultId;
                     me.postAjaxCallBack(queryform,url,deleteCallBack,"");
                 }
            });
        }

        function deleteCallBack(data){
            if(data.code=="000000"){
                query();
            }else{
                alertBootbox("删除测试结果异常，请联系管理员！");
            }
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/result/taskResultDetail?resultId="+data.resultId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "任务结果详情",
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

