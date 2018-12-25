<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>测试用例列表</title>
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
                            <label class="col-sm-1 control-label">系统名称</label>
                            <input type="text" id="systemName" name="systemName" class="form-control"  columWidthClass="col-sm-2" value=""  />
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
                        <th>系统名称</th>
                        <th>系统域名</th>
                        <th>邮件地址</th>
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
                    var url = "<%=request.getContextPath()%>/system/toSystemAdd";
                    $iframe.attr('src',timestamp(url)).attr('height', 350);
                    bootbox.dialog({
                        message: $iframe,
                        title: "新增系统",
                        className: "add-modal-1"
                    });
                }
            }];

            var columns = [
                { "mData": "systemName" },
                { "mData": "domainName" },
                { "mData": "ip" },
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

            datatable = me.initDataTable($('#datatable'), $("#queryform"), '/system/querySystemList', columns, columnDefs, aButtons,detail);
        });

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="systemEdit(\''+row.systemId+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
            btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="systemDel(\''+row.systemId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
            return btns;
        }
        
        function systemEdit(row) {
            var url = "<%=request.getContextPath()%>/system/toSystemEdit?systemId="+row;
            $iframe.attr('src', timestamp(url)).attr('height', 350);
            bootbox.dialog({
                message: $iframe,
                title: "编辑系统信息",
                className: "add-modal-1"
            });
        }

        function systemDel(systemId){
            bootbox.setLocale("zh_CN");
            bootbox.confirm("确认要删除该系统信息吗？", function (result) {
                if(result) {
                    var queryform = $('#queryform').serialize();
                    var url = "/system/deleteSystem?systemId="+systemId;
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
                alertBootbox("删除系统信息失败，请联系管理员！");
            }
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/system/systemDetail?systemId="+data.systemId;
            $iframe.attr('src', timestamp(url)).attr('height', 350);
            bootbox.dialog({
                message: $iframe,
                title: "系统详情",
                className: "add-modal-1"
            });
        }
        

        function query(){
            datatable.fnDraw();
        }


    </script>
</footer>
</body>
</html>

