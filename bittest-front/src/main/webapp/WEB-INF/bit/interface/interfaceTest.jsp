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
        p{
             word-wrap: break-word;
             word-break: break-all;
             overflow: hidden;
             width: 150px;
         }
        pre{
            background-color: white;
        }
    </style>
    <meta charset="UTF-8"/>
</head>
<body class="sidebar-mini wysihtml5-supported skin-red-light control-sidebar-open" style="height: auto; min-height: 100%;">
    <input  type="hidden" id="queryType" name="queryType" value="col"/>
        <div class="col-xs-12" style="height: 100%">
            <form id="jsfForm" onsubmit="return false;" class="form-bordered" role="form">
                <input  type="hidden" id="interfaceType" name="interfaceType" value="1"/>
                <input  type="hidden" id="interfaceId" name="interfaceId"/>
                <div class="form-horizontal">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="nav-tabs-custom">
                            <ul id="nav" class="nav nav-tabs">
                                <li class="active"><a id="1" href="#JSF" >dubbo 请求</a></li>
                                <li><a id="2" href="#POST" >HTTP POST</a></li>
                                <li><a id="3" href="#GET" >HTTP GET</a></li>
                            </ul>
                            <div class="tab-content">
                                <div class="tab-pane active" id="JSF">
                                    <div class="row">
                                        <div class="col-xs-11">
                                            <div class="box-body">
                                                <div class="form-group">
                                                    <label class="col-xs-1 control-label">接口名</label>
                                                    <input type="text" id="jsf_interfaceName" name="jsf_interfaceName" class="form-control" columWidthClass="col-xs-5" placeholder="添加接口后的展示名称" />
                                                    <button type="submit" id="jsfResolve" class="btn btn-success">解析接口</button>&nbsp;&nbsp;
                                                    <button type="submit" disabled="disabled" id="jsfRequest" class="btn btn-info">执行接口</button>&nbsp;&nbsp;
                                                    <button type="button"  id="jsfBodyFormart" class="btn btn-info">参数JSON格式化</button>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-xs-1 control-label">地址</label>
                                                    <input type="text" id="jsf_url" name="jsf_url" class="form-control" columWidthClass="col-xs-8" placeholder="如:com.bit.xxx.export.xxResource"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-xs-1 control-label">别名</label>
                                                    <select id="jsf_alias" name="jsf_alias" class="form-control" onchange="getIps();" columWidthClass="col-xs-4">
                                                    </select>
                                                    <label class="col-xs-1 control-label" >token</label>
                                                    <input type="text" id="jsf_token" name="jsf_token" class="form-control"  columWidthClass="col-xs-4"/>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-xs-1 control-label">方法名</label>
                                                    <select id="jsf_method" name="jsf_method" class="form-control" columWidthClass="col-xs-4">
                                                    </select>
                                                    <label class="col-xs-1 control-label">IP地址</label>
                                                    <select id="jsf_ip" name="jsf_ip" class="form-control" onchange="getMethods();" columWidthClass="col-xs-4">
                                                    </select>
                                                </div>
                                                <%--<div class="form-group">--%>

                                                <%--</div>--%>
                                                <div class="form-group">
                                                    <label class="col-xs-1 control-label">请求值</label>
                                                    <textarea id="jsf_body" name="jsf_body" rows="16" columWidthClass="col-xs-4" class="form-control"></textarea>
                                                    <label class="col-xs-1 control-label">返回值</label>
                                                    <%--<textarea id="jsf_res_body" rows="16" class="form-control" columWidthClass="col-xs-4"></textarea>--%>
                                                    <div id="jsf_res_body"  name="res_body" columwidthclass="col-xs-4" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                </div>
                                            </div><!-- /.box-body -->
                                        </div>
                                    </div>
                                </div>
                                <!-- /.tab-pane -->
                                <div class="tab-pane" id="POST">
                                    <div class="row">
                                        <div class="col-xs-11">
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
                                                        <input type="text" id="post_url" name="post_url" class="form-control" columWidthClass="col-xs-8" placeholder="请输入url地址信息"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-xs-1 control-label">请求值</label>
                                                        <textarea id="post_body" name="post_body" class="form-control" rows="16" columWidthClass="col-xs-4"></textarea>
                                                        <label class="col-xs-1 control-label">返回值</label>
                                                        <div id="post_res_body"  name="res_body" columwidthclass="col-xs-4" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                        <%--<textarea id="post_res_body"  rows="16" class="form-control" columWidthClass="col-xs-4"></textarea>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- /.tab-pane -->
                                <div class="tab-pane" id="GET">
                                    <div class="row">
                                        <div class="col-xs-11">
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
                                                        <input type="text" id="get_url" name="get_url" class="form-control" columWidthClass="col-xs-8" placeholder="请输入url地址信息"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-xs-1 control-label">返回值</label>
                                                        <div id="get_res_body"  name="res_body" columwidthclass="col-xs-8" class="form-control" contenteditable="true" style="height: 294px;padding: 0px;"></div>
                                                        <%--<textarea id="get_res_body" rows="16" class="form-control" columWidthClass="col-xs-8"></textarea>--%>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%--<div class="box-body col-xs-8">--%>
                                <%--<div class="form-group">--%>
                                    <%--<label class="col-xs-3">返回内容</label>--%>
                                    <%--<textarea id="res_body" class="form-control" rows="18" columWidthClass="col-xs-4"></textarea>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        </div>
                    </div>
                    <!-- /.col -->
                </div>
            </div>
            </form>
            <div class="col-xs-10 text-center">
                <div class="btn-group">
                    <button type="submit" id="btnSave" class="btn btn-info">保&nbsp;&nbsp;存</button>
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu pull-right" style="min-width: 95px;">
                        <li><a href="javascript:saveAs();">另存为</a></li>
                    </ul>
                    </div>
                    <button type="button" id="btnSaveCase" onclick="toSaveCase();" class="btn btn-info btn-submit">保存到用例</button>
                    <button type="button" id="btmCancel" class="btn btn-danger" onclick="javascript:resetInfo();">重&nbsp;&nbsp;置</button>
                </div>
            </div><!-- /.box-footer -->
            <aside class="control-sidebar control-sidebar-open control-sidebar-light" style="padding-top: 10px;width: 270px;height: 100%">
                <!-- Create the tabs -->
                <form id="queryForm" class="form-bordered" >
                    <div class="input-group" style="margin: 0px 5px 10px 5px;">
                        <input type="text" id="search"  name="search" class="form-control" style="height: 35px" placeholder="Search..." onchange="queryInterface();">
                        <span class="input-group-btn">
                            <button type="button" name="search" id="search-btn" class="btn btn-flat" style="height: 35px" onclick="queryInterface();">
                              <i class="fa fa-search"></i>
                            </button>
                        </span>
                    </div>
                </form>
                <ul class="nav nav-tabs nav-justified control-sidebar-tabs" id="queryTab">
                    <li class="active"><a href="#control-sidebar-interface" id="col"  data-toggle="tab"  aria-expanded="true"><H7><B>收藏接口</B></H7></a></li>
                    <li class=""><a href="#control-sidebar-history" id="his" data-toggle="tab"  aria-expanded="false"><H7><B>历史记录</B></H7></a></li>
                </ul>
                <!-- Tab panes -->
                <div class="tab-content" style="height: 84%">
                    <!-- Home tab content -->
                    <div id="control-sidebar-history" class="tab-pane" style="height: 100%;overflow-x: hidden;">
                        <%--异步加载执行记录--%>
                    </div>
                    <div id="control-sidebar-interface" class="tab-pane active" style="height: 100%;overflow-x: hidden;">
                        <%--异步加载收藏记录--%>
                    </div>
                </div>
                <!-- /.tab-pane -->
            </aside>
            <!-- /.col -->
            <div class="control-sidebar-bg" style="width:270px;"></div>
            <!-- /.box -->
        </div>

