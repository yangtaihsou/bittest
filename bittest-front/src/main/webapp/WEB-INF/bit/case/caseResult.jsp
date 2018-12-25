<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <link href="/static/css/jsonFormater.css" rel="stylesheet" />
    <script src="/static/js/jsonFormater.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>用例执行结果</title>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: right;
        }
        pre{
            background-color: white;
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
                <div id="collapseOne" name="collapseOne" class="panel-collapse collapse in">
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="from-control col-xs-4">用例名称</label>
                            <input type="text" id="caseName" name="caseName" value="${result.caseName}" class="form-control" columWidthClass="col-xs-8" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8" readonly="readonly">${result.remark}</textarea>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4 col-sm-2 control-label">执行结果</label>
                            <c:choose>
                                <c:when test="${result.result==1}">
                                    <input type="text"  value="通过" class="form-control" columWidthClass="col-xs-8" readonly="readonly" style="color: green"}
                                    />
                                </c:when>
                                <c:when test="${result.result==2}">
                                    <input type="text"  value="失败" class="form-control" columWidthClass="col-xs-8" readonly="readonly" style="color: red"/>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
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
                            <c:choose>
                                <c:when test="${result.caseParamMap!=null}">
                                    <c:forEach items="${result.caseParamMap}" var="caseParam"  varStatus="index">
                                        <div class="form-group" id="${index.index}" name="param">
                                            <label class="from-control col-xs-2 control-label">参数名称：</label>
                                            <input type="text" value="${caseParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3" readonly="readonly"/>
                                            <label class="from-control col-xs-2 control-label">参数值</label>
                                            <input type="text" value="${caseParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" readonly="readonly"/>
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
    <div class="box">
        <div class="box-body">
            <table id="datatable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>接口编号</th>
                    <th>接口名</th>
                    <th>接口类型</th>
                    <th>访问地址</th>
                    <th>执行结果</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                    <c:if test="${null!=result.interfaceResultList}">
                        <c:forEach items="${result.interfaceResultList}" var="it">
                            <tr>
                                <td>${it.interfaceId}</td>
                                <td>${it.name}</td>
                                <td><c:choose>
                                    <c:when test="${it.type==1}">
                                        JSF
                                    </c:when>
                                    <c:when test="${it.type==2}">
                                        POST
                                    </c:when>
                                    <c:when test="${it.type==3}">
                                        GET
                                    </c:when>
                                </c:choose></td>
                                <td>${it.url}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${it.result==1}">
                                            <span class="badge bg-green-active">通过</span>
                                        </c:when>
                                        <c:when test="${it.result==2}">
                                            <span class="badge bg-red-active">失败</span>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <a class="btn btn-xs btn-warning" href="javascript:void(0);" data-toggle="modal" data-target="#myModal${it.interfaceId}" ><i class="glyphicon glyphicon-facetime-video"></i>详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
        <!-- /.box-body -->
    </div>
