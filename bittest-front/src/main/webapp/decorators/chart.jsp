<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property='title' /></title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
	<script type="text/javascript">
		var contextPath='<%=request.getContextPath()%>';
	</script>
	<link rel="icon" href="//www.bit.com/favicon.ico" mce_href="//www.bit.com/favicon.ico" type="image/x-icon">
	<link rel="bookmark" type="image/x-icon" href="//www.bit.com/favicon.ico">
	<link rel="shortcut icon" type="image/x-icon" href="//www.bit.com/favicon.ico">
	<link href="/static/adminLTE/css/font-awesome.min.css" rel="stylesheet" />
	<link href="/static/adminLTE/css/ionicons.min.css" rel="stylesheet" />
	<link href="/static/adminLTE/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/static/adminLTE/css/AdminLTE.min.css?v=2" rel="stylesheet" />
	<link href="/static/adminLTE/js/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
	<link href="/static/adminLTE/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	<link href="/static/adminLTE/js/plugins/datatables/extensions/TableTools/css/dataTables.tableTools.min.css" rel="stylesheet" type="text/css" />
	<link href="/static/adminLTE/js/plugins/validation/bootstrapValidator.css" rel="stylesheet" />
	<link href="/static/adminLTE/css/erp_common.css?v=2" rel="stylesheet" />
    <script src="/static/adminLTE/js/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/dataTables.bootstrap.oem.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/extensions/Pagination/dataTables.pagination.bootstrap.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/datatable/dataTables.tableTools.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/app.min.js" type="text/javascript"></script>
   <script src="/static/adminLTE/js/echarts-all.js?t=1" type="text/javascript"></script>
   <!--  <script src="/static/adminLTE/js/echarts-all.2.2.7.js?t=1" type="text/javascript"></script> -->
    <script src="/static/adminLTE/extend/common.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/erp_common.js?t=1" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/authControl.js" type="text/javascript"></script>
    
    
<sitemesh:write property='head'/>
</head>
<body class="sidebar-mini skin-yellow-light">
<section class="content">
<div class="row">
	<sitemesh:write property='body' />
</div>
</section>
<sitemesh:write property='footer'/>  
</body>
</html>
