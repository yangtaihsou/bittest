<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>接口管理</title>
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
                            <label class="col-sm-1 control-label">接口名称</label>
                            <input type="text"  name="name" class="form-control"  columWidthClass="col-sm-2" value=""  />
                            <label class="col-sm-1 control-label">接口类型</label>
                            <select name="type" class="form-control"  columWidthClass="col-sm-2">
                                <option value="0">请选择</option>
                                <option value="1">Dubbo</option>
                                <option value="2">POST</option>
                                <option value="3">GET</option>
                            </select>
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
                        <th>接口编号</th>
                        <th>接口名称</th>
                        <th>访问地址</th>
                        <th>接口类型</th>
                        <th>用例名称</th>
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
                "innerHTML":'<i class="fa fa-user-plus"></i>新增接口',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/interface/toInterfaceAdd";
                    $iframe.attr('src',timestamp(url));
                    bootbox.dialog({
                        message: $iframe,
                        title: "新增接口",
                        className: "add-modal"
                    });
                }
            }];

            var columns = [
                { "mData": "interfaceId" },
                { "mData": "name" },
                { "mData": "url" },
                { "mData": "typeStr" },
                { "mData": "caseName" },
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

            datatable = me.initDataTable($('#datatable'), $("#queryform"), '/interface/addExitInterfaceList', columns, columnDefs, aButtons,detail);
        });

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="edit(\''+row.interfaceId+'\',\''+row.caseName+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
            btns +='<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="del(\''+row.interfaceId+'\',\''+row.caseName+'\')"><i class="glyphicon glyphicon-trash"></i>删 除</a>&nbsp;&nbsp;';
            return btns;
        }
        
        function edit(interfaceId,caseName){
/*            if(caseName!='null'&&caseName!=''){
                alertBootbox("接口已关联用例，为避免误操作，请到用例中进行维护！");
            }else{*/
                var url = "<%=request.getContextPath()%>/interface/toInterfaceEdit?interfaceId="+interfaceId;
                $iframe.attr('src',timestamp(url));
                bootbox.dialog({
                    message: $iframe,
                    title: "编辑接口信息",
                    className: "add-modal"
                });
            //}
        }

        function del(interfaceId,caseName){
            if(caseName!='null'&&caseName!=''){
                alertBootbox("接口已关联用例，为避免误操作，请到用例中进行维护！");
            }else{
                bootbox.setLocale("zh_CN");
                bootbox.confirm("确认要删除该接口吗？", function (result) {
                    if(result) {
                        var queryform = $('#queryform').serialize();
                        var url = '/interface/interfaceDel?interfaceId='+interfaceId;
                        me.postAjaxCallBack(queryform,url,delCallBack,"");
                    }
                });
            }
        }

        function delCallBack(data){
            if(data.code=="000000"){
                query();
            }else{
                alertBootbox("删除接口信息失败，请联系管理员！");
            }
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/interface/interfaceDetail?interfaceId="+data.interfaceId;
            $iframe.attr('src',timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "接口详情",
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

