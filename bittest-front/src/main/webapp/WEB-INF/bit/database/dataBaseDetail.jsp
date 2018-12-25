<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>数据库详情</title>
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
            <input type="text" id="name" name="name" class="form-control" columWidthClass="col-xs-8" value="${database.name}" disabled="disabled" />
            <input type="hidden" id="databaseId" name="databaseId" class="form-control" columWidthClass="col-xs-8" value="${database.databaseId}" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">数据库类型</label>
            <select class="form-control" columWidthClass="col-xs-4"  disabled="disabled">
                <option>MySql</option>
            </select> <span class="label label-warning">抱歉，目前只支持MySql数据库</span>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">数据库连接</label>
            <input type="text" id="url" name="url" class="form-control" columWidthClass="col-xs-6" value="${database.url}" required  placeholder="例如:172.0.0.1:3306/库名" disabled="disabled"/>
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">用户名</label>
            <input type="text" id="username" name="username" class="form-control" columWidthClass="col-xs-8" value="${database.username}" disabled="disabled"  />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">密&nbsp;&nbsp;&nbsp;&nbsp;码</label>
            <input type="password" id="password" name="password" class="form-control" columWidthClass="col-xs-8" value="${database.password}" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark" class="form-control" rows="3" columWidthClass="col-xs-8" disabled="disabled">${database.remark}</textarea>
        </div>
    </div><!-- /.box-body -->
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
    </div><!-- /.box-footer -->
</form>

<footer>
</footer></body>
</html>
