<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>
<head>
    <link href="/static/css/common.css" rel="stylesheet" />
    <script src="/static/adminLTE/extend/tableUtil.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/styleCore.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/dateUtil.js" type="text/javascript"></script>
    <title>系统详情</title>
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
    <input type="hidden" name="systemId" value="${systemVo.systemId}">
    <div class="box-body">
        <div class="form-group">
            <label class="from-control col-xs-4">系统名称</label>
            <input type="text" id="systemName" name="systemName" class="form-control" value="${systemVo.systemName}" columWidthClass="col-xs-8" required data-bv-notempty-message="系统名称不能为空" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">系统域名</label>
            <input type="text" id="domainName" name="domainName" class="form-control" value="${systemVo.domainName}" columWidthClass="col-xs-8" required data-bv-notempty-message="系统名称不能为空" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">邮件地址</label>
            <input type="text" id="ip" name="ip" class="form-control" value="${systemVo.ip}" columWidthClass="col-xs-8" required data-bv-notempty-message="系统名称不能为空" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">IP</label>
            <input type="text" id="domainParam" name="domainParam" class="form-control" value="${systemVo.domainParam}" columWidthClass="col-xs-8" required data-bv-notempty-message="系统名称不能为空" disabled="disabled" />
        </div>
        <div class="form-group">
            <label class="from-control col-xs-4">备&nbsp;&nbsp;&nbsp;&nbsp;注</label>
            <textarea id="remark" name="remark"  class="form-control" rows="3" columWidthClass="col-xs-8" disabled="disabled">${systemVo.remark}</textarea>
        </div>
    </div><!-- /.box-body -->
    <div class="box-footer">
        <button type="button" id="btmCancel" class="btn pull-right btn-cancel" data-dismiss="modal" onclick="window.parent.closeBootbox();">取&nbsp;&nbsp;消</button>
    </div><!-- /.box-footer -->
</form>
</body>
<footer>
</footer>
</html>
