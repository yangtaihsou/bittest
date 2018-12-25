<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<title>接口自动化测试平台</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
	<link rel="icon" href="//www.bit.com/favicon.ico" mce_href="//www.bit.com/favicon.ico" type="image/x-icon">
	<link rel="bookmark" type="image/x-icon" href="//www.bit.com/favicon.ico">
	<link rel="shortcut icon" type="image/x-icon" href="//www.bit.com/favicon.ico">
	<link href="/static/adminLTE/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/static/adminLTE/css/AdminLTE.min.css?v=2" rel="stylesheet" />
	<link href="/static/adminLTE/css/font-awesome.min.css" rel="stylesheet">
	<link href="/static/adminLTE/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	<link href="/static/adminLTE/css/erp_common.css?v=2" rel="stylesheet" />
    <script src="/static/adminLTE/js/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/static/adminLTE/js/app.erp.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/jQueryUI/jquery.contextmenu.r2.js" type="text/javascript"></script>
	<script src="/static/adminLTE/js/plugins/bootbox/bootbox.min.js" type="text/javascript"></script>
		<!-- mask -->
	<link href="/static/plugins/loadmask/jquery.loadmask.css" rel="stylesheet" />
    <script src="/static/plugins/loadmask/jquery.loadmask.min.js" type="text/javascript"></script>
    
	<script src="/static/adminLTE/extend/common.js" type="text/javascript"></script>
	<script src="/static/adminLTE/extend/tabs.js" type="text/javascript"></script><%--
	<script src="/static/js/erp_common.js" type="text/javascript"></script>
	
	
--%></head>
<body id="framBodyID" class="skin-blue sidebar-mini" style="overflow-y:hidden">
	<div class="wrapper">
	  <header class="main-header">
        <a href="#"  onclick="refresh();" class="logo">
          <span class="logo-mini"><b>测试平台</b></span>
          <span class="logo-lg"><b>测试平台</b></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
				<font size="4" >&nbsp;&nbsp;<span class="hidden-xs">bittest测试平台</span></font>
			</a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav"> 
			  <li class="dropdown user user-menu">
             	 <a href="#" class="dropdown-toggle">
                  <%--<span class="hidden-xs">${pin}，欢迎登录！</span>--%>
                </a>
              </li>
              <li>
              	<%--<a class="dropdown-toggle" href="/logout" class="btn btn-default btn-flat">
              		<i class="fa fa-sign-out"></i>
              		退出
              	</a>--%>
              </li>
            </ul>
          </div>
       	  </nav>
	 </header>
	 <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
          <!-- sidebar menu: : style can be found in sidebar.less -->
          <div id="menuContainer" style="overflow-y:auto;">
          <ul class="sidebar-menu tree" data-widget="tree">
          	 <%--<c:forEach items="${menus}" var="menu" varStatus="s">--%>
          	 		<%--<c:if test="${menu.parentCode eq systemCode}">--%>
						 <%--<li class="treeview">--%>
	           			 <%--<a href="javascript:void(0)"><i class="fa ${menu.resourceIcon}"></i><span>${menu.resourceName}</span><i class="fa fa-angle-left pull-right"></i></a>--%>
	            		 <%--<ul class="treeview-menu">--%>
	            		 	<%--<c:forEach items="${menus}" var="m1" varStatus="s1">--%>
	            		 		<%--<c:if test="${m1.parentCode eq menu.resourceCode}">--%>
									<%--<c:if test="${m1.nodeType eq 3}">--%>
										<%--<li class="link" link="${m1.resourceUrl}" mid="${m1.resourceCode}"><a href="javascript:void(0)"><i class="fa ${m1.resourceIcon}"></i>${m1.resourceName}</a></li>--%>
									<%--</c:if>--%>
				          		<%--</c:if>--%>
	            		 	<%--</c:forEach>--%>
	            		 <%--</ul>--%>
	            		 <%--</li>--%>
					<%--</c:if>--%>
	        <%--</c:forEach>--%>
				 <li class="treeview menu-open active">
					 <a href="javascript:void(0)">
						 <i class="fa fa-folder">
						 </i>
						 <span class="pull-right-container">我的测试
						 </span>
						 <i class="fa fa-angle-left pull-right">
						 </i>
					 </a>
					 <ul class="treeview-menu menu-open" style="display: block;">

						 <li class="link" link="interface/toQueryInterfaceList" mid="INTERFACE_LIST">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 接口管理
							 </a>
						 </li>
						 <li class="link" link="case/toQueryCaseList" mid="CASE_PAGE_LIST">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 用例管理
							 </a>
						 </li>
						 <li class="link" link="task/toTaskList" mid="TASK_PAGE_LIST">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 任务管理
							 </a>
						 </li>

						 <li class="link" link="result/toQueryTaskResultList" mid="RESULT_TASK">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 测试结果
							 </a>
						 </li>
						 <li class="link" link="timerTaskConfig/toList" mid="TIMER_TASK_CONFIG">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 任务定时执行配置
							 </a>
						 </li>

						 <li class="link" link="system/toSystemList" mid="SYSTEM_PAGE_LIST">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 系统管理
							 </a>
						 </li>
						 <li class="link" link="database/toDataBaseList" mid="DATABASE_PAGE_LIST">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 数据库管理
							 </a>
						 </li>

					 </ul>
				 </li>
				 <li class="treeview menu-open">
					 <a href="javascript:void(0)">
						 <i class="fa fa-folder">
						 </i>
						 <span class="pull-right-container">数据统计
						 </span>
						 <i class="fa fa-angle-left pull-right">
						 </i>
					 </a>
					 <ul class="treeview-menu">
						 <li class="link" link="<%=request.getContextPath()%>/chart" mid="DATA">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 数据统计
							 </a>
						 </li>

						 <li class="link" link="timerTask/toList" mid="TIMER_TASK">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 任务定时执行列表
							 </a>
						 </li>
						 <li class="link" link="asyn/toQueryNotifyList" mid="NOTIFY">
							 <a href="javascript:void(0)">
								 <i class="fa fa-circle-o">
								 </i>
								 异步通知列表
							 </a>
						 </li>
					 </ul>
				 </li>
          </ul>
          </div>
        </section>
        <!-- /.sidebar -->
	</aside>
	  <div class="content-wrapper">
	  	 <ul class="nav nav-tabs" id="indexTab">
		</ul>
		<div class="tab-content" id="indexTabContent">
		</div>
		<div class="contextMenu" id="tabMenuMenu">
		      <ul>
		        <li id="refresh">刷新</li>
		        <li id="closeSelf">关闭本页</li>
		        <li id="closeAll">关闭全部</li>
		        <li id="closeOther">关闭其他</li>
		        <li id="cancel">取消</li>
		      </ul>
		 </div>
		 <div class="contextMenu" id="tabMenuMenuFirst">
		      <ul>
		        <li id="refresh">刷新</li>
		        <li id="cancel">取消</li>
		      </ul>
		 </div>
	  </div>
	</div>
	<jsp:include page="common/modal.jsp"></jsp:include>
	
	
