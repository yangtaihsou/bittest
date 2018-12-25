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
    <title>修改任务信息</title>
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
                            <input type="text" id="taskName" name="taskName" class="form-control" columWidthClass="col-xs-8" required  value="${task.taskName}"/>
                            <input type="hidden" id="taskId" name="taskId"  value="${task.taskId}"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8" id="sysId" name="sysId" required >
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
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8">${task.remark}</textarea>
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
                        <div class="col-xs-6 text-right">
                            <button type="button" class="btn btn-warning btn-xs" onclick="addDiv();">添加</button>
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
                                        <input type="text" value="${taskParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3">
                                        <label class="from-control col-xs-2 control-label">参数值</label>
                                        <input type="text" value="${taskParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" >
                                        <button type="button" class="btn btn-danger btn-xs" onclick="removeDiv('${index.index}');">删除</button>
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
                                <select class="form-control" columWidthClass="col-xs-2"  name="sysId">
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
                            <th>操作</th>
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
        <button type="submit" id="btnSave" class="btn btn-info pull-right btn-submit" >保&nbsp;&nbsp;存</button>
    </div><!-- /.box-footer -->

<footer>

    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = null;
        var tableUtil = new TableUtil("datatable");
        $(document).ready(function(){
            //关闭新增页面
            window.parent.closeTab("TASK_TASKADD");
            var aButtons = [{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>关联用例',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/case/toNoFetchCaseList?taskId="+$("#taskId").val();
                    $iframe.attr('src',timestamp(url));
                    bootbox.dialog({
                        message: $iframe,
                        title: "关联用例",
                        className: "add-modal",
                    });
                }
            },
            {
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>添加用例',
                "click":function(){
                    var tabid = "CASE_CASEADD";
                    var title = "新增用例";
                    var url = "<%=request.getContextPath()%>/case/toCaseAdd?taskId="+$("#taskId").val();
                    window.parent.addTabs(tabid , title , url , true);
                }
            }];

            var columns = [
                { "mData": "caseId" },
                { "mData": "caseName" },
                { "mData": "systemName" },
                { "mData": "updateTimeStr" },
                { "mData": "createTimeStr" },
                { "mData": null }

            ];
            var columnDefs = [
                {
                    "render": function ( data, type, row ) {
                        return getBtns(row);
                    },
                    "targets": 5
                }
            ];
            var url = '/case/queryFetchCaseList?taskId='+$('#taskId').val();
            datatable = me.initDataTable($('#datatable'), $("#queryform"), url, columns, columnDefs, aButtons,detail);


            //表单验证
            var vaildator={
                tipType:true,//
                feedbackIcons: {//样式
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    taskName: {
                        //需要做的验证
                        validators: {
                            //验证项
                            notEmpty: {
                                message: '任务名称不能为空'
                            }
                        }
                    },
                    sysId: {
                        //需要做的验证
                        validators: {
                            //验证项
                            notEmpty: {
                                message: '所属系统不能为空'
                            }
                        }
                    },
                    paramName: {
                        //需要做的验证
                        validators: {
                            //验证项
                            notEmpty: {
                                message: '参数名称不能为空'
                            },
                            callback: {
                                message: '参数名称重复使用',
                                callback: function(value, validator) {
                                    //自定义参数化校验
                                    var index=0;
                                    if(value!=""){
                                        $("input[name=paramName]").each(function(){
                                            if(value==$(this).val()){
                                                index++;
                                            }
                                        });
                                    }
                                    if(index>1){
                                        return false;
                                    }else{
                                        return true;
                                    }

                                }
                            }
                        }
                    },
                    paramValue: {
                        //需要做的验证
                        validators: {
                            //验证项
                            notEmpty: {
                                message: '参数值不能为空'
                            }
                        }
                    },
                }
            };
            //保存信息
            $("#btnSave").on("click", function(){
                //初始化验证插件
                $("#erpform").bootstrapValidator(vaildator); //vaildatorRule 验证规则
                //得到获取validator对象或实例
                var bootstrapValidator = $("#erpform").data('bootstrapValidator');
                // 执行表单验证
                var result = bootstrapValidator.validate()
                if(result.isValid()){
                    dataSubmit();
                }
            });
        })


        $(function () { $('#collapseFour').collapse({
            toggle: false
        })});
        $(function () { $('#collapseTwo').collapse('show')});
        $(function () { $('#collapseOne').collapse('show')});

        // 解析提交时 关闭表单 submit 事件
        $("#btnSave").submit(function(ev){
            ev.preventDefault();
        });


        function dataSubmit(){
            changeDataInfo();
            var erpform = $('#erpform').serialize()
            me.postAjaxCallBack(erpform,'/task/taskEdit',submitCallBack,"");
        }


        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                alertBootbox("保存任务计划成功！");
            }else{
                alertBootbox("亲，保存任务信息失败了，请联系管理员！");
                return;
            };
        }


        //删除参数化信息
        function removeDiv(divName) {
            $("#"+divName+"").remove();
        }
        function changeDataInfo(){
            //遍历paramName paramValue 将paramName的value 负值给paramValue的name（springMVC封装数据Map<String,String>）
             $("div[name=param]").each(function(){
                 $(this).children().find("input").eq(1).attr("name","taskParamMap["+$(this).children().find("input").eq(0).val()+"]");
             });
        }
        //动态添加参数化信息
        function addDiv(){
            $(function () { $('#collapseTwo').collapse('show')});
            var index = $('div[name="param"]').length+1;
            var info = '<div class="form-group" id="'+index+'" name="param">'
                    +'<label class="from-control col-xs-2 control-label">参数名称：<font color="red">*</font></label>'
                    +'<div class="col-xs-3"><input type="text" name="paramName" class="form-control" columwidthclass="col-xs-3"></div>'
                    +'<label class="from-control col-xs-2 control-label">参数值</label>'
                    +'<div class="col-xs-3"><input type="text" name="paramValue" class="form-control" columwidthclass="col-xs-3" ></div>'
                    +'<button type="button" class="btn btn-danger btn-xs" onclick="removeDiv(\''+index+'\');">删除</button>'
                    +'</div>';
            $("#userParam").append(info);
        }

        function getBtns(row){
            var btns ='<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="removeFetchCase(\''+row.caseId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;移除关联</a>&nbsp;&nbsp;';
            return btns;
        }

        function removeFetchCase(caseId){
            var taskId=$("#taskId").val();
            var param = "taskId="+taskId+"&&caseId="+caseId;
            me.postAjaxCallBack(param,'/case/removeFetchCase',removeCallBack,"");
        }


        function removeCallBack(data){
            var result = {};
            if(data.code == "000000"){
                query();
            }else{
                alertBootbox("移除关联用例失败，请联系管理员！");
            }
        }

        function detail(data){
            var url = "<%=request.getContextPath()%>/case/caseDetail?caseId="+data.caseId;
            $iframe.attr('src', timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: "用例详情",
                className: "add-modal"
            });
        }

        function query(){
            datatable.fnDraw();
        }

    </script>
</footer></body>
</html>
