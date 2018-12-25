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
    <title>用例详情</title>
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
                            <input type="text" id="caseName" name="caseName" value="${cases.caseName}" class="form-control" columWidthClass="col-xs-8" disabled="disabled"  />
                            <input type="hidden" id="caseId" name="caseId" value="${cases.caseId}"/>
                        </div>
                        <div class="form-group">
                            <label class="from-control col-xs-4">所属系统</label>
                            <select class="form-control" columWidthClass="col-xs-8" id="sysId" name="sysId" disabled="disabled" >
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
                            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8" disabled="disabled">${cases.remark}</textarea>
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
                                            <input type="text" value="${caseParam.key}" name="paramName" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
                                            <label class="from-control col-xs-2 control-label">参数值</label>
                                            <input type="text" value="${caseParam.value}" name="paramValue" class="form-control" columwidthclass="col-xs-3" disabled="disabled">
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
                    <th>访问地址</th>
                    <th>执行顺序</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
        <!-- /.box-body -->
    </div>

    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
    </div><!-- /.box-footer -->
</form>

<footer>

    <script type="text/javascript">
        var me = commonErp.getInstance();
        var datatable = null;
        var tableUtil = new TableUtil("datatable");
        $(document).ready(function() {
            $(function () {
                $('#collapseTwo').collapse('hide')
            });
            $(function () {
                $('#collapseOne').collapse('show')
            });

            var aButtons = [];

            datatable = me.initEmptyDataTable($('#datatable'), "", "", aButtons, 100);
            //查询用例接口列表信息
            queryInterfaceByCase();
        });

        //查询用例下接口信息
        function queryInterfaceByCase(){
            var caseform = $('#caseform').serialize();
            var url = "/interface/queryInterfaceByCase";
            me.postAjaxCallBack(caseform,url,queryCallBack,"");
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
                for (var i=0;i<interfaceInfo.length;i++){
                    interfaceId = interfaceInfo[i].interfaceId;
                    var order = interfaceInfo[i].interfaceOrder+'<input type="hidden" name="'+interfaceId+'" value="'+interfaceInfo[i].interfaceOrder+'">';
                    table.row.add([
                        interfaceId,
                        interfaceInfo[i].name,
                        interfaceInfo[i].typeStr,
                        interfaceInfo[i].url,
                        order,
                    ] ).draw();
                }
            }else{
                alertBootbox("获取用例接口列表信息失败！");
                return;
            };
        }

    </script>
</footer>
</body>
</html>
