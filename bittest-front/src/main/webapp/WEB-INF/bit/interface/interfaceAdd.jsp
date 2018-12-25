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
    <script src="/static/js/nomalJsonFormat.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>接口添加</title>
    <style type="text/css">
        pre{
            background-color: white;
        }
    </style>
    <meta charset="UTF-8"/>
</head>
<body>
<form id="jsfForm" onsubmit="return false;" class="form-bordered" role="form">
    <input  type="hidden" id="interfaceType" name="interfaceType" value="1"/>
    <input  type="hidden" id="caseId" name="caseId" value="${interfaceReqVo.caseId}"/>
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
                                                <li class="active"><a id="1" href="#JSF" >dubbo 请求</a></li>
                                                <li><a id="2" href="#POST" >HTTP POST</a></li>
                                                <li><a id="3" href="#GET" >HTTP GET</a></li>
                                            </ul>
                                            <div class="tab-content">
                                                <div class="tab-pane active" id="JSF">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="box-body">
                                                                <div class="form-group">
                                                                    <label class="col-xs-1 control-label">接口名</label>
                                                                    <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" class="form-control" columWidthClass="col-xs-6" placeholder="添加接口后的展示名称" />
                                                                    <button type="submit" id="jsfResolve" class="btn btn-success">解析接口</button>&nbsp;&nbsp;
                                                                    <button type="submit" disabled="disabled" id="jsfRequest" class="btn btn-info">执行接口</button>&nbsp;&nbsp;
                                                                    <button type="button"  id="jsfBodyFormart" class="btn btn-info">参数JSON格式化</button>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-xs-1 control-label">地址</label>
                                                                    <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-11" placeholder="如:com.bit.xxx.export.xxResource"/>

                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-xs-1 control-label">别名</label>
                                                                    <select id="jsf_alias" name="jsf_alias" class="test_box form-control" columWidthClass="col-xs-5" onchange="getIps();">
                                                                    </select>
                                                                    <label class="col-xs-1 control-label">token</label>
                                                                    <input type="text" id="jsf_token" name="jsf_token" class="form-control" columWidthClass="col-xs-5" />
                                                                </div>
                                                                <div class="form-group">
                                                                    <label class="col-xs-1 control-label">方法名</label>
                                                                    <select id="jsf_method" name="jsf_method" class="form-control"  columWidthClass="col-xs-5">
                                                                    </select>
                                                                    <label class="col-xs-1 control-label">IP地址</label>
                                                                    <select id="jsf_ip" name="jsf_ip" class="form-control" columWidthClass="col-xs-5" onchange="getMethods();">
                                                                    </select>
                                                                </div>
                                                                <%--<div class="form-group">--%>
                                                                    <%--<label class="col-xs-1 control-label">请求内容</label>--%>
                                                                    <%--<textarea id="jsf_body" name="jsf_body" class="form-control" rows="14" columWidthClass="col-xs-9"></textarea>--%>
                                                                <%--</div>--%>
                                                                <div class="form-group">
                                                                    <label class="col-xs-1 control-label">请求值</label>
                                                                    <textarea  id="jsf_body" name="jsf_body" class="form-control" rows="16" columWidthClass="col-xs-5"></textarea>
                                                                    <label class="col-xs-1 control-label">返回值</label>
                                                                    <div id="jsf_res_body"  name="jsf_res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                                    <%--<textarea  id="jsf_res_body" class="form-control" rows="14" columWidthClass="col-xs-5"></textarea>--%>
                                                                </div>
                                                            </div><!-- /.box-body -->
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- /.tab-pane -->
                                                <div class="tab-pane" id="POST">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="box-body">
                                                                <div class="col-xs-12" id="postHead">
                                                                    <div class="form-group" id="post_head0" name="post_head">
                                                                        <label class="col-xs-1 control-label">head</label>
                                                                        <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value="Content-Type"/>
                                                                        <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="application/json" />
                                                                        <button type="button"  class="btn btn-warning btn-xs" onclick="addPostHead();">添加</button>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">接口名</label>
                                                                        <input type="text" id="post_interfaceName" name="post_interfaceName" class="form-control" columWidthClass="col-xs-6" placeholder="添加接口后的展示名称"/>
                                                                        <button type="submit" id="postRequest" class="btn btn-info">执行接口</button>&nbsp;&nbsp;
                                                                        <button type="button"  id="postBodyFormart" class="btn btn-info">参数JSON格式化</button>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">地址</label>
                                                                        <input type="text" id="post_url" name="post_url" class="form-control" columWidthClass="col-xs-11" placeholder="请输入url地址信息"/>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">请求值</label>
                                                                        <textarea id="post_body" name="post_body" class="form-control" rows="16" columWidthClass="col-xs-5"></textarea>
                                                                        <label class="col-xs-1 control-label">返回值</label>
                                                                        <div id="post_res_body"  name="post_res_body" columwidthclass="col-xs-5" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                                        <%--<textarea id="post_res_body" class="form-control" rows="14" columWidthClass="col-xs-5"></textarea>--%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--<div class="col-xs-6">--%>
                                                            <%--<p><b>接口返回信息</b></p>--%>
                                                            <%--<div class="box-body">--%>
                                                                <%--<div class="form-group">--%>
                                                                    <%--<label class="col-xs-3">请求内容</label>--%>
                                                                    <%--<textarea id="post_req_body" class="form-control" rows="6" columWidthClass="col-xs-8"></textarea>--%>
                                                                <%--</div>--%>

                                                            <%--</div>--%>
                                                        <%--</div>--%>
                                                    </div>
                                                </div>
                                                <!-- /.tab-pane -->
                                                <div class="tab-pane" id="GET">
                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <div class="box-body">
                                                                <div class="col-xs-12" id="getHead">
                                                                    <div class="form-group" id="get_head0" name="get_head">
                                                                        <label class="col-xs-1 control-label">head</label>
                                                                        <input type="text"  name="headKey" class="form-control" columWidthClass="col-xs-3" placeholder="key" value=""/>
                                                                        <input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="" />
                                                                        <button type="button"  class="btn btn-warning btn-xs" onclick="addGetHead();">添加</button>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">接口名</label>
                                                                        <input type="text" id="get_interfaceName" name="get_interfaceName" class="form-control" columWidthClass="col-xs-6" placeholder="添加接口后的展示名称"/>
                                                                        <button type="submit" id="getRequest" class="btn btn-info">执行接口</button>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">地址</label>
                                                                        <input type="text" id="gett_url" name="get_url" class="form-control" columWidthClass="col-xs-9" placeholder="请输入url地址信息"/>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <label class="col-xs-1 control-label">返回值</label>
                                                                        <div id="get_res_body"  name="get_res_body" columwidthclass="col-xs-9" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                                        <%--<textarea id="get_res_body" class="form-control" rows="16" columWidthClass="col-xs-9"></textarea>--%>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <%--<div class="col-xs-6">--%>
                                                            <%--<div class="box-body">--%>
                                                                <%--<div class="form-group">--%>
                                                                    <%--<label class="col-xs-3">返回状态</label>--%>
                                                                    <%--<input type="text" id="get_res_code"  class="form-control" columWidthClass="col-xs-8"/>--%>
                                                                <%--</div>--%>
                                                                <%--<div class="form-group">--%>
                                                                    <%--<label class="col-xs-3">请求内容</label>--%>
                                                                    <%--<textarea id="get_req_body" class="form-control" rows="6" columWidthClass="col-xs-8"></textarea>--%>
                                                                <%--</div>--%>
                                                                <%--<div class="form-group">--%>
                                                                <%--</div>--%>
                                                            <%--</div>--%>
                                                        <%--</div>--%>
                                                    </div>
                                                </div>
                                                <!-- /.tab-pane -->
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
                                        <div class="col-xs-6 text-right">
                                            <button type="button" class="btn btn-warning btn-xs" onclick="addCheck();">添加</button>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseTwo" class="panel-collapse collapse">
                                    <div class="panel-body" >
                                        <div class="col-xs-9">
                                            <div id="userCheckReq">

                                                <%--用户动态添加--%>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div id="userCheckRes">
                                                <%--执行后根据结果动态添加--%>
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
                                        <div class="col-xs-6 text-right">
                                            <button type="button" class="btn btn-warning btn-xs" onclick="addSqlCheck();">添加</button>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseFour" class="panel-collapse collapse">
                                    <div class="panel-body" id="userSqlCheck">
                                        <div class="col-xs-9">
                                            <div id="userSqlReq">
                                                <%--执行后根据结果动态添加--%>
                                            </div>
                                        </div>
                                        <%--用户动态添加--%>
                                        <div class="col-xs-3">
                                            <div id="userSqlRes">
                                                <%--执行后根据结果动态添加--%>

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
                                        <div class="col-xs-6 text-right">
                                            <button type="button" class="btn btn-warning btn-xs" onclick="addFetch();">添加</button>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapseThree" class="panel-collapse collapse">
                                    <div class="panel-body" id="userDataFetch">
                                        <div class="col-xs-9">
                                            <div id="userFetchReq">
                                                <%--执行后根据结果动态添加--%>

                                            </div>
                                            <%--用户动态添加--%>
                                        </div>
                                        <div class="col-xs-3">
                                            <div id="userFetchRes">
                                                <%--执行后根据结果动态添加--%>

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
                <button type="submit" id="btnSave" class="btn btn-info pull-right btn-submit">保&nbsp;&nbsp;存</button>
            </div><!-- /.box-footer -->
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
</form>
<!-- /.row -->

