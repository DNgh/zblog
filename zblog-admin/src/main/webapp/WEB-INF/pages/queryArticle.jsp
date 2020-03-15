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
      <h1>查询文章</h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-home"></i> 主页</a></li>
        <li class="active">查询文章</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
		<ul id="articleTab" class="nav nav-tabs">
			<li class="active"><a href="#all" data-toggle="tab">全部</a></li>
			<li><a href="#publish" data-toggle="tab">已发布</a></li>
			<li><a href="#draft" data-toggle="tab">草稿箱</a></li>
			<li><a href="#trash" data-toggle="tab">回收箱</a></li>
		</ul>
		<div id="articleTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="all">
				<div class="panel panel-default">
					<div class="panel-body">
						<div class="searchArea">
							<form class="form-inline" role="form">
								<div class="row">
									<div class="form-group col-sm-5">
										<label class="control-label">创建时间:</label>
										<select id="year" class="form-control">
											<!-- 年 -->
											<option value="0" selected>不限年份</option>
							        		<!-- /.年 -->
										</select>
										<select id="month" class="form-control">
											<!-- 月 -->
											<option value="0" selected>不限月份</option>
							        		<!-- /.月 -->
										</select>
									</div>
									<div class="form-group col-sm-5">
										<label class="control-label">分类:</label>
										<select id="category" class="form-control">
											<!-- 分类 -->
											<option value="" selected>不限分类</option>
											<c:forEach items="${categoryInfoList}" var="categoryInfo">  
												<option value="${categoryInfo.id}">${categoryInfo.categoryName}</option>
											</c:forEach>
							        		<!-- /.分类 -->
										</select>
									</div>
									<div class="form-group col-sm-2">
										<button type="button" class="btn btn-success" id="searchBtn">查询</button>
									</div>
								</div>
							</form>
						</div>
						<!-- /.searchArea -->
						<!-- 全部文章检索 -->
						<table id="allTable" lay-filter="allTable" style="width:100%"></table>
            		</div>
				</div>
			</div>
			<div class="tab-pane fade" id="publish">
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- 已发布文章检索 -->
						<table id="publishTable" lay-filter="publishTable" style="width:100%"></table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="draft">
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- 草稿箱文章检索 -->
						<table id="draftTable" lay-filter="draftTable" style="width:100%"></table>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="trash">
				<div class="panel panel-default">
					<div class="panel-body">
						<!-- 回收箱文章检索 -->
						<table id="trashTable" lay-filter="trashTable" style="width:100%"></table>
					</div>
				</div>
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

