<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <title>异步通知结果列表</title>
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
                            <label class="col-sm-1 control-label">系统编码</label>
                            <input name="sysCode" class="form-control"  columWidthClass="col-sm-2" />
                            <label class="col-sm-1 control-label">字段名</label>
                            <input name="fieldKey" class="form-control"  columWidthClass="col-sm-2" />

                            <label class="col-sm-1 control-label">字段值</label>
                            <input name="fieldId" class="form-control"  columWidthClass="col-sm-2" />
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
                        <th>系统编码</th>
                        <th>字段名</th>
                        <th>字段值</th>
                        <th>通知内容</th>
                        <th>创建时间</th>
                        <th>修改时间</th>
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
                { "mData": "sysCode" },
                { "mData": "fieldKey" },
                { "mData": "fieldId" },
                { "mData": "context" },
                { "mData": "createTime",'mRender': function(data, type, full) {
                        return formatDate(data);
                    } },
                { "mData": "updateTime",'mRender': function(data, type, full) {
                        return formatDate(data);
                    } }

            ];
            var columnDefs = [
      /*          {
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
                }*/
            ];
            var url = "/asyn/queryNotifyList";
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url , columns, columnDefs, aButtons,null);
        });

       /* function getBtns(row){
            var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="queryResult(\''+row.caseId+'\')"><i class="glyphicon glyphicon-facetime-video"></i>查看接口结果</a>&nbsp;&nbsp;';
            return btns;
        }*/

        function queryResult(caseId){
            var tabid = "INTERFACE_RESULT";
            var url = "<%=request.getContextPath()%>/result/toQueryInterfaceResultList?resultId="+$("#resultId").val()+"&caseId="+caseId;
            var title = "查看接口结果";
            window.parent.addTabs(tabid , title , url , true);
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
    </script>
</footer></body>
</html>