<footer>
    <script type="text/javascript">
        var me = commonErp.getInstance()

        $(document).ready(function(){
            $(function () { $('#collapseOne').collapse('show')});
            $(function () { $('#collapseTwo').collapse('hide')});
            $(function () { $('#collapseThree').collapse('hide')});
            $(function () { $('#collapseFour').collapse('hide')});

            $("#nav a").on("click",function(e){
                e.preventDefault();
                $("#interfaceType").attr("value",this.id);
                $(this).tab("show");
            });
            //jsf 入参格式化
            $("#jsfBodyFormart").on("click",function(){
                var json = $("#jsf_body").val();
                var formatjson = formatJson(json,"");
                if(formatjson!='formatError'){
                    $("#jsf_body").val(formatjson);
                }else{
                    alertBootbox("请输入正确的json格式");
                }
            });
            //post 入参格式化
            $("#postBodyFormart").on("click",function(){
                var json = $("#post_body").val();
                var formatjson = formatJson(json,"");
                if(formatjson!='formatError'){
                    $("#post_body").val(formatjson);
                }else{
                    alertBootbox("请输入正确的json格式");
                }
            });
        })
        var fomatValue = {
            dom: '',
            isCollapsible: true,
            quoteKeys: true,
            tabSize: 1
        };
        //jsf接口解析验证
        var vaildatorResolveJsf={
            submitButtons:$("#jsfResolve"),//触发按钮
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                jsf_interfaceName: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口名不能为空'
                        }
                    }
                },
                jsf_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '地址不能为空'
                        }
                    }
                }
            }
        };
        //接口执行验证
        var vaildatorRequestJsf={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                jsf_interfaceName: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口名不能为空'
                        }
                    }
                },
                jsf_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '地址不能为空'
                        }
                    }
                },
                jsf_alias: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口别名不能为空'
                        }
                    }
                },
                jsf_method: {
                    validators: {
                        notEmpty: {
                            message: '调用方法名不能为空'
                        },
                    }
                },
                jsf_ip: {
                    validators: {
                        notEmpty: {
                            message: 'IP地址不能为空'
                        },
                    }
                },
            }
        };

        //post接口解析验证
        var vaildatorRequestPost={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                post_interfaceName: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口名不能为空'
                        }
                    }
                },
                post_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: 'url请求地址不能为空'
                        }
                    }
                }
            }
        };

        //get接口解析验证
        var vaildatorRequestGet={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                get_interfaceName: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口名不能为空'
                        }
                    }
                },
                get_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: 'url请求地址不能为空'
                        }
                    }
                }
            }
        };

        //jsf接口解析
        $("#jsfResolve").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorResolveJsf); //vaildatorRule 验证规则
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator');
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                jsfResolveSubmit();
            }
        });

        //jsf 解析异步提交表单
        function jsfResolveSubmit(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/jsfResolveAlias',jsfResolveCallBack,"");
        }
        //jsf 解析异步回调 展示
        function jsfResolveCallBack(data){
            $("#jsfForm").data('bootstrapValidator').destroy();
            $("#jsf_alias").empty();
            if(data.code == "000000"){
                //jsf alias ip服务信息
                alias = data.value.alias;
                for(var i=0;i<alias.length;i++){
                    $("#jsf_alias").append("<option value='"+alias[i]+"'>"+alias[i]+"</option>");
                }
                getIps();
                $("#jsfRequest").removeAttr("disabled");
            }else{
                $('#jsfRequest').attr("disabled","true");
                alertBootbox(data.message);
                return;
            }
        }

        function getIps(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/jsfResolveIps',getIpsCallBack,"");
        }

        function getIpsCallBack(data){
            if(data.code=="000000"){
                var alias = $("#jsf_alias").val();
                $("#jsf_ip").empty();
                var ips = data.value.ips;
                for(var i=0;i<ips.length;i++) {
                    $("#jsf_ip").append("<option value='" + ips[i] + "'>" + ips[i] + "</option>");
                }
                getMethods();
            }
        }

        function getMethods(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/jsfResolveGetMethods',getMethodsCallBack,"");
        }

        function getMethodsCallBack(data){
            $("#jsf_method").empty();
            if(data.code=="000000"){
                var methods = data.value.methods;
                for(var i=0;i<methods.length;i++){
                    $("#jsf_method").append("<option value='"+methods[i]+"'>"+methods[i]+"</option>");
                }
            }else{
                alertBootbox(data.message);
            }
        }


        $("#jsfRequest").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorRequestJsf); //vaildatorRule 验证规则
            addValidate();
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator');
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                jsfRequestSubmit();
            }
        });

        function jsfRequestSubmit(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/jsfRequest',jsfRequestCallBack,"");
        }
        function jsfRequestCallBack(data){
            destroyValidate();
            if(data.value.reqType==1){
                $("#jsf_res_code").empty();
                $("#jsf_res_body").empty();
                $("#jsf_req_body").empty();
            }else if(data.value.reqType==2){
                $("#post_res_code").empty();
                $("#post_req_body").empty();
                $("#post_res_body").empty();
            }else if(data.value.reqType==3){
                $("#get_res_code").empty();
                $("#get_res_body").empty();
            }
            $("#userCheckRes").empty();
            $("#userFetchRes").empty();
            $("#userSqlRes").empty();
            if(data.code == "000000"){
                if(data.value.reqType==1){
                    //返回报文体
                    fomatValue.dom='#jsf_res_body';
                    window.jfp = new JsonFormater(fomatValue);
                    $("#jsf_res_body").val(jfp.doFormat(data.value.resBody));
//                    $("#jsf_res_body").val(data.value.resBody);
                    //jsf返回执行编码
                    $("#jsf_req_body").val(data.value.reqBody);
                }else if(data.value.reqType==2){
                    $("#post_req_body").val(data.value.reqBody);
                    fomatValue.dom='#post_res_body';
                    window.jfp = new JsonFormater(fomatValue);
                    $("#post_res_body").val(jfp.doFormat(data.value.resBody));
                    $("#post_res_body").val(data.value.resBody);
                }else if(data.value.reqType==3){
                    $("#get_req_body").val(data.value.reqBody);
                    fomatValue.dom='#get_res_body';
                    window.jfp = new JsonFormater(fomatValue);
                    $("#get_res_body").val(jfp.doFormat(data.value.resBody));
                }

                var check = data.value.checkPointList;
                var fetch = data.value.dataFetchList;
                var dataCheck = data.value.dataCheckList;
                var checkResult="";
                if(check!=undefined&&check!='null'){
                    for(var i=0;i<check.length;i++){
                        checkResult +='<div class="form-group"  name="userCheckRes" >';
                        checkResult +='<div class="col-xs-4"><label class="from-control control-label">匹配结果</label></div><div class="col-xs-8">';
                        if(check[i].result){
                            checkResult +='<a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>';
                        }else{
                            checkResult +='<a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color:red">失败</a>';
                        }
                        checkResult +='</div></div>'
                    }
                    $("#userCheckRes").append(checkResult);
                }
                if(fetch!=undefined&&fetch!='null') {
                    var fetchResult = "";
                    for (var i = 0; i < fetch.length; i++) {
                        fetchResult += '<div class="form-group"  name="userCheckRes" >';
                        fetchResult += '<label class="from-control col-xs-4 control-label">提取结果</label>';
                        fetchResult += '<div class="col-xs-8">';
                        alert(fetch[i].result);
                        fetchResult += '<input type="text"  name="fetchResult" class="form-control" value="' + fetch[i].result + '"/>';
                        fetchResult += '</div></div>'
                    }
                    $("#userFetchRes").append(fetchResult);
                }
                if(dataCheck!=undefined&&dataCheck!='null') {
                    var dataResult = "";
                    for (var i = 0; i < dataCheck.length; i++) {
                        dataResult += '<div class="form-group"  name="dataCheckRes" >';
                        dataResult += '<label class="from-control col-xs-4 control-label">结果内容</label>';
                        dataResult += '<div class="col-xs-4">';
                        dataResult += '<input type="text"  name="dataCheckResult" class="form-control" value="' + dataCheck[i].columns + '"/>';
                        dataResult += '</div><div class="col-xs-4">';
                        if (dataCheck[i].result) {
                            dataResult += '<a href="#" class="btn btn-success  btn-block disabled" role="button" style="background-color:green;">通过</a>';
                        } else {
                            dataResult += '<a href="#" class="btn btn-danger  btn-block disabled" role="button" style="background-color:red">失败</a>';
                        }
                        dataResult += '</div></div>';
                    }
                    $("#userSqlRes").append(dataResult);
                }
            }else{
                alertBootbox(data.message);
                return;
            };
        }

        $("#postRequest").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorRequestPost); //vaildatorRule 验证规则
            changePostHead();
            addValidate();
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator');
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                jsfRequestSubmit();
            }
        });

        $("#getRequest").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorRequestGet); //vaildatorRule 验证规则
            changeGetHead();
            addValidate();
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator');
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                jsfRequestSubmit();
            }
        });

        //添加post信息头
        function addPostHead(){
            var index = $('div[name="post_head"]').length;
            var info = '<div class="form-group" id="post_head'+index+'" name="post_head">'
                    +'<label class="col-xs-1 control-label"></label>'
                    +'<div class="col-xs-3"><input type="text"  name="headKey" class="form-control" placeholder="key" value=""/></div>'
                    +'<div class="col-xs-3"><input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="" /></div>'
                    +'<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>'
                    +'</div>';
            $("#postHead").append(info);
            $(":button[name=removeDiv]").unbind("click");
            $(":button[name=removeDiv]").on("click",function(){
                removeInfo($(this));
            });
        }

        //添加get信息头
        function addGetHead(){
            var index = $('div[name="get_head"]').length;
            var info = '<div class="form-group" id="get_head'+index+'" name="get_head">'
                    +'<label class="col-xs-1 control-label"></label>'
                    +'<div class="col-xs-3"><input type="text"  name="headKey" class="form-control" placeholder="key" value=""/></div>'
                    +'<div class="col-xs-3"><input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="" /></div>'
                    +'<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>'
                    +'</div>';
            $("#getHead").append(info);
            $(":button[name=removeDiv]").unbind("click");
            $(":button[name=removeDiv]").on("click",function(){
                removeInfo($(this));
            });
        }

        function changeGetHead(){
            $("div[name=get_head]").each(function(){
                $(this).children().find("input").eq(1).attr("name","get_head["+$(this).children().find("input").eq(0).val()+"]");
            });
        }

        function changePostHead(){
            $("div[name=post_head]").each(function(){
                $(this).children().find("input").eq(1).attr("name","post_head["+$(this).children().find("input").eq(0).val()+"]");
            });
        }

        //添加提取器
        function addFetch(){
            $('#collapseThree').collapse('show');
            var index = $('div[name="userFetch"]').length;
            var info='<div class="form-group" id="userFetch'+index+'" name="userFetch" >'
                    +'<label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取名称</label>'
                    +'<div class="col-xs-2">'
                    +'<input type="text"  name="dataFetchList['+index+'].paramName" class="form-control" placeholder="提取内容后的变量名称"/>'
                    +'</div>'
                    +'<label class="col-xs-1 control-label" style="width:50px;padding-left:0px;padding-right:0px;padding-top: 7px;">表达式</label>'
                    +'<div class="col-xs-4">'
                    +'<input type="text"  name="dataFetchList['+index+'].regular" class="form-control" placeholder="请输入正则表达式信息"/>'
                    +'</div>'
                    +'<label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">提取索引</label>'
                    +'<div class="col-xs-2">'
                    +'<input type="text"  name="dataFetchList['+index+'].regularIndex" class="form-control" value="1"/>'
                    +'</div>'
                    +'<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>'
                    +'</div>';
            $("#userFetchReq").append(info);
            $(":button[name=removeDiv]").unbind("click");
            $(":button[name=removeDiv]").on("click",function(){
                removeInfo($(this));
            });
        }

        //添加检查点
        function addCheck(){
            $('#collapseTwo').collapse('show');
            var index = $('div[name="userCheck"]').length;
            var info='<div class="form-group" id="userCheck'+index+'" name="userCheck" >'
                    +'<label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配内容</label>'
                    +'<div class="col-xs-4">'
                    +'<input type="text"  name="checkPointList['+index+'].checkValue" class="form-control" placeholder="请输入内容与接口返回结果匹配"/>'
                    +'</div>'
                    +'<label class="col-xs-1 control-label" style="width:70px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配规则</label>'
                    +'<div class="col-xs-4">'
                    +'<select name="checkPointList['+index+'].checkMethod" class="form-control">'
                    +'<option value="1">包含</option>'
                    +'<option value="2">不包含</option>'
                    +'<option value="3">等于</option>'
                    +'</select>'
                    +'<input type="hidden"  name="checkPointList['+index+'].checkType" value="1" />'
                    +'</div>'
                    +'<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>'
                    +'</div>';
            $("#userCheckReq").append(info);
            $(":button[name=removeDiv]").unbind("click");
            $(":button[name=removeDiv]").on("click",function(){
                removeInfo($(this));
            });
        }

        function addSqlCheckInfo(database){
            $('#collapseFour').collapse('show');
            var index = $('div[name="userDataCheck"]').length;
            var select = '<select class="form-control" name="dataCheckList['+index+'].databaseId">';
            for(var i=0;i<database.length;i++){
                select +='<option value="'+database[i].databaseId+'">'+database[i].name+'</option>';
            }
            select +='</select>';
            var sqlCheck = '<div class="form-group" id="userDataCheck'+index+'" name="userDataCheck">'
                +'<label class="col-xs-1 control-label" style="width:40px;padding-left:0px;padding-right:0px;padding-top: 7px;">数据库</label>'
                +'<div class="col-xs-2">';
                sqlCheck +=select+'</div>'
                +'<label class="col-xs-1 control-label" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">select</label>'
                +'<div class="col-xs-3">'
                +'<textarea   class="form-control" rows="3" name="dataCheckList['+index+'].wheres" placeholder="请输入select之后的查询语句(单列查询)"></textarea>'
                +'</div>'
                +'<label class="col-xs-1" style="width:30px;padding-left:0px;padding-right:0px;padding-top: 7px;">规则</label>'
                +'<div class="col-xs-2">'
                +'<select name="dataCheckList['+index+'].checkMethod" class="form-control">'
                +'<option value="1">包含</option>'
                +'<option value="2">不包含</option>'
                +'<option value="3">等于</option>'
                +'</select>'
                +'</div>'
                +'<label class="col-xs-1" style="width:40px;padding-left:0px;padding-right:0px;padding-top: 7px;">匹配值</label>'
                +'<div class="col-xs-2">'
                +'<input class="form-control" name="dataCheckList['+index+'].checkValue"  placeholder="匹配的内容" />'
                +'</div>'
                +'<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>'
                +'<input type="hidden"  name="dataCheckList['+index+'].checkType" value="2" />'
                +'</div>';
            $("#userSqlReq").append(sqlCheck);
            $(":button[name=removeDiv]").unbind("click");
            $(":button[name=removeDiv]").on("click",function(){
                removeInfo($(this));
            });
        }

        //添加数据库检查点（异步查询用户数据库信息）
        function addSqlCheck(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/database/queryUserDataBase',queryDataBaseCallBack,"");
        }
        //查询用户数据库信息回调
        function queryDataBaseCallBack(data){
            if(data.code="000000"){
                addSqlCheckInfo(data.list);
            }else{
                alertBootbox("获取数据库信息失败");
            }
        }

        //删除检查点
        function removeInfo(divId) {
            destroyValidate();
            var id = divId.parent("div").attr("id");
            var temName = $("#"+id+"").attr("name");
            $("#"+id+"").remove();
            if(temName=="userCheck"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                    $(this).children().find("input").eq(0).attr("name","checkPointList["+index+"].checkValue");
                    $(this).children().find("select").eq(0).attr("name","checkPointList["+index+"].checkMethod");
                    $(this).children().find("input").eq(1).attr("name","checkPointList["+index+"].checkType");
                });
            }else if(temName=="userFetch"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                    $(this).children().find("input").eq(0).attr("name","dataFetchList["+index+"].paramName");
                    $(this).children().find("input").eq(1).attr("name","dataFetchList["+index+"].regular");
                    $(this).children().find("input").eq(2).attr("name","checkPointList["+index+"].regularIndex");
                });
            }else if(temName=="userDataCheck"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                    $(this).children().find("select").eq(0).attr("name","dataCheckList["+index+"].databaseId");
                    $(this).children().find("select").eq(1).attr("name","dataCheckList["+index+"].checkMethod");
                    $(this).children().find("input").eq(0).attr("name","dataCheckList["+index+"].wheres");
                    $(this).children().find("input").eq(1).attr("name","dataCheckList["+index+"].checkValue");
                    $(this).children().find("input").eq(2).attr("name","dataCheckList["+index+"].checkType");
                });
            }else if(temName=="post_head"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                });
            }else if(temName=="get_head"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                });
            }

        }

        function addValidate(){
            $("div[name=userCheck]").each(function(index,item){
                var checkValue = $(this).children().find("input").eq(0).attr("name");
                $("#jsfForm").data('bootstrapValidator').addField(checkValue,{
                    validators: {
                        notEmpty: {
                            message: '匹配内容不能为空'
                        }
                    }
                });
            });

            $("div[name=userFetch]").each(function(index,item){
                var fetchName = $(this).children().find("input").eq(0).attr("name");
                var fetchReg = $(this).children().find("input").eq(1).attr("name");
                var regIndex = $(this).children().find("input").eq(2).attr("name");
                $("#jsfForm").data('bootstrapValidator').addField(fetchName,{
                    validators: {
                        notEmpty: {
                            message: '提取名称不能为空'
                        }
                    }
                });
                $("#jsfForm").data('bootstrapValidator').addField(fetchReg,{
                    validators: {
                        notEmpty: {
                            message: '提取规则不能为空'
                        }
                    }
                });
                $("#jsfForm").data('bootstrapValidator').addField(regIndex,{
                    validators: {
                        notEmpty: {
                            message: '提取索引不能为空'
                        },
                        numeric: {
                            message: '请输入数字类型'
                        }
                    }
                });
            });

            $("div[name=userDataCheck]").each(function(index,item){
                var sql = $(this).children().find("textarea").eq(0).attr("name");
                var checkValue = $(this).children().find("input").eq(0).attr("name");
                $("#jsfForm").data('bootstrapValidator').addField(sql,{
                    validators: {
                        notEmpty: {
                            message: '查询语句不能为空'
                        }
                    }
                });
                $("#jsfForm").data('bootstrapValidator').addField(checkValue,{
                    validators: {
                        notEmpty: {
                            message: '匹配值不能为空'
                        }
                    }
                });
            });

        }

        $("#btnSave").on("click", function(){
            destroyValidate();
            //初始化验证插件
            var tp = $("#interfaceType").val();
            if(tp==1){
                $("#jsfForm").bootstrapValidator(vaildatorRequestJsf); //vaildatorRule 验证规则
            }else if(tp==2){
                $("#jsfForm").bootstrapValidator(vaildatorRequestPost); //vaildatorRule 验证规则
            }else if(tp==3){
                $("#jsfForm").bootstrapValidator(vaildatorRequestGet); //vaildatorRule 验证规则
            }else{
                alertBootbox("不存在该接口类型");
                return;
            }
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator')
            addValidate();
            changeGetHead();
            changePostHead();
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                saveSubmit();
            }
        });

        function saveSubmit(){
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/interfaceAdd',submitCallBack,"");
        }

        function submitCallBack(data){

            if(data.code=="000000"){
                window.parent.closeBootbox();
            }else{
                alertBootbox("保存接口信息失败，请联系管理员！");
                return;
            }
            parent.query();
        }


        function destroyValidate(){
            var vali = $("#jsfForm").data('bootstrapValidator');
            if(undefined!=vali){
                vali.destroy();
            }
        }
    </script>
</footer>
</body></html>

