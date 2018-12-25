<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>用例结果列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<input type="hidden" id="resultId" value="${resultId}"  />
<input type="hidden" id="caseId" value="${caseId}"  />
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">用例名称</label>
                            <input name="caseName" class="form-control"  columWidthClass="col-sm-2" />
                            <label class="col-sm-1 control-label">所属系统</label>
                            <select id="sysId" name="sysId" class="form-control"  columWidthClass="col-sm-2">
                                <option value="">请选择</option>
                                <c:forEach items="${systems}" var="sys">
                                    <option value="${sys.systemId}">${sys.systemName}</option>
                                </c:forEach>
                            </select>
                            <label class="col-sm-1 control-label">运行结果</label>
                            <select id="result" name="result" class="form-control"  columWidthClass="col-sm-2">
                                <option value="0">请选择</option>
                                <option value="1">通过</option>
                                <option value="2">失败</option>
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
                        <th>用例编号</th>
                        <th>用例名称</th>
                        <th>系统名称</th>
                        <th>创建时间</th>
                        <th>执行结果</th>
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
                { "mData": "caseId" },
                { "mData": "caseName" },
                { "mData": "systemName" },
                { "mData": "createTimeStr" },
                { "mData": "result" },
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        var result;
                        var styles;
                        if(data==2){
                            styles = 'bg-red-active';
                            result = '失败';
                        }else if(data==1){
                            styles = 'bg-green-active';
                            result = '通过';
                        }
                        return '<span class="badge '+styles+'">'+result+'</span>';
                    },
                    "targets": 4
                },
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 5
                }
            ];
            var url = "/result/queryCaseResultList?resultId="+$("#resultId").val()+"&caseId="+$("#caseId").val();
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url , columns, columnDefs, aButtons,detail);
        });

        function getBtns(row){
            var caseId = "'"+row.caseId+"',";
            var resultId =  "'"+row.resultId+"'";
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="queryResult('+caseId+resultId+')"><i class="glyphicon glyphicon-facetime-video"></i>查看接口结果</a>&nbsp;&nbsp;';
            return btns;
        }

        function queryResult(caseId,resultId){
            var tabid = "INTERFACE_RESULT";
            var url = "<%=request.getContextPath()%>/result/toQueryInterfaceResultList?resultId="+resultId+"&caseId="+caseId;
            var title = "查看接口结果";
            window.parent.addTabs(tabid , title , url , true);
        }

        function query(){
            datatable.fnDraw();
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/result/caseResultDetail?resultId="+data.resultId+"&caseId="+data.caseId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "用例结果详情",
                className: "add-modal"
            });
        }
    </script>
</footer></body>
</html>

