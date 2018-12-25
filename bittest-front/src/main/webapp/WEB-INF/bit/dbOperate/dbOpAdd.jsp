<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>数据库操作新增</title>
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
    <input  type="hidden" name="caseId" value="${caseId}"/>
    <div class="box-body">
        <div class="form-group">
            <label class="from-control col-xs-4">名称</label>
            <input class="form-control" columWidthClass="col-xs-8" type="text" value="数据库操作" name="name" id="name" required/>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">数据库</label>
            <select class="form-control" columWidthClass="col-xs-8" id="url" name="url" required >
                <option value="">请选择</option>
                <c:forEach var="db" items="${db}" >
                    <option value="${db.databaseId}">${db.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">操作类型</label>
            <select class="form-control" columWidthClass="col-xs-8" id="method" name="method" required >
                <option value="insert">insert新增</option>
                <option value="update">修改or删除</option>
            </select>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">SQL</label>
            <textarea id="body" name="body" class="form-control" rows="4" required columWidthClass="col-xs-8"></textarea>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark" class="form-control" rows="4" columWidthClass="col-xs-8"></textarea>
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
                    name: {
                        validators: {
                            notEmpty: {
                                message: '名称不能为空'
                            },
                        }
                    },
                    url: {
                        validators: {
                            notEmpty: {
                                message: '数据库不能为空'
                            },
                        }
                    },
                    method: {
                        validators: {
                            notEmpty: {
                                message: '系统域名不能为空'
                            },
                        }
                    },
                    body: {
                        validators: {
                            notEmpty: {
                                message: 'sql信息不能为空'
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
            me.postAjaxCallBack(erpform,'/dbOp/dbOpAdd',submitCallBack,"");
        }

        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                window.parent.closeBootbox(result);
            }else{
                alertBootbox("亲，保存信息失败了，请联系管理员！");
                return;
            };
            parent.query();
        }
    </script>
</footer></body>
</html>