<footer>
    <script type="text/javascript">
        var me = commonErp.getInstance()

        $(document).ready(function(){
            //默认加载导航栏 （收藏）
            queryInterface();
            //绑定接口类型选项卡事件
            $("#nav a").on("click",function(e){
                e.preventDefault();
                $("#interfaceType").attr("value",this.id);
                $(this).tab("show");
            });
            //绑定导航栏click事件
            $("#queryTab a").on("click",function(e){
                e.preventDefault();
                $("#queryType").attr("value",this.id);
                queryInterface();
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
                jsf_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口地址不能为空'
                        }
                    }
                }
            }
        };
        //jsf接口保存验证
        var vaildatorSaveJsf={
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
                            message: '接口名称不能为空'
                        }
                    }
                },
                jsf_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '接口地址不能为空'
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
        //接口执行验证
        var vaildatorRequestJsf={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                jsf_url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: 'resource路径不能为空'
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

        //post接口保存验证
        var vaildatorSavePost={
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
                            message: '接口名称不能为空'
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

        //post接口执行验证
        var vaildatorRequestPost={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
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

        //get接口保存验证
        var vaildatorSaveGet={
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
                            message: '接口名称不能为空'
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
        //get接口解析验证
        var vaildatorRequestGet={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
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


        $("#jsfRequest").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorRequestJsf); //vaildatorRule 验证规则
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
                $("#jsf_res_body").empty();
            }else if(data.value.reqType==2){
                $("#post_res_body").empty();
            }else if(data.value.reqType==3){
                $("#get_res_body").empty();
            }
            if(data.code == "000000") {
                var collection = '';
                if (data.value.reqType == 1) {
                    collection='#jsf_res_body';
                } else if (data.value.reqType == 2) {
                    collection='#post_res_body';
                } else if (data.value.reqType == 3) {
                    collection='#get_res_body';
                }
                fomatValue.dom=collection;
                window.jfp = new JsonFormater(fomatValue);
                $(''+collection+'').val(jfp.doFormat(data.value.resBody))
            }else{
                alertBootbox(data.message);
            }
            //执行接口完成后，如果当前导航栏为查询执行记录 则刷新导航栏执行记录
            var type = $("#queryType").val();
            if(type=='his'){
                queryInterface();
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

        $("#postRequest").on("click", function(){
            destroyValidate();
            //初始化验证插件
            $("#jsfForm").bootstrapValidator(vaildatorRequestPost); //vaildatorRule 验证规则
            changePostHead();
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

        $("#btnSave").on("click", function(){
            destroyValidate();
            //初始化验证插件
            var tp = $("#interfaceType").val();
            if(tp==1){
                $("#jsfForm").bootstrapValidator(vaildatorSaveJsf); //vaildatorRule 验证规则
            }else if(tp==2){
                $("#jsfForm").bootstrapValidator(vaildatorSavePost); //vaildatorRule 验证规则
            }else if(tp==3){
                $("#jsfForm").bootstrapValidator(vaildatorSaveGet); //vaildatorRule 验证规则
            }else{
                alertBootbox("不存在该接口类型");
                return;
            }
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator')
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
            me.postAjaxCallBack(jsfForm,'/interface/saveCollection',submitCallBack,"");
        }

        function submitCallBack(data){
            var result = {};
            if(data.code=="000000"){
                $("#interfaceId").val(data.value.interfaceId);
                alertBootbox("保存接口信息成功！");
                var type = $("#queryType").val();
                if(type=='col'){
                    queryInterface();
                }
            }else{
                alertBootbox("保存接口信息失败，请联系管理员！");
                destroyValidate();
                return;
            }
        }

        //删除检查点
        function removeInfo(divId) {
            destroyValidate();
            var id = divId.parent("div").attr("id");
            var temName = $("#"+id+"").attr("name");
            $("#"+id+"").remove();
            if(temName=="post_head"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                });
            }else if(temName=="get_head"){
                $("div[name="+temName+"]").each(function(index,item){
                    $(this).attr("id",temName+index);
                });
            }

        }

        function destroyValidate(){
            var vali = $("#jsfForm").data('bootstrapValidator');
            if(undefined!=vali){
                vali.destroy();
            }
        }

        //异步查询收藏接口
        function queryInterface(){
            var queryForm = $('#queryForm').serialize();
            var type = $("#queryType").val();
            if(type=='col'){
                me.postAjaxCallBack(queryForm,'/interface/queryCollection',queryInterfaceCallBack,"");
            }else if(type=='his'){
                me.postAjaxCallBack(queryForm,'/interface/queryHistory',queryInterfaceCallBack,"");
            }

        }
        function queryInterfaceCallBack(data){
            if(data.code == "000000"){
                var res = data.list;
                loadList(res);
            }else{
                alertBootbox("加载信息异常，请联系管理员！");
            }

        }
        //加载左侧导航栏数据
        function loadList(data){
            var type = $("#queryType").val();
            var info = "";
            if(type=='col'){
                var collection = $("#control-sidebar-interface");
                for(i=0;i<data.length;i++){
                    info += '<div class="form-group">';
                    info +=   '<label class="pull-left" style="padding-right: 10px;width: 40px;">'+getInterfaceType(data[i].type)+'</label>'
                    info +=   '<a href="javascript:deleteInfo(\''+data[i].interfaceId+'\',\'col\')" class="text-red pull-right" style="margin-right: 8px;">'
                    info +=   '<i class="fa fa-remove"></i>'
                    info +=   '</a>'
                    info +=   '<a href="javascript:queryDetail(\''+data[i].interfaceId+'\',\'col\')" >'
                    info +=   '<p>'+data[i].name+'</P>'
                    info +=   '</a>'
                    info +=   '</div>';
                }
                collection.empty();
                collection.append(info);
            }else{
                var history = $("#control-sidebar-history");
                for(i=0;i<data.length;i++){
                    info += '<div class="form-group">';
                    info +=   '<label class="pull-left" style="padding-right: 10px;width: 40px;">'+getInterfaceType(data[i].type)+'</label>'
                    info +=   '<a href="javascript:deleteInfo('+data[i].id+',\'his\')" class="text-red pull-right" style="margin-right: 8px;">'
                    info +=   '<i class="fa fa-remove"></i>'
                    info +=   '</a>'
                    info +=   '<a href="javascript:queryDetail('+data[i].id+',\'his\')">'
                    info +=   '<p>'+data[i].url+'</P>'
                    info +=   '</a>'
                    info +=   '</div>';
                }
                history.empty();
                history.append(info);
            }

        }
        //获取导航栏接口类型
        function getInterfaceType(intType){
            var info = "";
            if(intType=="1"){
                info = '<font color="#DAA520">DUBBO </font>';
            }else if(intType=="2"){
                info = '<font color="#8E8E38">POST</font>';
            }else if(intType=="3"){
                info = '<font color="#8B2252">GET</font>';
            }
            return info;
        }

        function deleteInfo(id,type){
            var queryForm = $('#queryForm').serialize();
            if(type=="col"){
                me.postAjaxCallBack(queryForm,'/interface/deleteCollection?interfaceId='+id,deleteCallBack,"");
            }else if(type=="his"){
                me.postAjaxCallBack(queryForm,'/interface/deleteHistory?id='+id,deleteCallBack,"");
            }else{
                console.info("不存在该类型！");
            }
        }

        function deleteCallBack(data){
            if(data.code!="000000"){
                alertBootbox("删除信息异常，请联系管理员！");
            }else{
                queryInterface();
            }
        }

        //保存到用例
        function toSaveCase(){
            destroyValidate();
            //初始化验证插件
            var tp = $("#interfaceType").val();
            if(tp==1){
                $("#jsfForm").bootstrapValidator(vaildatorSaveJsf); //vaildatorRule 验证规则
            }else if(tp==2){
                $("#jsfForm").bootstrapValidator(vaildatorSavePost); //vaildatorRule 验证规则
            }else if(tp==3){
                $("#jsfForm").bootstrapValidator(vaildatorSaveGet); //vaildatorRule 验证规则
            }else{
                alertBootbox("不存在该接口类型");
                return;
            }
            //得到获取validator对象或实例
            var bootstrapValidator = $("#jsfForm").data('bootstrapValidator')
            changeGetHead();
            changePostHead();
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                var url = "<%=request.getContextPath()%>/interface/toSaveCaseInfo";
                $iframe.attr('src', timestamp(url)).attr('height', 250);
                bootbox.dialog({
                    message: $iframe,
                    title: "接口保存到用例",
                    className: "add-modal-1"
                });
            }
        }

        //保存到用例页面回调（传回caseId）
        function saveCase(caseId){
            if(undefined==caseId&&caseId==""){
                alertBootbox("用例信息未获取到，请联系管理员");
                return;
            }
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/interfaceSaveCase?caseId='+caseId,saveCaseCallBack,"");
        }

        function saveCaseCallBack(data){
            if(data.code=="000000"){
                alertBootbox("保存到用例成功！");
            }else{
                alertBootbox("保存到用例失败，请联系管理员！");
            }
        }

        //接口另存为
        function saveAs(){
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
            changeGetHead();
            changePostHead();
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                var url = "<%=request.getContextPath()%>/interface/toSaveAsInfo";
                $iframe.attr('src', timestamp(url)).attr('height', 250);
                bootbox.dialog({
                    message: $iframe,
                    title: "另存为",
                    className: "add-modal-1"
                });
            }
        }

        function interfaceSaveAs(otherName){
            if(undefined==otherName&&otherName==""){
                alertBootbox("新的接口名称未获取到，请联系管理员");
                return;
            }
            var jsfForm = $('#jsfForm').serialize();
            me.postAjaxCallBack(jsfForm,'/interface/saveAsCollection?otherName='+otherName,saveAsCallBack,"");
        }

        function saveAsCallBack(data){
            if(data.code=="000000"){
                alertBootbox("接口另存为成功！");
                var type = $("#queryType").val();
                if(type=='col'){
                    queryInterface();
                }
            }else{
                alertBootbox("接口另存为失败，请联系管理员！");
            }
        }

        //查询详情
        function queryDetail(id,type){
            if(type=="col"){
                me.postAjaxCallBack("",'/interface/queryCollectionDetail?interfaceId='+id,detailCallBack,"");
            }else if(type=="his"){
                me.postAjaxCallBack("",'/interface/queryHistoryDetail?id='+id,detailCallBack,"");
            }else{
                console.info("查询详情类型不存在！");
            }
        }
        //查询详情回调函数
        function detailCallBack(data){
            if(data.code=="000000"){
                var value = data.value;
                resetInfo();
                $("#"+value.type+"").tab("show");
                $("#interfaceType").val(value.type);
                $("#interfaceId").val(value.interfaceId);
                var index=0;
                var head = "";
                if(value.type=="1"){
                    $("#jsf_interfaceName").val(value.name);
                    $("#jsf_url").val(value.url);
                    $("#jsf_alias").append("<option value='"+value.alias+"'>"+value.alias+"</option>");
                    $("#jsf_method").append("<option value='"+value.method+"'>"+value.method+"</option>");
                    $("#jsf_ip").append("<option value='"+value.ip+"'>"+value.ip+"</option>");
                    $("#jsf_body").val(value.body);
                    $("#jsf_token").val(value.token);
                    $("#jsfRequest").removeAttr("disabled");
                }else if(value.type=="2"){
                    $("#postHead").empty();
                    $("#post_interfaceName").val(value.name);
                    $("#post_url").val(value.url);
                    $("#post_body").val(value.body);
                    $.each(value.headMap,function(key,value){
                        head = '<div class="form-group" id="post_head'+index+'" name="post_head">';
                        head +='<label class="col-xs-1 control-label">';
                        if(index==0){
                            head +='head';
                        }
                        head +='</label><div class="col-xs-3"><input type="text"  name="headKey" class="form-control" placeholder="key" value="'+key+'"/></div>'
                        head +='<div class="col-xs-3"><input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="'+value+'" /></div>'
                        if(index==0){
                            head +='<button type="button"  class="btn btn-warning btn-xs" onclick="addPostHead();">添加</button>';
                        }else {
                            head += '<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>';
                        }
                        head +='</div>';
                        $("#postHead").append(head);
                        $(":button[name=removeDiv]").unbind("click");
                        $(":button[name=removeDiv]").on("click",function(){
                            removeInfo($(this));
                        });
                        index++;
                    });
                }else if(value.type=="3"){
                    $("#getHead").empty();
                    $("#get_interfaceName").val(value.name);
                    $("#get_url").val(value.url);
                    $.map(value.headMap,function(value,key){
                        head = '<div class="form-group" id="get_head'+index+'" name="get_head">';
                        head +='<label class="col-xs-1 control-label">';
                        if(index==0){
                            head +='head';
                        }
                        head +='</label><div class="col-xs-3"><input type="text"  name="headKey" class="form-control" placeholder="key" value="'+key+'"/></div>'
                        head +='<div class="col-xs-3"><input type="text"  name="headValue" class="form-control" columWidthClass="col-xs-3" placeholder="value" value="'+value+'" /></div>'
                        if(index==0){
                            head +='<button type="button"  class="btn btn-warning btn-xs" onclick="addGetHead();">添加</button>';
                        }else {
                            head += '<button type="button" name="removeDiv" class="btn btn-danger btn-xs">删除</button>';
                        }
                        head +='</div>';
                        $("#getHead").append(head);
                        $(":button[name=removeDiv]").unbind("click");
                        $(":button[name=removeDiv]").on("click",function(){
                            removeInfo($(this));
                        });
                        index++;
                    });
                }

            }else{
                alertBootbox("加载详情异常，请联系管理员！");
            }
        }
        //重置所有接口展示信息
        function resetInfo(){
            //将myform表单中input元素type为button、submit、reset、hidden排除 //将input元素的value设为空值
            $(':input','#jsfForm').not(':button,:submit,:reset,#interfaceType,#queryType').val('').find("option").remove();
            $('[name=res_body]').empty();
            destroyValidate();
        }

    </script>
</footer>
</body>
</html>

