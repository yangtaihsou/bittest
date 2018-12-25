<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/js/My97DatePicker/WdatePicker.js"></script>
    <script src="/static/adminLTE/extend/erp_common.js?t=1" type="text/javascript"></script>
    <title>新增任务执行的定时配置</title>
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
            <label class="from-control col-xs-4">任务id</label>
            <input type="text" id="taskId" name="taskId" class="form-control" columWidthClass="col-xs-8" required data-bv-notempty-message="任务id不能为空"  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">任务描述</label>
            <input type="text" id="taskTimerDesc" name="taskTimerDesc" class="form-control" columWidthClass="col-xs-8" required data-bv-notempty-message="任务描述不能为空"  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">开始执行时间</label>
            <input type="text" id="biztime" name="biztime" class="form-control"  onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                   columWidthClass="col-xs-8"   />
        </div>


        <div class="form-group">
            <label class="from-control col-xs-4">定时类型</label>
            <select class="form-control" columWidthClass="col-xs-8" id="dataType" name="dataType" required >
                <option>请选择</option>
                <option value="year">年</option>
                <option value="month">月</option>
                <option value="day">日</option>
                <option value="hour">时</option>
                <option value="minute">分</option>
             <%--   <option value="second">秒</option>--%>
            </select>
                    </div>
        <div class="form-group">
            <label class="from-control col-xs-4">年</label>
            <input type="text" id="year" name="year" class="form-control" columWidthClass="col-xs-8"  placeholder="年" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">月</label>
            <input type="text" id="month" name="month" class="form-control" columWidthClass="col-xs-8"  placeholder="月" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">日</label>
            <input type="text" id="day" name="day" class="form-control" columWidthClass="col-xs-8"  placeholder="日" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">时</label>
            <input type="text" id="hour" name="hour" class="form-control" columWidthClass="col-xs-8"  placeholder="时" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">分</label>
            <input type="text" id="minute" name="minute" class="form-control" columWidthClass="col-xs-8"  placeholder="分" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">秒</label>
            <input type="text" id="second" name="second" class="form-control" columWidthClass="col-xs-8"  placeholder="秒" />
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
                    taskId: {
                        validators: {
                            notEmpty: {
                                message: '任务id'
                            },
                        }
                    },
                    taskTimerDesc: {
                        validators: {
                            notEmpty: {
                                message: '任务描述不能为空'
                            },
                        }
                    },
                    dataType: {
                    validators: {
                        notEmpty: {
                            message: '定时类型不能为空'
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
            me.postAjaxCallBack(erpform,'/timerTaskConfig/save',submitCallBack,"");
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
</footer>
</body>
</html>