<c:if test="${null!=result.interfaceResultList}">
    <c:forEach items="${result.interfaceResultList}" var="it" varStatus="id">
        <c:choose>
            <c:when test="${it.type==4}">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal${it.interfaceId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:100%;height: 300px;">
                    <div class="modal-dialog" style="width:90%;height: 90%">
                        <div class="modal-content" style="width:100%;height: 100%;">
                            <div class="modal-header" style="height:56px;">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                                </button>
                                <h4 class="modal-title" id="myModalLabel1">
                                    sleep执行结果
                                </h4>
                            </div>
                            <div class="modal-body" style="width:100%;height:75%;overflow:auto" >
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="panel-group" id="accordion${it.interfaceId}">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h1 class="panel-title">
                                                        <a data-toggle="collapse"
                                                           href="#collapseOne${it.interfaceId}">
                                                            接口信息
                                                        </a>
                                                    </h1>
                                                </div>
                                                <div id="collapseOne${it.interfaceId}" name="collapseOne" class="panel-collapse collapse in">
                                                    <div class="panel-body">
                                                        <c:choose>
                                                            <c:when test="${it.type==1}">
                                                                <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <div class="box-body">
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">接口名</label>
                                                                                <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" class="form-control" columWidthClass="col-xs-9" placeholder="添加接口后的展示名称"
                                                                                       value ="${it.name}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-11"
                                                                                       value ="${it.url}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">别名</label>
                                                                                <select id="jsf_alias" name="jsf_alias" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                    <option value="${it.alias}"> ${it.alias}</option>
                                                                                </select>
                                                                                <label class="col-xs-1 control-label">token</label>
                                                                                <input type="text" id="jsf_token" name="jsf_token" class="form-control" columWidthClass="col-xs-5"
                                                                                       value ="${it.token}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">方法名</label>
                                                                                <select id="jsf_method" name="jsf_method" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                    <option value="${it.method}"> ${it.method}</option>
                                                                                </select>
                                                                                <label class="col-xs-1 control-label">IP地址</label>
                                                                                <select id="jsf_ip" name="jsf_ip" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                    <option value="${it.ip}"> ${it.ip}</option>
                                                                                </select>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">状态</label>
                                                                                <input id="jsf_res_code"  class="form-control" columWidthClass="col-xs-5" value="${it.resultCode}" readonly="readonly"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">请求值</label>
                                                                                <textarea id="jsf_body" name="jsf_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                                <label class="col-xs-1 control-label">返回值</label>
                                                                                <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                            </div>
                                                                        </div><!-- /.box-body -->
                                                                    </div>

                                                                </div>
                                                            </c:when>
                                                            <c:when test="${it.type==2}">
                                                                <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <div class="box-body">
                                                                            <div class="col-xs-12" id="postHead">
                                                                                <c:forEach items="${it.headMap}" var="head"  varStatus="index">
                                                                                    <div class="form-group" id="post_head${index.index}" name="post_head">
                                                                                        <label class="col-xs-1 control-label">head</label>
                                                                                        <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" readonly="readonly"/>
                                                                                        <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" readonly="readonly"/>
                                                                                    </div>
                                                                                </c:forEach>
                                                                            </div>
                                                                            <div class="col-xs-12">
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">接口名</label>
                                                                                    <input type="text" id="post_interfaceName" name="post_interfaceName" class="form-control" columWidthClass="col-xs-11" placeholder="添加接口后的展示名称"
                                                                                           value ="${it.name}" readonly="readonly"
                                                                                    />
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">地址</label>
                                                                                    <input type="text" id="post_url" name="post_url" class="form-control" columWidthClass="col-xs-11" placeholder="请输入url地址信息"
                                                                                           value ="${it.url}" readonly="readonly"
                                                                                    />
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">状态</label>
                                                                                    <input type="text" id="post_res_code"  class="form-control" columWidthClass="col-xs-5" readonly="readonly" value="${it.resultCode}"/>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">请求值</label>
                                                                                    <textarea id="post_body" name="post_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                                    <label class="col-xs-1 control-label">返回值</label>
                                                                                    <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                            <c:when test="${it.type==3}">
                                                                <div class="row">
                                                                    <div class="col-xs-12">
                                                                        <div class="box-body">
                                                                            <div class="col-xs-12" id="getHead">
                                                                                <c:forEach items="${it.headMap}" var="head"  varStatus="index">
                                                                                    <div class="form-group" id="get_head${index.index}" name="get_head">
                                                                                        <label class="col-xs-1 control-label">head</label>
                                                                                        <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" readonly="readonly"/>
                                                                                        <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" readonly="readonly"/>
                                                                                    </div>
                                                                                </c:forEach>
                                                                            </div>
                                                                            <div class="col-xs-12">
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">接口名</label>
                                                                                    <input type="text" id="get_interfaceName" name="get_interfaceName" class="form-control" columWidthClass="col-xs-11" placeholder="添加接口后的展示名称"
                                                                                           value ="${it.name}" readonly="readonly"
                                                                                    />
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">地址</label>
                                                                                    <input type="text" id="gett_url" name="get_url" class="form-control" columWidthClass="col-xs-11" placeholder="请输入url地址信息"
                                                                                           value ="${it.url}" readonly="readonly"
                                                                                    />
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">状态</label>
                                                                                    <input type="text" id="get_res_code"  class="form-control" columWidthClass="col-xs-5" readonly="readonly" value="${it.resultCode}"/>
                                                                                </div>
                                                                                <div class="form-group">
                                                                                    <label class="col-xs-1 control-label">请求值</label>
                                                                                    <textarea id="get_req_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                                    <label class="col-xs-1 control-label">返回值</label>
                                                                                    <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:when>
                                                        </c:choose>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <h1 class="panel-title">
                                                                <a data-toggle="collapse"
                                                                   href="#collapseTwo${it.interfaceId}">
                                                                    检查点信息
                                                                </a>
                                                            </h1>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="collapseTwo${it.interfaceId}" class="panel-collapse collapse">
                                                    <div class="panel-body" >
                                                        <c:if test="${it.checkPointList!=null}">
                                                            <c:forEach items="${it.checkPointList}" var="checkPoint"  varStatus="index">
                                                                <div class="col-xs-9">
                                                                    <div id="userCheckReq">
                                                                        <div class="form-group">
                                                                            <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配内容</label>
                                                                            <div class="col-xs-3">
                                                                                <input type="text"  name="checkPointList[${index.index}].checkValue" class="form-control" placeholder="请输入内容与接口返回结果匹配" value="${fn:escapeXml(checkPoint.checkValue)}"/>
                                                                            </div>
                                                                            <label class="col-xs-2  control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配规则</label>
                                                                            <div class="col-xs-3">
                                                                                <select name="checkPointList[${index.index}].checkMethod" class="form-control" disabled="disabled">
                                                                                    <option <c:if test="${checkPoint.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                                    <option <c:if test="${checkPoint.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                                    <option <c:if test="${checkPoint.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                                </select>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <div id="userCheckRes">
                                                                        <div class="form-group"  name="userCheckRes" >
                                                                            <label class="from-control col-xs-4 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配结果</label>
                                                                            <div class="col-xs-8">
                                                                                <c:choose>
                                                                                    <c:when test="${checkPoint.result}">
                                                                                        <a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color: red">失败</a>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <h1 class="panel-title">
                                                                <a data-toggle="collapse"
                                                                   href="#collapseFour${it.interfaceId}">
                                                                    数据库检查点
                                                                </a>
                                                            </h1>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="collapseFour${it.interfaceId}" class="panel-collapse collapse">
                                                    <div class="panel-body" id="userSqlCheck">
                                                        <c:if test="${it.dataCheckList!=null}">
                                                            <c:forEach items="${it.dataCheckList}" var="dataCheck">
                                                                <div class="col-xs-9">
                                                                    <div id="userSqlReq">
                                                                        <div class="form-group" >
                                                                            <label class="col-xs-1 control-label" style="width:50px;padding-left:0px;padding-right:0px;padding-top: 7px;">数据库</label>
                                                                            <div class="col-xs-2">
                                                                                <select class="form-control" readonly="readonly">
                                                                                    <option>${dataCheck.tableName}</option>
                                                                                </select>
                                                                            </div>
                                                                            <label class="col-xs-1 control-label" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">select</label>
                                                                            <div class="col-xs-3">
                                                                                <textarea   class="form-control" rows="3" readonly="readonly">${dataCheck.wheres}</textarea>
                                                                            </div>
                                                                            <label class="col-xs-1" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">规则</label>
                                                                            <div class="col-xs-2">
                                                                                <select class="form-control" disabled="disabled">
                                                                                    <<option <c:if test="${dataCheck.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                                    <option <c:if test="${dataCheck.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                                    <option <c:if test="${dataCheck.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                                </select>
                                                                            </div>
                                                                            <label class="col-xs-1" style="width:40px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配值</label>
                                                                            <div class="col-xs-2">
                                                                                <input class="form-control" readonly="readonly" value="${dataCheck.checkValue}"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <div id="userSqlRes">
                                                                        <div class="form-group">
                                                                            <label class="from-control col-xs-3 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">结果内容</label>
                                                                            <div class="col-xs-4">
                                                                                <input type="text" class="form-control" value="${dataCheck.columns}" readonly="readonly"/>
                                                                            </div>
                                                                            <div class="col-xs-4">
                                                                                <c:choose>
                                                                                    <c:when test="${dataCheck.result}">
                                                                                        <a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color: red">失败</a>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <div class="row">
                                                        <div class="col-xs-6">
                                                            <h1 class="panel-title">
                                                                <a data-toggle="collapse"
                                                                   href="#collapseThree${it.interfaceId}">
                                                                    提取器信息
                                                                </a>
                                                            </h1>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div id="collapseThree${it.interfaceId}" class="panel-collapse collapse">
                                                    <div class="panel-body" id="userDataFetch">
                                                        <c:if test="${it.dataFetchList!=null}">
                                                            <c:forEach items="${it.dataFetchList}" var="dataFetch"  varStatus="index">
                                                                <div class="col-xs-9">
                                                                    <div id="userFetchReq">
                                                                        <div class="form-group" id="userFetch${index.index}" name="userFetch" >
                                                                            <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取名称</label>
                                                                            <div class="col-xs-2">
                                                                                <input type="text"  name="dataFetchList[${index.index}].paramName" class="form-control" placeholder="提取内容后的变量名称" value="${dataFetch.paramName}"/>
                                                                            </div>
                                                                            <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">表达式</label>
                                                                            <div class="col-xs-4">
                                                                                <input type="text"  name="dataFetchList[${index.index}].regular" class="form-control" placeholder="请输入正则表达式信息" value="${fn:escapeXml(dataFetch.regular)}"/>
                                                                            </div>
                                                                            <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取索引</label>
                                                                            <div class="col-xs-2">
                                                                                <input type="text" class="form-control" value="${dataFetch.regularIndex}"/>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-3">
                                                                    <div id="userFetchRes">
                                                                        <div class="form-group"  name="userCheckRes" >
                                                                            <label class="from-control col-xs-5 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取结果</label>
                                                                            <div class="col-xs-7">
                                                                                <input type="text"  name="fetchResult" class="form-control" value="${dataFetch.result}" readonly="readonly"/>
                                                                            </div>
                                                                        </div>
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
                            </div>
                            <div class="modal-footer" style="height: 63px;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </c:when>
            <c:when test="${it.type==5}">
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal${it.interfaceId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:100%;height: 400px;">
                    <div class="modal-dialog" style="width:90%;height: 90%">
                        <div class="modal-content" style="width:100%;height: 100%;">
                            <div class="modal-header" style="height:56px;">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                                </button>
                                <h4 class="modal-title" id="myModalLabel2">
                                    数据库操作结果
                                </h4>
                            </div>
                            <div class="modal-body" style="width:100%;height:75%;overflow:auto" >
                                <div class="row">
                                    <div class="box-body">
                                        <div class="form-group">
                                            <label class="from-control col-xs-4">名称</label>
                                            <input class="form-control" columWidthClass="col-xs-8" disabled="disabled" type="text" value="${it.name}" name="name" id="name" required/>
                                        </div>
                                        <div class="form-group">
                                            <label class="from-control col-xs-4">数据库</label>
                                            <select class="form-control" columWidthClass="col-xs-8" id="url" name="url" required disabled="disabled">
                                                    <option>${it.name}</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="from-control col-xs-4">操作类型</label>
                                            <select class="form-control" columWidthClass="col-xs-8" id="method" name="method" required disabled="disabled">
                                                <c:if test="${it.method eq 'insert'}">
                                                <option value="insert">insert新增</option>
                                                </c:if>
                                                <c:if test="${it.method eq 'update'}">
                                                <option value="update">修改or删除</option>
                                                </c:if>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label class="from-control col-xs-4">SQL</label>
                                            <textarea  name="body" disabled="disabled" class="form-control" rows="4" required columWidthClass="col-xs-8">${it.body}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
                                            <textarea  name="remark" class="form-control" rows="4" columWidthClass="col-xs-8" disabled="disabled">${it.remark}</textarea>
                                        </div>
                                    </div><!-- /.box-body -->
                                </div>
                            </div>
                            <div class="modal-footer" style="height: 63px;">
                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    关闭
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </c:when>
            <c:otherwise>
                <!-- 模态框（Modal） -->
                <div class="modal fade" id="myModal${it.interfaceId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="width:100%;height: 100%">
                <div class="modal-dialog" style="width:90%;height: 90%">
                    <div class="modal-content" style="width:100%;height: 100%;">
                        <div class="modal-header" style="height:56px;">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×
                            </button>
                            <h4 class="modal-title" id="myModalLabel">
                                接口执行结果
                            </h4>
                        </div>
                        <div class="modal-body" style="width:100%;height:75%;overflow:auto" >
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="panel-group" id="accordion${it.interfaceId}">
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <h1 class="panel-title">
                                                    <a data-toggle="collapse"
                                                       href="#collapseOne${it.interfaceId}">
                                                        接口信息
                                                    </a>
                                                </h1>
                                            </div>
                                            <div id="collapseOne${it.interfaceId}" name="collapseOne" class="panel-collapse collapse in">
                                                <div class="panel-body">
                                                    <c:choose>
                                                        <c:when test="${it.type==1}">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">接口名</label>
                                                                            <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" class="form-control" columWidthClass="col-xs-9" placeholder="添加接口后的展示名称"
                                                                                   value ="${it.name}" readonly="readonly"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">地址</label>
                                                                            <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-11"
                                                                                   value ="${it.url}" readonly="readonly"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">别名</label>
                                                                            <select id="jsf_alias" name="jsf_alias" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                <option value="${it.alias}"> ${it.alias}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">token</label>
                                                                            <input type="text" id="jsf_token" name="jsf_token" class="form-control" columWidthClass="col-xs-5"
                                                                                   value ="${it.token}" readonly="readonly"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">方法名</label>
                                                                            <select id="jsf_method" name="jsf_method" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                <option value="${it.method}"> ${it.method}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">IP地址</label>
                                                                            <select id="jsf_ip" name="jsf_ip" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                <option value="${it.ip}"> ${it.ip}</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">状态</label>
                                                                            <input id="jsf_res_code"  class="form-control" columWidthClass="col-xs-5" value="${it.resultCode}" readonly="readonly"/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">请求值</label>
                                                                            <textarea id="jsf_body" name="jsf_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                            <label class="col-xs-1 control-label">返回值</label>
                                                                            <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                        </div>
                                                                    </div><!-- /.box-body -->
                                                                </div>

                                                            </div>
                                                        </c:when>
                                                        <c:when test="${it.type==2}">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="postHead">
                                                                            <c:forEach items="${it.headMap}" var="head"  varStatus="index">
                                                                                <div class="form-group" id="post_head${index.index}" name="post_head">
                                                                                    <label class="col-xs-1 control-label">head</label>
                                                                                    <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" readonly="readonly"/>
                                                                                    <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" readonly="readonly"/>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">接口名</label>
                                                                                <input type="text" id="post_interfaceName" name="post_interfaceName" class="form-control" columWidthClass="col-xs-11" placeholder="添加接口后的展示名称"
                                                                                       value ="${it.name}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" id="post_url" name="post_url" class="form-control" columWidthClass="col-xs-11" placeholder="请输入url地址信息"
                                                                                       value ="${it.url}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">状态</label>
                                                                                <input type="text" id="post_res_code"  class="form-control" columWidthClass="col-xs-5" readonly="readonly" value="${it.resultCode}"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">请求值</label>
                                                                                <textarea id="post_body" name="post_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                                <label class="col-xs-1 control-label">返回值</label>
                                                                                <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                        <c:when test="${it.type==3}">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="getHead">
                                                                            <c:forEach items="${it.headMap}" var="head"  varStatus="index">
                                                                                <div class="form-group" id="get_head${index.index}" name="get_head">
                                                                                    <label class="col-xs-1 control-label">head</label>
                                                                                    <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="${head.key}" readonly="readonly"/>
                                                                                    <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="${head.value}" readonly="readonly"/>
                                                                                </div>
                                                                            </c:forEach>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">接口名</label>
                                                                                <input type="text" id="get_interfaceName" name="get_interfaceName" class="form-control" columWidthClass="col-xs-11" placeholder="添加接口后的展示名称"
                                                                                       value ="${it.name}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" id="gett_url" name="get_url" class="form-control" columWidthClass="col-xs-11" placeholder="请输入url地址信息"
                                                                                       value ="${it.url}" readonly="readonly"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">状态</label>
                                                                                <input type="text" id="get_res_code"  class="form-control" columWidthClass="col-xs-5" readonly="readonly" value="${it.resultCode}"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">请求值</label>
                                                                                <textarea id="get_req_body" class="form-control" rows="14" columWidthClass="col-xs-5" readonly="readonly">${it.body}</textarea>
                                                                                <label class="col-xs-1 control-label">返回值</label>
                                                                                <div id="resBody${id.index}"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:when>
                                                    </c:choose>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <h1 class="panel-title">
                                                            <a data-toggle="collapse"
                                                               href="#collapseTwo${it.interfaceId}">
                                                                检查点信息
                                                            </a>
                                                        </h1>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="collapseTwo${it.interfaceId}" class="panel-collapse collapse">
                                                <div class="panel-body" >
                                                    <c:if test="${it.checkPointList!=null}">
                                                        <c:forEach items="${it.checkPointList}" var="checkPoint"  varStatus="index">
                                                            <div class="col-xs-9">
                                                                <div id="userCheckReq">
                                                                    <div class="form-group">
                                                                        <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配内容</label>
                                                                        <div class="col-xs-3">
                                                                            <input type="text"  name="checkPointList[${index.index}].checkValue" class="form-control" placeholder="请输入内容与接口返回结果匹配" value="${fn:escapeXml(checkPoint.checkValue)}"/>
                                                                        </div>
                                                                        <label class="col-xs-2  control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配规则</label>
                                                                        <div class="col-xs-3">
                                                                            <select name="checkPointList[${index.index}].checkMethod" class="form-control" disabled="disabled">
                                                                                <option <c:if test="${checkPoint.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                                <option <c:if test="${checkPoint.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                                <option <c:if test="${checkPoint.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                            </select>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-3">
                                                                <div id="userCheckRes">
                                                                    <div class="form-group"  name="userCheckRes" >
                                                                        <label class="from-control col-xs-4 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配结果</label>
                                                                        <div class="col-xs-8">
                                                                            <c:choose>
                                                                                <c:when test="${checkPoint.result}">
                                                                                    <a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color: red">失败</a>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <h1 class="panel-title">
                                                            <a data-toggle="collapse"
                                                               href="#collapseFour${it.interfaceId}">
                                                                数据库检查点
                                                            </a>
                                                        </h1>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="collapseFour${it.interfaceId}" class="panel-collapse collapse">
                                                <div class="panel-body" id="userSqlCheck">
                                                    <c:if test="${it.dataCheckList!=null}">
                                                        <c:forEach items="${it.dataCheckList}" var="dataCheck">
                                                            <div class="col-xs-9">
                                                                <div id="userSqlReq">
                                                                    <div class="form-group" >
                                                                        <label class="col-xs-1 control-label" style="width:50px;padding-left:0px;padding-right:0px;padding-top: 7px;">数据库</label>
                                                                        <div class="col-xs-2">
                                                                            <select class="form-control" readonly="readonly">
                                                                                <option>${dataCheck.tableName}</option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-xs-1 control-label" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">select</label>
                                                                        <div class="col-xs-3">
                                                                            <textarea   class="form-control" rows="3" readonly="readonly">${dataCheck.wheres}</textarea>
                                                                        </div>
                                                                        <label class="col-xs-1" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">规则</label>
                                                                        <div class="col-xs-2">
                                                                            <select class="form-control" disabled="disabled">
                                                                                <<option <c:if test="${dataCheck.checkMethod==1}">selected="selected"</c:if>value="1">包含</option>
                                                                                <option <c:if test="${dataCheck.checkMethod==2}">selected="selected"</c:if>value="2">不包含</option>
                                                                                <option <c:if test="${dataCheck.checkMethod==3}">selected="selected"</c:if>value="3">等于</option>
                                                                            </select>
                                                                        </div>
                                                                        <label class="col-xs-1" style="width:40px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配值</label>
                                                                        <div class="col-xs-2">
                                                                            <input class="form-control" readonly="readonly" value="${dataCheck.checkValue}"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-3">
                                                                <div id="userSqlRes">
                                                                    <div class="form-group">
                                                                        <label class="from-control col-xs-3 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">结果内容</label>
                                                                        <div class="col-xs-4">
                                                                            <input type="text" class="form-control" value="${dataCheck.columns}" readonly="readonly"/>
                                                                        </div>
                                                                        <div class="col-xs-4">
                                                                            <c:choose>
                                                                                <c:when test="${dataCheck.result}">
                                                                                    <a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color: red">失败</a>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="panel panel-default">
                                            <div class="panel-heading">
                                                <div class="row">
                                                    <div class="col-xs-6">
                                                        <h1 class="panel-title">
                                                            <a data-toggle="collapse"
                                                               href="#collapseThree${it.interfaceId}">
                                                                提取器信息
                                                            </a>
                                                        </h1>
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="collapseThree${it.interfaceId}" class="panel-collapse collapse">
                                                <div class="panel-body" id="userDataFetch">
                                                    <c:if test="${it.dataFetchList!=null}">
                                                        <c:forEach items="${it.dataFetchList}" var="dataFetch"  varStatus="index">
                                                            <div class="col-xs-9">
                                                                <div id="userFetchReq">
                                                                    <div class="form-group" id="userFetch${index.index}" name="userFetch" >
                                                                        <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取名称</label>
                                                                        <div class="col-xs-2">
                                                                            <input type="text"  name="dataFetchList[${index.index}].paramName" class="form-control" placeholder="提取内容后的变量名称" value="${dataFetch.paramName}"/>
                                                                        </div>
                                                                        <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">表达式</label>
                                                                        <div class="col-xs-4">
                                                                            <input type="text"  name="dataFetchList[${index.index}].regular" class="form-control" placeholder="请输入正则表达式信息" value="${fn:escapeXml(dataFetch.regular)}"/>
                                                                        </div>
                                                                        <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取索引</label>
                                                                        <div class="col-xs-2">
                                                                            <input type="text" class="form-control" value="${dataFetch.regularIndex}"/>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <div class="col-xs-3">
                                                                <div id="userFetchRes">
                                                                    <div class="form-group"  name="userCheckRes" >
                                                                        <label class="from-control col-xs-5 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取结果</label>
                                                                        <div class="col-xs-7">
                                                                            <input type="text"  name="fetchResult" class="form-control" value="${dataFetch.result}" readonly="readonly"/>
                                                                        </div>
                                                                    </div>
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
                        </div>
                        <div class="modal-footer" style="height: 63px;">
                            <button type="button" class="btn btn-default" data-dismiss="modal">
                                关闭
                            </button>
                        </div>
                    </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
                </div><!-- /.modal -->
            </c:otherwise>



        </c:choose>
    </c:forEach>
</c:if>
</form>
<footer>
    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = null;
        var tableUtil = new TableUtil("datatable");
        $(document).ready(function() {
            $('#collapseOne').collapse('show')
            $('#collapseTwo').collapse('hide')
            var aButtons = [];
            datatable = me.initEmptyDataTable($('#datatable'), "", "", aButtons, 100);

            var fomatValue = {
                dom: '',
                isCollapsible: true,
                quoteKeys: true,
                tabSize: 1
            };
            var collection;
            var resBody;


//            $(''+collection+'').val(jfp.doFormat(resBody));
            //查询用例接口列表信息
            <c:if test="${null!=result.interfaceResultList}">
                <c:forEach items="${result.interfaceResultList}" var="it" varStatus="index">
                    collection = '#resBody'+${index.index};
                    resBody = '${it.resBody}';
                    fomatValue.dom=collection;
                    window.jfp = new JsonFormater(fomatValue)
                    $(''+collection+'').val(jfp.doFormat(resBody));
                </c:forEach>
            </c:if>
        });
    </script>
</footer>
</body>
</html>
