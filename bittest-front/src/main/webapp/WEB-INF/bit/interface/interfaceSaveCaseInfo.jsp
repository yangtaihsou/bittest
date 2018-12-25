<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <link  href="/static/bootstrap/css/bootstrap-select.css" rel="stylesheet">
    <script src="/static/bootstrap/js/bootstrap-select.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>接口保存到用例</title>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: right;
        }
    </style>
    <meta charset="UTF-8"/>
</head>
<body>
<form id="erpform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
    <div class="form-group">
        <label class="col-xs-3 control-label">用例信息</label>
        <div class="col-xs-8">
            <select name="caseId" id="caseId" class="selectpicker show-tick form-control"  data-first-option="false" title='请选择用例信息' data-live-search-placeholder="搜索" data-live-search="true">

            </select>
        </div>
    </div>
    <div class="box-body" style="height: 120px;">
    </div>
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
                    caseId: {
                        validators: {
                            notEmpty: {
                                message: '请选择用例信息'
                            },
                        }
                    },
                }
            });
            loadCaseInfo();
        });

        function loadCaseInfo(){
            var erpform = $('#erpform').serialize()
            me.postAjaxCallBack(erpform,'/case/queryCaseByName',loadCallBack,"");
        }

        function loadCallBack(data){
            if(data.code == "000000"){
                $("#caseId").find('option').remove();
                $("#caseId").append($('<option value=\'\'>' + "---请选择---" + '</option>'));
                for(i=0;i<data.list.length;i++){
                    $("#caseId").append($('<option value=' + data.list[i].caseId + '>' + data.list[i].caseName + '</option>'));
                }
                $('.selectpicker').selectpicker('refresh');
            }else{
                alertBootbox("加载用例信息异常！");
            }
        }

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
            var caseId = $("#caseId").val();
            parent.saveCase(caseId);
        }
    </script>
</footer></body>
</html>
