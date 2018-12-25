<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
                            <label class="col-sm-1 control-label">用例名称</label>
                            <input type="text" id="caseName" name="caseName" class="form-control"  columWidthClass="col-xs-2" value=""  />
                            <label class="from-control col-sm-1">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-2" id="sysId" name="sysId">
                                <option value="">请选择</option>
                                <c:forEach var="sys" items="${systems}" >
                                    <option value="${sys.systemId}">${sys.systemName}</option>
                                </c:forEach>
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
                var tabid = "CASE_CASEADD";
                var title = "新增用例";
                var url = "<%=request.getContextPath()%>/case/toCaseAdd";
                window.parent.addTabs(tabid , title , url , true);
            }
        }];

        var columns = [
            { "mData": "caseId" },
            { "mData": "caseName" },
            { "mData": "systemName" },
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

        datatable = me.initDataTable($('#datatable'), $("#queryform"), '/case/queryCaseList', columns, columnDefs, aButtons,detail);

    });

    function getBtns(row){
        var btns = '<a class="btn btn-xs btn-info" href="javascript:void(0);" onclick="run(\''+row.caseId+'\')"><i class="glyphicon glyphicon-check"></i>执 行</a>&nbsp;&nbsp;';
        btns +='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="queryResult(\''+row.caseId+'\')"><i class="glyphicon glyphicon-facetime-video"></i>查看用例结果</a>&nbsp;&nbsp;';
        btns +='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="caseEdit(\''+row.caseId+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
        btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="caseDel(\''+row.caseId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
        return btns;
    }


    function queryResult(resultId){
        var tabid = "CASE_RESULT";
        var url = "<%=request.getContextPath()%>/result/toQueryCaseResultList?caseId="+resultId;
        var title = "查看用例结果";
        window.parent.addTabs(tabid , title , url , true);
    }

    function caseEdit(caseId) {
        var tabid = "CASE_CASEEDIT";
        var title = "用例接口管理";
        var url = "<%=request.getContextPath()%>/case/toCaseEdit?caseId="+caseId;
        window.parent.addTabs(tabid , title , url , true);
    }

    function caseDel(caseId){
        bootbox.setLocale("zh_CN");
        bootbox.confirm("确认要删除该用例吗？", function (result) {
            if(result) {
                var queryform = $('#queryform').serialize();
                var url = "/case/deleteCase?caseId="+caseId;
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
            alertBootbox("删除用例信息失败，请联系管理员！");
        }
    }

    function detail(data){
        var url = "<%=request.getContextPath()%>/case/caseDetail?caseId="+data.caseId;
        $iframe.attr('src', timestamp(url));
        bootbox.dialog({
            message: $iframe,
            title: "用例详情",
            className: "add-modal"
        });
    }

    function query(){
        datatable.fnDraw();
    }

    function run(caseId){
        var tabid = "RUN_CASE_RESULT";
        var title = "用例执行结果";
        var url = "<%=request.getContextPath()%>/case/runCaseList?caseId="+caseId;
        window.parent.addTabs(tabid , title , url , true);
    }

</script>
</body>

</html>

