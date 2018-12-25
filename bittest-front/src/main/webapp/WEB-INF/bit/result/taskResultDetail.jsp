<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>任务计划结果详情</title>
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
                            <label class="from-control col-xs-4">结果名称</label>
                            <input type="text"  name="taskName" class="form-control" columWidthClass="col-xs-8"   value="${result.resultName}" disabled="disabled"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">任务名称</label>
                            <input type="text"  name="taskName" class="form-control" columWidthClass="col-xs-8"   value="${result.taskName}" disabled="disabled"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">执行状态</label>
                            <select class="form-control" disabled="disabled" columWidthClass="col-xs-8">
                                <option>
                                    <c:choose>
                                        <c:when test="${result.isfinish==1}">
                                            执行中
                                        </c:when>
                                        <c:when test="${result.isfinish==2}">
                                            已完成
                                        </c:when>
                                        <c:when test="${result.isfinish==3}">
                                            执行异常
                                        </c:when>
                                    </c:choose>
                                </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">成功率</label>
                            <input type="text"  name="taskName" class="form-control" columWidthClass="col-xs-8"   value="${result.successRate}" disabled="disabled"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">创建时间</label>
                            <input type="text"   class="form-control" columWidthClass="col-xs-8"   value="<fmt:formatDate pattern="yyyy-MM-dd HH-mm-ss" value="${result.createTime}" />" disabled="disabled"/>
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
                            <c:when test="${result.taskParamMap!=null}">
                                <c:forEach items="${result.taskParamMap}" var="taskParam"  varStatus="index">
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
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
    </div><!-- /.box-footer -->
</form>

<footer>

    <script type="text/javascript">
        var me = commonErp.getInstance();
        $(document).ready(function() {
            $(function () {
                $('#collapseTwo').collapse('show')
            });
            $(function () {
                $('#collapseOne').collapse('show')
            });

        });

    </script>
</footer></body>
</html>
