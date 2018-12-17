<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="_csrf" content="${_csrf.token}"/>
  <meta name="_csrf_header" content="${_csrf.headerName}"/>
  <title>MinZone | Blog</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="components/bootstrap/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="components/AdminLTE/css/AdminLTE.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="components/AdminLTE/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="components/layui/css/layui.css">
  <link rel="stylesheet" href="components/daterangepicker/daterangepicker.css">
  <!-- custom css -->
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="../dist/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body class="hold-transition skin-red sidebar-mini">
<div class="wrapper">

  <!-- 动态加载标题栏 -->
  <jsp:include page="header.jsp" flush="true" />

  <!-- 动态加载导航栏 -->
  <jsp:include page="navigation.jsp" flush="true" />
  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>查询标签</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">查询标签</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		<div class="panel panel-default">
			<div class="panel-body">
				<div class="searchArea">
					<form class="form-inline" role="form">
						<div class="row">
							<div class="form-group col-md-5">
								<label class="control-label">创建时间:</label>
								<div class="input-group">
									<!-- AdminLTE样式，导致输入框直角 -->
						            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
						            <input id="daterange" name="daterange" type="text" class="form-control" placeholder="不限时间" size="20"/>
						            <span id="removeBtn" class="input-group-addon"><i class="fa fa-remove"></i></span>
						     	</div>
							</div>
							<div class="form-group col-md-offset-5 col-md-2">
								<button type="button" class="btn btn-success" id="searchBtn">查询</button>
							</div>
						</div>
					</form>
				</div>
				<!-- /.searchArea -->
				<!-- 全部分类检索 -->
				<table id="allTable" lay-filter="allTable" style="width:100%"></table>
	    	</div>
		</div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <!-- 动态加载尾部栏 -->
  <jsp:include page="footer.jsp" flush="true" />

</div>
<!-- ./wrapper -->

<script type="text/html" id="optionBar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>

<!-- jQuery 1.12.4 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<script src="components/layui/layui.js"></script>
<script src="components/daterangepicker/moment.min.js"></script>
<script src="components/daterangepicker/daterangepicker.js"></script>
<!-- custom jQuery -->
<script src="custom/js/zblog.js"></script>
<script type="text/javascript">
	var table;//全局表格
	var allTable;//所有文章
	
	$(function(){
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		//设置全局ajax请求头
		$.ajaxSetup({ 
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		
		//初始化导航栏
		initNavBarStatus("tagMenu", "queryTagMenu");
		//初始化日历控控件
		initDateRange();
		
		//初始化表格
		layui.use('table', function(){
		  table = layui.table;
		  
		  allTable = table.render({
			id: 'layAllTable'
		    ,elem: '#allTable'
		    ,url:'tag/query'
		    ,limit: 10 //每页默认显示的数量
		    ,method:'post'  //提交方式
		    ,title: '标签数据表'
		    ,cellMinWidth: 100
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'tagName', title:'标签名称', width:'20%'}
		      ,{field:'description', title:'标签描述', width:'30%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'20%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,done: function(res, curr, count){
		    	table.resize('layAllTable');
			}
		  });
		  
		  //监听行工具事件
		  table.on('tool(allTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的删除行么，无法恢复', function(index){
		        var map = {
					'tagId':data.id
			   	};
		      	//ajax请求后端
		        deleteLayerObjAjax("tag/delete", obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		    	//跳转到编辑页面
		      	window.location.href = "tag/editorPage?tagId="+data.id;
		    }
		  });
		});
		
		//点击查询，获取参数，查询分页数据
		$("#searchBtn").click(function(){
			//创建时间
			var daterange = $("#daterange").val();
			var startDate;
			var endDate;
			if(daterange == null || daterange == undefined || daterange == 0){
				startDate = null;
				endDate = null;
			}else{
				var index = daterange.indexOf('至');
				if(index > -1){
					startDate = $.trim(daterange.substring(0, index));
					endDate = $.trim(daterange.substring(index+1));
				}else{
					startDate = null;
					endDate = null;
				}
			}
			
			//重新加载数据
			allTable.reload({
				page: {
					curr: 1
				},
				where:{
					'startDate':startDate,
					'endDate':endDate
				},
				done: function(res, curr, count){
					table.resize('layAllTable');
				}
			});
		});
		
		//点击日期删除按钮，清除日期
		$("#removeBtn").click(function(){
			$('input[name="daterange"]').val('');
		});
	});
	
	function initDateRange(){
		var locale = {
			"format": 'YYYY-MM-DD',
			"separator": " 至 ",
			"applyLabel": "确定",
			"cancelLabel": "取消",
			"resetLabel": "重置",
			"fromLabel": "起始时间",
			"toLabel": "结束时间'",
			"customRangeLabel": "自定义",
			"weekLabel": "W",
			"daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
			"monthNames": ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
			"firstDay": 1
		};
		
		$('input[name="daterange"]').daterangepicker({
			"locale": locale,
			"ranges" : {
				'最近1小时': [moment().subtract(1, 'hours'), moment()],
				'今日': [moment().startOf('day'), moment()],
				'昨日': [moment().subtract(1, 'days').startOf('day'), moment().subtract(1, 'days').endOf('day')],
				'最近7日': [moment().subtract(6, 'days'), moment()],
				'最近30日': [moment().subtract(29, 'days'), moment()],
				'本月': [moment().startOf("month"),moment().endOf("month")],
				'上个月': [moment().subtract(1,"month").startOf("month"),moment().subtract(1,"month").endOf("month")]
			},
			"opens":"right",
			"showDropdowns": true,
			"autoUpdateInput":false
		}, function(start, end, label) {
			//console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ",Label:"+label);
			this.element.val(this.startDate.format(this.locale.format) + this.locale.separator + this.endDate.format(this.locale.format));
		});
	}
</script>
</body>
</html>
