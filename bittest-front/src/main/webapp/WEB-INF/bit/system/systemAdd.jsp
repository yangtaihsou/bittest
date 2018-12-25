<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
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
    <div class="box-body">
        <div class="form-group">
            <label class="from-control col-xs-4">系统名称</label>
            <input type="text" id="systemName" name="systemName" class="form-control" columWidthClass="col-xs-8" required data-bv-notempty-message="系统名称不能为空"  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">系统域名</label>
            <input type="text" id="domainName" name="domainName" class="form-control" columWidthClass="col-xs-8" required data-bv-notempty-message="域名不能为空"  placeholder="如：test.bit.com"/>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">邮件地址</label>
            <input type="text" id="ip" name="ip" class="form-control" columWidthClass="col-xs-8"  placeholder="请输入邮件地址" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8"></textarea>
        </div>
    </div><!-- /.box-body -->
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
        <button type="submit" id="btnSave" class="btn btn-info pull-right btn-submit" >保&nbsp;&nbsp;存</button>
    </div><!-- /.box-footer -->
</form>
<footer>

    <script type="text/javascript">


        var me = commonErp.getInstance();
        $(document).ready(function(){
            $("#erpform").bootstrapValidator({
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    systemName: {
                        validators: {
                            notEmpty: {
                                message: '系统名称不能为空'
                            },
                        }
                    },
                    domainName: {
                        validators: {
                            notEmpty: {
                                message: '系统域名不能为空'
                            },
                        }
                    },
                }
            });
        });

        $("#btnSave").on("click",function(){
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
            me.postAjaxCallBack(erpform,'/system/systemAdd',submitCallBack,"");
        }

        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                window.parent.closeBootbox(result);
            }else{
                alertBootbox("亲，保存系统信息失败了，请联系管理员！");
                return;
            };
            parent.query();
        }
    </script>
</footer>
</body>
</html>
