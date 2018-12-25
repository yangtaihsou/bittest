<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>接口结果列表</title>
    <meta charset="UTF-8"/>
</head>
<body>
<input type="hidden" id="resultId" value="${resultId}"  />
<input type="hidden" id="caseId" value="${caseId}"  />
<input type="hidden" id="polling" value="${polling}"  />
<div class="row">
    <div class="col-xs-12">
        <div class="box box-info">
            <!-- form start -->
            <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                <div class="form-horizontal">
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-sm-1 control-label">接口名称</label>
                            <input name="name" class="form-control"  columWidthClass="col-sm-2" />
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
                        <th>接口编号</th>
                        <th>接口名称</th>
                        <th>访问地址</th>
                        <th>接口类型</th>
                        <th>创建时间</th>
                        <th>执行结果</th>
                        <%--<th>操作</th>--%>
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
                { "mData": "createTimeStr" },
                { "mData": "result" }

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
                    "targets": 5
                }
            ];
            var url = "/result/queryInterfaceResultList?resultId="+$("#resultId").val()+"&caseId="+$("#caseId").val();
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url , columns, columnDefs, aButtons,detail);

            var interval = setInterval(function(){
                pollingQuery(interval);
            }, 2000);
        });

        function detail(data){
            var resultId = $("#resultId").val();
            var caseId = $("#caseId").val();
            var url = "<%=request.getContextPath()%>/result/interfaceResultDetail?resultId="+resultId+"&caseId="+caseId+"&interfaceId="+data.interfaceId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "接口结果详情",
                className: "add-modal"
            });
        }

        function query(){
            datatable.fnDraw();
        }

        function pollingQuery(interval) {
            var resultId = $("#resultId").val();
            var caseId = $("#caseId").val();
            var polling = $("#polling").val();
            var url = "<%=request.getContextPath()%>/result/caseResultDetailForAjax?resultId="+resultId+"&caseId="+caseId;
            $.ajax({
                type: "get",
                url: url + "&_t=" + new Date().getTime(),
                cache: false,
                async: false,//同步
                success: function (resultData) {
                    console.log(resultData);
                    if(!resultData){
                        console.log("continue ");
                        query();
                    }else{
                        query();
                        clearInterval(interval);
                        console.log("continue and stop ");
                        alert("用例下的接口全部执行完毕，请查看。");
                    }
                }
            })
        }

    </script>
</footer>
</body>
</html>

