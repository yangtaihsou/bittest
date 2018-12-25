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
    <title>用例编辑</title>
    <style>
        .form-horizontal .control-label {
            padding-top: 7px;
            margin-bottom: 0;
            text-align: right;
        }
    </style>
</head>
<body>
<form id="caseform" onsubmit="return false;" class="form-horizontal form-bordered" role="form">
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
                            <input type="text" id="caseName" name="caseName" value="${cases.caseName}" class="form-control" columWidthClass="col-xs-8" required  />
                            <input type="hidden" id="caseId" name="caseId" value="${cases.caseId}"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8" id="sysId" name="sysId" required >
                                <option value="">请选择</option>
                                <c:forEach var="sys" items="${systems}" >
                                    <option value="${sys.systemId}"
                                        <c:if test="${sys.systemId==cases.sysId}">
                                            selected="selected"
                                        </c:if>
                                    >${sys.systemName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8">${cases.remark}</textarea>
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
                                <c:when test="${cases.caseParamMap!=null}">
                                    <c:forEach items="${cases.caseParamMap}" var="caseParam"  varStatus="index">
                                        <div class="form-group" id="${index.index}" name="param">
                                            <label class="from-control col-xs-2 control-label">参数名称：<font color="red">*</font></label>
                                            <input type="text" value="${caseParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3">
                                            <label class="from-control col-xs-2 control-label">参数值</label>
                                            <input type="text" value="${caseParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" >
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
    <div class="box">
        <div class="box-body">
            <table id="datatable" class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                    <th>接口编号</th>
                    <th>接口名称</th>
                    <th>接口类型</th>
                    <th>执行顺序</th>
                    <th>排序调整</th>
                    <th style="width: 20%;">操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <!-- /.box-body -->
    </div>

    <div class="box-footer">
        <button type="submit" id="btnSave" class="btn btn-info pull-right btn-submit" >保&nbsp;&nbsp;存</button>
    </div><!-- /.box-footer -->
</form>

<footer>

    <script type="text/javascript">


        var me = commonErp.getInstance();
        var datatable = null;
        var tableUtil = new TableUtil("datatable");
        $(document).ready(function(){
            //关闭新增页面
            window.parent.closeTab("CASE_CASEADD");
            $(function () { $('#collapseFour').collapse({
                toggle: false
            })});
            $(function () { $('#collapseTwo').collapse('hide')});
            $(function () { $('#collapseOne').collapse('show')});

            var aButtons = [{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>新增接口',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/interface/toInterfaceAdd?caseId="+$("#caseId").val();
                    $iframe.attr('src',timestamp(url));
                    bootbox.dialog({
                        message: $iframe,
                        title: "新增接口",
                        className: "add-modal"
                    });
                }
            }
            ,{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>添加已有接口',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/interface/toAddExitInterfaceList?caseId="+$("#caseId").val();
                    $iframe.attr('src',timestamp(url));
                    bootbox.dialog({
                        message: $iframe,
                        title: "添加已有接口信息",
                        className: "add-modal"
                    });
                }
            }
            ,{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>新增sleep',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/sleep/toSleepAdd?caseId="+$("#caseId").val();
                    $iframe.attr('src',timestamp(url)).attr("height",300);
                    bootbox.dialog({
                        message: $iframe,
                        title: "sleep添加",
                        className: "add-modal2"
                    });
                }
            }
            ,{
                "sExtends":"common",
                "sButtonClass": "btn btn-xs btn-danger btn-size",
                "innerHTML":'<i class="fa fa-user-plus"></i>新增数据库操作',
                "click":function(){
                    var url = "<%=request.getContextPath()%>/dbOp/toDbOpAdd?caseId="+$("#caseId").val();
                    $iframe.attr('src',timestamp(url)).attr("height",400);
                    bootbox.dialog({
                        message: $iframe,
                        title: "数据库操作添加",
                        className: "add-modal1"
                    });
                }
            }
            ];

            datatable = me.initEmptyDataTable($('#datatable'),"","",aButtons,100);
            //查询用例接口列表信息
            queryInterfaceByCase();

            //对行双击添加监听事件
            $('#datatable tbody').on('dblclick', 'tr', function () {
                var tr = $(this).closest('tr');
                var interfaceId = tr.find('td').eq(0).text();
                var type = tr.find('td').eq(2).text();
                detail(interfaceId,type);
            });
            // 初始化上升按钮
            $('#datatable tbody').on('click', 'a.up', function(e) {
                e.preventDefault();
                var table = $('#datatable').DataTable();
                var index = table.row($(this).parents('tr')).index();
                if ((index - 1) >= 0) {
                    var data = table.data();
                    table.clear();
                    data.splice((index - 1), 0, data.splice(index, 1)[0]);
                    for(var i=0;i<data.length;i++){
                        var temp = i+1;
                        data[i][3]=temp+'<input type="hidden" name="orders['+data[i][0]+']" value="'+temp+'"/> ';
                    }
                    table.rows.add(data).draw();
                }
            });

            // 初始化下降按钮
            $('#datatable tbody').on('click', 'a.down', function(e) {
                e.preventDefault();
                var table = $('#datatable').DataTable();
                var index = table.row($(this).parents('tr')).index();
                var max = table.rows().data().length;
                if ((index + 1) < max) {
                    var data = table.data();
                    table.clear();
                    data.splice((index + 1), 0, data.splice(index, 1)[0]);
                    for(var i=0;i<data.length;i++){
                        var temp = i+1;
                        data[i][3]=temp+'<input type="hidden" name="orders['+data[i][0]+']" value="'+temp+'"/> ';
                    }
                    table.rows.add(data).draw();
                }
            });

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
            $("#btnSave").on("click", function(){
                //初始化验证插件
                $("#caseform").bootstrapValidator(vaildator); //vaildatorRule 验证规则
                //得到获取validator对象或实例
                var bootstrapValidator = $("#caseform").data('bootstrapValidator');
                // 执行表单验证
                var result = bootstrapValidator.validate()
                if(result.isValid()){
                    dataSubmit();
                }
            });

        })

        function dataSubmit(){
            changeDataInfo();
            var caseform = $('#caseform').serialize()
            me.postAjaxCallBack(caseform,'/case/caseEdit',submitCallBack,"");
        }

        function submitCallBack(data){
            if(data.code == "000000"){
                alertBootbox("保存用例信息成功！");
            }else{
                alertBootbox("亲，保存用例接口信息失败了，请联系管理员！");
                return;
            }
            destroyValidate();
        }


        //删除参数化信息
        function removeDiv(divName) {
            destroyValidate();
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

        function query(){
            queryInterfaceByCase();
        }

        //查询用例下接口信息
        function queryInterfaceByCase(){
            var caseform = $('#caseform').serialize();
            me.postAjaxCallBack(caseform,'/interface/queryInterfaceByCase',queryCallBack,"");
        }
        //查询后将数据添加到datatables展示
        function queryCallBack(data){
            if(data.code == "000000"){
                var table = $("#datatable").DataTable();
                //如果是已经加载过数据信息，再次加载时清空所有行，重新添加
                if(table.rows().length>0){
                    table.rows().remove();
                };
                var interfaceInfo = data.list;
                var interfaceId;
                var type;
                for (var i=0;i<interfaceInfo.length;i++){
                    type = interfaceInfo[i].typeStr;
                    interfaceId = interfaceInfo[i].interfaceId;
                    var orderButton ='<a class="up btn btn-xs btn-default"  href="javascript:void(0);"><i class="glyphicon glyphicon-circle-arrow-up"></i></a>&nbsp;&nbsp;'
                            +'<a class="down btn btn-xs btn-default" href="javascript:void(0);" ><i class="glyphicon glyphicon-circle-arrow-down"></i></a>&nbsp;&nbsp;';
                    var btns ='<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="edit(\''+interfaceId+'\',\''+type+'\')"><i class="fa fa-edit"></i>编 辑</a>&nbsp;&nbsp;';
                    var status = interfaceInfo[i].status;
                    var statusBution;
                        if(status==1){
                            statusBution = '<i class="glyphicon glyphicon-stop"></i>禁 用</a>';
                        }else if(status==2){
                            statusBution = '<i class="glyphicon glyphicon-play"></i>启 用</a>';
                        }
                        btns += '<a class="btn btn-xs btn-warning" href="javascript:void(0);" onclick="disableInterface(\''+interfaceId+'\',\''+status+'\')">'+statusBution+'&nbsp;&nbsp;';
                        btns += '<a class="btn btn-xs btn-danger" href="javascript:void(0);" onclick="del(\''+interfaceId+'\')"><i class="glyphicon glyphicon-trash"></i>&nbsp;删 除</a>&nbsp;&nbsp;';
                    var order = interfaceInfo[i].interfaceOrder+'<input type="hidden" name="'+interfaceId+'" value="'+interfaceInfo[i].interfaceOrder+'">';
                    table.row.add([
                        interfaceId,
                        interfaceInfo[i].name,
                        type,
                        order,
                        orderButton,
                        btns
                    ] ).draw();
                }
            }else{
                alertBootbox("获取用例接口列表信息失败！");
                return;
            };
        }

        function destroyValidate(){
            var vali = $("#caseform").data('bootstrapValidator');
            if(undefined!=vali){
                vali.destroy();
                $('#caseform').data('bootstrapValidator', null);
            }
        }

        function edit(interfaceId,type){
            var url = "<%=request.getContextPath()%>/interface/toInterfaceEdit?interfaceId="+interfaceId;
            var title = "编辑接口信息";
            var className = "add-modal";
            if(type=='SLEEP'){
                url = "<%=request.getContextPath()%>/sleep/toSleepEdit?interfaceId="+interfaceId;
                title = "编辑sleep信息";
                className = "add-modal2";
                $iframe.attr("height",300);
            }else if(type=='DATABASE'){
                url = "<%=request.getContextPath()%>/dbOp/toDbOpEdit?interfaceId="+interfaceId;
                title = "编辑数据库操作";
                className = "add-modal1";
                $iframe.attr("height",400);
            }
            $iframe.attr('src',timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: title,
                className: className
            });
        }

        function del(interfaceId){
            bootbox.setLocale("zh_CN");
            bootbox.confirm("确认要删除该接口吗？", function (result) {
                if(result) {
                    var caseform = $('#caseform').serialize();
                    var url = '/interface/interfaceDel?interfaceId='+interfaceId;
                    me.postAjaxCallBack(caseform,url,delCallBack,"");
                }
            });
        }

        function delCallBack(data){
            if(data.code=="000000"){
                query();
            }else{
                alertBootbox("删除接口信息失败，请联系管理员！");
            }
        }

        function disableInterface(interfaceId,status){
            var caseform = $('#caseform').serialize();
            var url = '/interface/relastionUpdate?interfaceId='+interfaceId+'&status='+status;
            me.postAjaxCallBack(caseform,url,disableCallBack,"");
        }

        function disableCallBack(data){
            if(data.code=="000000"){
                query();
            }else{
                alertBootbox("删除接口信息失败，请联系管理员！");
            }
        }

        function detail(interfaceId,type){
            var title = "接口详情";
            var className = "add-modal";
            var url = "<%=request.getContextPath()%>/interface/interfaceDetail?interfaceId="+interfaceId;
            if(type=='SLEEP'){
                url = "<%=request.getContextPath()%>/sleep/sleepDetail?interfaceId="+interfaceId;
                title = "sleep详情";
                className = "add-modal2";
                $iframe.attr("height",300);
            }else if(type=='database'){
                url = "<%=request.getContextPath()%>/database/toDatabaseEdit?interfaceId="+interfaceId;
                title = "编辑数据库操作";
            }
            $iframe.attr('src',timestamp(url));
            bootbox.dialog({
                message: $iframe,
                title: title,
                className: className
            });
        }

    </script>
</footer>
</body>
</html>