<script type="text/html" id="repOptionBar">
  <a class="layui-btn layui-btn-xs" lay-event="edit">重新发布</a>
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
<!-- custom jQuery -->
<script src="custom/js/zblog.js"></script>
<script type="text/javascript">
	var table;//全局表格
	var allTable;//所有文章
	var publishTable;//已发布文章
	var draftTable;//已保存文章，未发布
	var trashTable;//已删除，放入回收箱
	var monthMap;//月份map，key为年，value为月
	var yearList;//年list
	
	$(function(){
		var header = $("meta[name='_csrf_header']").attr("content");
		var token = $("meta[name='_csrf']").attr("content");
		//设置全局ajax请求头
		$.ajaxSetup({ 
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			}
		});
		
		//初始化左侧导航栏，选中“文章管理”-“查询文章”
		initNavBarStatus("articleMenu", "queryArticleMenu");
		//初始化参数(查询条件：年、月等参数)
		initParam();
		
		//初始化表格
		layui.use('table', function(){
		  table = layui.table;
		  
		  allTable = table.render({
			id: 'layAllTable'
		    ,elem: '#allTable'
		    ,url:'article/query'
		    ,where:{
		    	state:'ALL'//所有文章
		    }
		    ,limit: 10 //每页默认显示的数量
		    ,method:'post'  //提交方式
		    ,title: '文章数据表'
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,loading: true
		    ,done: function(res, curr, count){
		    	table.resize('layAllTable');
			}
		  });//allTable end
		  
		  publishTable = table.render({
			id: 'layPublishTable'
			,elem: '#publishTable'
		    ,url:'article/query'
		    ,where:{
		    	state:'PUBLISH'//已发布文章
		    }
		    ,limit: 10 //每页默认显示的数量
	        ,method:'post'  //提交方式
	        ,title: '文章数据表'
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,loading: true
		    ,done: function(res, curr, count){
		    	table.resize('layPublishTable');
			}
		  });//publishTable end
		  
		  draftTable = table.render({
			id: 'layDraftTable'
		    ,elem: '#draftTable'
		    ,url:'article/query'
		    ,where:{
		    	state:'CREATE'//草稿箱
		    }
		    ,limit: 10 //每页默认显示的数量
	        ,method:'post'  //提交方式
	        ,title: '文章数据表'
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,loading: true
		    ,done: function(res, curr, count){
		    	table.resize('layDraftTable');
			}
		  });//draftTable
		  
		  trashTable = table.render({
			id: 'layTrashTable'
		    ,elem: '#trashTable'
		    ,url:'article/query'
		    ,where:{
		    	state:'DELETE'//回收箱
		    }
		    ,limit: 10 //每页默认显示的数量
	        ,method:'post'  //提交方式
	        ,title: '文章数据表'
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#repOptionBar'}
		    ]]
		    ,page: true
		    ,loading: true
		    ,done: function(res, curr, count){
		    	table.resize('layTrashTable');
			}
		  });//trashTable end
		  
		  //监听行工具事件
		  table.on('tool(allTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的删除行么，无法恢复', function(index){
		        var map = {
					'articleId':data.id,
					'realDelete':0
			   	};
		      	//ajax请求后端
		        deleteLayerObjAjax("article/delete", obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		    	//跳转到编辑页面
		      	window.location.href = "article/editorPage?articleId="+data.id;
		    }
		  });
		  
		  table.on('tool(publishTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的放入回收箱', function(index){
			     var map = {
					'articleId':data.id,
					'realDelete':2
			 	 };
			   	 //ajax请求后端
			     deleteLayerObjAjax("article/delete", obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		    	//跳转到编辑页面
		      	window.location.href = "article/editorPage?articleId="+data.id;
		    }
		  });
		  
		  table.on('tool(draftTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的放入回收箱', function(index){
			     var map = {
					'articleId':data.id,
					'realDelete':2
			 	 };
			   	 //ajax请求后端
			     deleteLayerObjAjax("article/delete", obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		    	//跳转到编辑页面
		      	window.location.href = "article/editorPage?articleId="+data.id;
		    }
		  });
		  
		  table.on('tool(trashTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的删除行么，无法恢复', function(index){
			     var map = {
					'articleId':data.id,
					'realDelete':1
			 	 };
			   	 //ajax请求后端
			     deleteLayerObjAjax("article/delete", obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		      layer.confirm('真的重新发布么？', function(index){
			    var map = {
			       'articleId':data.id
			 	};
			 	//ajax请求后端
			    deleteLayerObjAjax("article/republish", obj, map, index);
			  });
		    }
		  });
		  
		});
		
		//点击年列表，加载月份
		$("#year").change(function(){
			//获取年
			var year = $("#year option:selected").val()
			//填充月
			if(year == null || year == undefined || year == 0){
				$("#month").html("");
				$("#month").append("<option value='0' selected>不限月份</option>");
				return;
			}
			var monthList = monthMap[year];
			$("#month").html("");
			$("#month").append("<option value='0' selected>不限月份</option>");
			for(var i=0; i<monthList.length; i++){
				var month = monthList[i];
				$("#month").append("<option value='"+month+"'>"+month+"</option>");
			}
		});
		
		//点击查询，获取参数，查询分页数据
		$("#searchBtn").click(function(){
			//归档年月
			var year = $("#year option:selected").val();
			if(year == null || year == undefined || year == 0){
				year = "";
			}
			var month = $("#month option:selected").val();
			if(month == null || month == undefined || month == 0){
				month = "";
			}
			//分类id
			var categoryId = $("#category option:selected").val();
			
			//重新加载所有文章
			allTable.reload({
				page: {
					curr: 1
				},
				where:{
					'state':'ALL',
					'year':year,
					'month':month,
					'categoryId':categoryId
				},
				done: function(res, curr, count){
					table.resize('layAllTable');
				}
			});
		});
		
		//切换选项卡，重新设置搜索框，重新加载数据
		$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
			// 获取已激活的标签页的名称
			var activeTab = $(e.target).attr("href"); 
			// 获取前一个激活的标签页的名称
			//var previousTab = $(e.relatedTarget).attr("href"); 
			//判断选中的选项卡，重新加载数据
			if(activeTab == "#all"){
				//重新加载所有文章
				allTable.reload({
					page: {
						curr: 1
					},
					where:{
						'state':'ALL'
					},
					done: function(res, curr, count){
						table.resize('layAllTable');
					}
				});
			}else if(activeTab == "#publish"){
				//重新加载发布文章
				publishTable.reload({
					page: {
						curr: 1
					},
					where:{
						'state':'PUBLISH'
					},
					done: function(res, curr, count){
						table.resize('layPublishTable');
					}
				});
			}else if(activeTab == "#draft"){
				//重新加载已创建未发布文章
				draftTable.reload({
					page: {
						curr: 1
					},
					where:{
						'state':'CREATE'
					},
					done: function(res, curr, count){
						table.resize('layDraftTable');
					}
				});
			}else if(activeTab == "#trash"){
				//重新加载已回收文章
				trashTable.reload({
					page: {
						curr: 1
					},
					where:{
						'state':'DELETE'
					},
					done: function(res, curr, count){
						table.resize('layTrashTable');
					}
				});
			}
		});
	});
	
	function initParam(){
		yearList = ${years};
		monthMap=${months};
		
		if(yearList == null || yearList == undefined){
			return;
		}
		
		$("#year").html("");
		$("#year").append("<option value='0' selected>不限年份</option>");
		for(var i=0; i<yearList.length; i++){
			var year = yearList[i];
			$("#year").append("<option value='"+year+"'>"+year+"</option>");
		}
	}

</script>
</body>
</html>
