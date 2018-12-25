<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>接口详情</title>
    <meta charset="UTF-8"/>
    <style>
        pre{
            background-color: white;
        }
    </style>
</head>
<body>
<form id="jsfForm" onsubmit="return false;" class="form-bordered" role="form">
    <input  type="hidden" id="interfaceType" name="interfaceType" value="${result.type}" />
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
                                                    <c:when test="${result.type==1}">
                                                        <li><a id="1" href="#JSF" >dubbo 请求</a></li>
                                                    </c:when>
                                                    <c:when test="${result.type==2}">
                                                        <li><a id="2" href="#POST" >HTTP POST</a></li>
                                                    </c:when>
                                                    <c:when test="${result.type==3}">
                                                        <li><a id="3" href="#GET" >HTTP GET</a></li>
                                                    </c:when>
                                                </c:choose>
                                            </ul>
                                            <div class="tab-content">
                                                <c:choose>
                                                    <c:when test="${result.type==1}">
                                                        <div class="tab-pane active" id="JSF">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">接口名</label>
                                                                            <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" disabled="disabled" class="form-control" columWidthClass="col-xs-9" placeholder="添加接口后的展示名称"
                                                                                        value ="${result.name}"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">地址</label>
                                                                            <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-11" disabled="disabled"
                                                                                        value ="${result.url}"
                                                                            />
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">别名</label>
                                                                            <select id="jsf_alias" name="jsf_alias" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                    <option value="${result.alias}"> ${result.alias}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">token</label>
                                                                            <input type="text" id="jsf_token" name="jsf_token" class="form-control" columWidthClass="col-xs-5" disabled="disabled" value="${result.token}"/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">方法名</label>
                                                                            <select id="jsf_method" name="jsf_method" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                    <option value="${result.method}"> ${result.method}</option>
                                                                            </select>
                                                                            <label class="col-xs-1 control-label">IP地址</label>
                                                                            <select id="jsf_ip" name="jsf_ip" class="form-control" columWidthClass="col-xs-5" disabled="disabled">
                                                                                <option value="${result.ip}"> ${result.ip}</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">状态</label>
                                                                            <input  class="form-control" rows="6" columWidthClass="col-xs-5" disabled="disabled" value="${result.resultCode}"/>
                                                                            <label class="col-xs-1 control-label">结果</label>
                                                                            <input  class="form-control"  columWidthClass="col-xs-5" disabled="disabled" value="<c:choose><c:when test="${result.result==1}">通过</c:when><c:when test="${result.result==2}">失败</c:when></c:choose>"/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <label class="col-xs-1 control-label">请求值</label>
                                                                            <textarea id="jsf_body" name="jsf_body" class="form-control" rows="14" columWidthClass="col-xs-5" disabled="disabled"><c:if test="${result.type==1}">${result.body}</c:if></textarea>
                                                                            <label class="col-xs-1 control-label">返回值</label>
                                                                            <div id="jsf_res_body"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                        </div>
                                                                        <div class="form-group">

                                                                        </div>
                                                                    </div><!-- /.box-body -->
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${result.type==2}">
                                                        <div class="tab-pane" id="POST">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="postHead">
                                                                            <c:choose>
                                                                                <c:when test="${result.headMap!=null}">
                                                                                    <c:forEach items="${result.headMap}" var="head"  varStatus="index">
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
                                                                                <input type="text" id="post_interfaceName" name="post_interfaceName" class="form-control" columWidthClass="col-xs-11" disabled="disabled"
                                                                                            value ="${result.name}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" class="form-control" columWidthClass="col-xs-11"  disabled="disabled"  value ="${result.url}"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">状态</label>
                                                                                <input  class="form-control"  columWidthClass="col-xs-5" disabled="disabled" value="${result.resultCode}"/>
                                                                                <label class="col-xs-1 control-label">结果</label>
                                                                                <input  class="form-control"  columWidthClass="col-xs-5" disabled="disabled" value="<c:choose><c:when test="${result.result==1}">通过</c:when><c:when test="${result.result==2}">失败</c:when></c:choose>"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">请求值</label>
                                                                                <textarea  class="form-control" rows="14" columWidthClass="col-xs-5" disabled="disabled">${result.body}</textarea>
                                                                                <label class="col-xs-1 control-label">返回值</label>
                                                                                <div id="post_res_body"  name="res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:when>
                                                    <c:when test="${result.type==3}">
                                                        <div class="tab-pane" id="GET">
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="box-body">
                                                                        <div class="col-xs-12" id="getHead">
                                                                            <c:if test="${result.headMap!=null}">
                                                                                <c:forEach items="${result.headMap}" var="head"  varStatus="index">
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
                                                                                            value ="${result.name}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">地址</label>
                                                                                <input type="text" class="form-control" columWidthClass="col-xs-11" disabled="disabled"
                                                                                            value ="${result.name}"
                                                                                />
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">状态</label>
                                                                                <input  class="form-control" rows="6" columWidthClass="col-xs-5" disabled="disabled" value="${result.resultCode}"/>
                                                                                <label class="col-xs-1 control-label">执行结果</label>
                                                                                <input  class="form-control" rows="6" columWidthClass="col-xs-5" disabled="disabled" value="<c:choose><c:when test="${result.result==1}">通过</c:when><c:when test="${result.result==2}">失败</c:when></c:choose>"/>
                                                                            </div>
                                                                            <div class="form-group">
                                                                                <label class="col-xs-1 control-label">返回值</label>
                                                                                <div id="get_res_body"  name="res_body" columwidthclass="col-xs-9" class="form-control" contenteditable="true" style="height: 258px;padding: 0px;"></div>
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
                                                <a data-toggle="collapse"
                                                   href="#collapseTwo">
                                                    检查点信息
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body" >
                                        <c:if test="${result.checkPointList!=null}">
                                            <c:forEach items="${result.checkPointList}" var="checkPoint"  varStatus="index">
                                                <div class="col-xs-9">
                                                    <div id="userCheckReq">
                                                        <div class="form-group">
                                                            <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配内容</label>
                                                            <div class="col-xs-3">
                                                                <input type="text"  name="checkPointList[${index.index}].checkValue" class="form-control" disabled="disabled" value="${fn:escapeXml(checkPoint.checkValue)}"/>
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
                                                   href="#collapseFour">
                                                    数据库检查点
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseFour" class="panel-collapse collapse">
                                    <div class="panel-body" id="userSqlCheck">
                                        <c:if test="${result.dataCheckList!=null}">
                                            <c:forEach items="${result.dataCheckList}" var="dataCheck">
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
                                                                <textarea   class="form-control" rows="3" disabled="disabled">${dataCheck.wheres}</textarea>
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
                                                                <input class="form-control" disabled="disabled" value="${dataCheck.checkValue}"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">
                                                    <div id="userSqlRes">
                                                        <div class="form-group">
                                                            <label class="from-control col-xs-4 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">结果内容</label>
                                                            <div class="col-xs-4">
                                                                <input type="text" class="form-control" value="${dataCheck.columns}" disabled="disabled"/>
                                                            </div>
                                                            <div class="col-xs-4">
                                                                <c:choose>
                                                                    <c:when test="${dataCheck.result}">
                                                                        <a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color:red">失败</a>
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
                                                   href="#collapseThree">
                                                    提取器信息
                                                </a>
                                            </h1>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body" id="userDataFetch">
                                        <c:if test="${result.dataFetchList!=null}">
                                            <c:forEach items="${result.dataFetchList}" var="dataFetch"  varStatus="index">
                                                <div class="col-xs-9">
                                                    <div id="userFetchReq">
                                                        <div class="form-group" id="userFetch${index.index}" name="userFetch" >
                                                            <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取名称</label>
                                                            <div class="col-xs-2">
                                                                <input type="text"   class="form-control" disabled="disabled" value="${dataFetch.paramName}"/>
                                                            </div>
                                                            <label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">表达式</label>
                                                            <div class="col-xs-4">
                                                                <input type="text"   class="form-control" disabled="disabled" value="${fn:escapeXml(dataFetch.regular)}"/>
                                                            </div>
                                                            <label class="col-xs-2 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取索引</label>
                                                            <div class="col-xs-2">
                                                                <input type="text" class="form-control" value="${dataFetch.regularIndex}" disabled="disabled"/>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-xs-3">
                                                    <div id="userFetchRes">
                                                        <div class="form-group"  name="userCheckRes" >
                                                            <label class="from-control col-xs-5 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取结果</label>
                                                            <div class="col-xs-7">
                                                                <input type="text"  name="fetchResult" class="form-control" value="${dataFetch.result}" disabled="disabled"/>
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
            //将接口执行结果json格式化
            var type = '${result.type}';//接口类型
            var resBody = '${result.resBody}';//接口执行结果
            var fomatValue = {
                dom: '',
                isCollapsible: true,
                quoteKeys: true,
                tabSize: 1
            };
            var collection = "";
            if(type==1){
                collection = '#jsf_res_body';
            }else if(type==2){
                collection = '#post_res_body';
            }else if(type==3){
                collection = '#get_res_body';
            }
            fomatValue.dom=collection;
            window.jfp = new JsonFormater(fomatValue)
            $(''+collection+'').val(jfp.doFormat(resBody));
        })
    </script>
</footer>
</body>
</html>

