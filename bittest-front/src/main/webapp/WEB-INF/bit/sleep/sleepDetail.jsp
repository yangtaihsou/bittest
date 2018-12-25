<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>sleep编辑</title>
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
        <input type="hidden" name="interfaceId" value="${sleep.interfaceId}"/>
        <div class="form-group">
            <label class="from-control col-xs-4">名称</label>
            <input type="text" id="name" name="name" class="form-control" columWidthClass="col-xs-8" required data-bv-notempty-message="名称不能为空"  value="${sleep.name}" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">sleep</label>
            <input type="text" id="body" name="body" class="form-control" disabled="disabled" columWidthClass="col-xs-8" value="${sleep.body}" required data-bv-notempty-message="等待时长不能为空" placeholder="等待时长单位毫秒" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8" disabled="disabled">${sleep.remark}</textarea>
        </div>
    </div><!-- /.box-body -->
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">关&nbsp;&nbsp;闭</button>
    </div><!-- /.box-footer -->
</form>
<footer>

    <script type="text/javascript">
        //jsf接口解析验证
        var vaildator={
            tipType:true,//tip框提示
            feedbackIcons: {//样式
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '名称不能为空'
                        }
                    }
                },
                body: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '等待时长不能为空'
                        },
                        regexp: {
                            regexp: /^[0-9]+$/,
                            message: '请输入正确的等待时长'
                        },
                        stringLength: {
                            min: 2,
                            max: 6,
                            message: '等待长度必须在2~6位数字之间'
                        }
                    }
                }
            }
        };


        var me = commonErp.getInstance();

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


        function dataSubmit(){
            var erpform = $('#erpform').serialize();
            me.postAjaxCallBack(erpform,'/sleep/sleepEdit',submitCallBack,"");
        }

        function submitCallBack(data){
            $("#erpform").data('bootstrapValidator').destroy();
            var result = {};
            if(data.code == "000000"){
            }else{
                alertBootbox("亲，编辑sleep信息失败了，请联系管理员！");
                return;
            };
            window.parent.closeBootbox(result);
            parent.query();
        }

        $("#btnConnect").on("click", function(){
            //初始化验证插件
            $("#erpform").bootstrapValidator(vaildator); //vaildatorRule 验证规则
            //得到获取validator对象或实例
            var bootstrapValidator = $("#erpform").data('bootstrapValidator');
            // 执行表单验证
            var result = bootstrapValidator.validate()
            if(result.isValid()){
                connectDataBase();
            }
        });


    </script>
</footer>
</body>
</html>