<script>

var userPin = "${pin}";
var currentRoles = "${currentRoles}";


$(function () {
	
	console.log(currentRoles);
	
	var h = $(document.body).height();
	if(h < 780 ){
		$("#menuContainer").height(h - 200);//菜单加滚动条
	}else{
		$("#menuContainer").height(h - 180);//菜单加滚动条
	}
	
	//登陆后点击一个首页,且首页不允许关闭
	//addTabs(-100,"系统看板",'<%=request.getContextPath()%>/chart',false);
	addTabs(-100,"接口测试工具",'<%=request.getContextPath()%>/interface/interfaceTest',false);
	$(".sidebar-menu li.link").click(function(){
		var tabs = $("#indexTab li").length;
		if(tabs >= 20){
			var message='打开页面过多，可能导致系统缓慢，请关闭一些菜单再打开';
			bootbox.alert({title:'温馨提示',message:message,className: "alert-modal",buttons: { ok: {label: '确定', className: 'btn-success'}}});
			return;
		}
		var li = $(this);
		var link = li.attr("link");
		var title = li.find("a").text();
		var id = li.attr("mid");
		if(id != -100){
			if($("#tab_" + id).length > 0){
				if($("#indexTab li.tab_"+id).length == 0){
					$(".nav-tabs").append($("#tab_" + id));
					 $(".tab-content").append($("#tabpanel_" + id));
					 $(".active").removeClass("active");
					 addTabMenu($("#tab_" + id));
					 var url = '<%=request.getContextPath()%>'+link;
					 $("#tabpanel_" + id).find("iframe").attr("src",timestamp(url));
					 $("#tab_" + id).on("click", function () {	
						var li = $(this);
						var id = $(li).attr("id").replace("tab_","");
						 $("div.active").removeClass("active");
						 $("#tabpanel_" + id).addClass("active");
				     });
					 console.log(id);
				}
				$(".active").removeClass("active");
				$("#tab_" + id).addClass('active');
				$("#tabpanel_" + id).addClass("active");
			}else{
				addTabs(id,title,'<%=request.getContextPath()%>'+link,true);
			}
		}
	});
});

function refresh(){
	 window.location.reload();
}
function formMask(){
	
	$($("#framBodyID" , top.document)[0]).mask("Waiting...");
}	
function formUnmask(){
	$($("#framBodyID" , top.document)[0]).unmask();
}

function getCurrentRoles(){
	if(currentRoles == null || currentRoles == undefined){
		return "";
	}else{
		return currentRoles;
	}
	
}


</script>
</body>
</html>
