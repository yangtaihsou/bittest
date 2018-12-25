<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>添加已有接口列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <input type="hidden" id="caseId" name="caseId" value="${caseId}"/>
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
            var aButtons = [];

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

            datatable = me.initDataTable($('#datatable'), $("#queryform"), '/interface/addExitInterfaceList', columns, columnDefs, aButtons);
        });

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="add(\''+row.interfaceId+'\')"><i class="fa fa-edit"></i>添 加</a>&nbsp;&nbsp;';
            return btns;
        }
        
        function add(interfaceId) {
            var url = "<%=request.getContextPath()%>/interface/addExitInterface?interfaceId="+interfaceId;
            var queryform = $('#queryform').serialize();
            me.postAjaxCallBack(queryform,url,addCallBack,"");
        }

        function addCallBack(data){
            if(data.code="000000"){
                parent.query();
                query();
            }else{
                alertBootbox("添加已有接口失败，请联系系统管理员！");
            }
        }

        function query(){
            datatable.fnDraw();
        }
        

    </script>
</footer></body>
</html>

