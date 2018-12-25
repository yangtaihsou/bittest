<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>未关联用例列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<form id="queryNoFetchCaseform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->

                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">用例名称</label>
                            <input type="text" id="caseName" name="caseName" class="form-control"  columWidthClass="col-sm-2" value=""  />
                            <label class="from-control col-sm-1">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-2" id="sysId" name="sysId">
                                <option value="">请选择</option>
                                <c:forEach var="sys" items="${systems}" >
                                    <option value="${sys.systemId}">${sys.systemName}</option>
                                </c:forEach>
                            </select>
                            <input  type="hidden" name="taskId" id="taskId" value="${taskId}"/>
                        </div>
                    </div>
                    <!-- /.box-body -->
                    <div class="box-footer footer-center">
                        <a href="javascript:void(0)" class="btn btn-success" onclick="query()">&nbsp;查&nbsp;&nbsp;询&nbsp;</a>
                    </div>
                    <!-- /.box-footer -->
                </div>

        </div>
        <!-- /.box -->
        <div class="box">
            <div class="box-body">
                <table id="datatable" class="table table-striped table-bordered table-hover">
                    <thead>
                    <tr>
                        <th>选择</th>
                        <th>用例编号</th>
                        <th>用例名称</th>
                        <th>所属系统</th>
                        <th>修改时间</th>
                        <th>创建时间</th>
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
</form>
<footer>
    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = $("#datatable");
        var tableUtil = new TableUtil("datatable");

        $(document).ready(function () {
            var aButtons = [{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>确认添加',
                "click":function(){
                    fetchCase();
                }
            }];

            var columns = [
                { "mData": "caseId" },
                { "mData": "caseId" },
                { "mData": "caseName" },
                { "mData": "systemName" },
                { "mData": "updateTimeStr" },
                { "mData": "createTimeStr" },

            ];
            var columnDefs = [
                {
                    "render": function (data, type, row) {
                        return '<input name="caseIds" type="checkbox" value="' + data + '"/>';
                    },
                    "targets": 0
                }
            ];
            datatable = me.initDataTable($('#datatable'), $("#queryNoFetchCaseform"), "/case/queryNoFetchCaseList", columns, columnDefs, aButtons);

        });

        //关联用例
        function fetchCase(){
            var selectDatas = tableUtil.getCheckedDatas();
            if (selectDatas.length == 0) {
                alertBootbox("请选择关联的用例信息！");
                return;
            }
            var queryform = $('#queryNoFetchCaseform').serialize()
            me.postAjaxCallBack(queryform,'/case/addFetchCase',submitCallBack,"");
        }


        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                $("input[name='caseIds']").removeAttr("checked");
            }else{
                alertBootbox("关联用例失败，请联系管理员！");
                return;
            };
            query();
            parent.query();
        }


        function query(){
            $("input[name='caseIds']").removeAttr("checked");
            datatable.fnDraw();
        }


    </script>
</footer>
</body>
</html>

