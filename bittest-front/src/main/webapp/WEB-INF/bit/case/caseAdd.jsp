<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>新增系统</title>
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
    <input type="hidden" id="taskId" name="taskId" value="${taskId}"/>
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
                            <input type="text" id="caseName" name="caseName" class="form-control" columWidthClass="col-xs-8" required  />
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8" id="sysId" name="sysId" required >
                                <option value="">请选择</option>
                                <c:forEach var="sys" items="${systems}" >
                                    <option value="${sys.systemId}">${sys.systemName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8"></textarea>
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

                    </div>
                </div>
            </div>
        </div>
        </div>
    </div>


    <div class="box-footer">
        <%--<button type="submit" id="btmSave" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>--%>
        <button type="submit" id="btnNext" class="btn btn-info pull-right btn-submit" >下一步</button>
    </div><!-- /.box-footer -->
</form>

<footer>

    <script type="text/javascript">


        var me = commonErp.getInstance();
        $(document).ready(function(){
            //表单验证
            var vaildator={
                tipType:true,//
                feedbackIcons: {//样式
//                valid: 'glyphicon glyphicon-ok',
//                invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    caseName: {
                        //需要做的验证
                        validators: {
                            //验证项
                            notEmpty: {
                                message: '用例名称不能为空'
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
            $("#btnNext").on("click", function(){
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

        // 解析提交时 关闭表单 submit 事件
        $("#btnNext").submit(function(ev){
            ev.preventDefault();
        });

        $(function () { $('#collapseFour').collapse({
            toggle: false
        })});
        $(function () { $('#collapseTwo').collapse('show')});
        $(function () { $('#collapseOne').collapse('show')});


        function dataSubmit(){
            changeDataInfo();
            var erpform = $('#erpform').serialize()
            me.postAjaxCallBack(erpform,'/case/caseAdd',submitCallBack,"");
        }


        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                var tabid = "CASE_CASEEDIT";
                var title = "用例接口管理";
                var url = "<%=request.getContextPath()%>/case/toCaseEdit?caseId="+data.value.caseId;
                window.parent.addTabs(tabid , title , url , true);
            }else{
                alertBootbox("亲，保存用例信息失败了，请联系管理员！");
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
                 $(this).children().find("input").eq(1).attr("name","caseParamMap["+$(this).children().find("input").eq(0).val()+"]");
             });
        }
        //动态添加参数化信息
        function addDiv(){
            $('#collapseTwo').collapse('show');
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

    </script>
</footer></body>
</html>
