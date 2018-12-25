<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>任务定时配置列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<input type="hidden" id="resultId" value="${resultId}"  />
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">测试任务id</label>
                            <input name="taskId" class="form-control"  columWidthClass="col-sm-2" />
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
                        <th>测试任务id</th>
                        <th>任务描述</th>
                        <th>定时类型</th>
                        <th>状态(0表示停止)</th>
                        <th>执行时间</th>
                        <th>年</th>
                        <th>月</th>
                        <th>日</th>
                        <th>时</th>
                        <th>分</th>
                        <th>秒</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
                        <th></th>
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
                    var url = "<%=request.getContextPath()%>/timerTaskConfig/toAdd";
                    $iframe.attr('src',timestamp(url)).attr('height', 450);
                    bootbox.dialog({
                        message: $iframe,
                        title: "新增任务执行的定时配置",
                        className: "add-modal-1"
                    });
                }
            }];

            var columns = [
                { "mData": "taskId" },
                { "mData": "taskTimerDesc" },
                { "mData": "dataType" },
                { "mData": "status" },
                { "mData": "biztime",'mRender': function(data, type, full) {
                       return formatDate(data);
            }},
                { "mData": "year" },
                { "mData": "month" },
                { "mData": "day" },
                { "mData": "hour" },
                { "mData": "minute" },
                { "mData": "second" },
                { "mData": "createtime" ,'mRender': function(data, type, full) {
                        return formatDate(data);
                    }},
                { "mData": "lastupdatetime",'mRender': function(data, type, full) {
                        return formatDate(data);
                    }},
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        var result;
                        var styles;
                        if(data==0){
                            styles = 'bg-red-active';
                            result = 'stop';
                        }else if(data==1){
                            styles = 'bg-green-active';
                            result = 'start';
                        }
                        return '<span class="badge '+styles+'">'+result+'</span>';
                    },
                    "targets": 3
                },
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 13
                }
            ];
            var url = "/timerTaskConfig/list";
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url , columns, columnDefs, aButtons,null);
        });

        function getBtns(row){

            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="timerTaskConfigEdit(\''+row.id+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
            //btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="systemDel(\''+row.systemId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
            return btns;
        }


        function formatDate(data) {
            var da = new Date(data);
            var year = da.getFullYear();
            var month = da.getMonth()+1;
            var date = da.getDate();
            // var day = da.getDay();
            var hour = da.getHours();
            var minute = da.getMinutes();
            var second = da.getSeconds()
            var yymmdd = [year,month,date].join('-');
            var hms = [hour,minute,second].join(':');
            return yymmdd + " " + hms;
        }

        function query(){
            datatable.fnDraw();
        }

        function detail(data){
            var resultId = $("#resultId").val();
            var url = "<%=request.getContextPath()%>/result/caseResultDetail?resultId="+resultId+"&caseId="+data.caseId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "用例结果详情",
                className: "add-modal"
            });
        }

        function timerTaskConfigEdit(row) {
            var url = "<%=request.getContextPath()%>/timerTaskConfig/toEdit?id="+row;
            $iframe.attr('src', timestamp(url)).attr('height', 480);
            bootbox.dialog({
                message: $iframe,
                title: "编辑信息",
                className: "add-modal-1"
            });
        }

    </script>
</footer></body>
</html>

