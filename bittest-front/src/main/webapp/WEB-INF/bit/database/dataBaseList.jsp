<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>数据库列表</title>
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
                            <label class="col-xs-1 control-label">数据库名</label>
                            <input type="text" id="name" name="name" class="form-control"  columWidthClass="col-sm-2" value=""  />
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
                        <th>编号</th>
                        <th>数据库名称</th>
                        <th>连接地址</th>
                        <th>备&nbsp;&nbsp;&nbsp;&nbsp;注</th>
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
                    var url = "<%=request.getContextPath()%>/database/toDataBaseAdd";
                    $iframe.attr('src',timestamp(url)).attr('height',400);
                    bootbox.dialog({
                        message: $iframe,
                        title: "新增数据库信息",
                        className: "add-modal-3"
                    });
                }
            }];

            var columns = [
                { "mData": "databaseId" },
                { "mData": "name" },
                { "mData": "url"},
                { "mData": "remark" },
                { "mData": "updateTimeStr" },
                { "mData": "createTimeStr" },
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 6
                }
            ];

            datatable = me.initDataTable($('#datatable'), $("#queryform"), '/database/queryDataBaseList', columns, columnDefs, aButtons,detail);

        });

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="edit(\''+row.databaseId+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
            btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="del(\''+row.databaseId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
            return btns;
        }
        
        function edit(row) {
            var url = "<%=request.getContextPath()%>/database/toDataBaseEdit?databaseId="+row;
            $iframe.attr('src', timestamp(url)).attr('height', 400);
            bootbox.dialog({
                message: $iframe,
                title: "编辑数据库信息",
                className: "add-modal-3"
            });
        }

        function del(databaseId){
            bootbox.setLocale("zh_CN");
            bootbox.confirm("确认要删除该数据库信息吗？", function (result) {
                if(result) {
                    var queryform = $('#queryform').serialize();
                    var url = "/database/deleteDataBase?databaseId="+databaseId;
                    me.postAjaxCallBack(queryform,url,delCallBack,"");
                }
            });
        }

        function delCallBack(data){
            if(data.code=="000000"){
                query();
            }else if (data.code="999999"){
                alertBootbox(data.message);
            }else{
                alertBootbox("删除数据库信息失败，请联系管理员！");
            }
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/database/dataBaseDetail?databaseId="+data.databaseId;
            $iframe.attr('src', timestamp(url)).attr('height', 400);
            bootbox.dialog({
                message: $iframe,
                title: "数据库详情",
                className: "add-modal-3"
            });
        }

        function query(){
            datatable.fnDraw();
        }
    </script>
</footer>
</body>
</html>

