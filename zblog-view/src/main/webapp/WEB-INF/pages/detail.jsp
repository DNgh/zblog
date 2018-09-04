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
  <!-- typo.css -->
  <!-- <link rel="stylesheet" href="components/typo.css/typo.css"> -->
  <link rel="stylesheet" href="custom/css/custom.css">
  
  <!-- markdown 0.5.0 -->
  <script src="components/marked/marked.min.js"></script>
  
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]--> 
  
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
              <img class="img-circle" src="custom/img/profile-128x128.jpg" alt="User Avatar">
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
                    <h5 class="description-header">3,200</h5>
                    <span class="description-text">文章数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4 border-right">
                  <div class="description-block">
                    <h5 class="description-header">13,000</h5>
                    <span class="description-text">阅读数</span>
                  </div>
                  <!-- /.description-block -->
                </div>
                <!-- /.col -->
                <div class="col-sm-4">
                  <div class="description-block">
                    <h5 class="description-header">35</h5>
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
                <li class="active"><a href="#"><i class="fa fa-inbox"></i> Java
                  <span class="label label-primary pull-right">12</span></a></li>
                <li><a href="#"><i class="fa fa-tree"></i> Spring</a></li>
                <li><a href="#"><i class="fa fa-file-text-o"></i> Hibernate</a></li>
                <li><a href="#"><i class="fa fa-filter"></i> Struts <span class="label label-warning pull-right">65</span></a>
                </li>
                <li><a href="#"><i class="fa fa-ship"></i> Docker</a></li>
              </ul>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->

          <!-- Archive Box -->
          <div class="box box-info">
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
                <li class="active"><a href="#"><i class="fa fa-angle-double-right "></i> 2018-07
                  <span class="label label-primary pull-right">12</span></a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-06</a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-05</a></li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-04 <span class="label label-warning pull-right">65</span></a>
                </li>
                <li><a href="#"><i class="fa fa-angle-double-right"></i> 2018-03</a></li>
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
                <li><a href="#"><span class="label label-primary">大数据</span></a></li>
                <li><a href="#"><span class="label label-info">大数据</span></a></li>
                <li><a href="#"><span class="label label-danger">大数据</span></a></li>
                <li><a href="#"><span class="label label-success">大数据</span></a></li>
                <li><a href="#"><span class="label label-default">大数据</span></a></li>
                <li><a href="#"><span class="label label-warning">大数据</span></a></li>
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
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
                 <li><a href="#">
                	<strong>阅读第一</strong>
              		<p class="text-muted">阅读量：200</p></a>
                 </li>
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
				  	<!-- Article -->
                  	<h3><a href="javascript:void(0);" onclick="doPost('article/show', {'articleKey':'${article.id}'})"><s:property value="article.title"/></a></h3>
                  	<ul class="list-inline">
                  		<li>发表时间：<s:property value="article.createTime"/></li>
                  		<li>阅读数：84</li>
                  	</ul>
                  	<div class="divider-h"></div>
                  	
                  	<!-- 文章显示块 -->
			        <div id="show"></div>
			        <div class="divider-h"></div>
			        
			        <!-- 点赞 分享：按钮 -->
			        <div class="text-center">
			        	<ul class="list-inline">
			        	  <li>
			        	  	<button type="button" class="btn btn-block btn-default btn-lg" data-toggle="tooltip" data-placement="top" title="点赞">
				              <i class="fa fa-heart"></i>
				              <span class="badge bg-red">10</span>
				            </button>
			        	  </li>
			        	  <li>
			        	    <button type="button" class="btn btn-block btn-default btn-lg" data-toggle="tooltip" data-placement="top" title="分享">
				              <i class="fa fa-share"></i>
				              <span class="badge bg-green">10</span>
				            </button>
			        	  </li>
			        	</ul>
           			</div>
           			
		        	<ul class="list-inline">
		        		<li>文章分类：</li>
	                	<li><a href="#">网络开发</a></li>
	                	<li><a href="#">编程语言</a></li>
	               	</ul>
			        	
			        <ul class="list-inline">
			        	<li>标签：</li>
                  		<li><a href="#">java</a></li>
                  		<li><a href="#">docker</a></li>
                  	</ul>
		    	</div>
		    	<!-- /.panel-body -->
		  	</div>
		  	<!-- /.panel -->
		  	
		  	<div class="panel panel-default">
				<div class="panel-body">
				  	<div class="pull-left">
				  		<a href="#">上一篇</a>
                    </div>
	                <div class="pull-right">
	                	<a href="#">下一篇</a>
	                </div>
		    	</div>
		  	</div>
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

<!-- jQuery 3.3.1 -->
<script src="components/jquery/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="components/bootstrap/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="components/fastclick/fastclick.js"></script>
<!-- AdminLTE App 2.4.5 -->
<script src="components/AdminLTE/js/adminlte.min.js"></script>
<!-- bootstrap-paginator -->
<script src="components/bootstrap-paginator/bootstrap-paginator.min.js"></script>
<!-- highlight -->
<script src="http://cdn.bootcss.com/highlight.js/8.0/highlight.min.js"></script>
<!-- custom js -->
<script src="custom/js/zblog.js"></script>
<script>
    marked.setOptions({
        renderer: new marked.Renderer(),
        gfm: true,
        tables: true,
        escaped : true,
        breaks: true,
        pedantic: false,
        sanitize: false,
        smartLists: true,
        smartypants: false,
        highlight: function (code, lang) {
            console.log('code',code)
        // return   hljs.highlight(lang, code, false,true).value;
        return hljs.highlightAuto(code).value;
      }
    });
    
    //给table添加样式
    var renderer = new marked.Renderer();
    renderer.table = function (header, body) {
        return '<table class="table table-striped">'+header+body+'</table>'
    }
    
    $("#show").html(marked('${article.content}',{renderer: renderer}));
    
    $("[data-toggle='tooltip']").tooltip();
</script>
</body>
</html>
