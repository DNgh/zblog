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
  <!-- custom css -->
  <!-- <link rel="stylesheet" href="custom/css/custom.css"> -->
  
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
			<li class="active"><a href="#all" data-toggle="tab">全部(10)</a></li>
			<li><a href="#publish" data-toggle="tab">已发布(5)</a></li>
			<li><a href="#draft" data-toggle="tab">草稿箱(2)</a></li>
			<li><a href="#trash" data-toggle="tab">回收箱(3)</a></li>
		</ul>
		<div id="articleTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="all">
				<p>菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>
			</div>
			<div class="tab-pane fade" id="publish">
				<p>iOS 是一个由苹果公司开发和发布的手机操作系统。最初是于 2007 年首次发布 iPhone、iPod Touch 和 Apple 
					TV。iOS 派生自 OS X，它们共享 Darwin 基础。OS X 操作系统是用在苹果电脑上，iOS 是苹果的移动版本。</p>
			</div>
			<div class="tab-pane fade" id="draft">
				<p>jMeter 是一款开源的测试软件。它是 100% 纯 Java 应用程序，用于负载和性能测试。</p>
			</div>
			<div class="tab-pane fade" id="trash">
				<p>Enterprise Java Beans（EJB）是一个创建高度可扩展性和强大企业级应用程序的开发架构，部署在兼容应用程序服务器（比如 JBOSS、Web Logic 等）的 J2EE 上。
				</p>
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
<!-- custom jQuery -->
<!-- <script src="custom/js/zblog.js"></script> -->
<script type="text/javascript">
	 /* $(function(){
		$("").click(function(){
			
		});
	}); */
</script>
</body>
</html>
