<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>接口详情</title>
    <meta charset="UTF-8"/>
    <style>

    </style>
</head>
<body>
<form id="jsfForm" onsubmit="return false;" class="form-bordered" role="form">
    <input  type="hidden" id="interfaceType" name="interfaceType" value="${interfaces.type}" />
    <div class="row">
        <div class="col-xs-12">
            <div class="form-horizontal">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="panel-group" id="accordion">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h1 class="panel-title">
                                        <a data-toggle="collapse" data-parent="#accordion"
                                           href="#collapseOne">
                                            接口信息
                                        </a>
                                    </h1>
                                </div>
                                <div id="collapseOne" class="panel-collapse collapse in">
                                    <div class="panel-body">
                                        <div class="nav-tabs-custom">
                                            <ul id="nav" class="nav nav-tabs">
                                                <c:choose>
                                                    <c:when test="${interfaces.type==1}">
                                                        <li><a id="1" href="#JSF" >dubbo 请求</a></li>
                                                    </c:when>
                                                    <c:when test="${interfaces.type==2}">
                                                        <li><a id="2" href="#POST" >HTTP POST</a></li>
                                                    </c:when>
                                                    <c:when test="${interfaces.type==3}">
                                                        <li><a id="3" href="#GET" >HTTP GET</a></li>
                                                    </c:when>
                                                </c:choose>
                                            </ul>
                                            <div class="tab-content">
                                                <c:choose>
                                                    <c:when test="${interfaces.type==1}">
                                                        <div class="tab-pane active" id="JSF">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">接口名</label>
                                                                            <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" disabled="disabled" class="form-control" columWidthClass="col-xs-8" placeholder="添加接口后的展示名称"
                                                                                        value ="${interfaces.name}"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">地址</label>
                                                                            <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-9" disabled="disabled"
                                                                                        value ="${interfaces.url}"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">别名</label>
                                                                            <select id="jsf_alias" name="jsf_alias" class="form-control" columWidthClass="col-xs-4" disabled="disabled">
                                                                                    <option value="${interfaces.alias}"> ${interfaces.alias}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">token</label>
                                                                            <input type="text" id="jsf_token" name="jsf_token" class="form-control" columWidthClass="col-xs-4" disabled="disabled"
                                                                                   value ="${interfaces.token}"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">方法名</label>
                                                                            <select id="jsf_method" name="jsf_method" class="form-control" columWidthClass="col-xs-4" disabled="disabled">
                                                                                    <option value="${interfaces.method}"> ${interfaces.method}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">IP地址</label>
                                                                            <select id="jsf_ip" name="jsf_ip" class="form-control" columWidthClass="col-xs-4" disabled="disabled">
                                                                                <option value="${interfaces.ip}"> ${interfaces.ip}</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">请求值</label>
                                                                            <textarea id="jsf_body" name="jsf_body" class="form-control" rows="14" columWidthClass="col-xs-9" disabled="disabled"><c:if test="${interfaces.type==1}">${interfaces.body}</c:if></textarea>
                                                                        </div>
                                                                    </div><!-- /.box-body -->
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${interfaces.type==2}">
                                                        <div class="tab-pane" id="POST">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="postHead">
                                                                            <c:choose>
                                                                                <c:when test="${interfaces.headMap!=null}">
                                                                                    <c:forEach items="${interfaces.headMap}" var="head"  varStatus="index">
                                                                                        <div class="form-group" id="post_head${index.index}" name="post_head">
                                                                                            <label class="col-xs-1 control-label">head</label>
                                                                                            <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" disabled="disabled"/>
                                                                                            <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" disabled="disabled"/>
                                                                                        </div>
                                                                                    </c:forEach>
                                                                                </c:when>
                                                                            </c:choose>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">接口名</label>
                                                                                <input type="text" id="post_interfaceName" name="post_interfaceName" class="form-control" columWidthClass="col-xs-6" disabled="disabled"
                                                                                            value ="${interfaces.name}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" id="post_url" name="post_url" class="form-control" columWidthClass="col-xs-8"  disabled="disabled"
                                                                                            value ="${interfaces.url}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">请求值</label>
                                                                                <textarea id="post_body" name="post_body" class="form-control" rows="14" columWidthClass="col-xs-8" disabled="disabled">${interfaces.body}</textarea>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${interfaces.type==3}">
                                                        <div class="tab-pane" id="GET">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="getHead">
                                                                            <c:if test="${interfaces.headMap!=null}">
                                                                                <c:forEach items="${interfaces.headMap}" var="head"  varStatus="index">
                                                                                    <div class="form-group" id="get_head${index.index}" name="get_head">
                                                                                        <label class="col-xs-1 control-label">head</label>
                                                                                        <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" disabled="disabled"/>
                                                                                        <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" disabled="disabled"/>
                                                                                    </div>
                                                                                </c:forEach>
                                                                            </c:if>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">接口名</label>
                                                                                <input type="text" id="get_interfaceName" name="get_interfaceName" class="form-control" columWidthClass="col-xs-6" disabled="disabled"
                                                                                            value ="${interfaces.name}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" id="gett_url" name="get_url" class="form-control" columWidthClass="col-xs-8" disabled="disabled"
                                                                                            value ="${interfaces.url}"
                                                                                />
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                            <!-- /.tab-content -->
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
                                                    检查点信息
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body" >
                                        <div class="col-xs-12">
                                            <div id="userCheckReq">
                                                <c:if test="${interfaces.checkPointList!=null}">
                                                    <c:forEach items="${interfaces.checkPointList}" var="checkPoint"  varStatus="index">
                                                        <div class="form-group" id="userCheck${index.index}" name="userCheck" >
                                                            <label class="col-xs-1 control-label">匹配内容</label>
                                                            <div class="col-xs-4">
                                                                <input type="text"  name="checkPointList[${index.index}].checkValue" class="form-control" disabled="disabled" value="${fn:escapeXml(checkPoint.checkValue)}"/>
                                                             </div>
                                                            <label class="col-xs-1  control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配规则</label>
                                                            <div class="col-xs-4">
                                                                <select name="checkPointList[${index.index}].checkMethod" class="form-control" disabled="disabled">
                                                                    <option <c:if test="${checkPoint.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                    <option <c:if test="${checkPoint.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                    <option <c:if test="${checkPoint.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
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
                                                   href="#collapseFour">
                                                    数据库检查点
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseFour" class="panel-collapse collapse">
                                    <div class="panel-body" id="userSqlCheck">
                                        <div class="col-xs-12">
                                            <div id="userSqlReq">
                                                <c:if test="${interfaces.dataCheckList!=null}">
                                                    <c:forEach items="${interfaces.dataCheckList}" var="dataCheck"  varStatus="index">
                                                        <div class="form-group" id="userDataCheck${index.index}" name="userDataCheck">
                                                            <label class="col-xs-1 control-label">数据库</label>
                                                            <div class="col-xs-2">
                                                                <select class="form-control" name="dataCheckList[${index.index}].databaseId" disabled="disabled">
                                                                    <c:if test="${dataBase!=null}">
                                                                        <c:forEach items="${dataBase}" var="db">
                                                                            <option value="${db.databaseId}"
                                                                                    <c:if test="${db.databaseId==dataCheck.databaseId}">
                                                                                        selected="selected"
                                                                                    </c:if>
                                                                            >${db.name}</option>
                                                                        </c:forEach>
                                                                    </c:if>
                                                                </select>
                                                            </div>
                                                            <label class="col-xs-1 control-label" style="width:25px;padding-left:0px;padding-right:0px;padding-top: 7px;">select</label>
                                                            <div class="col-xs-3">
                                                                <textarea   class="form-control" rows="3" name="dataCheckList[${index.index}].wheres" disabled="disabled">${dataCheck.wheres}</textarea>
                                                            </div>
                                                            <label class="col-xs-1" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">规则</label>
                                                            <div class="col-xs-2">
                                                                <select name="dataCheckList[${index.index}].checkMethod" class="form-control" disabled="disabled">
                                                                    <<option <c:if test="${dataCheck.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                    <option <c:if test="${dataCheck.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                    <option <c:if test="${dataCheck.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                </select>
                                                            </div>
                                                            <label class="col-xs-1" style="width:40px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配值</label>
                                                            <div class="col-xs-2">
                                                                <input class="form-control" name="dataCheckList[${index.index}].checkValue"  disabled="disabled" value="${dataCheck.checkValue}"/>
                                                            </div>
                                                        <%--执行后根据结果动态添加--%>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
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
                                                   href="#collapseThree">
                                                    提取器信息
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body" id="userDataFetch">
                                        <div class="col-xs-12">
                                            <div id="userFetchReq">
                                                <c:if test="${interfaces.dataFetchList!=null}">
                                                    <c:forEach items="${interfaces.dataFetchList}" var="dataFetch"  varStatus="index">
                                                        <div class="form-group" id="userFetch${index.index}" name="userFetch" >
                                                            <label class="col-xs-1 control-label" >提取名称</label>
                                                            <div class="col-xs-2">
                                                                <input type="text"  name="dataFetchList[${index.index}].paramName" class="form-control" disabled="disabled" value="${dataFetch.paramName}" />
                                                            </div>
                                                            <label class="col-xs-1 control-label" style="width:50px;padding-left:0px;padding-right:0px;padding-top: 7px;">表达式</label>
                                                            <div class="col-xs-4">
                                                                <input type="text"  name="dataFetchList[${index.index}].regular" class="form-control" disabled="disabled" value="${fn:escapeXml(dataFetch.regular)}"/>
                                                            </div>
                                                            <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取索引</label>
                                                            <div class="col-xs-2">
                                                                <input type="text"  name="dataFetchList[${index.index}].regularIndex" class="form-control" value="${dataFetch.regularIndex}" disabled="disabled"/>
                                                            </div>
                                                        </div>
                                                    </c:forEach>
                                                </c:if>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Custom Tabs -->

                        <!-- nav-tabs-custom -->
                    </div>
                    <!-- /.col -->
                </div>
            </div>
            <div class="box-footer">
                <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
            </div><!-- /.box-footer -->
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
</form>
<!-- /.row -->

<footer>
    <script type="text/javascript">
        $(document).ready(function(){
            $(function () { $('#collapseOne').collapse('show')});
            $(function () { $('#collapseTwo').collapse('hide')});
            $(function () { $('#collapseThree').collapse('hide')});
            $(function () { $('#collapseFour').collapse('hide')});
            var tab = $("#interfaceType").val();
            $("#"+tab+"").tab("show");
        })
    </script>
</footer></body>
</html>

