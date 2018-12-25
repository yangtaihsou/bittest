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

	<link rel="icon" href="//www.bit.com/favicon.ico" type="image/x-icon">
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
	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <script src="/static/adminLTE/js/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/app.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/jquery.dataTables.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/dataTables.bootstrap.oem.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datatables/extensions/Pagination/dataTables.pagination.bootstrap.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/datatable/dataTables.tableTools.min.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/validation/bootstrapValidator.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/modal/modal.manager.plugin1.0.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/modal/jshow.utils.js" type="text/javascript"></script>
	<script src="/static/adminLTE/extend/map.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/common.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/erp_common.js?t=1" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/tabs.js" type="text/javascript"></script>
    <script src="/static/adminLTE/extend/authControl.js" type="text/javascript"></script>
    
    <!-- date -->
    <link href="/static/adminLTE/js/plugins/datepicker/datepicker3.css" rel="stylesheet" />
    <script src="/static/adminLTE/js/plugins/datepicker/bootstrap-datepicker.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js" type="text/javascript"></script>
    <link href="/static/adminLTE/js/plugins/daterangepicker/daterangepicker-bs3.css" rel="stylesheet" />
    <script src="/static/adminLTE/js/plugins/daterangepicker/moment.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/daterangepicker/daterangepicker.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/plugins/daterangepicker/daterangepicker.ext.js" type="text/javascript"></script>
	
	<script type="text/javascript">
	var contextPath = "<%=request.getContextPath()%>";
	$(function(){
		$("#queryform").keydown(function(){
			if(event.keyCode == 13){query();}
		});
		$('div.singledate').each(function(){
			var format = $(this).find("input.datepicker").attr("format");
			$(this).datepicker({
				format: format.toLowerCase(),
			    calendarWeeks: false,
			    autoclose: true,
			    language: "zh-CN",
			    clearBtn:true,
			    todayHighlight: true,
			    enableOnReadonly:false		    
			}).on("hide",function(e){
				try{
					var myform = $("form");
					var id = $(this).find("input").attr("id");
					var bValidator = $(myform).data('bootstrapValidator');
					if (bValidator != null) {
						bValidator.updateStatus(id, 'NOT_VALIDATED',null)
						  .validateField(id); 
					}
					
				}catch(e1){
					console.log(e1);
				}
			});
		});
		$('div.rangedate').each(function(){
			daterangepickerext(this);
		});
		$(document).ready(function(){
			$(".pagination").find("li").click(function(){
				var li = $(this);
				var a = $(li).find("a");
				if(a){
					var url = a.attr("src");
					var form = $(li).parents("form.paginateForm");
					form.action = url;
					if(url.indexOf("?") > -1){
						var paramstr = url.substring(url.indexOf("?")+1,url.length);
						var paramsarr = paramstr.split("&");

						for(var i = 0; i < paramsarr.length; i ++){
							var param = paramsarr[i];
							var key = param.split("=")[0];
							var value = param.split("=")[1];
							$(form).append("<input type='hidden' name='"+key+"' value='"+value+"'>");
						}
					}
					form.submit();
				}
			});
		});
	});
	function query(){}
	</script>
	<sitemesh:write property='head'/>
</head>
<body class="sidebar-mini skin-yellow-light">
	<div class="wrapper">
		 <!-- Main content -->
        <section class="content">
	  		<sitemesh:write property='body' />
	  	</section>
	</div>
<sitemesh:write property='footer'/>
</body>
</html>
