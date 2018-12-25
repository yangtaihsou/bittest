<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <script src="/static/js/My97DatePicker/WdatePicker.js"></script>
    <title>编辑定时任务信息</title>
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
    <input type="hidden" name="id" value="${timerTaskConfig.id}">
    <div class="box-body">
        <div class="form-group">
            <label class="from-control col-xs-4">任务id</label>
            <input type="text" id="taskId" name="taskId" class="form-control" value="${timerTaskConfig.taskId}" columWidthClass="col-xs-8" required data-bv-notempty-message="任务id不能为空"  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">任务描述</label>
            <input type="text" id="taskTimerDesc" name="taskTimerDesc" class="form-control" columWidthClass="col-xs-8" value="${timerTaskConfig.taskTimerDesc}" required data-bv-notempty-message="任务描述"  placeholder="如：test.bit.com"/>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">定时类型</label>
            <select class="form-control" columWidthClass="col-xs-8" id="dataType" name="dataType" required >
            <option>请选择 </option>
            <option value="year"
<c:if test="${timerTaskConfig.dataType  == 'year'}">
    selected
</c:if>
>年</option>
            <option value="month"
                    <c:if test="${timerTaskConfig.dataType  == 'month'}">
                        selected
                    </c:if>
            >月</option>
            <option value="day"
                    <c:if test="${timerTaskConfig.dataType  == 'day'}">
                        selected
                    </c:if>
            >日</option>
            <option value="hour"
                    <c:if test="${timerTaskConfig.dataType  == 'hour'}">
                        selected
                    </c:if>
            >时</option>
            <option value="minute"
                    <c:if test="${timerTaskConfig.dataType  == 'minute'}">
                        selected
                    </c:if>
            >分</option>
<%--            <option value="second"
                    <c:if test="${timerTaskConfig.dataType  == 'second'}">
                        selected
                    </c:if>
            >秒</option>--%>
        </select>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">开始执行时间</label>
            <input type="text" id="biztime" name="biztime" class="form-control"   value="<fmt:formatDate value="${timerTaskConfig.biztime}"    pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />"
                   onclick="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                   columWidthClass="col-xs-8"   />
        </div>
        <div class="form-group">
        <label class="from-control col-xs-4">状态</label>
        <select class="form-control" columWidthClass="col-xs-8" id="status" name="status" required >
            <option>请选择</option>
            <option value="0"
                    <c:if test="${timerTaskConfig.status  == 0}">
                        selected
                    </c:if>
            >停止</option>
            <option value="1"
                    <c:if test="${timerTaskConfig.status  == 1}">
                        selected
                    </c:if>
            >启动</option>
        </select>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">年</label>
            <input id="year" name="year"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.year}"/>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">月</label>
            <input id="month" name="month"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.month}">
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">日</label>
            <input id="day" name="day"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.day}">
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">时</label>
            <input id="hour" name="hour"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.hour}">
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">分</label>
            <input id="minute" name="minute"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.minute}">
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">秒</label>
            <input id="second" name="second"  class="form-control" rows="3" columWidthClass="col-xs-8" value="${timerTaskConfig.second}">
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
            me.postAjaxCallBack(erpform,'/timerTaskConfig/edit',submitCallBack,"");
        }

        function submitCallBack(data){
            var result = {};
            if(data.code == "000000"){
                window.parent.closeBootbox(result);
            }else{
                $("#erpform").data('bootstrapValidator').destroy();
                alertBootbox("亲，编辑系统信息失败了，请联系管理员！");
                return;
            };
            parent.query();
        }
    </script>
</footer>
</body>
</html>
