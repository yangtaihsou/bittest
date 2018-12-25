<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>用例结果详情</title>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: right;
        }
    </style>
</head>
<body>
<form id="caseform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
    <div class="box-body">
        <div class="col-xs-8 col-xs-offset-2">
            <div class="panel-group" id="accordion">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h1 class="panel-title">
                        <a data-toggle="collapse" data-parent="#accordion"
                           href="#collapseOne">
                            用例信息
                        </a>
                    </h1>
                </div>
                <div id="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="from-control col-xs-4">用例名称</label>
                            <input type="text"  value="${result.caseName}" class="form-control" columWidthClass="col-xs-8" disabled="disabled"  />
                            <input type="hidden" id="caseId" name="caseId" value="${result.caseId}"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8" disabled="disabled" >
                                    <option >${result.systemName}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">执行结果</label>
                            <input type="text"  value="<c:choose><c:when test="${result.result==1}">通过</c:when><c:when test="${result.result==2}">失败</c:when></c:choose>" class="form-control" columWidthClass="col-xs-8" disabled="disabled"  />
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">创建时间</label>
                            <input type="text"  value="<fmt:formatDate pattern="yyyy-MM-dd HH-mm-ss" value="${result.createTime}" />" class="form-control" columWidthClass="col-xs-8" disabled="disabled"  />
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
                                <c:when test="${result.caseParamMap!=null}">
                                    <c:forEach items="${result.caseParamMap}" var="caseParam"  varStatus="index">
                                        <div class="form-group" id="${index.index}" name="param">
                                            <label class="from-control col-xs-2 control-label">参数名称：</label>
                                            <input type="text" value="${caseParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
                                            <label class="from-control col-xs-2 control-label">参数值</label>
                                            <input type="text" value="${caseParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
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
