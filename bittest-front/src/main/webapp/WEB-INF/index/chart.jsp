<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="erp" uri="/WEB-INF/taglib.tld" %>
<html>

<head>
<title>首界面</title>
    <style>
        *{ margin:0; padding:0;list-style: none;}
        body {font:12px/1.5 Tahoma;}
        #outer {width:100%;margin:0px auto;}
        #tab {overflow:hidden;zoom:1;background:white;border-bottom:3px solid #d2d6de;}
        #tab li {float:left;color:black;height:30px;	cursor:pointer;	line-height:30px;padding:0 20px; }
        #tab li.current {font-weight: 800; color:#000;background:#ccc;}
        #content {border:0px solid #000;border-top-width:0;}
    </style>

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
</head>
<body>
<section class="content">
    <%--<div class="row" style="height:20%;width:90%;margin:0 auto">--%>
        <%--<div class="col-sm-12"  style="height: 20%"></div>--%>
		<%--<div class="col-sm-6">--%>
            <%--<!-- small box -->--%>
            <%--<div class="small-box bg-aqua">--%>
                <%--<div class="inner">--%>
                    <%--<h4>${chart.taskCount}</h4>--%>
                    <%--<p>任务计划总数</p>--%>
                <%--</div>--%>
                <%--<div class="icon">--%>
                    <%--<i class="ion ion-stats-bars"></i>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<!-- ./col -->--%>
        <%--<div class="col-sm-6" >--%>
            <%--<!-- small box -->--%>
            <%--<div class="small-box bg-green">--%>
                <%--<div class="inner">--%>
                    <%--<h4>${chart.caseCount}</h4>--%>
                    <%--<p>测试用例总数</p>--%>
                <%--</div>--%>
                <%--<div class="icon">--%>
                    <%--<i class="ion ion-stats-bars"></i>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
	<div class="row">
			<div class="col-lg-3 col-xs-3">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>${chart.userCount}</h3>
						<p>系统用户总数</p>
					</div>
					<div class="icon">
						<i class="ion ion-person-add"></i>
					</div>
					<a href="#" onclick="countList();" class="small-box-footer">统计详情<i class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-3">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner">
						<h3>${chart.taskCount}<sup style="font-size: 20px"></sup></h3>
						<p>系统任务总数</p>
					</div>
					<div class="icon">
						<i class="ion ion-stats-bars"></i>
					</div>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-3">
				<!-- small box -->
				<div class="small-box bg-yellow">
					<div class="inner">
						<h3>${chart.caseCount}</h3>
						<p>系统用例总数</p>
					</div>
					<div class="icon">
						<i class="ion ion-bag"></i>
					</div>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-3 col-xs-3">
				<!-- small box -->
				<div class="small-box bg-red">
					<div class="inner">
						<h3>${chart.interfaceCount}</h3>
						<p>系统接口总数</p>
					</div>
					<div class="icon">
						<i class="ion ion-pie-graph"></i>
					</div>
				</div>
			</div>
			<!-- ./col -->
		</div>
	<%--<div class="row">--%>
		<%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
			<%--<div class="info-box" style="background-color: azure">--%>
				<%--<span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>--%>

				<%--<div class="info-box-content">--%>
					<%--<span class="info-box-text">CPU Traffic</span>--%>
					<%--<span class="info-box-number">90<small>%</small></span>--%>
				<%--</div>--%>
				<%--<!-- /.info-box-content -->--%>
			<%--</div>--%>
			<%--<!-- /.info-box -->--%>
		<%--</div>--%>
		<%--<!-- /.col -->--%>
		<%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
			<%--<div class="info-box" style="background-color: azure">--%>
				<%--<span class="info-box-icon bg-red"><i class="fa fa-google-plus"></i></span>--%>

				<%--<div class="info-box-content">--%>
					<%--<span class="info-box-text">Likes</span>--%>
					<%--<span class="info-box-number">41,410</span>--%>
				<%--</div>--%>
				<%--<!-- /.info-box-content -->--%>
			<%--</div>--%>
			<%--<!-- /.info-box -->--%>
		<%--</div>--%>
		<%--<!-- /.col -->--%>

		<%--<!-- fix for small devices only -->--%>
		<%--<div class="clearfix visible-sm-block"></div>--%>

		<%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
			<%--<div class="info-box" style="background-color: azure">--%>
				<%--<span class="info-box-icon bg-green"><i class="ion ion-ios-cart-outline"></i></span>--%>

				<%--<div class="info-box-content">--%>
					<%--<span class="info-box-text">Sales</span>--%>
					<%--<span class="info-box-number">760</span>--%>
				<%--</div>--%>
				<%--<!-- /.info-box-content -->--%>
			<%--</div>--%>
			<%--<!-- /.info-box -->--%>
		<%--</div>--%>
		<%--<!-- /.col -->--%>
		<%--<div class="col-md-3 col-sm-6 col-xs-12">--%>
			<%--<div class="info-box" style="background-color: azure">--%>
				<%--<span class="info-box-icon bg-yellow"><i class="ion ion-ios-people-outline"></i></span>--%>

				<%--<div class="info-box-content">--%>
					<%--<span class="info-box-text">New Members</span>--%>
					<%--<span class="info-box-number">2,000</span>--%>
				<%--</div>--%>
				<%--<!-- /.info-box-content -->--%>
			<%--</div>--%>
			<%--<!-- /.info-box -->--%>
		<%--</div>--%>
		<%--<!-- /.col -->--%>
	<%--</div>--%>
    <div class="row" style="height:60%;margin-top: 50px;">
		<div class="row" style="width:100%;margin:0 auto">
			<div class="col-sm-6" style="height: 100%;" id="successChart"></div>
			<div class="col-sm-6" style="height: 100%;" id="systemChart"></div>
			<script type="text/javascript">
				var taskOption = {
					color: [ '#00A65A'],
//					backgroundColor: '#336699',//背景色
					tooltip: {
						trigger: 'axis'
					},
					title : {
						text:'最近10次任务执行情况',
						x:'center',
						align: 'right'
					},
					legend: {
						data:['成功率'],
						top:'25px'
					},
					grid: {
						left: '3%',
						right: '3%',
						bottom: '30%',
						containLabel: true
					},
					xAxis: [
						{
							type: 'category',
							data: ${x},
							axisLabel: {
								interval:0,
								rotate:40
							}
						}
					],
					yAxis: [
						{
							type: 'value'
						}
					],
					series: [
						{
							name:'成功率',
							type:'bar',
							data:${chart.taskResultRateTopTen},
							center:'left',
							barWidth : 30
						}
					]

				};
				var taskChart = echarts.init(document.getElementById('successChart'));
				taskChart.setOption(taskOption);

				var systemOption = {
					color: [ '#00C0EF'],
//					backgroundColor: '#336699',//背景色
					tooltip: {
						trigger: 'axis'
					},
					title : {
						text:'系统用例数量统计',
						x:'center',
						align: 'right'
					},
					legend: {
						data:['用例数量'],
						top:'25px'
					},
					grid: {
						left: '3%',
						right: '3%',
						bottom: '30%',
						containLabel: true
					},
					xAxis: [
						{
							type: 'category',
							data: ${systems},
							axisLabel: {
								interval:0,
								rotate:40
							}
						}
					],
					yAxis: [
						{
							type: 'value'
						}
					],
					series: [
						{
							name:'用例数量',
							type:'bar',
							data:${systemCaseCounts},
							barWidth : 30
						}
					]

				};
				var systemChart = echarts.init(document.getElementById('systemChart'));
				systemChart.setOption(systemOption);

				function countList(){
					var tabid = "COUNT_LIST";
					var title = "用户统计";
					var url = "<%=request.getContextPath()%>/toQueryCountList";
					window.parent.addTabs(tabid , title , url , true);

				}
			</script>
		</div>
    </div>
</section>
</body>
<footer>
</footer>
</html>
