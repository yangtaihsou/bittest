<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>编辑数据库</title>
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
        <div class="form-group">
            <label class="from-control col-xs-4">数据库名称</label>
            <input type="text" id="name" name="name" class="form-control" columWidthClass="col-xs-8" value="${database.name}" required />
            <input type="hidden" id="databaseId" name="databaseId" class="form-control" columWidthClass="col-xs-8" value="${database.databaseId}" required />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">数据库类型</label>
            <select class="form-control" columWidthClass="col-xs-4" required>
                <option>MySql</option>
            </select> <span class="label label-warning">抱歉，目前只支持MySql数据库</span>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">数据库连接</label>
            <input type="text" id="url" name="url" class="form-control" columWidthClass="col-xs-6" value="${database.url}" required  placeholder="例如:172.0.0.1:3306/库名" />
            <button type="submit" id="btnConnect" class="btn btn-success" >测试连接</button>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">用户名</label>
            <input type="text" id="username" name="username" class="form-control" columWidthClass="col-xs-8" value="${database.username}" required  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            <input type="password" id="password" name="password" class="form-control" columWidthClass="col-xs-8" value="${database.password}" required />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8">${database.remark}</textarea>
        </div>
    </div><!-- /.box-body -->
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
        <button type="submit" id="btnSave" class="btn btn-info pull-right btn-submit" >保&nbsp;&nbsp;存</button>
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
                            message: '数据库名称不能为空'
                        }
                    }
                },
                url: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '数据库连接不能为空'
                        }
                    }
                },
                username: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '用户名不能为空'
                        }
                    }
                },
                password: {
                    //需要做的验证
                    validators: {
                        //验证项
                        notEmpty: {
                            message: '数据库密码不能为空'
                        }
                    }
                },
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
            me.postAjaxCallBack(erpform,'/database/dataBaseEdit',submitCallBack,"");
        }

        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
            }else{
                $("#erpform").data('bootstrapValidator').destroy();
                alertBootbox("亲，修改数据库信息失败了，请联系管理员！");
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

        function connectDataBase(){
            var erpform = $('#erpform').serialize();
            me.postAjaxCallBack(erpform,'/database/connectDataBase',connectCallBack,"");
        }

        function connectCallBack(data){
            if(data.code == "000000"){
                alertBootbox("恭喜，数据库连接成功！");
            }else{
                alertBootbox("亲，连接数据库失败，请检查填写信息是否正确！");
            }
            $("#erpform").data('bootstrapValidator').destroy();
        }


    </script>
</footer>
</body>
</html>
