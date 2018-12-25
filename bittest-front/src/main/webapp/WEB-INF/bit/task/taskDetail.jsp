<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>任务计划详情</title>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: right;
        }
    </style>
</head>
<body>
<form id="erpform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
    <div class="box-body">
        <div class="col-xs-8 col-xs-offset-2">
            <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapseOne">
                            任务信息
                        </a>
                    </h1>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="from-control col-xs-4">任务名称</label>
                            <input type="text" id="taskName" name="taskName" class="form-control" columWidthClass="col-xs-8"   value="${task.taskName}" disabled="disabled"/>
                            <input type="hidden" id="taskId" name="taskId"  value="${task.taskId}"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8"  name="sysId" required disabled="disabled">
                                <option value="">请选择</option>
                                <c:forEach var="sys" items="${systems}" >
                                    <option value="${sys.systemId}"
                                            <c:if test="${sys.systemId==task.sysId}">
                                                selected="selected"
                                            </c:if>
                                    >${sys.systemName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8" disabled="disabled">${task.remark}</textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <h1 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion"
                                   href="#collapseTwo">
                                    参数化信息
                                </a>
                            </h1>
                        </div>
                    </div>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse">
                    <div class="panel-body" id="userParam">
                        <%--动态添加--%>
                        <c:choose>
                            <c:when test="${task.taskParamMap!=null}">
                                <c:forEach items="${task.taskParamMap}" var="taskParam"  varStatus="index">
                                    <div class="form-group" id="${index.index}" name="param">
                                        <label class="from-control col-xs-2 control-label">参数名称：<font color="red">*</font></label>
                                        <input type="text" value="${taskParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
                                        <label class="from-control col-xs-2 control-label">参数值</label>
                                        <input type="text" value="${taskParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
                                    </div>
                                </c:forEach>
                            </c:when>
                        </c:choose>

                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>
</form>
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-info">
                <!-- form start -->
                <form id="queryform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
                    <div class="form-horizontal">
                        <div class="box-body">
                            <div class="form-group">
                                <label class="col-sm-1 control-label">用例名称</label>
                                <input type="text" id="caseName" name="caseName" class="form-control"  columWidthClass="col-sm-2" value=""  />
                                <label class="col-sm-1 control-label">所属系统</label>
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

    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
    </div><!-- /.box-footer -->

<footer>

    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = null;
        var tableUtil = new TableUtil("datatable");
        $(document).ready(function() {
            var aButtons = [];

            var columns = [
                {"mData": "caseId"},
                {"mData": "caseName"},
                {"mData": "systemName"},
                {"mData": "updateTimeStr"},
                {"mData": "createTimeStr"},

            ];
            var columnDefs = [];
            var url = '/case/queryFetchCaseList?taskId=' + $('#taskId').val();
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url, columns, columnDefs, aButtons);

            $(function () {
                $('#collapseFour').collapse({
                    toggle: false
                })
            });
            $(function () {
                $('#collapseTwo').collapse('hide')
            });
            $(function () {
                $('#collapseOne').collapse('show')
            });

        });
        function query(){
            datatable.fnDraw();
        }

    </script>
</footer></body>
</html>
