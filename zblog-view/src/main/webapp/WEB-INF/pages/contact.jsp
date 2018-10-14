<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
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
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
  <!--<link rel="shortcut icon " type="images/x-icon" href="custom/img/favico.png">-->
  <link rel="shortcut icon " type="images/x-icon" href="custom/img/star.png">
  
</head>
<body class="hold-transition skin-red-light sidebar-mini">
<div class="wrapper">

  <header class="main-header">
    
    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <div class="navbar-custom-menu">

        <ul class="nav navbar-nav navbar-custom-list">
          <li>
          	<a href="home">
              <i class="fa fa-home"></i> | 首页
            </a>
          </li>
         
          <li>
            <a href="about">
              <i class="fa fa-info"></i> | 关于
            </a>
          </li>

          <li>
            <a href="contact">
              <i class="fa fa-phone"></i> | 联系
            </a>
          </li>

        </ul>
      </div>
    </nav>
  </header>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">

    <!-- Main content -->
    <section class="content">

      <div class="row">
      	<div class="col-md-1">
      	</div>
      	
        <div class="col-md-3">

          <!-- Widget: user widget style 1 -->
          <div class="box box-widget widget-user">
            <!-- Add the bg color to the header using any of the bg-* classes -->
            <div class="widget-user-header bg-black" style="background: url('custom/img/photo1.png') center center;">
              <h3 class="widget-user-username">周志民</h3>
              <h5 class="widget-user-desc">软件开发</h5>
            </div>
            <div class="widget-user-image">
              <img class="img-circle" src="custom/img/profile-128x128.jpg" alt="User zhouzm">
            </div>
            <div class="box-footer">
              <div class="row">
                <div class="col-sm-12">
                  <div class="description-block">
                    <span class="description-text">码农一枚，从事Java软件开发，坚持学习技术，努力提升自己。</span>
                  </div>
                </div>
              </div>
              <div class="row">
                <div class="col-sm-4 border-right">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalArticleNum"/></h5>
                    <span class="description-text">文章数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4 border-right">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalReadNum"/></h5>
                    <span class="description-text">阅读数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4">
                  <div class="description-block">
                    <h5 class="description-header"><s:property value="blogInfo.totalCommentNum"/></h5>
                    <span class="description-text">评论数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
              </div>
              <!-- /.row -->
            </div>
          </div>
          <!-- /.widget-user -->

          <!-- Categories Box -->
          <div class="box box-primary">
            <div class="box-header with-border">
              <i class="fa fa-th"></i>
              <h3 class="box-title">分类</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
              	<!-- Category -->
                <s:iterator value="categoryInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByCategory', {'pageSize':'5','categoryName':'${var.categoryName}'})">
				  	<i class="fa ${var.icon}"></i> <s:property value="#var.categoryName"/> 
				  	<span class="label label-default pull-right"><s:property value="#var.articleNum"/>篇</span></a>  
				  </li>
				</s:iterator>
                <!-- /.Category -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- Archive Box -->
          <div class="box box-warning">
            <div class="box-header with-border">
              <i class="fa fa-archive"></i>
              <h3 class="box-title">归档</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
                <!-- Archive -->
                <s:iterator value="archiveInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByArchive', {'pageSize':'5', 'archiveName':'${var.archiveName}'})">
				  	<i class="fa fa-angle-double-right"></i> <s:property value="#var.archiveTitle"/> 
				  	<span class="label label-default pull-right"><s:property value="#var.articleNum"/>篇</span></a>
				  </li>  
				</s:iterator>
                <!-- /.Archive -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- Tag Box -->
          <div class="box box-success">
            <div class="box-header with-border">
              <i class="fa fa-tags"></i>
              <h3 class="box-title">标签</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills">
                <!-- Tag -->
                <s:iterator value="tagInfoList" var="var">
				  <li><a href="javascript:void(0);" onclick="pageFunction('article/listArticleByTag', {'pageSize':'5','tagName':'${var.tagName}'})">
				  	<span class="label ${var.style}"><s:property value="#var.tagName"/></span></a>
				  </li>  
				</s:iterator>
                <!-- /.Tag -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
          
          <!-- Rank Box -->
          <div class="box box-danger">
            <div class="box-header with-border">
              <i class="fa fa-thumbs-up"></i>
              <h3 class="box-title">阅读排行</h3>
              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body no-padding">
              <ul class="nav nav-pills nav-stacked">
              	<!-- Rank -->
                <s:iterator value="articleRankList" var="var">
				  <li class="nav-item">
				    <a class="nav-link" href="javascript:void(0);" onclick="doPost('article/show', {'articleKey':'${var.id}'})">
				      <strong><s:property value="#var.title"/></strong>
				      <span class="text-muted block-oneline">阅读量：<s:property value="#var.readNum"/></span>
				    </a>
				  </li>  
				</s:iterator>
                <!-- /.Rank -->
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

        </div>
        <!-- /.col -->
        
        <div class="col-md-7">
        	<div class="panel panel-default">
				<div class="panel-body">
	                <div id="contact">
	                  <h3>联系方式</h3>
	                  <p>github：https://github.com/DNgh</p>
	                  <p>邮箱：zzm.cloud@gmail.com</p>
	                  <p>住址：上海</p>
	                </div>
	                
	                <!-- 点击左侧目录，加载分页数据，不能删除 -->
	                <div id="articlePanel"></div>
					<!-- Paginator -->
					<!-- <div class="divider"></div> -->
					<div class="text-center">
	             	   <ul id="homePaginator"></ul>
	           		</div>
	           		
            	</div>
            	<!-- /.panel-body -->
           </div>
           <!-- /.panel -->
        </div>
        <!-- /.col -->
        
        <div class="col-md-1">
      	</div>
      	
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.0
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
<script src="custom/js/zblog.js"></script>

</body>
</html>
