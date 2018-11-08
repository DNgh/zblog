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

  <header class="main-header">
    <!-- Logo -->
    <a href="home" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b>M</b>in</span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>Min</b>zone</span>
    </a>
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">切换导航栏</span>
      </a>

      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <img src="custom/img/profile-128x128.jpg" class="user-image" alt="User Image">
              <span class="hidden-xs">Zzm</span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <img src="custom/img/profile-128x128.jpg" class="img-circle" alt="User Image">

                <p>
                  Zzm - 软件开发工程师
                  <small>注册时间2018-10-01</small>
                </p>
              </li>
              <!-- Menu Body -->
              <li class="user-body">
                <div class="row">
                  <div class="col-xs-4 text-center">
                    <a href="#">文章数(0)</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">阅读数(0)</a>
                  </div>
                  <div class="col-xs-4 text-center">
                    <a href="#">评论数(0)</a>
                  </div>
                </div>
                <!-- /.row -->
              </li>
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">个人信息</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">退出</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- Sidebar user panel -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="custom/img/profile-128x128.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Zzm</p>
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>
      <!-- search form -->
      <form action="#" method="post" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索...">
          <span class="input-group-btn">
            <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
            </button>
          </span>
        </div>
      </form>
      <!-- /.search form -->
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">导航栏</li>
        <li class="active treeview">
          <a href="#">
            <i class="fa fa-book"></i>
            <span>文章管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li class="active"><a href="article/newPage"><i class="fa fa-circle-o"></i> 创建文章</a></li>
            <li><a href="article/queryPage"><i class="fa fa-circle-o"></i> 查询文章</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-th"></i>
            <span>分类管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-archive"></i>
            <span>归档管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-tags"></i>
            <span>标签管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-comments"></i>
            <span>评论管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-history"></i>
            <span>访问历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-history"></i>
            <span>操作历史管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> A</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> B</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-user"></i>
            <span>系统安全管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> 用户管理</a></li>
            <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> 角色管理</a></li>
            <li><a href="pages/charts/flot.html"><i class="fa fa-circle-o"></i> C</a></li>
            <li><a href="pages/charts/inline.html"><i class="fa fa-circle-o"></i> D</a></li>
          </ul>
        </li>
        <li>
          <a href="pages/widgets.html">
            <i class="fa fa-th"></i> <span>Widgets</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green">new</small>
            </span>
          </a>
        </li>
      </ul>
    </section>
    <!-- /.sidebar -->
  </aside>

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
									<button type="button" class="btn btn-info" id="searchBtn">查询</button>
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
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 1.0
    </div>
    <strong>Copyright &copy; 2018-2020.</strong> All rights
    reserved.
  </footer>

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
		//初始化参数
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
		    ,cellMinWidth: 100
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,done: function(res, curr, count){
		    	table.resize('layAllTable');
			}
		  });
		  
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
	        ,cellMinWidth: 100
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,done: function(res, curr, count){
		    	table.resize('layPublishTable');
			}
		  });
		  
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
	        ,cellMinWidth: 100
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,done: function(res, curr, count){
		    	table.resize('layDraftTable');
			}
		  });
		  
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
	        ,cellMinWidth: 100
		    ,cols: [[
		      {field:'id', title:'ID', width:'10%', sort: true}
		      ,{field:'title', title:'标题', width:'20%'}
		      ,{field:'readNum', title:'阅读数', width:'10%'}
		      ,{field:'commentNum', title:'评论数', width:'10%'}
		      ,{field:'createTime', title:'创建时间', width:'20%', sort: true}
		      ,{field:'option', title:'操作', width:'30%', toolbar: '#optionBar'}
		    ]]
		    ,page: true
		    ,done: function(res, curr, count){
		    	table.resize('layTrashTable');
			}
		  });
		  
		  //监听行工具事件
		  table.on('tool(allTable)', function(obj){
		    var data = obj.data;
		    //console.log(obj)
		    if(obj.event === 'del'){
		      layer.confirm('真的删除行么，无法恢复', function(index){
		        var map = {
					'articleId':data.id,
					'realDelete':true
			   	};
		      	//ajax请求后端
		        deleteArticleAjax(obj, map, index);
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
					'realDelete':false
			 	 };
			   	 //ajax请求后端
			     deleteArticleAjax(obj, map, index);
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
					'realDelete':false
			 	 };
			   	 //ajax请求后端
			     deleteArticleAjax(obj, map, index);
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
					'realDelete':true
			 	 };
			   	 //ajax请求后端
			     deleteArticleAjax(obj, map, index);
		      });
		    } else if(obj.event === 'edit'){
		    	//跳转到编辑页面
		      	window.location.href = "article/editorPage?articleId="+data.id;
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
			//重新加载数据
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
	
	function deleteArticleAjax(obj, map, index){
		$.ajax({
	        url: "article/delete",
	        datatype: 'json',
	        type: "POST",
	        data: convertAjaxDataNP(map),
	        success: function (result) {
	        	if(result.success == true){
	        		obj.del();
	        		$(".layui-laypage-btn").click();
	        		layer.close(index);
	        		layer.msg("成功删除");
	        	}else{
	        		//失败，提示信息
	        		layer.alert(result.message, {icon: 5});
	        	}
	        },
	        error: function(XMLHttpRequest, textStatus, errorThrown){
	        	//清除默认值
	        	layer.alert("请求失败", {icon: 5});
	        }
	    });
	}
</script>
</body>
</html>
